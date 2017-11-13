package com.pv.vampyrian.mokinkiszodzius.ui.wordlist;

import android.databinding.DataBindingUtil;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.pv.vampyrian.mokinkiszodzius.R;
import com.pv.vampyrian.mokinkiszodzius.databinding.WordListRowBinding;
import com.pv.vampyrian.mokinkiszodzius.room.entityAndDao.WordEntity;

import java.util.List;
import java.util.Objects;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordViewHolder> {

    private List<WordEntity> mWordList;

    private final WordListFragment.WordAdapterCallback mWordAdapterCallback;

    public WordAdapter (WordListFragment.WordAdapterCallback callback) {
        mWordAdapterCallback = callback;
    }

    public void setWordList (final List<WordEntity> wordList) {
        if (mWordList == null) {
            mWordList = wordList;
            notifyItemRangeInserted(0,wordList.size());
        } else {
            //TODO sita galetu imesti i atskira threade
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mWordList.size();
                }

                @Override
                public int getNewListSize() {
                    return wordList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return mWordList.get(oldItemPosition).getWordId() ==
                            wordList.get(newItemPosition).getWordId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    WordEntity oldWord = mWordList.get(oldItemPosition);
                    WordEntity newWord = wordList.get(newItemPosition);
                    return oldWord.getWordId() == newWord.getWordId() &&
                            oldWord.getParentId() == newWord.getParentId() &&
                            oldWord.getRating() == newWord.getRating() &&
                            Objects.equals(oldWord.getWord(),newWord.getWord()) &&
                            Objects.equals(oldWord.getTranslateWord(),newWord.getTranslateWord());
                }
            });
            mWordList = wordList;
            diffResult.dispatchUpdatesTo(this);
        }
    }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        WordListRowBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.word_list_row, parent, false);
        binding.setCallback(mWordAdapterCallback);
        return new WordViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        holder.binding.setWord(mWordList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mWordList == null ? 0 : mWordList.size();
    }

    static class WordViewHolder extends RecyclerView.ViewHolder {

        final WordListRowBinding binding;

        public WordViewHolder (WordListRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
