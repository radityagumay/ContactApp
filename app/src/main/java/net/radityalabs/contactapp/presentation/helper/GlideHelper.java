package net.radityalabs.contactapp.presentation.helper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

/**
 * Created by radityagumay on 4/16/17.
 */

public class GlideHelper {

    public static void loadFileNoAnimate(@NonNull Context context,
                                         final String url,
                                         @NonNull final ImageView resId,
                                         final int placeHolder) {
        if (TextUtils.isEmpty(url)) {
            resId.setImageResource(placeHolder);
            return;
        }

        Glide.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .skipMemoryCache(true)
                .dontAnimate()
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        resId.setImageResource(placeHolder);
                        return true;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(resId);
    }
}
