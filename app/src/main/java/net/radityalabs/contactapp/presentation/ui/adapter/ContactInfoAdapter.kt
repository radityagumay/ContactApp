package net.radityalabs.contactapp.presentation.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import net.radityalabs.contactapp.R
import net.radityalabs.contactapp.domain.model.ContactDetailInfoModel
import net.radityalabs.contactapp.presentation.ui.adapter.viewholder.ContactInfoViewHolder
import net.radityalabs.contactapp.presentation.util.CollectionUtil
import net.radityalabs.contactapp.presentation.widget.OnSimpleClickListener
import net.radityalabs.contactapp.presentation.widget.OnVHClickListener

/**
 * Created by radityagumay on 4/15/17.
 */

class ContactInfoAdapter(private val mInfoList: List<ContactDetailInfoModel>?, private val mSimpleClickListener: OnSimpleClickListener) : RecyclerView.Adapter<ContactInfoViewHolder>(), OnVHClickListener {

    private var mContext: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactInfoViewHolder {
        mContext = parent.context
        val view = LayoutInflater.from(mContext).inflate(R.layout.fragment_contact_detail_content_item, parent, false)
        val holder = ContactInfoViewHolder(view)
        holder.setOnClickListener(this)
        return holder
    }

    override fun onBindViewHolder(holder: ContactInfoViewHolder, position: Int) {
        if (!CollectionUtil.isValid(mInfoList)) {
            return
        }

        val obj = mInfoList!![position]
        holder.tvBodyOne!!.text = obj.bodyOne
        holder.tvBodyTwo!!.text = obj.bodyTwo
        holder.ivLeftIcon!!.setImageResource(obj.leftIcon)
        if (obj.isRightIconSet) {
            holder.ivRightIcon!!.setImageResource(obj.rightIcon)
            holder.ivRightIcon!!.visibility = View.VISIBLE
        }
    }

    override fun getItemCount(): Int {
        return mInfoList?.size ?: 0
    }

    override fun onClick(vh: RecyclerView.ViewHolder, view: View, position: Int, isLongPressed: Boolean) {
        mSimpleClickListener.onClick(view, position, isLongPressed)
    }

}
