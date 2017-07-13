package net.radityalabs.contactapp.presentation.di.module

import net.radityalabs.contactapp.ContactApp
import net.radityalabs.contactapp.data.network.RetrofitHelper
import net.radityalabs.contactapp.data.realm.RealmHelper
import net.radityalabs.contactapp.domain.usecase.ContactListUseCase

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

/**
 * Created by radityagumay on 4/11/17.
 */

@Module
class ContactListModule {

    @Provides
    @Singleton
    internal fun provideContactListUseCase(retrofitHelper: RetrofitHelper, realmHelper: RealmHelper, context: ContactApp): ContactListUseCase {
        return ContactListUseCase(retrofitHelper, realmHelper, context.applicationContext)
    }
}
