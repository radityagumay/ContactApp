package net.radityalabs.contactapp.presentation.ui.fragment;

import android.os.Bundle;

import net.radityalabs.contactapp.R;
import net.radityalabs.contactapp.presentation.presenter.ContactListFragmentPresenter;
import net.radityalabs.contactapp.presentation.presenter.contract.ContactListFragmentContract;

/**
 * Created by radityagumay on 4/11/17.
 */

public class ContactListFragment extends BaseFragment<ContactListFragmentPresenter> implements
        ContactListFragmentContract.View {

    public static final String TAG = ContactListFragment.class.getSimpleName();

    private static final String PARAM_1 = "param1";
    private static final String PARAM_2 = "param2";

    public static ContactListFragment newInstance(String param1, String param2) {
        ContactListFragment fragment = new ContactListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(PARAM_1, param1);
        bundle.putString(PARAM_2, param2);
        fragment.setArguments(bundle);
        return fragment;
    }

    public ContactListFragment() {

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
        mPresenter.getContactList();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_contact_list;
    }

    @Override
    public void showContactList() {

    }

    @Override
    public void showMessageError(String message) {

    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hideProgressDialog() {

    }
}
