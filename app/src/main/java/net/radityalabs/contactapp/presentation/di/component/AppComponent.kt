package net.radityalabs.contactapp.presentation.di.component

import net.radityalabs.contactapp.ContactApp
import net.radityalabs.contactapp.data.network.RetrofitHelper
import net.radityalabs.contactapp.data.realm.RealmHelper
import net.radityalabs.contactapp.domain.usecase.AddContactUseCase
import net.radityalabs.contactapp.domain.usecase.ContactDetailUseCase
import net.radityalabs.contactapp.domain.usecase.ContactListUseCase
import net.radityalabs.contactapp.presentation.di.module.AppModule
import net.radityalabs.contactapp.presentation.di.module.HttpModule

import javax.inject.Singleton

import dagger.Component

/**
 * Created by radityagumay on 4/11/17.
 */

@Singleton
@Component(modules = arrayOf(AppModule::class, HttpModule::class))
interface AppComponent {

    val context: ContactApp

    fun retrofitHelper(): RetrofitHelper

    fun realmHelper(): RealmHelper

    fun contactDetailUseCase(): ContactDetailUseCase

    fun addContactUseCase(): AddContactUseCase
}
