package net.radityalabs.contactapp;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.core.deps.guava.collect.Ordering;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import net.radityalabs.contactapp.data.network.response.ContactListResponse;
import net.radityalabs.contactapp.presentation.ui.activity.ContactDetailActivity;
import net.radityalabs.contactapp.presentation.ui.activity.ContactListActivity;
import net.radityalabs.contactapp.presentation.ui.adapter.ContactListAdapter;
import net.radityalabs.contactapp.presentation.ui.fragment.ContactDetailFragment;
import net.radityalabs.contactapp.presentation.ui.fragment.ContactListFragment;
import net.radityalabs.contactapp.test.FragmentTestRule;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by radityagumay on 4/18/17.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ContactListFragmentTest {

    @Rule
    public ActivityTestRule<ContactListActivity> mContactListRule =
            new ActivityTestRule<>(ContactListActivity.class);

    @Rule
    public FragmentTestRule<ContactListFragment> mFragmentTestRule =
            new FragmentTestRule<>(ContactListFragment.class);

    @Rule
    public FragmentTestRule<ContactDetailFragment> mContactDetailFragmentRule =
            new FragmentTestRule<>(ContactDetailFragment.class);

    @Rule
    public IntentsTestRule<ContactDetailActivity> mContactDetailRule =
            new IntentsTestRule<>(ContactDetailActivity.class, true, false);

    @Before
    public void setup() {
        mFragmentTestRule.launchActivity(null);
    }

    @Test
    public void fragment_can_be_instantiated() {
        onView(withId(R.id.container)).check(matches(isDisplayed()));
    }

    @Test
    public void recycle_item_selected() {
        onView(withId(R.id.rv_contact)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.tv_full_name)).check(matches(isDisplayed()));
    }

    @Test
    public void recycle_item_navigate_contactdetailactivity() {
        startActivity();
        onView(withId(R.id.tv_full_name)).check(matches(isDisplayed()));
    }

    @Test
    public void is_sorted_alphabetically() {
        onView(withId(R.id.rv_contact)).check(matches(isSortedAlphabetically()));
    }

    private ContactDetailActivity startActivity() {
        return mContactDetailRule.launchActivity(
                ContactDetailActivity.navigateTest(mContactListRule.getActivity(), contactListResponse()));
    }

    private ContactListResponse contactListResponse() {
        ContactListResponse obj = new ContactListResponse();
        obj.id = 1;
        obj.firstName = "adit";
        obj.lastName = "gumay";
        obj.profilePic = null;
        obj.isFavorite = true;
        obj.detailUrl = null;
        return obj;
    }

    private static Matcher<View> isSortedAlphabetically() {
        return new TypeSafeMatcher<View>() {

            private final List<String> name = new ArrayList<>();

            @Override
            protected boolean matchesSafely(View item) {
                RecyclerView recyclerView = (RecyclerView) item;
                ContactListAdapter teamsAdapter = (ContactListAdapter) recyclerView.getAdapter();
                name.clear();
                name.addAll(extractTeamNames(teamsAdapter.getNameAsc()));
                return Ordering.natural().isOrdered(name);
            }

            private List<String> extractTeamNames(List<String> list) {
                Collections.sort(list, new Comparator<String>() {
                    @Override
                    public int compare(final String object1, final String object2) {
                        return object1.compareTo(object2);
                    }
                });
                return name;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("has items sorted alphabetically: " + name);
            }
        };
    }
}
