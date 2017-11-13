package com.pv.vampyrian.mokinkiszodzius.ui.wordedit;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.pv.vampyrian.mokinkiszodzius.room.Repository;

public class WordEditViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final Repository mRepository;
    private final long mWordId;

    public WordEditViewModelFactory (Repository repository, long wordId) {
        mRepository = repository;
        mWordId = wordId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new WordEditViewModel(mRepository, mWordId);
    }
}
