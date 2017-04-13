package net.radityalabs.contactapp.presentation.di.module;

import android.app.Activity;
import android.support.v4.app.Fragment;

import net.radityalabs.contactapp.domain.usecase.ContactListFragmentUseCase;
import net.radityalabs.contactapp.presentation.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by radityagumay on 4/11/17.
 */

@Module
public class FragmentModule {

    private Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @FragmentScope
    public Activity provideActivity() {
        return fragment.getActivity();
    }
}
