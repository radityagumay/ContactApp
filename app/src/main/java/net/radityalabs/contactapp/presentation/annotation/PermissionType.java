package net.radityalabs.contactapp.presentation.annotation;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by radityagumay on 4/17/17.
 */

@IntDef({
        PermissionType.STORAGE,
        PermissionType.CAMERA,
})
@Retention(RetentionPolicy.SOURCE)
public @interface PermissionType {
    int STORAGE = 1;
    int CAMERA = 2;
}

