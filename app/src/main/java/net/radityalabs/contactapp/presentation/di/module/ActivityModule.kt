package net.radityalabs.contactapp.presentation.di.module

import android.app.Activity

import net.radityalabs.contactapp.presentation.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

/**
 * Created by radityagumay on 4/11/17.
 */

@Module
class ActivityModule(private val mActivity: Activity) {

    @Provides
    @ActivityScope
    fun provideActivity(): Activity {
        return mActivity
    }
}
