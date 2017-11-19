package com.pv.vampyrian.mokinkiszodzius.ui.wordedit;

import android.arch.lifecycle.Observer;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pv.vampyrian.mokinkiszodzius.R;
import com.pv.vampyrian.mokinkiszodzius.databinding.WordEditDialogFragmentBinding;
import com.pv.vampyrian.mokinkiszodzius.room.entityAndDao.WordEntity;
import com.pv.vampyrian.mokinkiszodzius.ui.base.BaseDialog;

import java.util.Objects;

public class WordEditDialogFragment extends BaseDialog {

    private static final String LOG_TAG = WordEditDialogFragment.class.getSimpleName();
    private WordEditDialogFragmentBinding mBinding;
    private static final String LESSON_ID = "lesson_id";
    private static final String WORD_ID = "word_id";

    public static WordEditDialogFragment newInstance (long lessonId, long wordId) {
        WordEditDialogFragment dialogFragment = new WordEditDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putLong(LESSON_ID, lessonId);
        bundle.putLong(WORD_ID, wordId);
        dialogFragment.setArguments(bundle);
        return dialogFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.word_edit_dialog_fragment, container, false);
        mBinding.setCallback(this);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        sharedViewModel.getObservableWordWithId(getArguments().getLong(WORD_ID)).observe(this, new Observer<WordEntity>() {
            @Override
            public void onChanged(@Nullable WordEntity wordEntity) {
                mBinding.setWord(wordEntity);
            }
        });
    }


    //***********************Apdirbam UI paspaudimus
    public void saveWordClicked () {
        if (getArguments().getLong(WORD_ID) != -1) {
            WordEntity word = mBinding.getWord();
            word.setWord(mBinding.word.getText().toString());
            word.setTranslateWord(mBinding.translate.getText().toString());
            if (!Objects.equals(word.getTranslateWord(), "") &&  !(Objects.equals(word.getWord(), ""))) {
                sharedViewModel.updateWord(word);
            }
        } else {
            WordEntity newWord = new WordEntity();
            newWord.setTranslateWord(mBinding.translate.getText().toString());
            newWord.setWord(mBinding.word.getText().toString());
            newWord.setRating(0);
            newWord.setParentId(getArguments().getLong(LESSON_ID));
            if (!Objects.equals(newWord.getTranslateWord(), "") &&  !(Objects.equals(newWord.getWord(), ""))) {
                sharedViewModel.insertWord(newWord);
            }
        }
        dismiss();
    }

    public void deleteWordClicked () {
        if (getArguments().getLong(WORD_ID) != -1) {
            WordEntity word = mBinding.getWord();
            sharedViewModel.deteleWord(word);
        }
        dismiss();
    }

}
