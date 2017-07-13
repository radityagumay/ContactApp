package net.radityalabs.contactapp.presentation.ui.fragment

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView

import net.radityalabs.contactapp.R
import net.radityalabs.contactapp.data.network.response.ContactDetailResponse
import net.radityalabs.contactapp.data.network.response.ContactListResponse
import net.radityalabs.contactapp.domain.model.ContactDetailInfoModel
import net.radityalabs.contactapp.presentation.di.module.ContactDetailModule
import net.radityalabs.contactapp.presentation.helper.GlideHelper
import net.radityalabs.contactapp.presentation.presenter.ContactDetailPresenter
import net.radityalabs.contactapp.presentation.presenter.contract.ContactDetailContract
import net.radityalabs.contactapp.presentation.ui.activity.ContactDetailActivity
import net.radityalabs.contactapp.presentation.ui.adapter.ContactInfoAdapter
import net.radityalabs.contactapp.presentation.util.RecycleViewUtil
import net.radityalabs.contactapp.presentation.util.StringUtil
import net.radityalabs.contactapp.presentation.widget.OnSimpleClickListener
import net.radityalabs.contactapp.presentation.widget.VerticalSpaceItemDecoration

import java.util.ArrayList

import butterknife.BindView

/**
 * Created by radityagumay on 4/13/17.
 */

class ContactDetailFragment : BaseFragment<ContactDetailPresenter>(), ContactDetailContract.View, OnSimpleClickListener {

    private var mContactInfoList: MutableList<ContactDetailInfoModel>? = null
    private var mContactInfoAdapter: ContactInfoAdapter? = null

    private var mContacts: ContactListResponse? = null

    @BindView(R.id.rv_info)
    internal var rvInfo: RecyclerView? = null
    @BindView(R.id.toolbar)
    internal var toolbar: Toolbar? = null
    @BindView(R.id.tv_full_name)
    internal var tvFullName: TextView? = null
    @BindView(R.id.iv_image)
    internal var ivImage: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        if (bundle != null) {
            mContacts = bundle.getParcelable<ContactListResponse>(USER)
        }
    }

    override fun setupInjection() {
        fragmentComponent.plus(ContactDetailModule()).inject(this)
    }

    override fun setupEventAndData() {
        setupToolbar(toolbar, null, -1)

        mContactInfoList = ArrayList<ContactDetailInfoModel>()
        mContactInfoAdapter = ContactInfoAdapter(mContactInfoList, this)

        setupRecycleView()
        setupView()

        mPresenter!!.getContactDetail(mContacts!!.id)
    }

    override fun onDestroyUI() {
        mPresenter!!.detachView()
    }

    protected override val layoutId: Int
        get() = R.layout.fragment_contact_detail

    override fun showContactDetail(contactDetailResponse: ContactDetailResponse) {
        mContactInfoList!!.addAll(mPresenter!!.getInfoBuilder(contactDetailResponse))
        mContactInfoAdapter!!.notifyDataSetChanged()
    }

    override fun showError(msg: String) {

    }

    override fun onClick(view: View, position: Int, isLongPressed: Boolean) {
        mPresenter!!.composeOnClick(view, position, mContactInfoList!![position], isLongPressed)
    }

    private fun setupRecycleView() {
        rvInfo!!.setHasFixedSize(true)
        rvInfo!!.layoutManager = RecycleViewUtil.simpleLinearLayoutManager(mContext)
        rvInfo!!.addItemDecoration(VerticalSpaceItemDecoration(mContext, 50, R.drawable.divider))
        rvInfo!!.adapter = mContactInfoAdapter
    }

    private fun setupView() {
        GlideHelper.loadFileNoAnimate(mContext!!, mContacts!!.profilePic, ivImage!!, R.mipmap.ic_betty_allen)
        tvFullName!!.text = StringUtil.mergeString(mContacts!!.firstName, mContacts!!.lastName)
    }

    fun setFavoriteMarked(menu: Menu) {
        val item = menu.findItem(R.id.action_favorite)
        if (mContacts!!.isFavorite) {
            item.icon = resources.getDrawable(R.mipmap.ic_favourite_filled)
        }
    }

    companion object {

        val TAG = ContactDetailFragment::class.java!!.getSimpleName()

        private val USER = "user"

        fun newInstance(user: ContactListResponse): ContactDetailFragment {
            val fragment = ContactDetailFragment()
            val bundle = Bundle()
            bundle.putParcelable(USER, user)
            fragment.arguments = bundle
            return fragment
        }
    }
}// TODO
