package net.radityalabs.contactapp.presentation.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import net.radityalabs.contactapp.R;
import net.radityalabs.contactapp.data.network.response.ContactDetailResponse;
import net.radityalabs.contactapp.data.network.response.ContactListResponse;
import net.radityalabs.contactapp.domain.model.ContactDetailInfoModel;
import net.radityalabs.contactapp.presentation.helper.GlideHelper;
import net.radityalabs.contactapp.presentation.presenter.ContactDetailPresenter;
import net.radityalabs.contactapp.presentation.presenter.contract.ContactDetailContract;
import net.radityalabs.contactapp.presentation.ui.adapter.ContactInfoAdapter;
import net.radityalabs.contactapp.presentation.util.RecycleViewUtil;
import net.radityalabs.contactapp.presentation.util.StringUtil;
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

    private static final String USER = "user";

    private List<ContactDetailInfoModel> mContactInfoList;
    private ContactInfoAdapter mContactInfoAdapter;

    private ContactListResponse mContacts;

    @BindView(R.id.rv_info)
    RecyclerView rvInfo;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_full_name)
    TextView tvFullName;
    @BindView(R.id.iv_image)
    ImageView ivImage;

    public static ContactDetailFragment newInstance(ContactListResponse user) {
        ContactDetailFragment fragment = new ContactDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(USER, user);
        fragment.setArguments(bundle);
        return fragment;
    }

    public ContactDetailFragment() {
        // TODO
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setHasOptionsMenu(true);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mContacts = bundle.getParcelable(USER);
        }
    }

    /*@Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.action_favorite);
        if (mContacts.isFavorite) {
            item.setIcon(getResources().getDrawable(R.mipmap.ic_favourite_filled));
        }
        super.onPrepareOptionsMenu(menu);
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
    }*/

    @Override
    protected void setupInjection() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void setupEventAndData() {
        setupToolbar(toolbar, null, -1);

        mContactInfoList = new ArrayList<>();
        mContactInfoAdapter = new ContactInfoAdapter(mContactInfoList, this);

        setupRecycleView();
        setupView();

        mPresenter.getContactDetail(mContacts.id);
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

    @Override
    public void onClick(View view, int position, boolean isLongPressed) {
        mPresenter.composeOnClick(view, position, mContactInfoList.get(position), isLongPressed);
    }

    private void setupRecycleView() {
        rvInfo.setHasFixedSize(true);
        rvInfo.setLayoutManager(RecycleViewUtil.simpleLinearLayoutManager(mContext));
        rvInfo.addItemDecoration(new VerticalSpaceItemDecoration(mContext, 50, R.drawable.divider));
        rvInfo.setAdapter(mContactInfoAdapter);
    }

    private void setupView() {
        GlideHelper.loadFileNoAnimate(mContext, mContacts.profilePic, ivImage, R.mipmap.ic_betty_allen);
        tvFullName.setText(StringUtil.mergeString(mContacts.firstName, mContacts.lastName));
    }

    public void setFavoriteMarked(Menu menu) {
        MenuItem item = menu.findItem(R.id.action_favorite);
        if (mContacts.isFavorite) {
            item.setIcon(getResources().getDrawable(R.mipmap.ic_favourite_filled));
        }
    }
}
