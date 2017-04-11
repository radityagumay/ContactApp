package net.radityalabs.contactapp;

import android.app.Application;

import net.radityalabs.contactapp.presentation.di.component.AppComponent;
import net.radityalabs.contactapp.presentation.di.component.DaggerAppComponent;
import net.radityalabs.contactapp.presentation.di.module.AppModule;
import net.radityalabs.contactapp.presentation.di.module.HttpModule;

/**
 * Created by radityagumay on 4/11/17.
 */

public class ContactApp extends Application {

    private static ContactApp sInstance;
    private static AppComponent sAppComponent;

    public static synchronized ContactApp getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static AppComponent getAppComponent(){
        if (sAppComponent == null) {
            sAppComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(sInstance))
                    .httpModule(new HttpModule())
                    .build();
        }
        return sAppComponent;
    }
}
