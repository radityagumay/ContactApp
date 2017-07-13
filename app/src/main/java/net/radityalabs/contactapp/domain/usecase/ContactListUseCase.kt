package net.radityalabs.contactapp.domain.usecase

import android.content.Context
import android.support.annotation.VisibleForTesting
import android.util.Log

import net.radityalabs.contactapp.data.network.RestService
import net.radityalabs.contactapp.data.network.RetrofitHelper
import net.radityalabs.contactapp.data.network.response.ContactListResponse
import net.radityalabs.contactapp.data.realm.RealmHelper
import net.radityalabs.contactapp.data.realm.table.ContactObject
import net.radityalabs.contactapp.presentation.factory.DialogFactory
import net.radityalabs.contactapp.presentation.listener.Callback
import net.radityalabs.contactapp.presentation.rx.RxUtil

import java.util.ArrayList

import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.SingleEmitter
import io.reactivex.SingleOnSubscribe
import io.reactivex.SingleSource
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import io.realm.RealmList
import io.realm.Sort

/**
 * Created by radityagumay on 4/13/17.
 */

class ContactListUseCase {

    private val realmHelper: RealmHelper
    private var service: RestService? = null
    private val context: Context

    constructor(retrofitHelper: RetrofitHelper, realmHelper: RealmHelper, context: Context) {
        this.service = retrofitHelper.restService
        this.realmHelper = realmHelper
        this.context = context
    }

    constructor(service: RestService) {
        this.service = service
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun getContactListTest(callback: Callback<List<ContactListResponse>>) {
        service!!.contactList.subscribe({ responses -> callback.onSuccess(responses) }) { throwable -> callback.onFailure(throwable) }
    }

    val contactList: Flowable<List<ContactListResponse>>
        get() = Single.create(SingleOnSubscribe<List<ContactObject>> { emmit ->
            val realm = Realm.getInstance(realmHelper.buildRealmConfiguration())
            val fieldNames = arrayOf(ContactObject.FIRST_NAME)
            val sort = arrayOf(Sort.ASCENDING)
            val contacts = realm.copyFromRealm(
                    realm.where<ContactObject>(ContactObject::class.java).findAllSorted(fieldNames, sort))
            emmit.onSuccess(if (contacts.size > 0) contacts else ArrayList<ContactObject>())
            realm.close()
        }).compose(RxUtil.singleIo<List<ContactObject>>())
                .map { contacts ->
                    val response = ArrayList<ContactListResponse>(contacts.size)
                    for (i in contacts.indices) {
                        val obj = ContactListResponse()
                        obj.id = contacts[i].id.toInt()
                        obj.firstName = contacts[i].firstName
                        obj.lastName = contacts[i].lastName
                        obj.profilePic = contacts[i].profilePic
                        obj.isFavorite = contacts[i].isFavorite
                        obj.detailUrl = contacts[i].detailUrl
                        response.add(obj)
                    }
                    response
                }.toFlowable()

    val contactListApi: Flowable<List<ContactListResponse>>
        get() = service!!.contactList
                .compose(RxUtil.singleNewThread<List<ContactListResponse>>())
                .compose { upstream -> upstream.doOnSuccess { responses -> insertContact(responses) }.doOnError { DialogFactory.showAlertDialog(context) }.doFinally { DialogFactory.dismissAlertDialog() } }.toFlowable()

    private fun insertContact(responses: List<ContactListResponse>) {
        Single.create(SingleOnSubscribe<List<ContactListResponse>> {
            val contacts = RealmList<ContactObject>()
            val size = responses.size
            for (i in 0..size - 1) {
                val obj = ContactObject()
                obj.id = responses[i].id.toLong()
                obj.firstName = responses[i].firstName
                obj.lastName = responses[i].lastName
                obj.profilePic = responses[i].profilePic
                obj.isFavorite = responses[i].isFavorite
                obj.detailUrl = responses[i].detailUrl
                obj.isCompleted = false
                contacts.add(obj)
            }

            val realm = Realm.getInstance(realmHelper.buildRealmConfiguration())
            realm.executeTransaction { realm -> realm.insertOrUpdate(contacts) }
            realm.close()
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ responses -> Log.d(TAG, "Insert to database: " + responses.size) }) { throwable -> Log.e(TAG, throwable.message, throwable) }
    }

    companion object {

        private val TAG = ContactListUseCase::class.java!!.getSimpleName()
    }
}
