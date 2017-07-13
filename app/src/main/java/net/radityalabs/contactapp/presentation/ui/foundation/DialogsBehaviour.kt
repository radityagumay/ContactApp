package net.radityalabs.contactapp.presentation.ui.foundation

import android.app.ProgressDialog

import net.radityalabs.contactapp.ContactApp
import net.radityalabs.contactapp.presentation.factory.DialogFactory

import javax.inject.Inject

/**
 * Created by radityagumay on 4/12/17.
 */

class DialogsBehaviour @Inject
constructor(private val application: ContactApp, private val dialog: DialogFactory) : Dialogs {
    private val progressDialog: ProgressDialog? = null

    override fun showLoading() {
        DialogFactory.showNotCancelableProgress(application.applicationContext)
    }

    override fun showLoading(message: String) {
        DialogFactory.showNotCancelableProgress(application.applicationContext)
    }

    override fun showNoCancelableLoading() {
        DialogFactory.showNotCancelableProgress(application.applicationContext)
    }

    override fun showNoCancelableLoading(message: String) {
        DialogFactory.showNotCancelableProgress(application.applicationContext)
    }

    override fun hideLoading() {
        DialogFactory.dismissProgress()
    }
}
