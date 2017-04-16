package net.radityalabs.contactapp.presentation.presenter;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import net.radityalabs.contactapp.presentation.presenter.contract.AddContactContract;
import net.radityalabs.contactapp.presentation.rx.RxPresenter;

import javax.inject.Inject;

/**
 * Created by radityagumay on 4/16/17.
 */

public class AddContactPresenter extends RxPresenter<AddContactContract.View> implements
        AddContactContract.Presenter {

    @Inject
    public AddContactPresenter() {

    }

    public void composeOnClick(View view) {
    }

    public TextWatcher composetWatcherListerner(final int id) {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }
}
