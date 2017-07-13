package net.radityalabs.contactapp.presentation.util

import android.os.Build

/**
 * Created by radityagumay on 4/17/17.
 */

object BuildUtil {

    val isAndroidM: Boolean
        get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

    val isAndroidKitkat: Boolean
        get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
}
