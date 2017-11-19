package com.pv.vampyrian.mokinkiszodzius.ui.wordedit;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.Observer;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pv.vampyrian.mokinkiszodzius.R;
import com.pv.vampyrian.mokinkiszodzius.databinding.WordEditFragmentBinding;
import com.pv.vampyrian.mokinkiszodzius.room.entityAndDao.WordEntity;
import com.pv.vampyrian.mokinkiszodzius.ui.MainActivity;
import com.pv.vampyrian.mokinkiszodzius.ui.base.BaseFragment;

import java.util.Objects;

public class WordEditFragment extends BaseFragment {

    private static final String LOG_TAG = WordEditFragment.class.getSimpleName();
    private static final String LESSON_ID = "lesson_id";
    private static final String WORD_ID = "word_id";

    private WordEditFragmentBinding mBinging;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mBinging = DataBindingUtil.inflate(inflater, R.layout.word_edit_fragment, container, false);
        mBinging.setWordEditFragment(this);
        return mBinging.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        sharedViewModel.getObservableWordWithId(getArguments().getLong(WORD_ID)).observe(this, new Observer<WordEntity>() {
            @Override
            public void onChanged(@Nullable WordEntity wordEntity) {
                mBinging.setWord(wordEntity);
            }
        });
    }

    //***********************Apdirbam UI paspaudimus
    public void saveWordClicked (View view) {
        if (getArguments().getLong(WORD_ID) != -1) {
            WordEntity word = mBinging.getWord();
            word.setWord(mBinging.word.getText().toString());
            word.setTranslateWord(mBinging.translate.getText().toString());
            if (!Objects.equals(word.getTranslateWord(), "") &&  !(Objects.equals(word.getWord(), ""))) {
                sharedViewModel.updateWord(word);
            }
        } else {
            WordEntity newWord = new WordEntity();
            newWord.setTranslateWord(mBinging.translate.getText().toString());
            newWord.setWord(mBinging.word.getText().toString());
            newWord.setRating(0);
            newWord.setParentId(getArguments().getLong(LESSON_ID));
            if (!Objects.equals(newWord.getTranslateWord(), "") &&  !(Objects.equals(newWord.getWord(), ""))) {
                sharedViewModel.insertWord(newWord);
            }
        }
        showWordListFragment();
    }

    public void deleteWordClicked (View view) {
        if (getArguments().getLong(WORD_ID) != -1) {
            WordEntity word = mBinging.getWord();
            sharedViewModel.deteleWord(word);
        }
        showWordListFragment();
    }

    public void deleteWordClicked () {
        if (getArguments().getLong(WORD_ID) != -1) {
            WordEntity word = mBinging.getWord();
            sharedViewModel.deteleWord(word);
        }
        showWordListFragment();
    }

    private void showWordListFragment() {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            ((MainActivity) getActivity()).showWordsListFragment(getArguments().getLong(LESSON_ID));
        }
    }

    public static WordEditFragment createWordEditFragmendWithLessonIdAndWordId (long lessonId, long wordId) {
        WordEditFragment fragment = new WordEditFragment();
        Bundle bundle = new Bundle();
        bundle.putLong(LESSON_ID, lessonId);
        bundle.putLong(WORD_ID, wordId);
        fragment.setArguments(bundle);
        return fragment;
    }
}
