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
        NetworkInfo.State wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
        return wifi == NetworkInfo.State.CONNECTED;
    }

    public static boolean isMobileNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) ContactApp.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo.State mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
        return mobile == NetworkInfo.State.CONNECTED;
    }

    public static boolean isNetworkConnected() {
        boolean netstate = false;
        ConnectivityManager connectivity = (ConnectivityManager) ContactApp.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (NetworkInfo anInfo : info) {
                    if (anInfo.getState() == NetworkInfo.State.CONNECTED) {
                        netstate = true;
                        break;
                    }
                }
            }
        }
        return netstate;
    }
}
