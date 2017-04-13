package net.radityalabs.contactapp.presentation.di.component;

import android.app.Activity;

import net.radityalabs.contactapp.presentation.di.module.FragmentModule;
import net.radityalabs.contactapp.presentation.di.scope.FragmentScope;
import net.radityalabs.contactapp.presentation.ui.fragment.ContactListFragment;

import dagger.Component;

/**
 * Created by radityagumay on 4/11/17.
 */

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();

    void inject(ContactListFragment contactListFragment);
}
