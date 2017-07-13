package net.radityalabs.contactapp

import android.app.Activity
import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex

import net.radityalabs.contactapp.presentation.di.component.AppComponent
import net.radityalabs.contactapp.presentation.di.component.DaggerAppComponent
import net.radityalabs.contactapp.presentation.di.module.AppModule
import net.radityalabs.contactapp.presentation.di.module.HttpModule

import java.util.HashSet

import io.realm.Realm

/**
 * Created by radityagumay on 4/11/17.
 */

class ContactApp : Application() {
    companion object {
        lateinit var instance: ContactApp

        private var sAppComponent: AppComponent? = null
        val appComponent: AppComponent
            get() {
                if (sAppComponent == null) {
                    sAppComponent = DaggerAppComponent.builder()
                            .appModule(AppModule(instance))
                            .httpModule(HttpModule())
                            .build()
                }
                return sAppComponent as AppComponent
            }
    }

    private var allActivities: MutableSet<Activity> = mutableSetOf()

    override fun onCreate() {
        super.onCreate()
        instance = this

        setupRealm()
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    private fun setupRealm() {
        Realm.init(this)
    }

    fun addActivity(act: Activity) {
        allActivities.add(act)
    }

    fun removeActivity(act: Activity) {
        if (allActivities.size > 0) {
            allActivities.remove(act)
        }
    }

    fun exitApp() {
        if (allActivities.size > 0) {
            synchronized(allActivities) {
                for (act in allActivities) {
                    act.finish()
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid())
        System.exit(0)
    }
}
