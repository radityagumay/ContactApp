package net.radityalabs.contactapp.presentation.listener

/**
 * Created by radityagumay on 4/17/17.
 */

interface Callback<T> {

    fun onSuccess(success: T)

    fun onFailure(throwable: Throwable)
}
