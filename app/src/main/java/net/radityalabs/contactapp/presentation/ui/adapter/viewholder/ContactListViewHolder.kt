package net.radityalabs.contactapp.presentation.ui.adapter.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView

import net.radityalabs.contactapp.R
import net.radityalabs.contactapp.presentation.widget.OnVHClickListener

import butterknife.BindView
import butterknife.ButterKnife

/**
 * Created by radityagumay on 4/12/17.
 */

class ContactListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private var mSimpleClickListener: OnVHClickListener? = null

    @BindView(R.id.tv_first_char)
    var tvFirstChar: TextView? = null
    @BindView(R.id.tv_full_name)
    var tvFullName: TextView? = null
    @BindView(R.id.iv_favorite)
    var ivFavorite: ImageView? = null

    init {
        ButterKnife.bind(this, itemView)
        itemView.setOnClickListener(this)
    }

    fun setOnClickListener(listener: OnVHClickListener) {
        this.mSimpleClickListener = listener
    }

    override fun onClick(v: View) {
        mSimpleClickListener!!.onClick(this, v, adapterPosition, false)
    }
}
