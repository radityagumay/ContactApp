package net.radityalabs.contactapp.test

import android.support.test.rule.ActivityTestRule
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction

import junit.framework.Assert

import net.radityalabs.contactapp.R
import net.radityalabs.contactapp.presentation.ui.activity.TestActivity

/**
 * Created by radityagumay on 4/19/17.
 */

class FragmentTestRule<F : Fragment>(private val mFragmentClass: Class<F>) : ActivityTestRule<TestActivity>(TestActivity::class.java, true, false) {
    var fragment: F? = null
        private set

    override fun afterActivityLaunched() {
        super.afterActivityLaunched()
        activity.runOnUiThread {
            val manager = activity.supportFragmentManager
            val transaction = manager.beginTransaction()
            try {
                fragment = mFragmentClass.newInstance()
                transaction.replace(R.id.container, fragment)
                transaction.commit()
            } catch (e: Exception) {
                Assert.fail(String.format("%s: Could not insert %s into TestActivity: %s",
                        javaClass.getSimpleName(),
                        mFragmentClass.simpleName,
                        e.message))
            }
        }
    }
}
