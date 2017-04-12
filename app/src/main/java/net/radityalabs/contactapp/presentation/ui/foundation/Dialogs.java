package net.radityalabs.contactapp.presentation.ui.foundation;

/**
 * Created by radityagumay on 4/12/17.
 */

public interface Dialogs {
    void showLoading();

    void showLoading(String message);

    void showNoCancelableLoading();

    void showNoCancelableLoading(String message);

    void hideLoading();
}
