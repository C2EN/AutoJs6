package org.autojs.autojs.core.ui.inflater.inflaters

import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.view.children
import org.autojs.autojs.core.ui.inflater.ResourceParser
import org.autojs.autojs.core.ui.inflater.ViewCreator
import org.autojs.autojs.core.ui.inflater.util.Gravities
import org.autojs.autojs.core.ui.widget.JsFrameLayout

/**
 * Created by Stardust on 2017/11/29.
 * Transformed by SuperMonster003 on May 20, 2023.
 */
class JsFrameLayoutInflater(resourceParser: ResourceParser) : FrameLayoutInflater<JsFrameLayout>(resourceParser) {

    private var mGravity: Int? = null

    override fun setAttr(view: JsFrameLayout, attrName: String, value: String, parent: ViewGroup?): Boolean {
        when (attrName) {
            "gravity" -> mGravity = Gravities.parse(value)
            else -> return super.setAttr(view, attrName, value, parent)
        }
        return true
    }

    override fun applyPendingAttributesOfChildren(view: JsFrameLayout) {
        mGravity?.let { gravity ->
            view.children.forEach { child ->
                child.layoutParams = (child.layoutParams as FrameLayout.LayoutParams).apply {
                    this.gravity = gravity
                }
            }
        }
        mGravity = null
    }

    override fun getCreator(): ViewCreator<in JsFrameLayout> = ViewCreator { context, _ -> JsFrameLayout(context) }

}