package net.radityalabs.contactapp.presentation.presenter.contract

import net.radityalabs.contactapp.data.network.response.ContactListResponse
import net.radityalabs.contactapp.presentation.presenter.BasePresenter
import net.radityalabs.contactapp.presentation.view.BaseView

/**
 * Created by radityagumay on 4/12/17.
 */

interface ContactListContract {

    interface View : BaseView {
        fun showContactList(response: List<ContactListResponse>)
        fun showContactListRange(responses: List<ContactListResponse>)
        fun showProgressDialog()
        fun hideProgressDialog()
    }

    interface Presenter : BasePresenter<View> {
        fun getContactList()
        fun disposed()
    }
}
