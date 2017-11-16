package com.pv.vampyrian.mokinkiszodzius.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.pv.vampyrian.mokinkiszodzius.ui.lessonlist.LessonListFragment;
import com.pv.vampyrian.mokinkiszodzius.ui.trainingwords.TrainingWordFragment;

public class MyPageAdapter extends FragmentStatePagerAdapter {

    public MyPageAdapter (FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new LessonListFragment();
        }
        if (position == 1) {
            return new TrainingWordFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
