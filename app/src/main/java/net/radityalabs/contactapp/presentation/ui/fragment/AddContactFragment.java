package net.radityalabs.contactapp.presentation.ui.fragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import net.radityalabs.contactapp.R;
import net.radityalabs.contactapp.data.network.response.ContactListResponse;
import net.radityalabs.contactapp.presentation.presenter.AddContactPresenter;
import net.radityalabs.contactapp.presentation.presenter.contract.AddContactContract;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by radityagumay on 4/16/17.
 */

public class AddContactFragment extends BaseFragment<AddContactPresenter> implements
        AddContactContract.View {

    public static final String TAG = AddContactFragment.class.getSimpleName();

    private static final String EXTRA_USER = "extra_user";

    private ContactListResponse mContacts;

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
        mPresenter.composeOnClick(view);
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

    private void setupView() {
        etPhone.addTextChangedListener(mPresenter.composetWatcherListerner(R.id.et_phone));
        etEmail.addTextChangedListener(mPresenter.composetWatcherListerner(R.id.et_email));
        etName.addTextChangedListener(mPresenter.composetWatcherListerner(R.id.et_name));
    }

    public void saveProfile() {
        mPresenter.saveProfile();
    }
}
