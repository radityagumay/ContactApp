package net.radityalabs.contactapp.presentation.factory;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by radityagumay on 4/13/17.
 */

public class SnackbarFactory {

    public static void show(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
    }
}
