package net.radityalabs.contactapp.presentation.util

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore

/**
 * Created by radityagumay on 4/17/17.
 */

object FileUtil {

    val SELECT_MEDIA_FROM_GALLERY = 132

    fun getMimeType(data: Intent, context: Context): String {
        val selectedUri = data.data
        val columns = arrayOf(MediaStore.Images.Media.DATA, MediaStore.Images.Media.MIME_TYPE)
        val cursor = context.contentResolver.query(selectedUri, columns, null, null, null)
        cursor!!.moveToFirst()
        val pathColumnIndex = cursor.getColumnIndex(columns[0])
        val mimeTypeColumnIndex = cursor.getColumnIndex(columns[1])
        val contentPath = cursor.getString(pathColumnIndex)
        val mimeType = cursor.getString(mimeTypeColumnIndex)
        cursor.close()
        return mimeType
    }

    fun getImageGalleryPath(data: Intent, context: Context): String {
        val selectedImage = data.data
        val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = context.contentResolver.query(selectedImage, filePathColumn, null, null, null)
        cursor!!.moveToFirst()
        val columnIndex = cursor.getColumnIndex(filePathColumn[0])
        val picturePath = cursor.getString(columnIndex)
        cursor.close()
        return picturePath
    }
}
