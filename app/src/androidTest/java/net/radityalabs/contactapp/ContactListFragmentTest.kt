package net.radityalabs.contactapp

import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.core.deps.guava.collect.Ordering
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.view.View

import net.radityalabs.contactapp.data.network.response.ContactListResponse
import net.radityalabs.contactapp.presentation.ui.activity.ContactDetailActivity
import net.radityalabs.contactapp.presentation.ui.activity.ContactListActivity
import net.radityalabs.contactapp.presentation.ui.adapter.ContactListAdapter
import net.radityalabs.contactapp.presentation.ui.fragment.ContactDetailFragment
import net.radityalabs.contactapp.presentation.ui.fragment.ContactListFragment
import net.radityalabs.contactapp.test.FragmentTestRule

import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import java.util.ArrayList
import java.util.Collections
import java.util.Comparator

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId

/**
 * Created by radityagumay on 4/18/17.
 */

@RunWith(AndroidJUnit4::class)
@LargeTest
class ContactListFragmentTest {

    @Rule
    var mContactListRule = ActivityTestRule<ContactListActivity>(ContactListActivity::class.java)

    @Rule
    var mFragmentTestRule = FragmentTestRule<ContactListFragment>(ContactListFragment::class.java)

    @Rule
    var mContactDetailFragmentRule = FragmentTestRule<ContactDetailFragment>(ContactDetailFragment::class.java)

    @Rule
    var mContactDetailRule = IntentsTestRule<ContactDetailActivity>(ContactDetailActivity::class.java, true, false)

    @Before
    fun setup() {
        mFragmentTestRule.launchActivity(null)
    }

    @Test
    fun fragment_can_be_instantiated() {
        onView(withId(R.id.container)).check(matches(isDisplayed()))
    }

    @Test
    fun recycle_item_selected() {
        onView(withId(R.id.rv_contact)).perform(RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(0, click()))
        onView(withId(R.id.tv_full_name)).check(matches(isDisplayed()))
    }

    @Test
    fun recycle_item_navigate_contactdetailactivity() {
        startActivity()
        onView(withId(R.id.tv_full_name)).check(matches(isDisplayed()))
    }

    @Test
    fun is_sorted_alphabetically() {
        onView(withId(R.id.rv_contact)).check(matches(isSortedAlphabetically))
    }

    private fun startActivity(): ContactDetailActivity {
        return mContactDetailRule.launchActivity(
                ContactDetailActivity.navigateTest(mContactListRule.activity, contactListResponse()))
    }

    private fun contactListResponse(): ContactListResponse {
        val obj = ContactListResponse()
        obj.id = 1
        obj.firstName = "adit"
        obj.lastName = "gumay"
        obj.profilePic = null
        obj.isFavorite = true
        obj.detailUrl = null
        return obj
    }

    private val isSortedAlphabetically: Matcher<View>
        get() = object : TypeSafeMatcher<View>() {

            private val name = ArrayList<String>()

            override fun matchesSafely(item: View): Boolean {
                val recyclerView = item as RecyclerView
                val teamsAdapter = recyclerView.adapter as ContactListAdapter
                name.clear()
                name.addAll(extractTeamNames(teamsAdapter.nameAsc))
                return Ordering.natural<Comparable>().isOrdered(name)
            }

            private fun extractTeamNames(list: List<String>): List<String> {
                Collections.sort(list) { object1, object2 -> object1.compareTo(object2) }
                return name
            }

            override fun describeTo(description: Description) {
                description.appendText("has items sorted alphabetically: " + name)
            }
        }
}
