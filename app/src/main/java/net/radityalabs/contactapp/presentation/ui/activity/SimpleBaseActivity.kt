package net.radityalabs.contactapp.presentation.ui.activity

import android.app.Activity
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View

import net.radityalabs.contactapp.ContactApp
import net.radityalabs.contactapp.R

import butterknife.ButterKnife
import butterknife.Unbinder

import android.support.v4.app.FragmentManager.POP_BACK_STACK_INCLUSIVE

/**
 * Created by radityagumay on 4/13/17.
 */

abstract class SimpleBaseActivity : AppCompatActivity() {

    protected var mContext: Activity
    protected var mUnBinder: Unbinder

    private val fragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)
        mUnBinder = ButterKnife.bind(this)
        mContext = this
        ContactApp.instance.addActivity(this)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        setupEventAndData()
    }

    override fun onDestroy() {
        super.onDestroy()
        mUnBinder.unbind()
        ContactApp.instance.removeActivity(this)
    }

    override fun onBackPressed() {
        if (fragmentManager.backStackEntryCount > 1) {
            fragmentManager.popBackStack()
            currentFragment(currentFragment(fragmentManager))
        } else {
            super.onBackPressed()
            onBackPressedSupport()
        }
    }

    protected fun setupToolBar(toolbar: Toolbar, title: String?) {
        toolbar.title = title
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(title != null)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        toolbar.setNavigationOnClickListener { onBackPressedSupport() }
    }

    private fun onBackPressedSupport() {
        finish()
    }

    private fun currentFragment(fragmentManager: FragmentManager): Fragment {
        return fragmentManager.findFragmentByTag(fragmentManager.getBackStackEntryAt(fragmentManager.backStackEntryCount - 1).name)
    }

    protected fun replaceFragment(@IdRes containerViewId: Int, fragment: Fragment, fragmentTag: String, backStackStateName: String) {
        fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .replace(containerViewId, fragment, fragmentTag).addToBackStack(backStackStateName).commit()
    }

    protected fun addFragment(@IdRes containerViewId: Int, fragment: Fragment, fragmentTag: String, backStackStateName: String) {
        fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .add(containerViewId, fragment, fragmentTag).addToBackStack(backStackStateName).commit()
    }

    protected fun popBackStack(name: String) {
        fragmentManager.popBackStack(name, POP_BACK_STACK_INCLUSIVE)
    }

    protected abstract fun setupEventAndData()

    protected abstract fun currentFragment(fragment: Fragment)

    protected abstract val layout: Int
}
