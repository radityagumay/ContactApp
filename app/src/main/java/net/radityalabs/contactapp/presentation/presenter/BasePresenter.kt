package net.radityalabs.contactapp.presentation.presenter

import net.radityalabs.contactapp.presentation.view.BaseView

/**
 * Created by radityagumay on 4/11/17.
 */

interface BasePresenter<T : BaseView> {

    fun attachView(view: T)

    fun detachView()
}
