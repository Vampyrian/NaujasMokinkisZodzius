package com.pv.vampyrian.mokinkiszodzius.ui.lessonlist;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.pv.vampyrian.mokinkiszodzius.room.Repository;
import com.pv.vampyrian.mokinkiszodzius.room.entityAndDao.LessonEntity;

import java.util.List;

public class LessonListViewModel extends ViewModel {

    private LiveData<List<LessonEntity>> mObservableLesson;

    private Repository mRepository;

    public LessonListViewModel(Repository repository) {

        mRepository = repository;

        mObservableLesson = mRepository.getAllLessonList();
    }

    public LiveData<List<LessonEntity>> getObservableLesson () {
        return mObservableLesson;
    }

    public int updateLesson (LessonEntity lesson) {
        return mRepository.updateLesson(lesson);
    }

}
