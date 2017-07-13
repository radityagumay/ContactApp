package net.radityalabs.contactapp.presentation.annotation

import android.support.annotation.IntDef

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

/**
 * Created by radityagumay on 4/17/17.
 */

@IntDef(PermissionType.STORAGE.toLong(), PermissionType.CAMERA.toLong())
@Retention(RetentionPolicy.SOURCE)
annotation class PermissionType {
    companion object {
        val STORAGE = 1
        val CAMERA = 2
    }
}

