package net.radityalabs.contactapp.presentation.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import net.radityalabs.contactapp.R;
import net.radityalabs.contactapp.data.network.response.ContactDetailResponse;
import net.radityalabs.contactapp.domain.model.ContactDetailInfoModel;
import net.radityalabs.contactapp.presentation.presenter.ContactDetailPresenter;
import net.radityalabs.contactapp.presentation.presenter.contract.ContactDetailContract;
import net.radityalabs.contactapp.presentation.ui.adapter.ContactInfoAdapter;
import net.radityalabs.contactapp.presentation.util.RecycleViewUtil;
import net.radityalabs.contactapp.presentation.widget.OnSimpleClickListener;
import net.radityalabs.contactapp.presentation.widget.VerticalSpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by radityagumay on 4/13/17.
 */

public class ContactDetailFragment extends BaseFragment<ContactDetailPresenter> implements
        ContactDetailContract.View,
        OnSimpleClickListener {

    public static final String TAG = ContactDetailFragment.class.getSimpleName();

    private static final String USER_ID = "user_id";

    private List<ContactDetailInfoModel> mContactInfoList;
    private ContactInfoAdapter mContactInfoAdapter;

    private int mUserId;

    @BindView(R.id.rv_info)
    RecyclerView rvInfo;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public static ContactDetailFragment newInstance(int userId) {
        ContactDetailFragment fragment = new ContactDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(USER_ID, userId);
        fragment.setArguments(bundle);
        return fragment;
    }

    public ContactDetailFragment() {
        // TODO
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        Bundle bundle = getArguments();
        if (bundle != null) {
            mUserId = bundle.getInt(USER_ID);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_contact_detail, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    @Override
    protected void setupInjection() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void setupEventAndData() {
        setupToolbar(toolbar, null);

        mContactInfoList = new ArrayList<>();
        mContactInfoAdapter = new ContactInfoAdapter(mContactInfoList, this);

        setupRecycleView();

        mPresenter.getContactDetail(mUserId);
    }

    @Override
    protected void onDestroyUI() {
        mPresenter.disposed();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_contact_detail;
    }

    @Override
    public void showContactDetail(ContactDetailResponse contactDetailResponse) {
        mContactInfoList.addAll(mPresenter.getInfoBuilder(contactDetailResponse));
        mContactInfoAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String msg) {

    }

    private void setupRecycleView() {
        rvInfo.setHasFixedSize(true);
        rvInfo.setLayoutManager(RecycleViewUtil.linearLayoutManager(mContext));
        rvInfo.addItemDecoration(new VerticalSpaceItemDecoration(mContext, 50, R.drawable.divider));
        rvInfo.setAdapter(mContactInfoAdapter);
    }

    @Override
    public void onClick(View view, int position) {
        mPresenter.composeOnClick(view, position, mContactInfoList.get(position));
    }
}
