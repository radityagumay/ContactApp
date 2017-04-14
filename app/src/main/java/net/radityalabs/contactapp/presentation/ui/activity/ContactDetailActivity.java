package net.radityalabs.contactapp.presentation.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import net.radityalabs.contactapp.R;
import net.radityalabs.contactapp.presentation.ui.fragment.ContactDetailFragment;

/**
 * Created by radityagumay on 4/13/17.
 */

public class ContactDetailActivity extends SimpleBaseActivity {

    public static void navigate(Activity activity) {
        activity.startActivity(new Intent(activity, ContactDetailActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setupEventAndData() {
        addFragment(R.id.container, ContactDetailFragment.newInstance("", ""), ContactDetailFragment.TAG, ContactDetailFragment.TAG);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_contact_detail;
    }
}
