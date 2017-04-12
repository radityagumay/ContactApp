package net.radityalabs.contactapp.presentation.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import net.radityalabs.contactapp.R;
import net.radityalabs.contactapp.data.network.response.ContactListResponse;
import net.radityalabs.contactapp.presentation.factory.DialogFactory;
import net.radityalabs.contactapp.presentation.presenter.ContactListFragmentPresenter;
import net.radityalabs.contactapp.presentation.presenter.contract.ContactListFragmentContract;
import net.radityalabs.contactapp.presentation.ui.adapter.ContactListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by radityagumay on 4/11/17.
 */

public class ContactListFragment extends BaseFragment<ContactListFragmentPresenter> implements
        ContactListFragmentContract.View {

    public static final String TAG = ContactListFragment.class.getSimpleName();

    private static final String PARAM_1 = "param1";
    private static final String PARAM_2 = "param2";

    private ContactListAdapter mContactListAdapter;
    private List<ContactListResponse> mContactList;

    @BindView(R.id.rv_contact)
    RecyclerView rvContact;

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
    protected void setupInjection() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void setupEventAndData() {
        mContactList = new ArrayList<>();
        mContactListAdapter = new ContactListAdapter(mContext, mContactList);

        setupRecycleView();

        mPresenter.getContactList();
    }

    @Override
    protected void onDestroyUI() {
        mPresenter.disposed();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_contact_list;
    }

    @Override
    public void showContactList(List<ContactListResponse> response) {
        mContactList.addAll(response);
        mContactListAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showProgressDialog() {
        DialogFactory.showNotCancelableProgress(mContext);
    }

    @Override
    public void hideProgressDialog() {
        DialogFactory.dismissProgress();
    }

    private void setupRecycleView() {
        rvContact.setHasFixedSize(true);
        rvContact.setLayoutManager(layoutManagerBuilder());
        rvContact.setAdapter(mContactListAdapter);
    }

    private LinearLayoutManager layoutManagerBuilder() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setReverseLayout(false);
        linearLayoutManager.setStackFromEnd(true);
        return linearLayoutManager;
    }
}
