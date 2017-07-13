package net.radityalabs.contactapp.presentation.presenter.contract

import net.radityalabs.contactapp.data.network.response.ContactDetailResponse
import net.radityalabs.contactapp.domain.model.ContactDetailInfoModel
import net.radityalabs.contactapp.presentation.presenter.BasePresenter
import net.radityalabs.contactapp.presentation.view.BaseView

/**
 * Created by radityagumay on 4/13/17.
 */

interface ContactDetailContract {

    interface View : BaseView {
        fun showContactDetail(contactDetailResponse: ContactDetailResponse)
    }

    interface Presenter : BasePresenter<View> {
        fun getInfoBuilder(contactDetailResponse: ContactDetailResponse): List<ContactDetailInfoModel>

        fun getContactDetail(userId: Int)
    }
}
