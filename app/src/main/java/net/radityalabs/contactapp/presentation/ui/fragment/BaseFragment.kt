package net.radityalabs.contactapp.presentation.ui.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.annotation.DrawableRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import net.radityalabs.contactapp.ContactApp
import net.radityalabs.contactapp.presentation.di.component.DaggerFragmentComponent
import net.radityalabs.contactapp.presentation.di.component.FragmentComponent
import net.radityalabs.contactapp.presentation.di.module.FragmentModule
import net.radityalabs.contactapp.presentation.presenter.BasePresenter
import net.radityalabs.contactapp.presentation.view.BaseView

import javax.inject.Inject

import butterknife.ButterKnife
import butterknife.Unbinder

/**
 * Created by radityagumay on 4/11/17.
 */

abstract class BaseFragment<T : BasePresenter<*>> : Fragment(), BaseView {

    @Inject
    var mPresenter: T? = null
    protected var mView: View
    protected var mActivity: Activity? = null
    protected var mContext: Context? = null

    private var mUnbinder: Unbinder? = null
    private var isInflated = false

    protected val fragmentComponent: FragmentComponent
        get() = DaggerFragmentComponent.builder()
                .appComponent(ContactApp.appComponent)
                .fragmentModule(fragmentModule)
                .build()

    protected val fragmentModule: FragmentModule
        get() = FragmentModule(this)

    override fun onAttach(context: Context?) {
        mActivity = context as Activity?
        mContext = context
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater!!.inflate(layoutId, container, false)
        setupInjection()
        return mView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mPresenter!!.attachView(this)
        mUnbinder = ButterKnife.bind(this, view!!)
        if (savedInstanceState == null) {
            initEventAndData()
        } else {
            initEventAndData()
        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!isInflated && !hidden) {
            isInflated = true
            initEventAndData()
        } else {
            isInflated = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onDestroyUI()
        mUnbinder!!.unbind()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mPresenter != null) {
            mPresenter!!.detachView()
        }
    }

    override fun onDetach() {
        mActivity = null
        mContext = null
        super.onDetach()
    }

    private fun initEventAndData() {
        if (!isHidden) {
            isInflated = true
            setupEventAndData()
        }
    }

    protected fun setupToolbar(toolbar: Toolbar, title: String?, @DrawableRes resId: Int) {
        val isVisible = title != null
        (mContext as AppCompatActivity).setSupportActionBar(toolbar)
        if (resId != -1) {
            toolbar.setNavigationIcon(resId)
        }

        (mContext as AppCompatActivity).supportActionBar!!.title = title
        (mContext as AppCompatActivity).supportActionBar!!.setDisplayShowTitleEnabled(isVisible)
        (mContext as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(isVisible)
        (mContext as AppCompatActivity).supportActionBar!!.setDisplayShowHomeEnabled(isVisible)
    }


    protected abstract fun setupInjection()

    protected abstract fun setupEventAndData()

    protected abstract fun onDestroyUI()

    protected abstract val layoutId: Int
}
