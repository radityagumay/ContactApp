package net.radityalabs.contactapp.presentation.util;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

/**
 * Created by radityagumay on 4/17/17.
 */

public class FileUtil {

    public static final int SELECT_MEDIA_FROM_GALLERY = 132;

    public static String getMimeType(Intent data, Context context) {
        Uri selectedUri = data.getData();
        String[] columns = {MediaStore.Images.Media.DATA, MediaStore.Images.Media.MIME_TYPE};
        Cursor cursor = context.getContentResolver().query(selectedUri, columns, null, null, null);
        cursor.moveToFirst();
        int pathColumnIndex = cursor.getColumnIndex(columns[0]);
        int mimeTypeColumnIndex = cursor.getColumnIndex(columns[1]);
        String contentPath = cursor.getString(pathColumnIndex);
        String mimeType = cursor.getString(mimeTypeColumnIndex);
        cursor.close();
        return mimeType;
    }

    public static String getImageGalleryPath(Intent data, Context context) {
        Uri selectedImage = data.getData();
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(selectedImage, filePathColumn, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String picturePath = cursor.getString(columnIndex);
        cursor.close();
        return picturePath;
    }
}
