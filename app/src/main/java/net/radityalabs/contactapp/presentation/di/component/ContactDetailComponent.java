package net.radityalabs.contactapp.presentation.di.component;

import net.radityalabs.contactapp.presentation.di.module.ContactDetailModule;
import net.radityalabs.contactapp.presentation.ui.fragment.ContactDetailFragment;

import javax.inject.Singleton;

import dagger.Subcomponent;

/**
 * Created by radityagumay on 7/14/17.
 */

@Singleton
@Subcomponent(modules = ContactDetailModule.class)
public interface ContactDetailComponent {
    void inject(ContactDetailFragment fragment);
}
