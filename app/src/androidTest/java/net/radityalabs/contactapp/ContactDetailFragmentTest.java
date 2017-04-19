package net.radityalabs.contactapp;

import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import net.radityalabs.contactapp.presentation.ui.fragment.ContactDetailFragment;
import net.radityalabs.contactapp.test.FragmentTestRule;

import org.junit.Rule;
import org.junit.runner.RunWith;

/**
 * Created by radityagumay on 4/19/17.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ContactDetailFragmentTest {

    @Rule
    public FragmentTestRule<ContactDetailFragment> mFragmentTestRule =
            new FragmentTestRule<>(ContactDetailFragment.class);

}
