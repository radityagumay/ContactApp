package net.radityalabs.contactapp.presentation.factory;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.widget.ProgressBar;

import net.radityalabs.contactapp.R;

/**
 * Created by radityagumay on 4/12/17.
 */

public class DialogFactory {

    private static ProgressDialog mProgressDialog;

    public static void showNotCancelableProgress(Context context) {
        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setMessage("Menunggu");
        mProgressDialog.setCancelable(false);
        mProgressDialog.setIndeterminate(true);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            Drawable drawable = new ProgressBar(context).getIndeterminateDrawable().mutate();
            drawable.setColorFilter(ContextCompat.getColor(context, R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
            mProgressDialog.setIndeterminateDrawable(drawable);
        }
        mProgressDialog.show();
    }

    public static void dismissProgress() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }
}
