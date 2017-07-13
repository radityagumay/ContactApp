package net.radityalabs.contactapp.test

import android.support.test.espresso.UiController
import android.view.View
import android.widget.TextView

import org.hamcrest.Matcher

import android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import org.hamcrest.CoreMatchers.allOf

/**
 * Created by radityagumay on 4/19/17.
 */

object ViewAction {

    fun setTextInTextView(value: String): android.support.test.espresso.ViewAction {
        return object : android.support.test.espresso.ViewAction {
            override fun getConstraints(): Matcher<View> {
                return allOf(isDisplayed(), isAssignableFrom(TextView::class.java!!))
            }

            override fun perform(uiController: UiController, view: View) {
                (view as TextView).text = value
            }

            override fun getDescription(): String {
                return "replace text"
            }
        }
    }
}
