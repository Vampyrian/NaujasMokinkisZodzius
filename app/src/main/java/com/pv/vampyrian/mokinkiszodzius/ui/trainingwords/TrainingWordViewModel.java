package com.pv.vampyrian.mokinkiszodzius.ui.trainingwords;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.pv.vampyrian.mokinkiszodzius.room.Repository;
import com.pv.vampyrian.mokinkiszodzius.room.entityAndDao.WordEntity;

import java.util.List;

public class TrainingWordViewModel extends ViewModel {

    private LiveData<List<WordEntity>> mObservableWord;

    private Repository mRepository;

    public TrainingWordViewModel(Repository repository) {
        mRepository = repository;
        mObservableWord = mRepository.getAllWordsFromSelectedLesson();
    }

    public LiveData<List<WordEntity>> getObservableWord (){
        return mObservableWord;
    }

    public int updateWord (WordEntity word) {
        return mRepository.updateWord(word);
    }
}
