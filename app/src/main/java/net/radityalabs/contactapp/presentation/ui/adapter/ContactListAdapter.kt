package net.radityalabs.contactapp.presentation.ui.adapter

import android.content.Context
import android.support.annotation.VisibleForTesting
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import net.radityalabs.contactapp.R
import net.radityalabs.contactapp.data.network.response.ContactListResponse
import net.radityalabs.contactapp.presentation.ui.adapter.viewholder.ContactListViewHolder
import net.radityalabs.contactapp.presentation.util.CollectionUtil
import net.radityalabs.contactapp.presentation.util.StringUtil
import net.radityalabs.contactapp.presentation.widget.OnSimpleClickListener
import net.radityalabs.contactapp.presentation.widget.OnVHClickListener

import java.util.ArrayList
import java.util.Collections
import java.util.Comparator

/**
 * Created by radityagumay on 4/12/17.
 */

class ContactListAdapter(private val mContactList: List<ContactListResponse>?, private val mOnSimpleClickListener: OnSimpleClickListener) : RecyclerView.Adapter<ContactListViewHolder>(), OnVHClickListener {
    private var mContext: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactListViewHolder {
        this.mContext = parent.context
        val view = LayoutInflater.from(mContext).inflate(R.layout.fragment_contact_list_item, parent, false)
        val holder = ContactListViewHolder(view)
        holder.setOnClickListener(this)
        return holder
    }

    override fun onBindViewHolder(holder: ContactListViewHolder, position: Int) {
        if (!CollectionUtil.isValid(mContactList)) {
            return
        }

        val item = mContactList!![position]
        holder.tvFirstChar!!.text = StringUtil.getFirstChar(item.firstName)
        holder.tvFullName!!.text = StringUtil.mergeString(item.firstName, item.lastName)
        holder.ivFavorite!!.visibility = View.VISIBLE
    }

    override fun getItemCount(): Int {
        return mContactList?.size ?: 0
    }

    override fun onClick(vh: RecyclerView.ViewHolder, view: View, position: Int, isLongPressed: Boolean) {
        mOnSimpleClickListener.onClick(view, position, isLongPressed)
    }

    val nameAsc: List<String>
        @VisibleForTesting
        get() {
            val list = ArrayList<String>(mContactList!!.size)
            for (i in mContactList.indices) {
                list.add(mContactList[i].firstName)
            }
            return list
        }
}
