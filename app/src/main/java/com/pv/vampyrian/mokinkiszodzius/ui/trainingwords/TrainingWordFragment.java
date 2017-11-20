package com.pv.vampyrian.mokinkiszodzius.ui.trainingwords;

import android.arch.lifecycle.Observer;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pv.vampyrian.mokinkiszodzius.R;
import com.pv.vampyrian.mokinkiszodzius.databinding.TrainingWordFragmentBinding;
import com.pv.vampyrian.mokinkiszodzius.room.entityAndDao.WordEntity;
import com.pv.vampyrian.mokinkiszodzius.ui.base.BaseFragment;
import com.pv.vampyrian.mokinkiszodzius.util.WordUtil;

import java.util.ArrayList;
import java.util.List;


public class TrainingWordFragment extends BaseFragment {

    private static int ADD_WHEN_KNOW = 10;
    private static int SUNBSTACT_WHEN_DONT_KNOW = 5;

    private TrainingWordFragmentBinding mBinding;
    private List<WordEntity> mWordList = new ArrayList<>();
    private int mWordId = 0;
    private boolean mRandom;
    private boolean mFirstShowTranslate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.training_word_fragment, container, false);
        mBinding.setTrainingWordFragment(this);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        sharedViewModel.getAllWordFromSelectedLesson().observe(this, new Observer<List<WordEntity>>() {
            @Override
            public void onChanged(@Nullable List<WordEntity> wordEntities) {
                if (wordEntities.size()>0) {
                    mBinding.setEmptyTrainingList(false);
                    mWordList = wordEntities;
                    if(mBinding.getWord() == null) {
                        showNextWord();
                    }
                } else {
                    mBinding.setEmptyTrainingList(true);
                }
            }
        });
        setPreference();
    }

    private void setPreference() {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getContext());
        mRandom = pref.getBoolean("random_on", false);
        mFirstShowTranslate = pref.getBoolean("first_translate_on", false);
    }

    private void showNextWord () {
        mBinding.setShowTranslate(false);
        int wordId = WordUtil.getNext(mWordId, mWordList.size(), mRandom);
        mWordId = wordId;

        if (mWordList.size()>0) {
            WordEntity newWord = mWordList.get(mWordId);
            if (mFirstShowTranslate) {
                String translate = newWord.getTranslateWord();
                String wordString = newWord.getWord();
                newWord.setTranslateWord(wordString);
                newWord.setWord(translate);
            }
            mBinding.setWord(newWord);
        }
    }

    //**************************UI paspaudimu apdirbimas
    public void showTranslate(View view) {
        mBinding.setShowTranslate(true);
    }

    public void onKnowPressed(View view) {
        if (!mBinding.getShowTranslate()) {
            mBinding.setShowTranslate(true);
        } else {
            if (mWordList.size()>0) {
                WordEntity word = mWordList.get(mWordId);
                if (word != null) {
                    int oldRating = word.getRating();
                    word.setRating(oldRating + ADD_WHEN_KNOW > 100 ? 100 : oldRating + ADD_WHEN_KNOW);
                    sharedViewModel.updateWord(word);
                }
                showNextWord();
            }
        }
    }

    public void onDontKnowPressed(View view) {
        if (!mBinding.getShowTranslate()) {
            mBinding.setShowTranslate(true);
        } else {
            if (mWordList.size()>0) {
                WordEntity word = mWordList.get(mWordId);
                if (word != null) {
                    int oldRating = word.getRating();
                    word.setRating(oldRating - SUNBSTACT_WHEN_DONT_KNOW < 0 ? 0 : oldRating - SUNBSTACT_WHEN_DONT_KNOW);
                    sharedViewModel.updateWord(word);
                }
                showNextWord();
            }
        }
    }
}