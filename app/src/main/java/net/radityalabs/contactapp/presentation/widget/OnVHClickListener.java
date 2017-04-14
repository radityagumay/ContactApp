package net.radityalabs.contactapp.presentation.widget;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by radityagumay on 4/13/17.
 */

public interface OnVHClickListener {
    void onClick(RecyclerView.ViewHolder vh, View view, int position);
}
