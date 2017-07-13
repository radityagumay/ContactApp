package net.radityalabs.contactapp.presentation.presenter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View

import net.radityalabs.contactapp.ContactApp
import net.radityalabs.contactapp.R
import net.radityalabs.contactapp.data.network.response.ContactDetailResponse
import net.radityalabs.contactapp.domain.model.ContactDetailInfoModel
import net.radityalabs.contactapp.domain.usecase.ContactDetailUseCase
import net.radityalabs.contactapp.presentation.factory.ToastFactory
import net.radityalabs.contactapp.presentation.manager.RClipBoardManager
import net.radityalabs.contactapp.presentation.presenter.contract.ContactDetailContract
import net.radityalabs.contactapp.presentation.rx.RxPresenter
import net.radityalabs.contactapp.presentation.util.PhoneUtil

import java.util.ArrayList

import javax.inject.Inject

import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer

import android.content.Intent.FLAG_ACTIVITY_NEW_TASK

/**
 * Created by radityagumay on 4/13/17.
 */

class ContactDetailPresenter @Inject
constructor(private val useCase: ContactDetailUseCase, context: ContactApp) : RxPresenter<ContactDetailContract.View>(), ContactDetailContract.Presenter {

    private val mContext: Context

    init {
        this.mContext = context.applicationContext
    }

    override fun getContactDetail(userId: Int) {
        val disposable = useCase.getDetailContact(userId)
                .subscribe({ contactDetailResponse ->
                    Log.d(TAG, "getContactDetail: Success")
                    mView!!.showContactDetail(contactDetailResponse)
                }) { throwable -> Log.e(TAG, "getContactDetail: " + throwable.message, throwable) }
        addDisposable(disposable)
    }

    override fun getInfoBuilder(contactDetailResponse: ContactDetailResponse): List<ContactDetailInfoModel> {
        val contacts = ArrayList<ContactDetailInfoModel>(2)

        val phoneModel = ContactDetailInfoModel()
        phoneModel.leftIcon = R.mipmap.ic_call_blue
        phoneModel.rightIcon = R.mipmap.ic_message
        phoneModel.isRightIconSet = true
        phoneModel.bodyOne = contactDetailResponse.phoneNumber
        phoneModel.bodyTwo = "Mobile"
        contacts.add(phoneModel)

        val email = ContactDetailInfoModel()
        email.leftIcon = R.mipmap.ic_email_blue
        email.isRightIconSet = false
        email.bodyOne = contactDetailResponse.email
        email.bodyTwo = "Home"
        contacts.add(email)
        return contacts
    }

    fun composeOnClick(view: View, position: Int, contact: ContactDetailInfoModel, isLongPressed: Boolean) {
        when (position) {
            0 -> {
                when (view.id) {
                    R.id.iv_left_icon -> {
                        if (!isLongPressed) {
                            val intent = PhoneUtil.call(contact.bodyOne)
                            intent.flags = FLAG_ACTIVITY_NEW_TASK
                            if (intent.resolveActivity(mContext.packageManager) != null) {
                                mContext.startActivity(intent)
                            }
                        } else {
                            copyToClipBoard(contact.bodyOne)
                        }
                    }
                    R.id.iv_right_icon -> {
                        if (!isLongPressed) {
                            val intent = PhoneUtil.sms(contact.bodyOne)
                            intent.flags = FLAG_ACTIVITY_NEW_TASK
                            if (intent.resolveActivity(mContext.packageManager) != null) {
                                mContext.startActivity(intent)
                            }
                        } else {
                            copyToClipBoard(contact.bodyOne)
                        }
                    }
                }
            }
            1 -> {
                when (view.id) {
                    R.id.iv_left_icon -> {
                        if (!isLongPressed) {
                            val intent = PhoneUtil.email(contact.bodyOne)
                            intent.flags = FLAG_ACTIVITY_NEW_TASK
                            if (intent.resolveActivity(mContext.packageManager) != null) {
                                mContext.startActivity(Intent.createChooser(intent, "Choose an Email client :"))
                            }
                        } else {
                            copyToClipBoard(contact.bodyOne)
                        }
                    }
                }
            }
        }
    }

    private fun copyToClipBoard(message: String) {
        val isSuccess = RClipBoardManager.copyToClipboard(mContext, message)
        val builder = StringBuilder()
        builder.append(message).append(" ")
                .append(if (isSuccess) "Berhasil disalin" else "Gagal disalin")
        ToastFactory.show(mContext, builder.toString())
    }

    companion object {

        private val TAG = ContactDetailPresenter::class.java!!.getSimpleName()
    }
}
