package net.radityalabs.contactapp.presentation.util

import android.content.Intent

/**
 * Created by radityagumay on 4/17/17.
 */

object IntentUtil {

    val mediaFromGallery: Intent
        get() {
            if (BuildUtil.isAndroidKitkat) {
                val intent = Intent()
                intent.type = "image/*, video/*"
                intent.action = Intent.ACTION_GET_CONTENT
                intent.addCategory(Intent.CATEGORY_OPENABLE)
                intent.putExtra(Intent.EXTRA_MIME_TYPES, arrayOf("image/*", "video/*"))
                return intent
            } else {
                val intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.type = "image/* video/*"
                return intent
            }
        }
}
