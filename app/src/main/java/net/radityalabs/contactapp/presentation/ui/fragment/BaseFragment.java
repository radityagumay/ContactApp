package net.radityalabs.contactapp.presentation.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.radityalabs.contactapp.ContactApp;
import net.radityalabs.contactapp.presentation.di.component.DaggerFragmentComponent;
import net.radityalabs.contactapp.presentation.di.component.FragmentComponent;
import net.radityalabs.contactapp.presentation.di.module.FragmentModule;
import net.radityalabs.contactapp.presentation.presenter.BasePresenter;
import net.radityalabs.contactapp.presentation.view.BaseView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by radityagumay on 4/11/17.
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements BaseView {

    @Inject
    protected T mPresenter;
    protected View mView;
    protected Activity mActivity;
    protected Context mContext;

    private Unbinder mUnbinder;
    private boolean isInflated = false;

    protected FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent.builder()
                .appComponent(ContactApp.getAppComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }

    protected FragmentModule getFragmentModule() {
        return new FragmentModule(this);
    }

    @Override
    public void onAttach(Context context) {
        mActivity = (Activity) context;
        mContext = context;
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), container, false);
        setupInjection();
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPresenter.attachView(this);
        mUnbinder = ButterKnife.bind(this, view);
        if (savedInstanceState == null) {
            initEventAndData();
        } else {
            initEventAndData();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!isInflated && !hidden) {
            isInflated = true;
            initEventAndData();
        } else {
            isInflated = false;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        onDestroyUI();
        mUnbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    @Override
    public void onDetach() {
        mActivity = null;
        mContext = null;
        super.onDetach();
    }

    private void initEventAndData() {
        if (!isHidden()) {
            isInflated = true;
            setupEventAndData();
        }
    }

    protected void setupToolbar(Toolbar toolbar, String title) {
        boolean isVisible = title != null;
        ((AppCompatActivity) mContext).setSupportActionBar(toolbar);
        ((AppCompatActivity) mContext).getSupportActionBar().setTitle(title);
        ((AppCompatActivity) mContext).getSupportActionBar().setDisplayShowTitleEnabled(isVisible);
        ((AppCompatActivity) mContext).getSupportActionBar().setDisplayHomeAsUpEnabled(isVisible);
        ((AppCompatActivity) mContext).getSupportActionBar().setDisplayShowHomeEnabled(isVisible);
    }

    protected abstract void setupInjection();

    protected abstract void setupEventAndData();

    protected abstract void onDestroyUI();

    protected abstract int getLayoutId();
}
