package net.radityalabs.contactapp.presentation.presenter;

import net.radityalabs.contactapp.data.network.RetrofitHelper;
import net.radityalabs.contactapp.presentation.presenter.contract.ContactListFragmentContract;

import javax.inject.Inject;

/**
 * Created by radityagumay on 4/12/17.
 */

public class ContactListFragmentPresenter extends RxPresenter<ContactListFragmentContract.View> implements
        ContactListFragmentContract.Presenter {

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public ContactListFragmentPresenter(RetrofitHelper retrofitHelper) {
        this.mRetrofitHelper = retrofitHelper;
    }

    @Override
    public void getContactList() {

    }
}
