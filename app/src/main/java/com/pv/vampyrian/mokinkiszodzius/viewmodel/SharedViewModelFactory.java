package com.pv.vampyrian.mokinkiszodzius.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.pv.vampyrian.mokinkiszodzius.room.Repository;

public class SharedViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final Repository mRepository;

    public SharedViewModelFactory(Repository repository) {
        mRepository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new SharedViewModel(mRepository);
    }
}
