package net.radityalabs.contactapp.presentation.ui.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import net.radityalabs.contactapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by radityagumay on 4/12/17.
 */

public class ContactListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_first_char)
    public TextView tvFirstChar;
    @BindView(R.id.tv_full_name)
    public TextView tvFullName;
    @BindView(R.id.iv_favorite)
    public ImageView ivFavorite;

    public ContactListViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
