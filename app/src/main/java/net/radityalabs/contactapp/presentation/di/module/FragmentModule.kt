package net.radityalabs.contactapp.presentation.di.module

import android.app.Activity
import android.support.v4.app.Fragment

import net.radityalabs.contactapp.presentation.di.scope.FragmentScope

import dagger.Module
import dagger.Provides

/**
 * Created by radityagumay on 4/11/17.
 */

@Module
class FragmentModule(private val fragment: Fragment) {

    @Provides
    @FragmentScope
    fun provideActivity(): Activity {
        return fragment.activity
    }
}
