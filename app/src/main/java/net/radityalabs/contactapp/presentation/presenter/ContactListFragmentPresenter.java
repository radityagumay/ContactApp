package net.radityalabs.contactapp.presentation.presenter;

import android.util.Log;

import net.radityalabs.contactapp.data.network.RestService;
import net.radityalabs.contactapp.data.network.RetrofitHelper;
import net.radityalabs.contactapp.data.network.response.ContactListResponse;
import net.radityalabs.contactapp.presentation.presenter.contract.ContactListFragmentContract;
import net.radityalabs.contactapp.presentation.rx.RxUtil;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

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
        Disposable dispose = service.getContactList()
                .compose(RxUtil.<List<ContactListResponse>>rxScheduler())
                .subscribe(new Consumer<List<ContactListResponse>>() {
                    @Override
                    public void accept(List<ContactListResponse> response) throws Exception {
                        Log.d(TAG, "Hello");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG, throwable.getMessage(), throwable);
                    }
                });
        addDisposable(dispose);
    }
}
