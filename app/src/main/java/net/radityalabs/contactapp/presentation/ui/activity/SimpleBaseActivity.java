package net.radityalabs.contactapp.presentation.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import net.radityalabs.contactapp.ContactApp;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by radityagumay on 4/13/17.
 */

public abstract class SimpleBaseActivity extends AppCompatActivity {

    protected Activity mContext;
    protected Unbinder mUnBinder;

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
        super.onBackPressed();
        onBackPressedSupport();
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

    protected void replaceFragment(@IdRes int containerViewId, @NonNull Fragment fragment, String fragmentTag, String backStackStateName) {
        getSupportFragmentManager().beginTransaction().replace(containerViewId, fragment, fragmentTag).addToBackStack(backStackStateName).commit();
    }

    protected void addFragment(@IdRes int containerViewId, @NonNull Fragment fragment, String fragmentTag, String backStackStateName) {
        getSupportFragmentManager().beginTransaction().add(containerViewId, fragment, fragmentTag).addToBackStack(backStackStateName).commit();
    }

    protected abstract void setupEventAndData();

    protected abstract int getLayout();
}
