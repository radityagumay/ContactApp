package net.radityalabs.contactapp.presentation.di.component;

import net.radityalabs.contactapp.presentation.di.module.ContactListModule;
import net.radityalabs.contactapp.presentation.ui.fragment.ContactListFragment;

import javax.inject.Singleton;

import dagger.Subcomponent;

/**
 * Created by radityagumay on 7/13/17.
 */

@Singleton
@Subcomponent(modules = ContactListModule.class)
public interface ContactListComponent {
    void inject(ContactListFragment contactListFragment);
}
