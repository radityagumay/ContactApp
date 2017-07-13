package net.radityalabs.contactapp.presentation.util

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat

/**
 * Created by radityagumay on 4/17/17.
 */

object PermissionUtil {

    fun isGranted(result: IntArray): Boolean {
        return result.size > 0 && result[0] == PackageManager.PERMISSION_GRANTED
    }

    fun isPermissionGranted(activity: Activity, permission: String): Boolean {
        return ActivityCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED
    }

    fun camera(): Array<String> {
        return arrayOf(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
    }
}
