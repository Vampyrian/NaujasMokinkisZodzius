package com.pv.vampyrian.mokinkiszodzius.ui.lerningwords;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.pv.vampyrian.mokinkiszodzius.room.Repository;
import com.pv.vampyrian.mokinkiszodzius.room.entityAndDao.WordEntity;

import java.util.List;

public class LerningWordViewModel extends ViewModel {

    private LiveData<List<WordEntity>> mObservableWord;

    //private Repository mRepository;

    public LerningWordViewModel(Repository repository) {
        //mRepository = repository;
        mObservableWord = repository.getAllWordsFromSelectedLesson();
    }

    public LiveData<List<WordEntity>> getObservableWord (){
        return mObservableWord;
    }
}
