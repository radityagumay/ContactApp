package net.radityalabs.contactapp.domain.usecase;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import net.radityalabs.contactapp.data.network.RestService;
import net.radityalabs.contactapp.data.network.RetrofitHelper;
import net.radityalabs.contactapp.data.network.response.ContactDetailResponse;
import net.radityalabs.contactapp.data.realm.RealmHelper;
import net.radityalabs.contactapp.data.realm.table.ContactObject;
import net.radityalabs.contactapp.presentation.rx.RxUtil;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.SingleSource;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;

/**
 * Created by radityagumay on 4/13/17.
 */

public class ContactDetailUseCase {

    private static final String TAG = ContactDetailUseCase.class.getSimpleName();

    private RealmHelper realmHelper;
    private RestService service;
    private Context context;

    public ContactDetailUseCase(RetrofitHelper retrofitHelper, RealmHelper realmHelper, Context context) {
        this.service = retrofitHelper.getRestService();
        this.realmHelper = realmHelper;
        this.context = context;
    }

    public Flowable<ContactDetailResponse> getDetailContact(final int userId) {
        return service.getContactDetail(userId)
                .compose(RxUtil.<ContactDetailResponse>singleNewThread())
                .compose(new SingleTransformer<ContactDetailResponse, ContactDetailResponse>() {
                    @Override
                    public SingleSource<ContactDetailResponse> apply(Single<ContactDetailResponse> upstream) {
                        return upstream.doOnSuccess(new Consumer<ContactDetailResponse>() {
                            @Override
                            public void accept(ContactDetailResponse response) throws Exception {
                                updateContactDetail(userId, response);
                            }
                        });
                    }
                }).toFlowable();
    }

    private void updateContactDetail(final int userId, @NonNull final ContactDetailResponse response) {
        Single.create(new SingleOnSubscribe<ContactObject>() {
            @Override
            public void subscribe(SingleEmitter<ContactObject> e) throws Exception {
                Realm realm = Realm.getInstance(realmHelper.buildRealmConfiguration());
                final ContactObject obj = realm.copyFromRealm(realm.where(ContactObject.class).equalTo(ContactObject.ID, userId).findFirst());
                if (obj != null) {
                    obj.email = response.email;
                    obj.phoneNumber = response.phoneNumber;
                    obj.isCompleted = true;
                }
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        realm.insertOrUpdate(obj);
                    }
                });
                realm.close();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ContactObject>() {
                    @Override
                    public void accept(ContactObject contactObject) throws Exception {
                        Log.d(TAG, "UpdateContactDetail: Success");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG, "UpdateContactDetail: " + throwable.getMessage(), throwable);
                    }
                });
    }
}
