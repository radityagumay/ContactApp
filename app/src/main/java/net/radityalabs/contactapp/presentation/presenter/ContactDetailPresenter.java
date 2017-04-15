package net.radityalabs.contactapp.presentation.presenter;

import android.content.Context;
import android.util.Log;

import net.radityalabs.contactapp.ContactApp;
import net.radityalabs.contactapp.R;
import net.radityalabs.contactapp.data.network.response.ContactDetailResponse;
import net.radityalabs.contactapp.domain.model.ContactDetailInfoModel;
import net.radityalabs.contactapp.domain.usecase.ContactDetailUseCase;
import net.radityalabs.contactapp.presentation.presenter.contract.ContactDetailContract;
import net.radityalabs.contactapp.presentation.rx.RxPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

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
    public void getContactDetail(int userId) {
        Disposable disposable = useCase.getDetailContact(userId)
                .subscribe(new Consumer<ContactDetailResponse>() {
                    @Override
                    public void accept(ContactDetailResponse contactDetailResponse) throws Exception {
                        Log.d(TAG, "getContactDetail: Success");
                        mView.showContactDetail(contactDetailResponse);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG, "getContactDetail: " + throwable.getMessage(), throwable);
                    }
                });
        addDisposable(disposable);
    }

    @Override
    public void disposed() {
        dispose();
    }

    public List<ContactDetailInfoModel> getInfoBuilder(ContactDetailResponse contactDetailResponse) {
        List<ContactDetailInfoModel> contacts = new ArrayList<>(2);

        ContactDetailInfoModel phoneModel = new ContactDetailInfoModel();
        phoneModel.leftIcon = R.mipmap.ic_call_blue;
        phoneModel.rightIcon = R.mipmap.ic_message;
        phoneModel.isRightIconSet = true;
        phoneModel.bodyOne = contactDetailResponse.phoneNumber;
        phoneModel.bodyTwo = "Mobile";
        contacts.add(phoneModel);

        ContactDetailInfoModel email = new ContactDetailInfoModel();
        email.leftIcon = R.mipmap.ic_email_blue;
        email.isRightIconSet = false;
        email.bodyOne = contactDetailResponse.email;
        email.bodyTwo = "Home";
        contacts.add(email);
        return contacts;
    }
}
