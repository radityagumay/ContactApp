package net.radityalabs.contactapp.presentation.presenter;

import net.radityalabs.contactapp.presentation.presenter.contract.ContactListActivityContract;
import net.radityalabs.contactapp.presentation.rx.RxPresenter;

import javax.inject.Inject;

/**
 * Created by radityagumay on 4/12/17.
 */

public class ContactListActivityPresenter extends RxPresenter<ContactListActivityContract.View> implements
        ContactListActivityContract.Presenter {

    @Inject
    public ContactListActivityPresenter() {
    }
}
