package net.radityalabs.contactapp.presentation.factory;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by radityagumay on 4/13/17.
 */

public class ToastFactory {

    public static void show(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
