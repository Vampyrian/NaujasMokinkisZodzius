package com.pv.vampyrian.mokinkiszodzius.ui.lessonedit;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.pv.vampyrian.mokinkiszodzius.R;
import com.pv.vampyrian.mokinkiszodzius.databinding.LessonEditFragmentBinding;
import com.pv.vampyrian.mokinkiszodzius.room.entityAndDao.LessonEntity;
import com.pv.vampyrian.mokinkiszodzius.ui.MainActivity;
import com.pv.vampyrian.mokinkiszodzius.util.InjectorUtils;

import java.util.Date;
import java.util.Objects;

public class LessonEditFragment extends Fragment {

    private static String LESSON_ID;

    private LessonEditViewModel mLessonEditViewModel;

    private LessonEditFragmentBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.lesson_edit_fragment, container, false);
        mBinding.setLessonEditFragment(this);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        LessonEditViewModelFactory lessonEditViewModelFactory =
                InjectorUtils.provideLessonEditViewModelFactory(getContext(),getArguments().getLong(LESSON_ID));
        mLessonEditViewModel = ViewModelProviders.of(this, lessonEditViewModelFactory).get(LessonEditViewModel.class);

        long lessonId = getArguments().getLong(LESSON_ID);
        if (lessonId != -1) {
            subscribeUI(mLessonEditViewModel);
        }
    }

    private void subscribeUI(LessonEditViewModel viewModel) {
        viewModel.getObsorvableLesson().observe(this, new Observer<LessonEntity>() {
            @Override
            public void onChanged(@Nullable LessonEntity lessonEntity) {
                mBinding.setLesson(lessonEntity);
            }
        });
    }

    private void hideKeyboard (View v) {
        InputMethodManager imn = (InputMethodManager) getContext().getSystemService(getContext().INPUT_METHOD_SERVICE);
        imn.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }



    //***********************Apdirbam UI paspaudimus
    public void saveLessonClicked (View view) {
        Date date = new Date();
        long lessonId = getArguments().getLong(LESSON_ID);
        if (lessonId != -1) {
            LessonEntity lesson = mBinding.getLesson();
            lesson.setName(mBinding.word.getText().toString());
            lesson.setTimeId(date);
            if (!Objects.equals(lesson.getName(), "")) {
                mLessonEditViewModel.updateLesson(lesson);
            }
        } else {
            LessonEntity newLesson = new LessonEntity();
            newLesson.setName(mBinding.word.getText().toString());
            newLesson.setSelected(0);
            newLesson.setRating(0);
            newLesson.setTimeId(date);
            if (!Objects.equals(newLesson.getName(), "")) {
                mLessonEditViewModel.insertLesson(newLesson);
            }
        }
        hideKeyboard(view);
        showLessonListFragment();
    }

    public void deleteLessonClicked (View view) {
        long lessonId = getArguments().getLong(LESSON_ID);
        if (lessonId != -1) {
            LessonEntity lesson = new LessonEntity();
            lesson.setLessonId(lessonId);
            mLessonEditViewModel.deleteLesson(lesson);
        }
        hideKeyboard(view);
        showLessonListFragment();
    }

    private void showLessonListFragment() {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            ((MainActivity) getActivity()).showLessonsListFragment();
        }
    }

    public static LessonEditFragment createLessonEditFragmnetForLessonId (long lessonId) {
        LessonEditFragment fragment = new LessonEditFragment();
        Bundle bundle = new Bundle();
        bundle.putLong(LESSON_ID, lessonId);
        fragment.setArguments(bundle);
        return fragment;
    }

}
