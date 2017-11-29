package com.pv.vampyrian.mokinkiszodzius.ui;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.pv.vampyrian.mokinkiszodzius.R;
import com.pv.vampyrian.mokinkiszodzius.databinding.MainActivityBinding;
import com.pv.vampyrian.mokinkiszodzius.ui.base.NavigationBetweenFragmentActivity;

public class MainActivity extends NavigationBetweenFragmentActivity{

    private static final String LOG_TAG = "Main Activity";
    private static final String PREFERENCE = "myPreference";
    private static final String IS_FIRST_START = "firstStart";

    private MainActivityBinding mBinding;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.main_activity);
        mBinding.tabLayout.setOnTabSelectedListener(tabSelectedListener);

        getSupportActionBar().setElevation(0);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            showLessonsListFragment();
        } else {
            showSignInFragment();
            mBinding.setShowTabLayout(false);
        }
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
                showLessonsListFragment1();
            }
            if (position ==1) {
                showTrainingFragment();
            }
        }
        @Override
        public void onTabUnselected(TabLayout.Tab tab) {}
        @Override
        public void onTabReselected(TabLayout.Tab tab) {
            int position = tab.getPosition();
            if (position == 0) {
                showLessonsListFragment1();
            }
            if (position ==1) {
                showTrainingFragment();
            }
        }
    };

    public void showTabLayout() {
        mBinding.setShowTabLayout(true);
        showLessonsListFragment();
    }



}
