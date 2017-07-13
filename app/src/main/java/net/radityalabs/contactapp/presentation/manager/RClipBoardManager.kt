package net.radityalabs.contactapp.presentation.manager

import android.annotation.SuppressLint
import android.content.Context

import net.radityalabs.contactapp.R

/**
 * Created by radityagumay on 4/16/17.
 */

object RClipBoardManager {

    @SuppressLint("NewApi")
    fun copyToClipboard(context: Context, text: String): Boolean {
        try {
            val sdk = android.os.Build.VERSION.SDK_INT
            if (sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
                val clipboard = context.getSystemService(context.CLIPBOARD_SERVICE) as android.text.ClipboardManager
                clipboard.text = text
            } else {
                val clipboard = context.getSystemService(context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
                val clip = android.content.ClipData.newPlainText(context.resources.getString(R.string.message), text)
                clipboard.primaryClip = clip
            }
            return true
        } catch (e: Exception) {
            return false
        }

    }
}
