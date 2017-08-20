package net.radityalabs.contactapp.presentation.presenter.contract;

import net.radityalabs.contactapp.data.network.response.ContactListResponse;
import net.radityalabs.contactapp.presentation.presenter.BasePresenter;
import net.radityalabs.contactapp.presentation.view.BaseView;

import java.util.List;

/**
 * Created by radityagumay on 4/12/17.
 */

public interface ContactListContract {
    interface View extends BaseView {
        void showContactList(List<ContactListResponse> response);

        void showContactListRange(List<ContactListResponse> responses);

        void showProgressDialog();

        void hideProgressDialog();
    }

    interface Presenter extends BasePresenter<View> {
        void getContactList();

        void disposed();
    }
}

/**
 * 1. Contract
 * 2. Unit Test
 * 3. Code
 * 4. Tun test
 * 5. Refactor
 */