package net.radityalabs.contactapp.presentation.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import net.radityalabs.contactapp.R;
import net.radityalabs.contactapp.data.network.response.ContactListResponse;
import net.radityalabs.contactapp.presentation.ui.fragment.ContactDetailFragment;

/**
 * Created by radityagumay on 4/13/17.
 */

public class ContactDetailActivity extends SimpleBaseActivity {

    public static final String EXTRA_USER = "extra_user";

    public static void navigate(Activity activity, ContactListResponse user) {
        Intent intent = new Intent(activity, ContactDetailActivity.class);
        intent.putExtra(EXTRA_USER, user);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setupEventAndData() {
        addFragment(R.id.container,
                ContactDetailFragment.newInstance((ContactListResponse) getIntent().getParcelableExtra(EXTRA_USER)),
                ContactDetailFragment.TAG, ContactDetailFragment.TAG);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_contact_detail;
    }
}
