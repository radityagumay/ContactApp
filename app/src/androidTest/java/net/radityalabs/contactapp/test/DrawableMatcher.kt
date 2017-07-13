package net.radityalabs.contactapp.test

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView

import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

/**
 * Created by radityagumay on 4/19/17.
 */

class DrawableMatcher(private val expectedId: Int) : TypeSafeMatcher<View>(View::class.java) {
    private var resourceName: String? = null

    override fun matchesSafely(target: View): Boolean {
        if (target !is ImageView) {
            return false
        }
        val imageView = target
        if (expectedId < 0) {
            return imageView.drawable == null
        }
        val resources = target.getContext().resources
        val expectedDrawable = resources.getDrawable(expectedId)
        resourceName = resources.getResourceEntryName(expectedId)

        if (expectedDrawable == null) {
            return false
        }

        val bitmap = (imageView.drawable as BitmapDrawable).bitmap
        val otherBitmap = (expectedDrawable as BitmapDrawable).bitmap
        return bitmap.sameAs(otherBitmap)
    }


    override fun describeTo(description: Description) {
        description.appendText("with drawable from resource id: ")
        description.appendValue(expectedId)
        if (resourceName != null) {
            description.appendText("[")
            description.appendText(resourceName)
            description.appendText("]")
        }
    }
}