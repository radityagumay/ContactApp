package net.radityalabs.contactapp.domain.usecase;

import android.content.Context;

import net.radityalabs.contactapp.data.network.RestService;
import net.radityalabs.contactapp.data.network.RetrofitHelper;
import net.radityalabs.contactapp.data.network.request.ContactDetailRequest;
import net.radityalabs.contactapp.data.network.response.ContactDetailResponse;
import net.radityalabs.contactapp.presentation.rx.RxUtil;

import io.reactivex.Single;

/**
 * Created by radityagumay on 4/16/17.
 */

public class AddContactUseCase {

    private RestService mService;
    private Context mContext;

    public AddContactUseCase(RetrofitHelper retrofitHelper, Context applicationContext) {
        this.mService = retrofitHelper.getRestService();
        this.mContext = applicationContext;
    }

    public Single<ContactDetailResponse> addNewContact(ContactDetailRequest payload) {
        return mService.addContact(payload)
                .compose(RxUtil.<ContactDetailResponse>singleNewThread());
    }
}
