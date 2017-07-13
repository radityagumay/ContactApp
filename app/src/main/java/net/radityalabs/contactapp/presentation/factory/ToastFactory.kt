package net.radityalabs.contactapp.presentation.factory

import android.content.Context
import android.widget.Toast

/**
 * Created by radityagumay on 4/13/17.
 */

object ToastFactory {

    fun show(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}
