package net.radityalabs.contactapp.presentation.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.EditText
import android.widget.ImageView

import com.bumptech.glide.Glide

import net.radityalabs.contactapp.R
import net.radityalabs.contactapp.data.network.response.ContactDetailResponse
import net.radityalabs.contactapp.data.network.response.ContactListResponse
import net.radityalabs.contactapp.presentation.annotation.PermissionType
import net.radityalabs.contactapp.presentation.factory.ToastFactory
import net.radityalabs.contactapp.presentation.helper.GlideHelper
import net.radityalabs.contactapp.presentation.presenter.AddContactPresenter
import net.radityalabs.contactapp.presentation.presenter.contract.AddContactContract
import net.radityalabs.contactapp.presentation.util.KeyboardUtil
import net.radityalabs.contactapp.presentation.util.StringUtil

import butterknife.BindView
import butterknife.OnClick
import io.reactivex.functions.Consumer

/**
 * Created by radityagumay on 4/16/17.
 */

class AddContactFragment : BaseFragment<AddContactPresenter>(), AddContactContract.View {

    private var mObserver: AddContactObserver? = null
    private var mContacts: ContactListResponse? = null
    private var mUserDetailInfo: ContactDetailResponse? = null

    @BindView(R.id.toolbar)
    internal var toolbar: Toolbar? = null
    @BindView(R.id.iv_image)
    internal var ivImage: ImageView? = null
    @BindView(R.id.iv_camera)
    internal var ivCamera: ImageView? = null
    @BindView(R.id.et_name)
    internal var etName: EditText? = null
    @BindView(R.id.et_phone)
    internal var etPhone: EditText? = null
    @BindView(R.id.et_email)
    internal var etEmail: EditText? = null

    @OnClick(R.id.iv_image, R.id.iv_camera)
    fun onClick(view: View) {
        when (view.id) {
            R.id.iv_camera -> {
                mPresenter!!.requestPermission(mActivity, PermissionType.CAMERA)
            }
        }
    }

    interface AddContactObserver {
        fun removeStack(tag: String)
    }

    fun setObserver(observer: AddContactObserver): AddContactFragment {
        this.mObserver = observer
        return this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        if (bundle != null) {
            mContacts = bundle.getParcelable<ContactListResponse>(EXTRA_USER)
        }
    }

    override fun showError(msg: String) {

    }

    override fun setupInjection() {
        fragmentComponent.inject(this)
    }

    override fun setupEventAndData() {
        setupToolbar(toolbar, "Add new contact", R.mipmap.ic_back)
        setupView()

        if (mContacts != null) {
            mPresenter!!.getDetailContact(mContacts!!.id)
        }
    }

    override fun onDestroyUI() {
        mPresenter!!.detachView()
    }

    protected override val layoutId: Int
        get() = R.layout.fragment_add_contact

    override fun editTextEmpty(@IdRes id: Int) {

    }

    override fun addContactSuccess(s: String) {
        ToastFactory.show(mContext, s)
        KeyboardUtil.hideSoftInput(mActivity)
        mPresenter!!.animateTimer().subscribe(Consumer<Long> { mObserver!!.removeStack(TAG) })
    }

    override fun onGetDetailContactSuccess(response: ContactDetailResponse) {
        this.mUserDetailInfo = response

        etName!!.setText(StringUtil.mergeString(response.firstName, response.lastName))
        etPhone!!.setText(response.phoneNumber)
        etEmail!!.setText(response.email)

        ivImage!!.scaleType = ImageView.ScaleType.CENTER_CROP
        GlideHelper.loadFileNoAnimate(mContext!!, response.profilePic, ivImage!!, R.mipmap.ic_betty_allen)
    }

    override fun onPermissionGranted(@PermissionType type: Int) {
        mPresenter!!.openMediaGallery(mActivity)
    }

    override fun onSuccessPickMedia(data: Intent) {
        Glide.with(mContext).load(data.data).into(ivImage!!)
    }

    override fun onErrorPickImage(throwable: Throwable) {

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mPresenter!!.onActivityResult(requestCode, resultCode, data, mContext)
    }

    private fun setupView() {
        etPhone!!.addTextChangedListener(mPresenter!!.composetWatcherListerner(R.id.et_phone))
        etEmail!!.addTextChangedListener(mPresenter!!.composetWatcherListerner(R.id.et_email))
        etName!!.addTextChangedListener(mPresenter!!.composetWatcherListerner(R.id.et_name))
    }

    fun saveAddProfile() {
        mPresenter!!.saveAddProfile()
    }

    fun saveEditProfile() {
        mPresenter!!.saveEditProfile(mUserDetailInfo)
    }

    companion object {

        val TAG = AddContactFragment::class.java!!.getSimpleName()

        private val EXTRA_USER = "extra_user"

        fun newInstance(user: ContactListResponse): AddContactFragment {
            val fragment = AddContactFragment()
            val bundle = Bundle()
            bundle.putParcelable(EXTRA_USER, user)
            fragment.arguments = bundle
            return fragment
        }

        fun newInstance(): AddContactFragment {
            return AddContactFragment()
        }
    }
}
