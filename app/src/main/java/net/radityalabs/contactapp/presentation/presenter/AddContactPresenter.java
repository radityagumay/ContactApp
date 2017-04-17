package net.radityalabs.contactapp.presentation.presenter;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import net.radityalabs.contactapp.R;
import net.radityalabs.contactapp.data.network.request.ContactDetailRequest;
import net.radityalabs.contactapp.data.network.response.ContactDetailResponse;
import net.radityalabs.contactapp.domain.usecase.AddContactUseCase;
import net.radityalabs.contactapp.presentation.presenter.contract.AddContactContract;
import net.radityalabs.contactapp.presentation.rx.RxPresenter;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by radityagumay on 4/16/17.
 */

public class AddContactPresenter extends RxPresenter<AddContactContract.View> implements
        AddContactContract.Presenter {

    private static final String TAG = AddContactPresenter.class.getSimpleName();

    private AddContactUseCase useCase;

    private String firstName = "", lastName = "", email = "", phoneNumber = "", profilePic = "";

    @Inject
    public AddContactPresenter(AddContactUseCase useCase) {
        this.useCase = useCase;
    }

    public void composeOnClick(View view) {
    }

    public TextWatcher composetWatcherListerner(final int id) {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                switch (id) {
                    case R.id.et_name: {
                        firstName = s.toString();
                        lastName = firstName;
                    }
                    break;
                    case R.id.et_phone: {
                        phoneNumber = s.toString();
                    }
                    break;
                    case R.id.et_email: {
                        email = s.toString();
                    }
                    break;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };
    }

    public void saveAddProfile() {
        Disposable disposable = useCase.addNewContact(request(
                firstName, lastName, email, phoneNumber, profilePic))
                .subscribe(new Consumer<ContactDetailResponse>() {
                    @Override
                    public void accept(ContactDetailResponse response) throws Exception {
                        mView.addContactSuccess("Berhasil menambah kontak");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.showError(throwable.getMessage());
                    }
                });
        addDisposable(disposable);
    }

    public void getDetailContact(int id) {
        Disposable disposal = useCase.getUserDetail(id)
                .subscribe(new Consumer<ContactDetailResponse>() {
                    @Override
                    public void accept(ContactDetailResponse response) throws Exception {
                        mView.onGetDetailContactSuccess(response);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.showError(throwable.getMessage());
                    }
                });
        addDisposable(disposal);
    }

    public Single<Long> animateTimer() {
        return useCase.animateTimer();
    }

    public void saveEditProfile(ContactDetailResponse body) {
        Disposable disposable = useCase.editContact(
                body.id, request(
                !TextUtils.isEmpty(firstName) ? firstName : body.firstName,
                !TextUtils.isEmpty(lastName) ? lastName : body.lastName,
                !TextUtils.isEmpty(email) ? email : body.email,
                !TextUtils.isEmpty(phoneNumber) ? phoneNumber : body.phoneNumber,
                !TextUtils.isEmpty(profilePic) ? profilePic : body.profilePic))
                .subscribe(new Consumer<ContactDetailResponse>() {
                    @Override
                    public void accept(ContactDetailResponse response) throws Exception {
                        mView.addContactSuccess("Berhasil merubah kontak");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.showError(throwable.getMessage());
                    }
                });
        addDisposable(disposable);
    }

    private ContactDetailRequest request(String firstName, String lastName, String email, String phoneNumber, String profilePic) {
        return new ContactDetailRequest.Builder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPhoneNumber(phoneNumber)
                .setProfilePic(profilePic).build();
    }
}
