package net.radityalabs.contactapp.presentation.presenter.contract;

import net.radityalabs.contactapp.data.network.response.ContactListResponse;
import net.radityalabs.contactapp.presentation.presenter.BasePresenter;
import net.radityalabs.contactapp.presentation.view.BaseView;

import java.util.List;

/**
 * Created by radityagumay on 4/12/17.
 */

public interface ContactListFragmentContract {

    interface View extends BaseView {
        void showContactList(List<ContactListResponse> response);
        void showProgressDialog();
        void hideProgressDialog();
    }

    interface Presenter extends BasePresenter<View> {
        void getContactList();
        void disposed();
    }
}
