package net.radityalabs.contactapp.presentation.di.component;

import android.app.Activity;

import net.radityalabs.contactapp.presentation.di.module.ActivityModule;
import net.radityalabs.contactapp.presentation.di.scope.ActivityScope;
import net.radityalabs.contactapp.presentation.ui.activity.ContactListActivity;

import dagger.Component;

/**
 * Created by radityagumay on 4/11/17.
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();

    void inject(ContactListActivity contactListActivity);
}
