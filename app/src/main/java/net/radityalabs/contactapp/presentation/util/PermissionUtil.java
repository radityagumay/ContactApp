package net.radityalabs.contactapp.presentation.util;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

/**
 * Created by radityagumay on 4/17/17.
 */

public class PermissionUtil {

    public static boolean isGranted(@NonNull int[] result) {
        return result.length > 0 && result[0] == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean isPermissionGranted(Activity activity, String permission) {
        return ActivityCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED;
    }
    public static String[] camera() {
        return new String[]{
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
    }
}
