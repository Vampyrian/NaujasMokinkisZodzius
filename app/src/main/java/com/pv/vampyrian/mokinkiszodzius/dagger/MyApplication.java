package com.pv.vampyrian.mokinkiszodzius.dagger;

import android.app.Application;

import com.pv.vampyrian.mokinkiszodzius.dagger.module.AppModule;

public class MyApplication extends Application {

    private static AppComponent component;

    public static AppComponent getComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        make();
    }

    private void make() {
        component = DaggerAppComponent.builder()
                .appModule(new AppModule(this)).build();

    }

}
