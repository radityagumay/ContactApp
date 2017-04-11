package net.radityalabs.contactapp.presentation.presenter;

import net.radityalabs.contactapp.presentation.view.BaseView;

/**
 * Created by radityagumay on 4/11/17.
 */

public interface BasePresenter<T extends BaseView>{

    void attachView(T view);

    void detachView();
}
