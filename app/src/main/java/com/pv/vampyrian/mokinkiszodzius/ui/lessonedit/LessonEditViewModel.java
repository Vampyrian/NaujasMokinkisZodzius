package com.pv.vampyrian.mokinkiszodzius.ui.lessonedit;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.pv.vampyrian.mokinkiszodzius.room.Repository;
import com.pv.vampyrian.mokinkiszodzius.room.entityAndDao.LessonEntity;

public class LessonEditViewModel extends ViewModel {

    private Repository mRepository;

    private LiveData<LessonEntity> mLesson;

    public LessonEditViewModel(Repository repository, long lessonId) {

        mRepository = repository;

        mLesson = mRepository.getLessonWithId(lessonId);
    }

    public LiveData<LessonEntity> getObsorvableLesson () {
        return mLesson;
    }

    public long insertLesson (LessonEntity lesson) {
        return mRepository.insertLesson(lesson);
    }

    public int updateLesson (LessonEntity lesson) {
        return mRepository.updateLesson(lesson);
    }

    public int deleteLesson (LessonEntity lesson) {
        return mRepository.deleteLesson(lesson);
    }

}
