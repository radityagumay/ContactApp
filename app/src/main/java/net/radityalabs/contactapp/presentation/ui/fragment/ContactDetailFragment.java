package net.radityalabs.contactapp.presentation.ui.fragment;

import android.os.Bundle;

import net.radityalabs.contactapp.R;
import net.radityalabs.contactapp.presentation.presenter.ContactDetailPresenter;
import net.radityalabs.contactapp.presentation.presenter.contract.ContactDetailContract;

/**
 * Created by radityagumay on 4/13/17.
 */

public class ContactDetailFragment extends BaseFragment<ContactDetailPresenter> implements
        ContactDetailContract.View {

    public static final String TAG = ContactDetailFragment.class.getSimpleName();

    private static final String PARAM_1 = "param1";
    private static final String PARAM_2 = "param2";

    public static ContactDetailFragment newInstance(String param1, String param2) {
        ContactDetailFragment fragment = new ContactDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(PARAM_1, param1);
        bundle.putString(PARAM_2, param2);
        fragment.setArguments(bundle);
        return fragment;
    }

    public ContactDetailFragment() {
        // TODO
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    protected void setupInjection() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void setupEventAndData() {

    }

    @Override
    protected void onDestroyUI() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_contact_detail;
    }

    @Override
    public void showContactDetail() {

    }
}
