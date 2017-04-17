package net.radityalabs.contactapp.presentation.presenter;

import android.text.Editable;
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
                Log.d(TAG, "beforeTextChanged");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d(TAG, "onTextChanged");

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
                Log.d(TAG, "afterTextChanged");
            }
        };
    }

    public void saveProfile() {
        Disposable disposable = useCase.addNewContact(request())
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

    private ContactDetailRequest request() {
        return new ContactDetailRequest.Builder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPhoneNumber(phoneNumber)
                .setProfilePic(profilePic).build();
    }

    public Single<Long> animateTimer() {
        return useCase.animateTimer();
    }
}
