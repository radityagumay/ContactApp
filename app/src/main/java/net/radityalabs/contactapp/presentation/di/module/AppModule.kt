package net.radityalabs.contactapp.presentation.di.module

import net.radityalabs.contactapp.ContactApp
import net.radityalabs.contactapp.data.network.RestService
import net.radityalabs.contactapp.data.network.RetrofitHelper
import net.radityalabs.contactapp.data.realm.RealmHelper
import net.radityalabs.contactapp.domain.usecase.AddContactUseCase
import net.radityalabs.contactapp.domain.usecase.ContactDetailUseCase
import net.radityalabs.contactapp.domain.usecase.ContactListUseCase

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

/**
 * Created by radityagumay on 4/11/17.
 */

@Module
class AppModule(private val application: ContactApp) {

    @Provides
    @Singleton
    internal fun provideApplicationContext(): ContactApp {
        return this.application
    }

    @Provides
    @Singleton
    internal fun provideRetrofitHelper(restService: RestService): RetrofitHelper {
        return RetrofitHelper(restService)
    }

    @Provides
    @Singleton
    internal fun provideRealmHelper(): RealmHelper {
        return RealmHelper()
    }

    @Provides
    @Singleton
    internal fun provideAddContactUseCase(retrofitHelper: RetrofitHelper, context: ContactApp): AddContactUseCase {
        return AddContactUseCase(retrofitHelper, context.applicationContext)
    }
}
