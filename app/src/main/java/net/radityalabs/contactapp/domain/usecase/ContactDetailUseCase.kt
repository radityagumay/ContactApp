package net.radityalabs.contactapp.domain.usecase

import android.content.Context
import android.support.annotation.VisibleForTesting
import android.util.Log

import net.radityalabs.contactapp.data.network.RestService
import net.radityalabs.contactapp.data.network.RetrofitHelper
import net.radityalabs.contactapp.data.network.response.ContactDetailResponse
import net.radityalabs.contactapp.data.realm.RealmHelper
import net.radityalabs.contactapp.data.realm.table.ContactObject
import net.radityalabs.contactapp.presentation.listener.Callback
import net.radityalabs.contactapp.presentation.rx.RxUtil

import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.SingleEmitter
import io.reactivex.SingleOnSubscribe
import io.reactivex.SingleSource
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import io.realm.Realm

/**
 * Created by radityagumay on 4/13/17.
 */

class ContactDetailUseCase {

    private val realmHelper: RealmHelper
    private var service: RestService? = null
    private val context: Context

    constructor(retrofitHelper: RetrofitHelper, realmHelper: RealmHelper, context: Context) {
        this.service = retrofitHelper.restService
        this.realmHelper = realmHelper
        this.context = context
    }

    @VisibleForTesting
    constructor(service: RestService) {
        this.service = service
    }

    @VisibleForTesting
    fun getDetailContactTest(id: Int, callback: Callback<ContactDetailResponse>) {
        service!!.getContactDetail(id).subscribe({ response -> callback.onSuccess(response) }) { throwable -> callback.onFailure(throwable) }
    }

    fun getDetailContact(userId: Int): Flowable<ContactDetailResponse> {
        return service!!.getContactDetail(userId)
                .compose(RxUtil.singleNewThread<ContactDetailResponse>())
                .compose { upstream -> upstream.doOnSuccess { response -> updateContactDetail(userId, response) } }.toFlowable()
    }

    private fun updateContactDetail(userId: Int, response: ContactDetailResponse) {
        Single.create(SingleOnSubscribe<ContactObject> {
            val realm = Realm.getInstance(realmHelper.buildRealmConfiguration())
            val obj = realm.copyFromRealm(realm.where<ContactObject>(ContactObject::class.java).equalTo(ContactObject.ID, userId).findFirst())
            if (obj != null) {
                obj.email = response.email
                obj.phoneNumber = response.phoneNumber
                obj.isCompleted = true
            }
            realm.executeTransaction { realm -> realm.insertOrUpdate(obj!!) }
            realm.close()
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ Log.d(TAG, "UpdateContactDetail: Success") }) { throwable -> Log.e(TAG, "UpdateContactDetail: " + throwable.message, throwable) }
    }

    companion object {

        private val TAG = ContactDetailUseCase::class.java!!.getSimpleName()
    }
}
