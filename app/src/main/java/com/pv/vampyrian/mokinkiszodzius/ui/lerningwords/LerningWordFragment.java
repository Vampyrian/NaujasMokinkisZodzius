package com.pv.vampyrian.mokinkiszodzius.ui.lerningwords;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.pv.vampyrian.mokinkiszodzius.R;
import com.pv.vampyrian.mokinkiszodzius.databinding.LerningWordFragmentBinding;
import com.pv.vampyrian.mokinkiszodzius.room.entityAndDao.WordEntity;
import com.pv.vampyrian.mokinkiszodzius.util.InjectorUtils;
import com.pv.vampyrian.mokinkiszodzius.util.WordUtil;

import java.util.ArrayList;
import java.util.List;

public class LerningWordFragment extends Fragment {

    private LerningWordFragmentBinding mBinding;

    private LerningWordViewModel mLerningWordViewModel;
    private List<WordEntity> mWordList = new ArrayList<>();
    private int mWordId = 0;
    private boolean mRandom;
    private boolean mFirstShowTranslate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.lerning_word_fragment, container, false);
        mBinding.setLerningWordFragment(this);

        return mBinding.getRoot();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        LerningWordViewModelFactory factory = InjectorUtils.provideLerningWordViewModelFactory(getContext());
        mLerningWordViewModel = ViewModelProviders.of(this, factory).get(LerningWordViewModel.class);
        mLerningWordViewModel.getObservableWord().observe(this, new Observer<List<WordEntity>>() {
            @Override
            public void onChanged(@Nullable List<WordEntity> wordEntities) {
                if (wordEntities.size()>0) {
                    mWordList = wordEntities;
                } else {
                    Toast.makeText(getContext(), R.string.toast_no_lesson_sellected, Toast.LENGTH_LONG).show();
                }
                showNextWord();
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
        int wordId = WordUtil.getNext(mWordId, mWordList.size(), mRandom);
        mWordId = wordId;
        if (mWordList.size()>0) {
            mBinding.setWord(mWordList.get(mWordId));
        }
    }

    //*******************************UI paspaudimu apdirbimas
    public void showNexWord(View view) {
        showNextWord();
    }

}
