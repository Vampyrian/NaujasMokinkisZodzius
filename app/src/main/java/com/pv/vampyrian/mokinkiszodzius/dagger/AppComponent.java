package com.pv.vampyrian.mokinkiszodzius.dagger;

import com.pv.vampyrian.mokinkiszodzius.dagger.module.AppModule;
import com.pv.vampyrian.mokinkiszodzius.dagger.module.PreferenceModule;
import com.pv.vampyrian.mokinkiszodzius.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppModule.class, PreferenceModule.class})
@Singleton
public interface AppComponent {

    void inject(MainActivity mainActivity);

}
