package net.radityalabs.contactapp.presentation.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

import net.radityalabs.contactapp.ContactApp

/**
 * Created by radityagumay on 4/11/17.
 */

object ConnectionUtil {

    val isWifiConnected: Boolean
        get() {
            val connectivityManager = ContactApp.instance.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).state
            return wifi == NetworkInfo.State.CONNECTED
        }

    val isMobileNetworkConnected: Boolean
        get() {
            val connectivityManager = ContactApp.instance.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).state
            return mobile == NetworkInfo.State.CONNECTED
        }

    val isNetworkConnected: Boolean
        get() {
            var netstate = false
            val connectivity = ContactApp.instance.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (connectivity != null) {
                val info = connectivity.allNetworkInfo
                if (info != null) {
                    for (anInfo in info) {
                        if (anInfo.state == NetworkInfo.State.CONNECTED) {
                            netstate = true
                            break
                        }
                    }
                }
            }
            return netstate
        }
}
