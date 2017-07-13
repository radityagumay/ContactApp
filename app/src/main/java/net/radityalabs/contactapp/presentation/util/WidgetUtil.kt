package net.radityalabs.contactapp.presentation.util

import android.content.Context

/**
 * Created by radityagumay on 4/15/17.
 */

object WidgetUtil {

    private fun density(context: Context): Float {
        return context.resources.displayMetrics.density
    }

    fun pxToDp(context: Context, pixel: Int): Float {
        return pixel / density(context)
    }

    fun dpToPx(context: Context, dp: Int): Float {
        return dp * density(context)
    }
}
