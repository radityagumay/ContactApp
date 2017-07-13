package net.radityalabs.contactapp.presentation.presenter

import android.content.Context
import android.util.Log

import net.radityalabs.contactapp.ContactApp
import net.radityalabs.contactapp.R
import net.radityalabs.contactapp.data.network.response.ContactListResponse
import net.radityalabs.contactapp.domain.usecase.ContactListUseCase
import net.radityalabs.contactapp.presentation.presenter.contract.ContactListContract
import net.radityalabs.contactapp.presentation.rx.RxPresenter
import net.radityalabs.contactapp.presentation.util.ConnectionUtil

import org.reactivestreams.Publisher
import org.reactivestreams.Subscription

import java.util.ArrayList
import java.util.Collections
import java.util.Comparator

import javax.inject.Inject

import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function

/**
 * Created by radityagumay on 4/12/17.
 */

class ContactListPresenter @Inject
constructor(private val useCase: ContactListUseCase, context: ContactApp) : RxPresenter<ContactListContract.View>(), ContactListContract.Presenter {

    private val mContext: Context

    init {
        this.mContext = context.applicationContext
    }

    override fun getContactList() {
        mView!!.showProgressDialog()
        val disposable = useCase.contactList
                .compose { upstream ->
                    upstream.doOnComplete {
                        /* fetch new one */
                        fetch()
                    }
                }.map { responses ->
            val list = ArrayList<ContactListResponse>(responses.size)
            for (obj in responses) {
                if (!list.contains(obj)) {
                    list.add(obj)
                }
            }

            Collections.sort(list) { object1, object2 -> object1.firstName!!.compareTo(object2.firstName!!) }
            list
        }.subscribe({ responses ->
            Log.d(TAG, "Success fetch contact list from db: " + responses.size)

            mView!!.hideProgressDialog()
            mView!!.showContactList(responses)
        }) { throwable ->
            Log.e(TAG, "Failed fetch contact list from db: " + throwable.message)

            mView!!.hideProgressDialog()
            mView!!.showError(throwable.message)
        }
        addDisposable(disposable)
    }

    override fun disposed() {
        dispose()
    }

    private fun fetch() {
        val disposable = useCase.contactListApi
                .compose { upstream ->
                    upstream.doOnSubscribe {
                        if (!ConnectionUtil.isNetworkConnected) {
                            mView!!.showError("No Internet Connection")
                        }
                    }
                }.subscribe({ responses ->
            Log.d(TAG, "Success fetch contact list from api: " + responses.size)

            if (responses.size > 0) {
                mView!!.showContactListRange(responses)
            } else {
                mView!!.showError(mContext.resources.getString(R.string.no_contacts_found))
            }
        }) { throwable ->
            Log.e(TAG, "Failed fetch contact list from api: " + throwable.message)

            mView!!.showError(throwable.message)
        }
        addDisposable(disposable)
    }

    companion object {

        private val TAG = ContactListPresenter::class.java!!.getSimpleName()
    }
}
