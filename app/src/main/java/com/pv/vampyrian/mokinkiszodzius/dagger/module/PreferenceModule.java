package com.pv.vampyrian.mokinkiszodzius.dagger.module;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PreferenceModule {

    @Provides
    @Singleton
    SharedPreferences provideSharedPreference (Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }
}
