package net.radityalabs.contactapp

import android.app.Activity
import android.app.Instrumentation
import android.os.Build
import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.View

import net.radityalabs.contactapp.data.network.response.ContactListResponse
import net.radityalabs.contactapp.presentation.ui.activity.ContactDetailActivity
import net.radityalabs.contactapp.presentation.ui.activity.ContactListActivity
import net.radityalabs.contactapp.presentation.ui.fragment.ContactDetailFragment
import net.radityalabs.contactapp.presentation.util.PhoneUtil
import net.radityalabs.contactapp.test.DrawableMatcher
import net.radityalabs.contactapp.test.FragmentTestRule

import org.hamcrest.Matcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.closeSoftKeyboard
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.Intents.intending
import android.support.test.espresso.intent.matcher.IntentMatchers.toPackage
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import net.radityalabs.contactapp.test.ViewAction.setTextInTextView

/**
 * Created by radityagumay on 4/19/17.
 */

@RunWith(AndroidJUnit4::class)
@LargeTest
class ContactDetailFragmentTest {

    @Rule
    var mContactDetailFragmentRule = FragmentTestRule<ContactDetailFragment>(ContactDetailFragment::class.java)

    @Rule
    var mContactListRule = ActivityTestRule<ContactListActivity>(ContactListActivity::class.java)

    @Rule
    var mContactDetailRule = IntentsTestRule<ContactDetailActivity>(ContactDetailActivity::class.java, true, false)

    @Before
    fun setup() {
        startActivity()
    }

    @Test
    fun fragment_can_be_instantiated() {
        onView(withId(R.id.container)).check(matches(isDisplayed()))
    }

    @Test
    fun is_view_available() {
        onView(withId(R.id.rv_info)).check(matches(isDisplayed()))
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_full_name)).check(matches(isDisplayed()))
        onView(withId(R.id.iv_image)).check(matches(isDisplayed()))
    }

    @Test
    fun is_widget_filled() {
        onView(withId(R.id.tv_full_name)).perform(setTextInTextView(STRING_TO_BE_TYPED), closeSoftKeyboard())
        onView(withId(R.id.tv_full_name)).check(matches(withText(STRING_TO_BE_TYPED)))

        onView(withId(R.id.iv_image)).check(matches(withDrawable(R.mipmap.ic_betty_allen)))
    }

    @Test
    fun send_sms_test() {
        val result = Instrumentation.ActivityResult(Activity.RESULT_OK, PhoneUtil.sms(PHONE_NUMBER))
        intending(toPackage(PACKAGE_ANDROID_DIALER)).respondWith(result)
        onView(withId(R.id.rv_info)).perform(RecyclerViewActions
                .actionOnItemAtPosition<ViewHolder>(0, object : ViewAction {
                    override fun getConstraints(): Matcher<View>? {
                        return null
                    }

                    override fun getDescription(): String {
                        return "Click on specific button"
                    }

                    override fun perform(uiController: UiController, view: View) {
                        val v = view.findViewById(R.id.iv_right_icon)
                        v.performClick()
                    }
                }))
    }


    @Test
    fun calling_test() {
        val result = Instrumentation.ActivityResult(Activity.RESULT_OK, PhoneUtil.call(PHONE_NUMBER))
        intending(toPackage(PACKAGE_ANDROID_DIALER)).respondWith(result)
        onView(withId(R.id.rv_info)).perform(RecyclerViewActions
                .actionOnItemAtPosition<ViewHolder>(0, object : ViewAction {
                    override fun getConstraints(): Matcher<View>? {
                        return null
                    }

                    override fun getDescription(): String {
                        return "Click on specific button"
                    }

                    override fun perform(uiController: UiController, view: View) {
                        val v = view.findViewById(R.id.iv_left_icon)
                        v.performClick()
                    }
                }))
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

    companion object {

        private val STRING_TO_BE_TYPED = "raditya gumay"
        private val PHONE_NUMBER = "1234567890"

        private var PACKAGE_ANDROID_DIALER = "com.android.phone"

        init {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                PACKAGE_ANDROID_DIALER = "com.android.server.telecom"
            }
        }

        fun withDrawable(resourceId: Int): Matcher<View> {
            return DrawableMatcher(resourceId)
        }

        fun noDrawable(): Matcher<View> {
            return DrawableMatcher(-1)
        }
    }
}
