package com.pv.vampyrian.mokinkiszodzius.ui.wordedit;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.pv.vampyrian.mokinkiszodzius.room.Repository;
import com.pv.vampyrian.mokinkiszodzius.room.entityAndDao.WordEntity;

public class WordEditViewModel extends ViewModel {

    private Repository mRepository;

    private LiveData<WordEntity> mObservableWord;
//
//    private final long mWordId;

    public WordEditViewModel(Repository repository, long wordId) {
        mRepository = repository;

//        mWordId = wordId;
        mObservableWord = mRepository.getWordWithId(wordId);
    }

    public LiveData<WordEntity> getObservableWord () {
        return mObservableWord;
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

}
