package com.pv.vampyrian.mokinkiszodzius.ui.trainingwords;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.pv.vampyrian.mokinkiszodzius.room.Repository;

public class TrainingWordViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final Repository mRepository;

    public TrainingWordViewModelFactory(Repository repository) {
        mRepository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new TrainingWordViewModel(mRepository);
    }
}
