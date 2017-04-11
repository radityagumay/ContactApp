package net.radityalabs.contactapp.presentation.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import net.radityalabs.contactapp.ContactApp;

/**
 * Created by radityagumay on 4/11/17.
 */

public class ConnectionUtil {

    public static boolean isWifiConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) ContactApp.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return wifiInfo != null;
    }

    public static boolean isMobileNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) ContactApp.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobileNetworkInfo = connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        return mobileNetworkInfo != null;
    }

    public static boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) ContactApp.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null;
    }
}
