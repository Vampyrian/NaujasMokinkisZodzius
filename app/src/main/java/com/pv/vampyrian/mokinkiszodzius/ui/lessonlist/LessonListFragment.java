package com.pv.vampyrian.mokinkiszodzius.ui.lessonlist;


import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pv.vampyrian.mokinkiszodzius.R;
import com.pv.vampyrian.mokinkiszodzius.databinding.LessonsListFragmentBinding;
import com.pv.vampyrian.mokinkiszodzius.room.entityAndDao.LessonEntity;
import com.pv.vampyrian.mokinkiszodzius.ui.MainActivity;
import com.pv.vampyrian.mokinkiszodzius.util.InjectorUtils;

import java.util.List;

//Fragment atsakingas uz pamoku saraso rodyma
public class LessonListFragment extends Fragment {

    public static final String TAG = "LessonListFragment";

    private LessonsListFragmentBinding mBinding;

    private LessonAdapter mLessonAdapter;

    private LessonListViewModel mLessonViewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mLessonAdapter = new LessonAdapter(mLessonAdapterCallback);

        mBinding = DataBindingUtil.inflate(inflater, R.layout.lessons_list_fragment, container, false);
        mBinding.lessonRecyclerView.setAdapter(mLessonAdapter);
        mBinding.lessonRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mBinding.setLessonListFragment(this);

        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        LessonListViewModelFactory lessonListViewModelFactory = InjectorUtils.provideLessonListViewModelFactory(getContext());
        mLessonViewModel = ViewModelProviders.of(this, lessonListViewModelFactory).get(LessonListViewModel.class);
        subscribeUI(mLessonViewModel);
    }

    private void subscribeUI(LessonListViewModel viewModel) {
        viewModel.getObservableLesson().observe(this, new Observer<List<LessonEntity>>() {
            @Override
            public void onChanged(@Nullable List<LessonEntity> lessonList) {
                mLessonAdapter.setLessonList(lessonList);
            }
        });
    }

    //***************Apdirbam UI paspaudimus

    public void fabButtonClicked() {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            ((MainActivity) getActivity()).showLessonEditFragment(-1);
        }
    }


    private final LessonsAdapterCallback mLessonAdapterCallback = new LessonsAdapterCallback() {
        @Override
        public void onSelect(@NonNull LessonEntity lesson) {
            lesson.setSelected(lesson.getSelected() == 1 ? 0 : 1);
            mLessonViewModel.updateLesson(lesson);
    }

        @Override
        public void onEdit(LessonEntity lesson) {
            long lessonId = lesson.getLessonId();

            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                ((MainActivity) getActivity()).showLessonEditFragment(lessonId);
            }
        }

        @Override
        public void onClick(@NonNull LessonEntity lesson) {
            long lessonId = lesson.getLessonId();

            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                ((MainActivity) getActivity()).showWordsListFragment(lessonId);
            }
        }
    };

    public interface LessonsAdapterCallback {
        void onSelect (LessonEntity lesson);
        void onEdit (LessonEntity lesson);
        void onClick (LessonEntity lesson);
    }
}
