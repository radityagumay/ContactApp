package net.radityalabs.contactapp.presentation.presenter.contract;

import net.radityalabs.contactapp.data.network.response.ContactDetailResponse;
import net.radityalabs.contactapp.domain.model.ContactDetailInfoModel;
import net.radityalabs.contactapp.presentation.presenter.BasePresenter;
import net.radityalabs.contactapp.presentation.view.BaseView;

import java.util.List;

/**
 * Created by radityagumay on 4/13/17.
 */

public interface ContactDetailContract {

    interface View extends BaseView {
        void showContactDetail(ContactDetailResponse contactDetailResponse);
    }

    interface Presenter extends BasePresenter<View> {
        List<ContactDetailInfoModel> getInfoBuilder(ContactDetailResponse contactDetailResponse);

        void getContactDetail(int userId);
    }
}
