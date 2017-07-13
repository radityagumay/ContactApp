package net.radityalabs.contactapp.presentation.di.module;

import net.radityalabs.contactapp.ContactApp;
import net.radityalabs.contactapp.data.network.RetrofitHelper;
import net.radityalabs.contactapp.data.realm.RealmHelper;
import net.radityalabs.contactapp.domain.usecase.ContactDetailUseCase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by radityagumay on 7/14/17.
 */

@Module
public class ContactDetailModule {
    @Provides
    @Singleton
    ContactDetailUseCase provideContactDetailUseCase(RetrofitHelper retrofitHelper, RealmHelper realmHelper, ContactApp context) {
        return new ContactDetailUseCase(retrofitHelper, realmHelper, context.getApplicationContext());
    }
}
