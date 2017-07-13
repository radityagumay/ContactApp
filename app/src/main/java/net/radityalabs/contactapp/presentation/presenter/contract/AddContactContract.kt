package net.radityalabs.contactapp.presentation.presenter.contract

import android.content.Intent
import android.support.annotation.IdRes

import net.radityalabs.contactapp.data.network.response.ContactDetailResponse
import net.radityalabs.contactapp.presentation.annotation.PermissionType
import net.radityalabs.contactapp.presentation.presenter.BasePresenter
import net.radityalabs.contactapp.presentation.view.BaseView

/**
 * Created by radityagumay on 4/16/17.
 */

interface AddContactContract {

    interface View : BaseView {
        fun editTextEmpty(@IdRes id: Int)

        fun addContactSuccess(str: String)

        fun onGetDetailContactSuccess(response: ContactDetailResponse)

        fun onPermissionGranted(@PermissionType type: Int)

        fun onSuccessPickMedia(data: Intent)

        fun onErrorPickImage(throwable: Throwable)
    }

    interface Presenter : BasePresenter<View>
}
