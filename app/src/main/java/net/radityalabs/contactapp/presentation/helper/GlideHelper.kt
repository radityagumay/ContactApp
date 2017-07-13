package net.radityalabs.contactapp.presentation.helper

import android.content.Context
import android.text.TextUtils
import android.widget.ImageView

import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

/**
 * Created by radityagumay on 4/16/17.
 */

object GlideHelper {

    fun loadFileNoAnimate(context: Context,
                          url: String,
                          resId: ImageView,
                          placeHolder: Int) {
        if (TextUtils.isEmpty(url)) {
            resId.setImageResource(placeHolder)
            return
        }

        Glide.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .skipMemoryCache(true)
                .dontAnimate()
                .listener(object : RequestListener<String, GlideDrawable> {
                    override fun onException(e: Exception, model: String, target: Target<GlideDrawable>, isFirstResource: Boolean): Boolean {
                        resId.setImageResource(placeHolder)
                        return true
                    }

                    override fun onResourceReady(resource: GlideDrawable, model: String, target: Target<GlideDrawable>, isFromMemoryCache: Boolean, isFirstResource: Boolean): Boolean {
                        return false
                    }
                })
                .into(resId)
    }
}
