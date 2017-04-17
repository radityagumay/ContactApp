package net.radityalabs.contactapp.presentation.presenter.contract;

import android.support.annotation.IdRes;

import net.radityalabs.contactapp.data.network.response.ContactDetailResponse;
import net.radityalabs.contactapp.presentation.presenter.BasePresenter;
import net.radityalabs.contactapp.presentation.view.BaseView;

/**
 * Created by radityagumay on 4/16/17.
 */

public interface AddContactContract {

    interface View extends BaseView {
        void editTextEmpty(@IdRes int id);
        void addContactSuccess(String str);
        void onGetDetailContactSuccess(ContactDetailResponse response);
    }

    interface Presenter extends BasePresenter<View> {

    }
}
