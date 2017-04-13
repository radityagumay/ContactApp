package net.radityalabs.contactapp.presentation.presenter;

import net.radityalabs.contactapp.presentation.presenter.contract.EmptyContract;
import net.radityalabs.contactapp.presentation.rx.RxPresenter;

import javax.inject.Inject;

/**
 * Created by radityagumay on 4/12/17.
 */

public class EmptyPresenter extends RxPresenter<EmptyContract.View> implements
        EmptyContract.Presenter {

    @Inject
    public EmptyPresenter() {
    }
}
