package net.radityalabs.contactapp.presentation.util;

import android.content.Intent;

/**
 * Created by radityagumay on 4/17/17.
 */

public class IntentUtil {

    public static Intent getMediaFromGallery() {
        if (BuildUtil.isAndroidKitkat()) {
            Intent intent = new Intent();
            intent.setType("image/*, video/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.putExtra(Intent.EXTRA_MIME_TYPES, new String[]{"image/*", "video/*"});
            return intent;
        } else {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/* video/*");
            return intent;
        }
    }
}
