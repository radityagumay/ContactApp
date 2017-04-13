package net.radityalabs.contactapp.presentation.presenter;

import android.util.Log;

import net.radityalabs.contactapp.data.network.response.ContactListResponse;
import net.radityalabs.contactapp.domain.usecase.ContactListFragmentUseCase;
import net.radityalabs.contactapp.presentation.presenter.contract.ContactListFragmentContract;

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

    private ContactListFragmentUseCase useCase;

    @Inject
    public ContactListFragmentPresenter(ContactListFragmentUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public void getContactList() {
        mView.showProgressDialog();
        Disposable disposable = useCase.getContactListDb()
                .subscribe(new Consumer<List<ContactListResponse>>() {
                    @Override
                    public void accept(List<ContactListResponse> responses) throws Exception {
                        Log.d(TAG, "Success fetch contact list from db: " + responses.size());

                        mView.showContactList(responses);
                        /* fetch new one */
                        fetch();
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
                .subscribe(new Consumer<List<ContactListResponse>>() {
                    @Override
                    public void accept(List<ContactListResponse> responses) throws Exception {
                        Log.d(TAG, "Success fetch contact list from api: " + responses.size());

                        mView.hideProgressDialog();
                        mView.showContactListRange(responses);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG, "Failed fetch contact list from api: " + throwable.getMessage());

                        mView.hideProgressDialog();
                        mView.showError(throwable.getMessage());
                    }
                });
        addDisposable(disposable);
    }
}
