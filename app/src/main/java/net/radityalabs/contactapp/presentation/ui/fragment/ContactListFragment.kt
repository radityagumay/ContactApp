package net.radityalabs.contactapp.presentation.ui.fragment

import android.os.Bundle
import android.support.annotation.VisibleForTesting
import android.support.v7.widget.RecyclerView
import android.view.View

import net.radityalabs.contactapp.R
import net.radityalabs.contactapp.data.network.response.ContactListResponse
import net.radityalabs.contactapp.presentation.di.module.ContactListModule
import net.radityalabs.contactapp.presentation.factory.DialogFactory
import net.radityalabs.contactapp.presentation.factory.SnackbarFactory
import net.radityalabs.contactapp.presentation.presenter.ContactListPresenter
import net.radityalabs.contactapp.presentation.presenter.contract.ContactListContract
import net.radityalabs.contactapp.presentation.ui.activity.ContactDetailActivity
import net.radityalabs.contactapp.presentation.ui.adapter.ContactListAdapter
import net.radityalabs.contactapp.presentation.util.RecycleViewUtil
import net.radityalabs.contactapp.presentation.widget.OnSimpleClickListener

import java.util.ArrayList

import butterknife.BindView

/**
 * Created by radityagumay on 4/11/17.
 */

class ContactListFragment : BaseFragment<ContactListPresenter>(), ContactListContract.View, OnSimpleClickListener {

    private var mContactListAdapter: ContactListAdapter? = null
    private var mContactList: MutableList<ContactListResponse>? = null

    @BindView(R.id.rv_contact)
    internal var rvContact: RecyclerView? = null

    override fun setupInjection() {
        fragmentComponent.plus(ContactListModule()).inject(this)
    }

    override fun setupEventAndData() {
        mContactList = ArrayList<ContactListResponse>()
        mContactListAdapter = ContactListAdapter(mContactList, this)

        setupRecycleView()

        mPresenter!!.getContactList()
    }

    override fun onDestroyUI() {
        mPresenter!!.disposed()
    }

    protected override val layoutId: Int
        get() = R.layout.fragment_contact_list

    override fun showContactList(response: List<ContactListResponse>) {
        mContactList!!.addAll(response)
        mContactListAdapter!!.notifyDataSetChanged()
    }

    override fun showContactListRange(responses: List<ContactListResponse>) {
        if (mContactList!!.size > 0) {
            mContactList!!.addAll(responses)
            mContactListAdapter!!.notifyItemRangeChanged(mContactList!!.size - 1, responses.size - mContactList!!.size)
        } else {
            mContactList!!.addAll(responses)
            mContactListAdapter!!.notifyDataSetChanged()
        }
    }

    override fun showError(msg: String) {
        if (mContactList!!.size <= 0) {
            SnackbarFactory.show(mView, msg)
        }
    }

    override fun showProgressDialog() {
        DialogFactory.showNotCancelableProgress(mContext)
    }

    override fun hideProgressDialog() {
        DialogFactory.dismissProgress()
    }

    override fun onClick(view: View, position: Int, isLongPressed: Boolean) {
        ContactDetailActivity.navigate(mActivity, mContactList!![position])
    }

    private fun setupRecycleView() {
        rvContact!!.setHasFixedSize(true)
        rvContact!!.layoutManager = RecycleViewUtil.linearLayoutManager(mContext)
        rvContact!!.adapter = mContactListAdapter
    }

    companion object {

        val TAG = ContactListFragment::class.java!!.getSimpleName()

        private val PARAM_1 = "param1"
        private val PARAM_2 = "param2"

        fun newInstance(param1: String, param2: String): ContactListFragment {
            val fragment = ContactListFragment()
            val bundle = Bundle()
            bundle.putString(PARAM_1, param1)
            bundle.putString(PARAM_2, param2)
            fragment.arguments = bundle
            return fragment
        }

        @VisibleForTesting
        fun newInstance(): ContactListFragment {
            return ContactListFragment()
        }
    }
}// TODO
