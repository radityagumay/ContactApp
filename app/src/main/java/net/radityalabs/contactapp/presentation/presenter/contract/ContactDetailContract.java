package net.radityalabs.contactapp.presentation.presenter.contract;

import net.radityalabs.contactapp.presentation.presenter.BasePresenter;
import net.radityalabs.contactapp.presentation.view.BaseView;

/**
 * Created by radityagumay on 4/13/17.
 */

public interface ContactDetailContract {

    interface View extends BaseView {
        void showContactDetail();
    }

    interface Presenter extends BasePresenter<View> {
        void getContactDetail();
        void disposed();
    }
}
