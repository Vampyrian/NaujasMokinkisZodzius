package com.pv.vampyrian.mokinkiszodzius.ui.lessonedit;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.Observer;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pv.vampyrian.mokinkiszodzius.R;
import com.pv.vampyrian.mokinkiszodzius.databinding.LessonEditFragmentBinding;
import com.pv.vampyrian.mokinkiszodzius.room.entityAndDao.LessonEntity;
import com.pv.vampyrian.mokinkiszodzius.ui.MainActivity;
import com.pv.vampyrian.mokinkiszodzius.ui.base.BaseFragment;

import java.util.Date;
import java.util.Objects;

public class LessonEditFragment extends BaseFragment {

    private static final String LOG_TAG = LessonEditFragment.class.getSimpleName();
    private static final String LESSON_ID = "lesson_id";

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

        long lessonId = getArguments().getLong(LESSON_ID);
        if (lessonId != -1) {
            sharedViewModel.getObservableLessonWithId(getArguments().getLong(LESSON_ID)).observe(this, new Observer<LessonEntity>() {
                @Override
                public void onChanged(@Nullable LessonEntity lessonEntity) {
                    mBinding.setLesson(lessonEntity);
                }
            });
        }
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
                sharedViewModel.updateLesson(lesson);
            }
        } else {
            LessonEntity newLesson = new LessonEntity();
            newLesson.setName(mBinding.word.getText().toString());
            newLesson.setSelected(0);
            newLesson.setRating(0);
            newLesson.setTimeId(date);
            if (!Objects.equals(newLesson.getName(), "")) {
                sharedViewModel.insertLesson(newLesson);
            }
        }
        hideKeyboard();
        showLessonListFragment();
    }

    public void deleteLessonClicked (View view) {
        long lessonId = getArguments().getLong(LESSON_ID);
        if (lessonId != -1) {
            LessonEntity lesson = new LessonEntity();
            lesson.setLessonId(lessonId);
            sharedViewModel.deleteLesson(lesson);
        }
        hideKeyboard();
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
