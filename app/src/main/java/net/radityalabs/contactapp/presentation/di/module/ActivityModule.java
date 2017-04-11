package net.radityalabs.contactapp.presentation.di.module;

import android.app.Activity;

import net.radityalabs.contactapp.presentation.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by radityagumay on 4/11/17.
 */

@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityScope
    public Activity provideActivity() {
        return mActivity;
    }
}
