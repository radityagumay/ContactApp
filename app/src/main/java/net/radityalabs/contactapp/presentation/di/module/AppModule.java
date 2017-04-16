package net.radityalabs.contactapp.presentation.di.module;

import net.radityalabs.contactapp.ContactApp;
import net.radityalabs.contactapp.data.network.RestService;
import net.radityalabs.contactapp.data.network.RetrofitHelper;
import net.radityalabs.contactapp.data.realm.RealmHelper;
import net.radityalabs.contactapp.domain.usecase.AddContactUseCase;
import net.radityalabs.contactapp.domain.usecase.ContactDetailUseCase;
import net.radityalabs.contactapp.domain.usecase.ContactListUseCase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by radityagumay on 4/11/17.
 */

@Module
public class AppModule {
    private final ContactApp application;

    public AppModule(ContactApp application) {
        this.application = application;
    }

    @Provides
    @Singleton
    ContactApp provideApplicationContext() {
        return this.application;
    }

    @Provides
    @Singleton
    RetrofitHelper provideRetrofitHelper(RestService restService) {
        return new RetrofitHelper(restService);
    }

    @Provides
    @Singleton
    RealmHelper provideRealmHelper() {
        return new RealmHelper();
    }

    @Provides
    @Singleton
    ContactListUseCase provideContactListUseCase(RetrofitHelper retrofitHelper, RealmHelper realmHelper, ContactApp context) {
        return new ContactListUseCase(retrofitHelper, realmHelper, context.getApplicationContext());
    }

    @Provides
    @Singleton
    ContactDetailUseCase provideContactDetailUseCase(RetrofitHelper retrofitHelper, RealmHelper realmHelper, ContactApp context) {
        return new ContactDetailUseCase(retrofitHelper, realmHelper, context.getApplicationContext());
    }

    @Provides
    @Singleton
    AddContactUseCase provideAddContactUseCase(RetrofitHelper retrofitHelper, ContactApp context) {
        return new AddContactUseCase(retrofitHelper, context.getApplicationContext());
    }
}
