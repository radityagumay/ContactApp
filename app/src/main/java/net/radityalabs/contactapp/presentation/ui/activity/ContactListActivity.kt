package net.radityalabs.contactapp.presentation.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View

import net.radityalabs.contactapp.R
import net.radityalabs.contactapp.presentation.ui.fragment.AddContactFragment
import net.radityalabs.contactapp.presentation.ui.fragment.ContactListFragment

import butterknife.BindView

class ContactListActivity : SimpleBaseActivity(), NavigationView.OnNavigationItemSelectedListener, AddContactFragment.AddContactObserver, View.OnClickListener {

    private var mSelectedFragment: Fragment? = null

    @BindView(R.id.fab)
    internal var fab: FloatingActionButton? = null
    @BindView(R.id.nav_view)
    internal var navigationView: NavigationView? = null
    @BindView(R.id.toolbar)
    internal var toolbar: Toolbar? = null
    @BindView(R.id.drawer_layout)
    internal var drawer: DrawerLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupToolbar()
        setupFab()
        setupNavigationView()
    }

    private fun setupNavigationView() {
        navigationView!!.setNavigationItemSelectedListener(this)
    }

    private fun setupFab() {
        fab!!.setOnClickListener(this)
    }

    private fun setupToolbar() {
        setupToolBar(toolbar!!, "ContactApp")
        setupDrawer(toolbar)
    }

    private fun setupDrawer(toolbar: Toolbar) {
        val toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer!!.setDrawerListener(toggle)
        toggle.syncState()
    }

    override fun setupEventAndData() {
        mSelectedFragment = ContactListFragment.newInstance("", "")
        addFragment(R.id.container, mSelectedFragment!!, ContactListFragment.TAG, ContactListFragment.TAG)
    }

    override fun currentFragment(fragment: Fragment) {
        visibileView(true)
    }

    protected override val layout: Int
        get() = R.layout.activity_main

    override fun onBackPressed() {
        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        if (mSelectedFragment is ContactListFragment) {
            menuInflater.inflate(R.menu.main, menu)
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
            R.id.action_add -> {
                (mSelectedFragment as AddContactFragment).saveAddProfile()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.nav_camera) {
        } else if (id == R.id.nav_gallery) {
        } else if (id == R.id.nav_slideshow) {
        } else if (id == R.id.nav_manage) {
        } else if (id == R.id.nav_share) {
        } else if (id == R.id.nav_send) {
        }

        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.fab -> {
                visibileView(false)
                mSelectedFragment = AddContactFragment.newInstance().setObserver(this)
                addFragment(R.id.container, mSelectedFragment!!, AddContactFragment.TAG, AddContactFragment.TAG)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        if (mSelectedFragment is AddContactFragment) {
            (mSelectedFragment as AddContactFragment).onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun visibileView(visible: Boolean) {
        fab!!.visibility = if (visible) View.VISIBLE else View.GONE
        toolbar!!.visibility = if (visible) View.VISIBLE else View.GONE
    }

    override fun removeStack(tag: String) {
        visibileView(true)

        popBackStack(tag)
    }
}
