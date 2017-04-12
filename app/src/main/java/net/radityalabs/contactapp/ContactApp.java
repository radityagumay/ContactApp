package net.radityalabs.contactapp;

import android.app.Activity;
import android.app.Application;

import net.radityalabs.contactapp.presentation.di.component.AppComponent;
import net.radityalabs.contactapp.presentation.di.component.DaggerAppComponent;
import net.radityalabs.contactapp.presentation.di.module.AppModule;
import net.radityalabs.contactapp.presentation.di.module.HttpModule;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by radityagumay on 4/11/17.
 */

public class ContactApp extends Application {

    private static ContactApp sInstance;
    private static AppComponent sAppComponent;

    private Set<Activity> allActivities;

    public static synchronized ContactApp getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static AppComponent getAppComponent() {
        if (sAppComponent == null) {
            sAppComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(sInstance))
                    .httpModule(new HttpModule())
                    .build();
        }
        return sAppComponent;
    }

    public void addActivity(Activity act) {
        if (allActivities == null) {
            allActivities = new HashSet<>();
        }
        allActivities.add(act);
    }

    public void removeActivity(Activity act) {
        if (allActivities != null) {
            allActivities.remove(act);
        }
    }

    public void exitApp() {
        if (allActivities != null) {
            synchronized (allActivities) {
                for (Activity act : allActivities) {
                    act.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
}
