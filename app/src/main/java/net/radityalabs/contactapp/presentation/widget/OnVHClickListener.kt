package net.radityalabs.contactapp.presentation.widget

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by radityagumay on 4/13/17.
 */

interface OnVHClickListener {
    fun onClick(vh: RecyclerView.ViewHolder, view: View, position: Int, isLongPressed: Boolean)
}
