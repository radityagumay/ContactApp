package net.radityalabs.contactapp.presentation.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.radityalabs.contactapp.R;
import net.radityalabs.contactapp.domain.model.ContactDetailInfoModel;
import net.radityalabs.contactapp.presentation.ui.adapter.viewholder.ContactInfoViewHolder;
import net.radityalabs.contactapp.presentation.util.CollectionUtil;

import java.util.List;

/**
 * Created by radityagumay on 4/15/17.
 */

public class ContactInfoAdapter extends RecyclerView.Adapter<ContactInfoViewHolder> {

    private Context mContext;
    private List<ContactDetailInfoModel> mInfoList;

    public ContactInfoAdapter(List<ContactDetailInfoModel> infoList) {
        this.mInfoList = infoList;
    }

    @Override
    public ContactInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_contact_detail_content_item, parent, false);
        return new ContactInfoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactInfoViewHolder holder, int position) {
        if (!CollectionUtil.isValid(mInfoList)) {
            return;
        }

        ContactDetailInfoModel obj = mInfoList.get(position);
        holder.tvBodyOne.setText(obj.bodyOne);
        holder.tvBodyTwo.setText(obj.bodyTwo);
        holder.ivLeftIcon.setImageResource(obj.leftIcon);
        if (obj.isRightIconSet) {
            holder.ivRightIcon.setImageResource(obj.rightIcon);
            holder.ivRightIcon.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return mInfoList != null ? mInfoList.size() : 0;
    }
}
