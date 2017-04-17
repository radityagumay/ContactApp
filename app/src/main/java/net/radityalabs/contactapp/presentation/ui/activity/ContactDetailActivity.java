package net.radityalabs.contactapp.presentation.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;

import net.radityalabs.contactapp.R;
import net.radityalabs.contactapp.data.network.response.ContactListResponse;
import net.radityalabs.contactapp.presentation.ui.fragment.AddContactFragment;
import net.radityalabs.contactapp.presentation.ui.fragment.ContactDetailFragment;

/**
 * Created by radityagumay on 4/13/17.
 */

public class ContactDetailActivity extends SimpleBaseActivity implements
        AddContactFragment.AddContactObserver {

    public static final String EXTRA_USER = "extra_user";

    private Fragment mSelectedFragment;

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
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (mSelectedFragment instanceof ContactDetailFragment) {
            ((ContactDetailFragment) mSelectedFragment).setFavoriteMarked(menu);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (mSelectedFragment instanceof ContactDetailFragment) {
            getMenuInflater().inflate(R.menu.menu_contact_detail, menu);
        } else {
            getMenuInflater().inflate(R.menu.menu_add_detail, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings: {
                return true;
            }
            case R.id.action_edit: {
                changeToAddFragment();
                return true;
            }
            case R.id.action_add: {
                ((AddContactFragment) mSelectedFragment).saveEditProfile();
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void setupEventAndData() {
        changeToDetailFragment();
    }

    @Override
    protected void currentFragment(Fragment fragment) {
        invalidateOptionsMenu();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_contact_detail;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mSelectedFragment instanceof AddContactFragment) {
            ((AddContactFragment) mSelectedFragment).onActivityResult(requestCode, resultCode, data);
        }
    }

    private void changeToAddFragment() {
        mSelectedFragment = AddContactFragment.newInstance(
                (ContactListResponse) getIntent().getParcelableExtra(EXTRA_USER))
                .setObserver(this);
        addFragment(R.id.container, mSelectedFragment, AddContactFragment.TAG, AddContactFragment.TAG);

        invalidateOptionsMenu();
    }

    private void changeToDetailFragment() {
        mSelectedFragment = ContactDetailFragment.newInstance((ContactListResponse) getIntent().getParcelableExtra(EXTRA_USER));
        addFragment(R.id.container, mSelectedFragment, ContactDetailFragment.TAG, ContactDetailFragment.TAG);

        invalidateOptionsMenu();
    }

    @Override
    public void removeStack(String tag) {
        popBackStack(tag);
    }
}
