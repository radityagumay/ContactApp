package net.radityalabs.contactapp.presentation.factory

import android.support.design.widget.Snackbar
import android.view.View

/**
 * Created by radityagumay on 4/13/17.
 */

object SnackbarFactory {

    fun show(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
    }
}
