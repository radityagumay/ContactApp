package net.radityalabs.contactapp.presentation.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import net.radityalabs.contactapp.R;
import net.radityalabs.contactapp.data.network.response.ContactListResponse;
import net.radityalabs.contactapp.presentation.factory.DialogFactory;
import net.radityalabs.contactapp.presentation.factory.SnackbarFactory;
import net.radityalabs.contactapp.presentation.presenter.ContactListPresenter;
import net.radityalabs.contactapp.presentation.presenter.contract.ContactListContract;
import net.radityalabs.contactapp.presentation.ui.activity.ContactDetailActivity;
import net.radityalabs.contactapp.presentation.ui.adapter.ContactListAdapter;
import net.radityalabs.contactapp.presentation.util.RecycleViewUtil;
import net.radityalabs.contactapp.presentation.widget.OnSimpleClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by radityagumay on 4/11/17.
 */

public class ContactListFragment extends BaseFragment<ContactListPresenter> implements
        ContactListContract.View, OnSimpleClickListener {

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
        // TODO
    }

    @Override
    protected void setupInjection() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void setupEventAndData() {
        mContactList = new ArrayList<>();
        mContactListAdapter = new ContactListAdapter(mContext, mContactList, this);

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
    public void showContactListRange(List<ContactListResponse> responses) {
        if (mContactList.size() > 0) {
            mContactList.addAll(responses);
            mContactListAdapter.notifyItemRangeChanged(mContactList.size() - 1, responses.size() - mContactList.size());
        } else {
            mContactList.addAll(responses);
            mContactListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showError(String msg) {
        if (!(mContactList.size() > 0)) {
            SnackbarFactory.show(mView, msg);
        }
    }

    @Override
    public void showProgressDialog() {
        DialogFactory.showNotCancelableProgress(mContext);
    }

    @Override
    public void hideProgressDialog() {
        DialogFactory.dismissProgress();
    }

    @Override
    public void onClick(View view, int position) {
        ContactDetailActivity.navigate(mActivity);
    }

    private void setupRecycleView() {
        rvContact.setHasFixedSize(true);
        rvContact.setLayoutManager(RecycleViewUtil.linearLayoutManager(mContext));
        rvContact.setAdapter(mContactListAdapter);
    }
}
