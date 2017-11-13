package com.pv.vampyrian.mokinkiszodzius.ui.lerningwords;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.pv.vampyrian.mokinkiszodzius.room.Repository;

public class LerningWordViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private final Repository mRepository;

    public LerningWordViewModelFactory (Repository repository) {
        mRepository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new LerningWordViewModel(mRepository);
    }
}
