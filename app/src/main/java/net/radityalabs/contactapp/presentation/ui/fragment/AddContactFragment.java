package net.radityalabs.contactapp.presentation.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import net.radityalabs.contactapp.R;
import net.radityalabs.contactapp.data.network.response.ContactDetailResponse;
import net.radityalabs.contactapp.data.network.response.ContactListResponse;
import net.radityalabs.contactapp.presentation.annotation.PermissionType;
import net.radityalabs.contactapp.presentation.factory.ToastFactory;
import net.radityalabs.contactapp.presentation.helper.GlideHelper;
import net.radityalabs.contactapp.presentation.presenter.AddContactPresenter;
import net.radityalabs.contactapp.presentation.presenter.contract.AddContactContract;
import net.radityalabs.contactapp.presentation.util.KeyboardUtil;
import net.radityalabs.contactapp.presentation.util.StringUtil;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

/**
 * Created by radityagumay on 4/16/17.
 */

public class AddContactFragment extends BaseFragment<AddContactPresenter> implements
        AddContactContract.View {

    public static final String TAG = AddContactFragment.class.getSimpleName();

    private static final String EXTRA_USER = "extra_user";

    private AddContactObserver mObserver;
    private ContactListResponse mContacts;
    private ContactDetailResponse mUserDetailInfo;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv_image)
    ImageView ivImage;
    @BindView(R.id.iv_camera)
    ImageView ivCamera;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_email)
    EditText etEmail;

    @OnClick({
            R.id.iv_image,
            R.id.iv_camera
    })
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_camera: {
                mPresenter.requestPermission(mActivity, PermissionType.CAMERA);
            }
            break;
        }
    }

    public interface AddContactObserver {
        void removeStack(String tag);
    }

    public static AddContactFragment newInstance(ContactListResponse user) {
        AddContactFragment fragment = new AddContactFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_USER, user);
        fragment.setArguments(bundle);
        return fragment;
    }

    public static AddContactFragment newInstance() {
        return new AddContactFragment();
    }

    public AddContactFragment() {

    }

    public AddContactFragment setObserver(AddContactObserver observer) {
        this.mObserver = observer;
        return this;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mContacts = bundle.getParcelable(EXTRA_USER);
        }
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
        setupToolbar(toolbar, "Add new contact", R.mipmap.ic_back);
        setupView();

        if (mContacts != null) {
            mPresenter.getDetailContact(mContacts.id);
        }
    }

    @Override
    protected void onDestroyUI() {
        mPresenter.detachView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_add_contact;
    }

    @Override
    public void editTextEmpty(@IdRes int id) {

    }

    @Override
    public void addContactSuccess(String s) {
        ToastFactory.show(mContext, s);
        KeyboardUtil.hideSoftInput(mActivity);
        mPresenter.animateTimer().subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                mObserver.removeStack(TAG);
            }
        });
    }

    @Override
    public void onGetDetailContactSuccess(ContactDetailResponse response) {
        this.mUserDetailInfo = response;

        etName.setText(StringUtil.mergeString(response.firstName, response.lastName));
        etPhone.setText(response.phoneNumber);
        etEmail.setText(response.email);

        ivImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
        GlideHelper.loadFileNoAnimate(mContext, response.profilePic, ivImage, R.mipmap.ic_betty_allen);
    }

    @Override
    public void onPermissionGranted(@PermissionType int type) {
        mPresenter.openMediaGallery(mActivity);
    }

    @Override
    public void onSuccessPickMedia(Intent data) {
        Glide.with(mContext).load(data.getData()).into(ivImage);
    }

    @Override
    public void onErrorPickImage(Throwable throwable) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPresenter.onActivityResult(requestCode, resultCode, data, mContext);
    }

    private void setupView() {
        etPhone.addTextChangedListener(mPresenter.composetWatcherListerner(R.id.et_phone));
        etEmail.addTextChangedListener(mPresenter.composetWatcherListerner(R.id.et_email));
        etName.addTextChangedListener(mPresenter.composetWatcherListerner(R.id.et_name));
    }

    public void saveAddProfile() {
        mPresenter.saveAddProfile();
    }

    public void saveEditProfile() {
        mPresenter.saveEditProfile(mUserDetailInfo);
    }
}
