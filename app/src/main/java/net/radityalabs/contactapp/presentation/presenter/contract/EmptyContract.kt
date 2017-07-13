package net.radityalabs.contactapp.presentation.presenter.contract

import net.radityalabs.contactapp.presentation.presenter.BasePresenter
import net.radityalabs.contactapp.presentation.view.BaseView

/**
 * Created by radityagumay on 4/12/17.
 */

interface EmptyContract {

    interface View : BaseView

    interface Presenter : BasePresenter<View>
}
