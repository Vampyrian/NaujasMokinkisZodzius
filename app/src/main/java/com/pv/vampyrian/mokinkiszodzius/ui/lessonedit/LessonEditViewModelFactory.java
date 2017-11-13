package com.pv.vampyrian.mokinkiszodzius.ui.lessonedit;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.pv.vampyrian.mokinkiszodzius.room.Repository;

public class LessonEditViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final Repository mRepository;
    private final long mLessonId;

    public LessonEditViewModelFactory (Repository repository, long lessonId) {
        mRepository = repository;
        mLessonId = lessonId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new LessonEditViewModel(mRepository, mLessonId);
    }
}
