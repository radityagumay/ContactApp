package net.radityalabs.contactapp.presentation.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import net.radityalabs.contactapp.R;
import net.radityalabs.contactapp.presentation.ui.fragment.AddContactFragment;
import net.radityalabs.contactapp.presentation.ui.fragment.ContactListFragment;

import butterknife.BindView;

public class ContactListActivity extends SimpleBaseActivity implements NavigationView.OnNavigationItemSelectedListener,
        AddContactFragment.AddContactObserver,
        View.OnClickListener {

    private Fragment mSelectedFragment;

    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupToolbar();
        setupFab();
        setupNavigationView();
    }

    private void setupNavigationView() {
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setupFab() {
        fab.setOnClickListener(this);
    }

    private void setupToolbar() {
        setupToolBar(toolbar, "ContactApp");
        setupDrawer(toolbar);
    }

    private void setupDrawer(Toolbar toolbar) {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    protected void setupEventAndData() {
        mSelectedFragment = ContactListFragment.newInstance("", "");
        addFragment(R.id.container, mSelectedFragment, ContactListFragment.TAG, ContactListFragment.TAG);
    }

    @Override
    protected void currentFragment(Fragment fragment) {
        visibileView(true);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (mSelectedFragment instanceof ContactListFragment) {
            getMenuInflater().inflate(R.menu.main, menu);
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
            case R.id.action_add: {
                ((AddContactFragment) mSelectedFragment).saveAddProfile();
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_camera) {
        } else if (id == R.id.nav_gallery) {
        } else if (id == R.id.nav_slideshow) {
        } else if (id == R.id.nav_manage) {
        } else if (id == R.id.nav_share) {
        } else if (id == R.id.nav_send) {
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab: {
                visibileView(false);
                mSelectedFragment = AddContactFragment.newInstance().setObserver(this);
                addFragment(R.id.container, mSelectedFragment, AddContactFragment.TAG, AddContactFragment.TAG);
            }
            break;
        }
    }

    private void visibileView(boolean visible) {
        fab.setVisibility(visible ? View.VISIBLE : View.GONE);
        toolbar.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void removeStack(String tag) {
        visibileView(true);

        popBackStack(tag);
    }
}
