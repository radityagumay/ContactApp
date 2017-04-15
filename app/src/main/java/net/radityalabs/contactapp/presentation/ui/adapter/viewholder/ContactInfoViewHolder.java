package net.radityalabs.contactapp.presentation.ui.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import net.radityalabs.contactapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by radityagumay on 4/15/17.
 */

public class ContactInfoViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.iv_left_icon)
    public ImageView ivLeftIcon;
    @BindView(R.id.tv_body_one)
    public TextView tvBodyOne;
    @BindView(R.id.tv_body_two)
    public TextView tvBodyTwo;
    @BindView(R.id.iv_right_icon)
    public ImageView ivRightIcon;

    public ContactInfoViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
