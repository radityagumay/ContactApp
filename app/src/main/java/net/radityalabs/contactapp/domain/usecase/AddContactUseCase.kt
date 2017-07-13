package net.radityalabs.contactapp.domain.usecase

import android.content.Context

import net.radityalabs.contactapp.data.network.RestService
import net.radityalabs.contactapp.data.network.RetrofitHelper
import net.radityalabs.contactapp.data.network.request.ContactDetailRequest
import net.radityalabs.contactapp.data.network.response.ContactDetailResponse
import net.radityalabs.contactapp.presentation.rx.RxUtil

import java.util.concurrent.TimeUnit

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by radityagumay on 4/16/17.
 */

class AddContactUseCase(retrofitHelper: RetrofitHelper, private val mContext: Context) {

    private val mService: RestService

    init {
        this.mService = retrofitHelper.restService
    }

    fun addNewContact(payload: ContactDetailRequest): Single<ContactDetailResponse> {
        return mService.addContact(payload)
                .compose(RxUtil.singleNewThread<ContactDetailResponse>())
    }

    fun editContact(userId: Int, payload: ContactDetailRequest): Single<ContactDetailResponse> {
        return mService.editContact(userId, payload)
                .compose(RxUtil.singleNewThread<ContactDetailResponse>())
    }

    fun getUserDetail(id: Int): Single<ContactDetailResponse> {
        return mService.getUserDetail(id)
                .compose(RxUtil.singleNewThread<ContactDetailResponse>())
    }

    fun animateTimer(): Single<Long> {
        return Single.timer(500, TimeUnit.MILLISECONDS, Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}
