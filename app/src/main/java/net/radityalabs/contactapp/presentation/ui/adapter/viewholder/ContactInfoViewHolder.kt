package net.radityalabs.contactapp.presentation.ui.adapter.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView

import net.radityalabs.contactapp.R
import net.radityalabs.contactapp.presentation.widget.OnVHClickListener

import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.OnLongClick

/**
 * Created by radityagumay on 4/15/17.
 */

class ContactInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private var mSimpleClickListener: OnVHClickListener? = null

    @BindView(R.id.iv_left_icon)
    var ivLeftIcon: ImageView? = null
    @BindView(R.id.tv_body_one)
    var tvBodyOne: TextView? = null
    @BindView(R.id.tv_body_two)
    var tvBodyTwo: TextView? = null
    @BindView(R.id.iv_right_icon)
    var ivRightIcon: ImageView? = null

    @OnClick(R.id.iv_left_icon, R.id.iv_right_icon)
    override fun onClick(view: View) {
        mSimpleClickListener!!.onClick(this, view, adapterPosition, false)
    }

    @OnLongClick(R.id.iv_left_icon, R.id.iv_right_icon)
    fun onLongClick(view: View): Boolean {
        mSimpleClickListener!!.onClick(this, view, adapterPosition, true)
        return true
    }

    init {
        ButterKnife.bind(this, itemView)
    }

    fun setOnClickListener(listener: OnVHClickListener) {
        this.mSimpleClickListener = listener
    }
}
