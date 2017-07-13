package net.radityalabs.contactapp.presentation.ui.activity

import android.os.Bundle

import net.radityalabs.contactapp.ContactApp
import net.radityalabs.contactapp.presentation.di.component.ActivityComponent
import net.radityalabs.contactapp.presentation.di.component.DaggerActivityComponent
import net.radityalabs.contactapp.presentation.di.module.ActivityModule
import net.radityalabs.contactapp.presentation.presenter.BasePresenter
import net.radityalabs.contactapp.presentation.view.BaseView

import javax.inject.Inject

/**
 * Created by radityagumay on 4/11/17.
 */

abstract class BaseActivity<T : BasePresenter<*>> : SimpleBaseActivity(), BaseView {

    @Inject
    var mPresenter: T? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupInjection()
        if (mPresenter != null) {
            mPresenter!!.attachView(this)
        }
    }

    override fun onDestroy() {
        if (mPresenter != null) {
            mPresenter!!.detachView()
        }
        super.onDestroy()
    }

    protected val activityComponent: ActivityComponent
        get() = DaggerActivityComponent.builder()
                .appComponent(ContactApp.appComponent)
                .activityModule(activityModule)
                .build()

    protected val activityModule: ActivityModule
        get() = ActivityModule(this)

    protected abstract fun setupInjection()
}
