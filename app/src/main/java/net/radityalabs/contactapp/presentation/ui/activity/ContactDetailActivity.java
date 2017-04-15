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

    public static final String EXTRA_USER_ID = "extra_user_id";

    public static void navigate(Activity activity, int userId) {
        Intent intent = new Intent(activity, ContactDetailActivity.class);
        intent.putExtra(EXTRA_USER_ID, userId);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setupEventAndData() {
        addFragment(R.id.container, ContactDetailFragment.newInstance(getIntent().getIntExtra(EXTRA_USER_ID, -1)), ContactDetailFragment.TAG, ContactDetailFragment.TAG);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_contact_detail;
    }
}
