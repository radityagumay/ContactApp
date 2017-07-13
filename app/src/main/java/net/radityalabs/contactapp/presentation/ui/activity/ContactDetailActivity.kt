package net.radityalabs.contactapp.presentation.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.annotation.VisibleForTesting
import android.support.test.espresso.IdlingResource
import android.support.v4.app.Fragment
import android.view.Menu
import android.view.MenuItem

import net.radityalabs.contactapp.R
import net.radityalabs.contactapp.data.network.response.ContactListResponse
import net.radityalabs.contactapp.presentation.ui.fragment.AddContactFragment
import net.radityalabs.contactapp.presentation.ui.fragment.ContactDetailFragment

import net.radityalabs.contactapp.presentation.util.task.EspressoIdlingResource.getIdlingResource

/**
 * Created by radityagumay on 4/13/17.
 */

class ContactDetailActivity : SimpleBaseActivity(), AddContactFragment.AddContactObserver {

    private var mSelectedFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        if (mSelectedFragment is ContactDetailFragment) {
            (mSelectedFragment as ContactDetailFragment).setFavoriteMarked(menu)
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        if (mSelectedFragment is ContactDetailFragment) {
            menuInflater.inflate(R.menu.menu_contact_detail, menu)
        } else {
            menuInflater.inflate(R.menu.menu_add_detail, menu)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> {
                return true
            }
            R.id.action_edit -> {
                changeToAddFragment()
                return true
            }
            R.id.action_add -> {
                (mSelectedFragment as AddContactFragment).saveEditProfile()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun setupEventAndData() {
        changeToDetailFragment()
    }

    override fun currentFragment(fragment: Fragment) {
        invalidateOptionsMenu()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    protected override val layout: Int
        get() = R.layout.activity_contact_detail

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        if (mSelectedFragment is AddContactFragment) {
            (mSelectedFragment as AddContactFragment).onActivityResult(requestCode, resultCode, data)
        }
    }

    val countingIdlingResource: IdlingResource
        @VisibleForTesting
        get() = idlingResource

    private fun changeToAddFragment() {
        mSelectedFragment = AddContactFragment.newInstance(
                intent.getParcelableExtra<Parcelable>(EXTRA_USER) as ContactListResponse)
                .setObserver(this)
        addFragment(R.id.container, mSelectedFragment!!, AddContactFragment.TAG, AddContactFragment.TAG)

        invalidateOptionsMenu()
    }

    private fun changeToDetailFragment() {
        mSelectedFragment = ContactDetailFragment.newInstance(intent.getParcelableExtra<Parcelable>(EXTRA_USER) as ContactListResponse)
        addFragment(R.id.container, mSelectedFragment!!, ContactDetailFragment.TAG, ContactDetailFragment.TAG)

        invalidateOptionsMenu()
    }

    override fun removeStack(tag: String) {
        popBackStack(tag)
    }

    companion object {

        val TAG = ContactDetailActivity::class.java!!.getSimpleName()
        val EXTRA_USER = "extra_user"

        fun navigate(activity: Activity, user: ContactListResponse) {
            val intent = Intent(activity, ContactDetailActivity::class.java)
            intent.putExtra(EXTRA_USER, user)
            activity.startActivity(intent)
        }

        @VisibleForTesting
        fun navigateTest(activity: Activity, user: ContactListResponse): Intent {
            val intent = Intent(activity, ContactDetailActivity::class.java)
            intent.putExtra(EXTRA_USER, user)
            return intent
        }
    }
}
