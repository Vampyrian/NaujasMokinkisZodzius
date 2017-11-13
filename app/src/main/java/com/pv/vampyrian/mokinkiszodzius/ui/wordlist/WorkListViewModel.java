package com.pv.vampyrian.mokinkiszodzius.ui.wordlist;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.pv.vampyrian.mokinkiszodzius.room.Repository;
import com.pv.vampyrian.mokinkiszodzius.room.entityAndDao.WordEntity;

import java.util.List;

public class WorkListViewModel extends ViewModel {

    private LiveData<List<WordEntity>> mObservableWord;

    private Repository mRepository;

    public WorkListViewModel(Repository repository, long lessonId) {
        mRepository = repository;
        mObservableWord = mRepository.getWordWithLessonId(lessonId);
    }

    public LiveData<List<WordEntity>> getObservableWord (){
        return mObservableWord;
    }
}
