package net.radityalabs.contactapp.presentation.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.radityalabs.contactapp.R;
import net.radityalabs.contactapp.data.network.response.ContactListResponse;
import net.radityalabs.contactapp.presentation.ui.adapter.viewholder.ContactListViewHolder;
import net.radityalabs.contactapp.presentation.util.StringUtil;

import java.util.List;

/**
 * Created by radityagumay on 4/12/17.
 */

public class ContactListAdapter extends RecyclerView.Adapter<ContactListViewHolder> {

    private Context mContext;
    private List<ContactListResponse> mContactList;

    public ContactListAdapter(Context context, List<ContactListResponse> contactList) {
        this.mContext = context;
        this.mContactList = contactList;
    }

    @Override
    public ContactListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_contact_list_item, parent, false);
        return new ContactListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactListViewHolder holder, int position) {
        if (mContactList != null && mContactList.size() > 0) {
            ContactListResponse item = mContactList.get(position);
            holder.tvFirstChar.setText(StringUtil.getFirstChar(item.firstName));
            holder.tvFullName.setText(StringUtil.mergeString(item.firstName, item.lastName));
        }
    }

    @Override
    public int getItemCount() {
        return mContactList != null ? mContactList.size() : 0;
    }
}
