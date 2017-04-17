package net.radityalabs.contactapp.presentation.util;

import android.os.Build;

/**
 * Created by radityagumay on 4/17/17.
 */

public class BuildUtil {

    public static boolean isAndroidM() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    public static boolean isAndroidKitkat() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }
}
