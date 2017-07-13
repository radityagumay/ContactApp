package net.radityalabs.contactapp.presentation.widget

import android.view.View

/**
 * Created by radityagumay on 4/13/17.
 */

interface OnSimpleClickListener {
    fun onClick(view: View, position: Int, isLongPressed: Boolean)
}
