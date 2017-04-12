package net.radityalabs.contactapp.presentation.ui.foundation;

import android.app.ProgressDialog;

import net.radityalabs.contactapp.ContactApp;
import net.radityalabs.contactapp.presentation.factory.DialogFactory;

import javax.inject.Inject;

/**
 * Created by radityagumay on 4/12/17.
 */

public class DialogsBehaviour implements Dialogs {

    private ContactApp application;
    private DialogFactory dialog;
    private ProgressDialog progressDialog;

    @Inject
    public DialogsBehaviour(ContactApp application, DialogFactory dialog) {
        this.application = application;
        this.dialog = dialog;
    }

    @Override
    public void showLoading() {
        DialogFactory.showNotCancelableProgress(application.getApplicationContext());
    }

    @Override
    public void showLoading(String message) {
        DialogFactory.showNotCancelableProgress(application.getApplicationContext());
    }

    @Override
    public void showNoCancelableLoading() {
        DialogFactory.showNotCancelableProgress(application.getApplicationContext());
    }

    @Override
    public void showNoCancelableLoading(String message) {
        DialogFactory.showNotCancelableProgress(application.getApplicationContext());
    }

    @Override
    public void hideLoading() {
        DialogFactory.dismissProgress();
    }
}
