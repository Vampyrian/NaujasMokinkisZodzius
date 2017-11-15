package com.pv.vampyrian.mokinkiszodzius.ui;


import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.Menu;
import android.view.MenuItem;

import com.pv.vampyrian.mokinkiszodzius.R;
import com.pv.vampyrian.mokinkiszodzius.databinding.MainActivityBinding;
import com.pv.vampyrian.mokinkiszodzius.room.Repository;
import com.pv.vampyrian.mokinkiszodzius.ui.base.NavigationBetweenFragmentActivity;
import com.pv.vampyrian.mokinkiszodzius.util.InjectorUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends NavigationBetweenFragmentActivity{

    private static final String LOG_TAG = "Main Activity";
    private static final String PREFERENCE = "myPreference";
    private static final String IS_FIRST_START = "firstStart";

    private MainActivityBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.main_activity);
        mBinding.tabLayout.setOnTabSelectedListener(tabSelectedListener);
        getSupportActionBar().setElevation(0);

        chechPreference();
        showLessonsListFragment();
    }

    @Override
    protected void onDestroy() {
        mBinding.tabLayout.removeOnTabSelectedListener(tabSelectedListener);
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            showPreferenceFragment();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    TabLayout.OnTabSelectedListener tabSelectedListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            int position = tab.getPosition();
            if (position == 0) {
                showLessonsListFragment();
            }
            if (position ==1) {
                showTrainingFragment();
            }
        }
        @Override
        public void onTabUnselected(TabLayout.Tab tab) {}
        @Override
        public void onTabReselected(TabLayout.Tab tab) {}
    };









    /*
                   Sita funkcija irasys pirmus durnus duomenis jeigu apsas pasileidzia pirma karta
         */
    private void chechPreference() {
        SharedPreferences settings = getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        boolean isFirstStart = settings.getBoolean(IS_FIRST_START, true);

        if (isFirstStart) {
            List<String> wordList = new ArrayList<>();
            List<String> translateList = new ArrayList<>();
            String newLesson = getString(R.string.dummy_lesson);
            wordList.add(getString(R.string.dummy_word1));
            translateList.add(getString(R.string.dummy_word_translate1));
            wordList.add(getString(R.string.dummy_word2));
            translateList.add(getString(R.string.dummy_word_translate2));
            wordList.add(getString(R.string.dummy_word3));
            translateList.add(getString(R.string.dummy_word_translate3));
            wordList.add(getString(R.string.dummy_word4));
            translateList.add(getString(R.string.dummy_word_translate4));
            wordList.add(getString(R.string.dummy_word5));
            translateList.add(getString(R.string.dummy_word_translate5));
            wordList.add(getString(R.string.dummy_word6));
            translateList.add(getString(R.string.dummy_word_translate6));
            wordList.add(getString(R.string.dummy_word7));
            translateList.add(getString(R.string.dummy_word_translate7));

            Repository repository = InjectorUtils.provideRepository(getApplicationContext());
            repository.initDummyData(newLesson, wordList, translateList);
        }

        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(IS_FIRST_START, false);
        editor.apply();
    }


}
