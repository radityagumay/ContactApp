package net.radityalabs.contactapp.presentation.factory

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.v4.content.ContextCompat
import android.widget.ProgressBar

import net.radityalabs.contactapp.R

/**
 * Created by radityagumay on 4/12/17.
 */

object DialogFactory {

    private var mProgressDialog: ProgressDialog? = null
    private var mAlertDialog: AlertDialog? = null

    fun showNotCancelableProgress(context: Context) {
        mProgressDialog = ProgressDialog(context)
        mProgressDialog!!.setMessage(context.resources.getString(R.string.please_wait))
        mProgressDialog!!.setCancelable(false)
        mProgressDialog!!.isIndeterminate = true

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            val drawable = ProgressBar(context).indeterminateDrawable.mutate()
            drawable.setColorFilter(ContextCompat.getColor(context, R.color.colorPrimary), PorterDuff.Mode.SRC_IN)
            mProgressDialog!!.setIndeterminateDrawable(drawable)
        }
        mProgressDialog!!.show()
    }

    fun dismissProgress() {
        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
            mProgressDialog!!.dismiss()
            mProgressDialog = null
        }
    }

    fun showAlertDialog(context: Context) {
        mAlertDialog = AlertDialog.Builder(context)
                .setTitle(context.resources.getString(R.string.network_error))
                .setMessage(context.resources.getString(R.string.unable_to_contact_the_server))
                .show()
    }

    fun dismissAlertDialog() {
        if (mAlertDialog != null && mAlertDialog!!.isShowing) {
            mAlertDialog!!.dismiss()
            mAlertDialog = null
        }
    }
}
