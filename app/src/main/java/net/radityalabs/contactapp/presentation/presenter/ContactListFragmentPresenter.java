package net.radityalabs.contactapp.presentation.presenter;

import android.util.Log;

import net.radityalabs.contactapp.data.network.RestService;
import net.radityalabs.contactapp.data.network.RetrofitHelper;
import net.radityalabs.contactapp.data.network.response.ContactListResponse;
import net.radityalabs.contactapp.presentation.presenter.contract.ContactListFragmentContract;
import net.radityalabs.contactapp.presentation.rx.RxUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by radityagumay on 4/12/17.
 */

public class ContactListFragmentPresenter extends RxPresenter<ContactListFragmentContract.View> implements
        ContactListFragmentContract.Presenter {

    private static final String TAG = ContactListFragmentPresenter.class.getSimpleName();

    private RestService service;

    @Inject
    public ContactListFragmentPresenter(RetrofitHelper retrofitHelper) {
        this.service = retrofitHelper.getRestService();
    }

    @Override
    public void getContactList() {
        mView.showProgressDialog();
        Disposable dispose = service.getContactList()
                .compose(RxUtil.<List<ContactListResponse>>rxScheduler())
                .map(new Function<List<ContactListResponse>, List<ContactListResponse>>() {
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
                })
                .subscribe(new Consumer<List<ContactListResponse>>() {
                    @Override
                    public void accept(List<ContactListResponse> response) throws Exception {
                        mView.hideProgressDialog();
                        mView.showContactList(response);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG, throwable.getMessage(), throwable);
                        mView.hideProgressDialog();
                        mView.showError(throwable.getMessage());
                    }
                });
        addDisposable(dispose);
    }

    @Override
    public void disposed() {
        dispose();
    }
}
