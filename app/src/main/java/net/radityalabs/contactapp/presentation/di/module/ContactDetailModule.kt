package net.radityalabs.contactapp.presentation.di.module

import net.radityalabs.contactapp.ContactApp
import net.radityalabs.contactapp.data.network.RetrofitHelper
import net.radityalabs.contactapp.data.realm.RealmHelper
import net.radityalabs.contactapp.domain.usecase.ContactDetailUseCase

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

/**
 * Created by radityagumay on 7/14/17.
 */

@Module
class ContactDetailModule {
    @Provides
    @Singleton
    internal fun provideContactDetailUseCase(retrofitHelper: RetrofitHelper, realmHelper: RealmHelper, context: ContactApp): ContactDetailUseCase {
        return ContactDetailUseCase(retrofitHelper, realmHelper, context.applicationContext)
    }
}
