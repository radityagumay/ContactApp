package net.radityalabs.contactapp;

import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import net.radityalabs.contactapp.presentation.ui.fragment.ContactListFragment;
import net.radityalabs.contactapp.test.FragmentTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
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
    public FragmentTestRule<ContactListFragment> mFragmentTestRule = new FragmentTestRule<>(ContactListFragment.class);

    @Test
    public void fragment_can_be_instantiated() {
        mFragmentTestRule.launchActivity(null);
        onView(withId(R.id.container)).check(matches(isDisplayed()));
    }
}
