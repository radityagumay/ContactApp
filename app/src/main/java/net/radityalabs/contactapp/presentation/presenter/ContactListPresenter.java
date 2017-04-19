package net.radityalabs.contactapp.presentation.presenter;

import android.content.Context;
import android.util.Log;

import net.radityalabs.contactapp.ContactApp;
import net.radityalabs.contactapp.R;
import net.radityalabs.contactapp.data.network.response.ContactListResponse;
import net.radityalabs.contactapp.domain.usecase.ContactListUseCase;
import net.radityalabs.contactapp.presentation.presenter.contract.ContactListContract;
import net.radityalabs.contactapp.presentation.rx.RxPresenter;
import net.radityalabs.contactapp.presentation.util.ConnectionUtil;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by radityagumay on 4/12/17.
 */

public class ContactListPresenter extends RxPresenter<ContactListContract.View> implements
        ContactListContract.Presenter {

    private static final String TAG = ContactListPresenter.class.getSimpleName();

    private Context mContext;
    private ContactListUseCase useCase;

    @Inject
    public ContactListPresenter(ContactListUseCase useCase, ContactApp context) {
        this.useCase = useCase;
        this.mContext = context.getApplicationContext();
    }

    @Override
    public void getContactList() {
        mView.showProgressDialog();
        Disposable disposable = useCase.getContactList()
                .compose(new FlowableTransformer<List<ContactListResponse>, List<ContactListResponse>>() {
                    @Override
                    public Publisher<List<ContactListResponse>> apply(Flowable<List<ContactListResponse>> upstream) {
                        return upstream.doOnComplete(new Action() {
                            @Override
                            public void run() throws Exception {
                                /* fetch new one */
                                fetch();
                            }
                        });
                    }
                }).map(new Function<List<ContactListResponse>, List<ContactListResponse>>() {
                    @Override
                    public List<ContactListResponse> apply(List<ContactListResponse> responses) throws Exception {
                        List<ContactListResponse> list = new ArrayList<>(responses.size());
                        for (ContactListResponse obj : responses) {
                            if (!list.contains(obj)) {
                                list.add(obj);
                            }
                        }

                        Collections.sort(list, new Comparator<ContactListResponse>() {
                            @Override
                            public int compare(final ContactListResponse object1, final ContactListResponse object2) {
                                return object1.firstName.compareTo(object2.firstName);
                            }
                        });
                        return list;
                    }
                }).subscribe(new Consumer<List<ContactListResponse>>() {
                    @Override
                    public void accept(List<ContactListResponse> responses) throws Exception {
                        Log.d(TAG, "Success fetch contact list from db: " + responses.size());

                        mView.hideProgressDialog();
                        mView.showContactList(responses);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG, "Failed fetch contact list from db: " + throwable.getMessage());

                        mView.hideProgressDialog();
                        mView.showError(throwable.getMessage());
                    }
                });
        addDisposable(disposable);
    }

    @Override
    public void disposed() {
        dispose();
    }

    private void fetch() {
        Disposable disposable = useCase.getContactListApi()
                .compose(new FlowableTransformer<List<ContactListResponse>, List<ContactListResponse>>() {
                    @Override
                    public Publisher<List<ContactListResponse>> apply(Flowable<List<ContactListResponse>> upstream) {
                        return upstream.doOnSubscribe(new Consumer<Subscription>() {
                            @Override
                            public void accept(Subscription subscription) throws Exception {
                                if (!ConnectionUtil.isNetworkConnected()) {
                                    mView.showError("No Internet Connection");
                                }
                            }
                        });
                    }
                }).subscribe(new Consumer<List<ContactListResponse>>() {
                    @Override
                    public void accept(List<ContactListResponse> responses) throws Exception {
                        Log.d(TAG, "Success fetch contact list from api: " + responses.size());
                        if (responses.size() > 0) {
                            mView.showContactListRange(responses);
                        } else {
                            mView.showError(mContext.getResources().getString(R.string.no_contacts_found));
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG, "Failed fetch contact list from api: " + throwable.getMessage());

                        mView.showError(throwable.getMessage());
                    }
                });
        addDisposable(disposable);
    }
}
