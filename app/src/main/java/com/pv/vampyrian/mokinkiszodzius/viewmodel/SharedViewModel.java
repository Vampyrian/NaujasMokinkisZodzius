package com.pv.vampyrian.mokinkiszodzius.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.pv.vampyrian.mokinkiszodzius.room.Repository;
import com.pv.vampyrian.mokinkiszodzius.room.entityAndDao.LessonEntity;
import com.pv.vampyrian.mokinkiszodzius.room.entityAndDao.WordEntity;

import java.util.List;

public class SharedViewModel extends ViewModel {

    private static final String LOG_TAG = SharedViewModel.class.getSimpleName();
    private Repository mRepository;
    private LiveData<List<LessonEntity>> mAllObservableLesson;
    private LiveData<LessonEntity> mLesson;
    private LiveData<List<WordEntity>> mAllWordFromLessonWithId;
    private LiveData<WordEntity> mWord;
    private LiveData<List<WordEntity>> mAllWordFromSelectedLesson;

    public SharedViewModel (Repository repository) {
        Log.d(LOG_TAG, "Sukuriam SharedViewModel");
        mRepository = repository;
    }





    public LiveData<List<LessonEntity>> getAllObservableLesson () {
        mAllObservableLesson = mRepository.getAllLessonList();
        return mAllObservableLesson;
    }

    public int updateLesson (LessonEntity lesson) {
        return mRepository.updateLesson(lesson);
    }

    public LiveData<LessonEntity> getObservableLessonWithId (long id) {
        mLesson = mRepository.getLessonWithId(id);
        return mLesson;
    }

    public long insertLesson (LessonEntity lesson) {
        return mRepository.insertLesson(lesson);
    }

    public int deleteLesson (LessonEntity lesson) {
        return mRepository.deleteLesson(lesson);
    }

    public LiveData<List<WordEntity>> getmAllWordFromLessonWithId (long id) {
        mAllWordFromLessonWithId = mRepository.getWordWithLessonId(id);
        return mAllWordFromLessonWithId;
    }

    public LiveData<WordEntity> getObservableWordWithId(long id) {
        mWord = mRepository.getWordWithId(id);
        return mWord;
    }

    public long insertWord (WordEntity word) {
        return mRepository.insertWord(word);
    }

    public int updateWord (WordEntity word) {
        return mRepository.updateWord(word);
    }

    public int deteleWord (WordEntity word) {
        return mRepository.deleteWord(word);
    }

    public LiveData<List<WordEntity>> getAllWordFromSelectedLesson () {
        mAllWordFromSelectedLesson = mRepository.getAllWordsFromSelectedLesson();
        return mRepository.getAllWordsFromSelectedLesson();
    }

}
