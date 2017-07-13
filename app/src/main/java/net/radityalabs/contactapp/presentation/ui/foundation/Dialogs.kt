package net.radityalabs.contactapp.presentation.ui.foundation

/**
 * Created by radityagumay on 4/12/17.
 */

interface Dialogs {
    fun showLoading()

    fun showLoading(message: String)

    fun showNoCancelableLoading()

    fun showNoCancelableLoading(message: String)

    fun hideLoading()
}
