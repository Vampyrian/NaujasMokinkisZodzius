package com.pv.vampyrian.mokinkiszodzius.ui.wordlist;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.Observer;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pv.vampyrian.mokinkiszodzius.R;
import com.pv.vampyrian.mokinkiszodzius.databinding.WordListFragmentBinding;
import com.pv.vampyrian.mokinkiszodzius.room.entityAndDao.WordEntity;
import com.pv.vampyrian.mokinkiszodzius.ui.MainActivity;
import com.pv.vampyrian.mokinkiszodzius.ui.base.BaseFragment;

import java.util.List;


public class WordListFragment extends BaseFragment {

    private static final String LOG_TAG = WordListFragment.class.getSimpleName();
    private static final String LESSON_ID = "lesson_id";
    private WordListFragmentBinding mBinding;
    private WordAdapter mWordAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mWordAdapter = new WordAdapter(mWordAdapterCallback);

        mBinding = DataBindingUtil.inflate(inflater, R.layout.word_list_fragment, container,false);
        mBinding.wordRecyclerView.setAdapter(mWordAdapter);
        mBinding.wordRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mBinding.setWordListFragment(this);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        sharedViewModel.getmAllWordFromLessonWithId(getArguments().getLong(LESSON_ID)).observe(this, new Observer<List<WordEntity>>() {
            @Override
            public void onChanged(@Nullable List<WordEntity> wordEntities) {
                mWordAdapter.setWordList(wordEntities);
            }
        });
    }

    //**********************Apdirbame UI paspaudimus
    public void fabButtonClicked(View view) {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            ((MainActivity) getActivity())
                    .showWordEditFragmentWithLessonIdAndWordId(getArguments().getLong(LESSON_ID),-1);
        }
    }

    private final WordAdapterCallback mWordAdapterCallback = new WordAdapterCallback() {
        @Override
        public void onEdit(WordEntity word) {
            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                ((MainActivity) getActivity())
                        .showWordEditFragmentWithLessonIdAndWordId(getArguments().getLong(LESSON_ID),word.getWordId());
            }
        }
    };

    public interface WordAdapterCallback {
        void onEdit (WordEntity word);
    }

    //**********************Konstruktorius
    public static WordListFragment createWordListFragmentForLessonId (long id) {
        WordListFragment fragment = new WordListFragment();
        Bundle bundle = new Bundle();
        bundle.putLong(LESSON_ID, id);
        fragment.setArguments(bundle);
        return fragment;
    }
}
