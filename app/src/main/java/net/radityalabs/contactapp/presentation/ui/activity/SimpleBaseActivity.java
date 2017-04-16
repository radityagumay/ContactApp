package net.radityalabs.contactapp.presentation.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import net.radityalabs.contactapp.ContactApp;
import net.radityalabs.contactapp.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.support.v4.app.FragmentManager.POP_BACK_STACK_INCLUSIVE;

/**
 * Created by radityagumay on 4/13/17.
 */

public abstract class SimpleBaseActivity extends AppCompatActivity {

    protected Activity mContext;
    protected Unbinder mUnBinder;

    private final FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        mUnBinder = ButterKnife.bind(this);
        mContext = this;
        ContactApp.getInstance().addActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setupEventAndData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnBinder.unbind();
        ContactApp.getInstance().removeActivity(this);
    }

    @Override
    public void onBackPressed() {
        if (fragmentManager.getBackStackEntryCount() > 1) {
            fragmentManager.popBackStack();
            currentFragment(currentFragment(fragmentManager));
        } else {
            super.onBackPressed();
            onBackPressedSupport();
        }
    }

    protected void setupToolBar(@NonNull Toolbar toolbar, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(title != null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressedSupport();
            }
        });
    }

    private void onBackPressedSupport() {
        finish();
    }

    private Fragment currentFragment(FragmentManager fragmentManager) {
        return fragmentManager.findFragmentByTag(fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1).getName());
    }

    protected void replaceFragment(@IdRes int containerViewId, @NonNull Fragment fragment, String fragmentTag, String backStackStateName) {
        fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .replace(containerViewId, fragment, fragmentTag).addToBackStack(backStackStateName).commit();
    }

    protected void addFragment(@IdRes int containerViewId, @NonNull Fragment fragment, String fragmentTag, String backStackStateName) {
        fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .add(containerViewId, fragment, fragmentTag).addToBackStack(backStackStateName).commit();
    }

    protected void popBackStack(String name) {
        fragmentManager.popBackStack(name, POP_BACK_STACK_INCLUSIVE);
    }

    protected abstract void setupEventAndData();

    protected abstract void currentFragment(Fragment fragment);

    protected abstract int getLayout();
}
