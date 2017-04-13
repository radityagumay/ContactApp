package net.radityalabs.contactapp.presentation.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import net.radityalabs.contactapp.ContactApp;
import net.radityalabs.contactapp.presentation.di.component.ActivityComponent;
import net.radityalabs.contactapp.presentation.di.component.DaggerActivityComponent;
import net.radityalabs.contactapp.presentation.di.module.ActivityModule;
import net.radityalabs.contactapp.presentation.presenter.BasePresenter;
import net.radityalabs.contactapp.presentation.view.BaseView;

import javax.inject.Inject;

/**
 * Created by radityagumay on 4/11/17.
 */

public abstract class BaseActivity<T extends BasePresenter> extends SimpleBaseActivity implements BaseView {

    @Inject
    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupInjection();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroy();
    }

    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .appComponent(ContactApp.getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    protected abstract void setupInjection();
}
