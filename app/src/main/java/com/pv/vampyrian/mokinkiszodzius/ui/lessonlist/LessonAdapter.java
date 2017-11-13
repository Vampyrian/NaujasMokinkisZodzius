package com.pv.vampyrian.mokinkiszodzius.ui.lessonlist;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.pv.vampyrian.mokinkiszodzius.R;
import com.pv.vampyrian.mokinkiszodzius.databinding.LessonListRowBinding;
import com.pv.vampyrian.mokinkiszodzius.room.entityAndDao.LessonEntity;

import java.util.List;
import java.util.Objects;

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.LessonViewHolder> {

    private List<LessonEntity> mLessonList;

    @Nullable
    private final LessonListFragment.LessonsAdapterCallback mLessonAdapterCallback;

    public LessonAdapter (@Nullable LessonListFragment.LessonsAdapterCallback callback) {
        mLessonAdapterCallback = callback;
    }

    public void setLessonList (final List<LessonEntity> lessonList) {
        if (mLessonList == null) {
            mLessonList = lessonList;
            notifyItemRangeInserted(0, lessonList.size());
        } else {
            //TODO Sita galetu sukisti i background threada, nes gali ilgai uzimti laiko kol visa lista patikrins
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mLessonList.size();
                }

                @Override
                public int getNewListSize() {
                    return lessonList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return mLessonList.get(oldItemPosition).getLessonId()==
                            lessonList.get(newItemPosition).getLessonId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    LessonEntity oldLesson = mLessonList.get(oldItemPosition);
                    LessonEntity newLesson = lessonList.get(newItemPosition);
                    return oldLesson.getLessonId() == newLesson.getLessonId() &&
                            oldLesson.getSelected() == newLesson.getSelected() &&
                            oldLesson.getRating() == newLesson.getRating() &&
                            Objects.equals(oldLesson.getName(), newLesson.getName());
                }
            });
            mLessonList = lessonList;
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    public LessonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LessonListRowBinding binding = DataBindingUtil.
                inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.lesson_list_row, parent, false);
        binding.setCallback(mLessonAdapterCallback);

        return new LessonViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(LessonViewHolder holder, int position) {
        holder.binding.setLesson(mLessonList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mLessonList==null ? 0 : mLessonList.size();
    }

    static class LessonViewHolder extends RecyclerView.ViewHolder {

        final LessonListRowBinding binding;

        public LessonViewHolder (LessonListRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}
