package net.radityalabs.contactapp.presentation.presenter;

import android.content.Context;

import net.radityalabs.contactapp.ContactApp;
import net.radityalabs.contactapp.domain.usecase.ContactDetailUseCase;
import net.radityalabs.contactapp.presentation.presenter.contract.ContactDetailContract;
import net.radityalabs.contactapp.presentation.rx.RxPresenter;

import javax.inject.Inject;

/**
 * Created by radityagumay on 4/13/17.
 */

public class ContactDetailPresenter extends RxPresenter<ContactDetailContract.View> implements
        ContactDetailContract.Presenter {

    private static final String TAG = ContactDetailPresenter.class.getSimpleName();

    private Context mContext;
    private ContactDetailUseCase useCase;

    @Inject
    public ContactDetailPresenter(ContactDetailUseCase useCase, ContactApp context) {
        this.useCase = useCase;
        this.mContext = context.getApplicationContext();
    }

    @Override
    public void getContactDetail() {

    }

    @Override
    public void disposed() {

    }
}
