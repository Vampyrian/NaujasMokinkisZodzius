package com.pv.vampyrian.mokinkiszodzius.ui.lessonedit;

import android.arch.lifecycle.Observer;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pv.vampyrian.mokinkiszodzius.R;
import com.pv.vampyrian.mokinkiszodzius.databinding.LessonEditDialogFragmentBinding;
import com.pv.vampyrian.mokinkiszodzius.room.entityAndDao.LessonEntity;
import com.pv.vampyrian.mokinkiszodzius.ui.base.BaseDialog;

import java.util.Date;
import java.util.Objects;

//TODO pavyzdyje yra static ir man reiketu padaryti static

public class LessonEditDialogFragment extends BaseDialog {

    private static final String LOG_TAG = LessonEditDialogFragment.class.getSimpleName();
    private static final String LESSON_ID = "lesson_id";
    private LessonEditDialogFragmentBinding mBinding;

    public static LessonEditDialogFragment newInstance(long id) {
        LessonEditDialogFragment dialogFragment = new LessonEditDialogFragment();
        Bundle args = new Bundle();
        args.putLong(LESSON_ID, id);
        dialogFragment.setArguments(args);
        return dialogFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.lesson_edit_dialog_fragment, container, false);
        mBinding.setCallback(this);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
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

    public void onPositiveClick() {
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
        dismiss();
    }

    public void onNegativeClick() {
        long lessonId = getArguments().getLong(LESSON_ID);
        if (lessonId != -1) {
            LessonEntity lesson = new LessonEntity();
            lesson.setLessonId(lessonId);
            sharedViewModel.deleteLesson(lesson);
        }
        dismiss();
    }
}
