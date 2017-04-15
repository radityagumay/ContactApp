package net.radityalabs.contactapp.presentation.ui.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import net.radityalabs.contactapp.R;
import net.radityalabs.contactapp.presentation.widget.OnVHClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

/**
 * Created by radityagumay on 4/15/17.
 */

public class ContactInfoViewHolder extends RecyclerView.ViewHolder implements
        View.OnClickListener {

    private OnVHClickListener mSimpleClickListener;

    @BindView(R.id.iv_left_icon)
    public ImageView ivLeftIcon;
    @BindView(R.id.tv_body_one)
    public TextView tvBodyOne;
    @BindView(R.id.tv_body_two)
    public TextView tvBodyTwo;
    @BindView(R.id.iv_right_icon)
    public ImageView ivRightIcon;

    @OnClick({
            R.id.iv_left_icon,
            R.id.iv_right_icon
    })
    public void onClick(View view) {
        mSimpleClickListener.onClick(this, view, getAdapterPosition(), false);
    }

    @OnLongClick({
            R.id.iv_left_icon,
            R.id.iv_right_icon
    })
    public boolean onLongClick(View view) {
        mSimpleClickListener.onClick(this, view, getAdapterPosition(), true);
        return true;
    }

    public ContactInfoViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setOnClickListener(OnVHClickListener listener) {
        this.mSimpleClickListener = listener;
    }
}
