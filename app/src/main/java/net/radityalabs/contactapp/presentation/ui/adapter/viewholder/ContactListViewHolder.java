package net.radityalabs.contactapp.presentation.ui.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import net.radityalabs.contactapp.R;
import net.radityalabs.contactapp.presentation.widget.OnVHClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by radityagumay on 4/12/17.
 */

public class ContactListViewHolder extends RecyclerView.ViewHolder implements
        View.OnClickListener {

    private OnVHClickListener mSimpleClickListener;

    @BindView(R.id.tv_first_char)
    public TextView tvFirstChar;
    @BindView(R.id.tv_full_name)
    public TextView tvFullName;
    @BindView(R.id.iv_favorite)
    public ImageView ivFavorite;

    public ContactListViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
    }

    public void setOnClickListener(OnVHClickListener listener) {
        this.mSimpleClickListener = listener;
    }

    @Override
    public void onClick(View v) {
        mSimpleClickListener.onClick(this, v, getAdapterPosition());
    }
}
