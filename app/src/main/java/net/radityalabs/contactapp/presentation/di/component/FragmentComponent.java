package net.radityalabs.contactapp.presentation.di.component;

import android.app.Activity;

import net.radityalabs.contactapp.presentation.di.module.FragmentModule;
import net.radityalabs.contactapp.presentation.di.scope.FragmentScope;
import net.radityalabs.contactapp.presentation.factory.DialogFactory;
import net.radityalabs.contactapp.presentation.rx.transformation.Transformation;
import net.radityalabs.contactapp.presentation.ui.foundation.Dialogs;
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
