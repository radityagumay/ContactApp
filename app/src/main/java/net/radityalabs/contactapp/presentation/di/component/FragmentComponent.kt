package net.radityalabs.contactapp.presentation.di.component

import android.app.Activity

import net.radityalabs.contactapp.presentation.di.module.ContactDetailModule
import net.radityalabs.contactapp.presentation.di.module.ContactListModule
import net.radityalabs.contactapp.presentation.di.module.FragmentModule
import net.radityalabs.contactapp.presentation.di.scope.FragmentScope
import net.radityalabs.contactapp.presentation.presenter.contract.ContactDetailContract
import net.radityalabs.contactapp.presentation.ui.fragment.AddContactFragment
import net.radityalabs.contactapp.presentation.ui.fragment.ContactDetailFragment
import net.radityalabs.contactapp.presentation.ui.fragment.ContactListFragment

import dagger.Component

/**
 * Created by radityagumay on 4/11/17.
 */

@FragmentScope
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(FragmentModule::class))
interface FragmentComponent {

    val activity: Activity

    operator fun plus(module: ContactListModule): ContactListComponent

    operator fun plus(module: ContactDetailModule): ContactDetailComponent

    fun inject(addContactFragment: AddContactFragment)
}
