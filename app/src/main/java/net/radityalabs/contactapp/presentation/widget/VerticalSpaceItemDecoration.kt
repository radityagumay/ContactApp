package net.radityalabs.contactapp.presentation.widget

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View

import net.radityalabs.contactapp.R
import net.radityalabs.contactapp.presentation.util.WidgetUtil

/**
 * Created by radityagumay on 4/15/17.
 */

class VerticalSpaceItemDecoration(private val mContext: Context, private val verticalSpaceHeight: Int, resId: Int) : RecyclerView.ItemDecoration() {
    private var divider: Drawable? = null
    private var mView: View? = null

    init {
        val styledAttributes = mContext.obtainStyledAttributes(ATTRS)
        divider = styledAttributes.getDrawable(0)
        styledAttributes.recycle()
        this.divider = ContextCompat.getDrawable(mContext, resId)
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
        mView = view
        if (parent.getChildAdapterPosition(view) != parent.adapter.itemCount - 1) {
            outRect.bottom = verticalSpaceHeight
        }
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State?) {
        if (mView != null) {
            val left = parent.paddingLeft + WidgetUtil.dpToPx(mContext, 20 + mView!!.findViewById(R.id.tv_body_one).paddingLeft).toInt()
            val right = parent.width - parent.paddingRight

            val childCount = parent.childCount
            for (i in 0..childCount - 1 - 1) {
                val child = parent.getChildAt(i)

                val params = child.layoutParams as RecyclerView.LayoutParams

                val top = child.bottom + params.bottomMargin + WidgetUtil.dpToPx(mContext, 15).toInt()
                val bottom = top + divider!!.intrinsicHeight

                divider!!.setBounds(left, top, right, bottom)
                divider!!.draw(c)
            }
        }
    }

    companion object {

        private val ATTRS = intArrayOf(android.R.attr.listDivider)
    }
}