package com.pv.vampyrian.mokinkiszodzius.ui;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.pv.vampyrian.mokinkiszodzius.R;
import com.pv.vampyrian.mokinkiszodzius.room.Repository;
import com.pv.vampyrian.mokinkiszodzius.ui.lerningwords.LerningWordFragment;
import com.pv.vampyrian.mokinkiszodzius.ui.lessonedit.LessonEditFragment;
import com.pv.vampyrian.mokinkiszodzius.ui.lessonlist.LessonListFragment;
import com.pv.vampyrian.mokinkiszodzius.ui.preference.MyPreferenceFragment;
import com.pv.vampyrian.mokinkiszodzius.ui.signin.SignInFragment;
import com.pv.vampyrian.mokinkiszodzius.ui.trainingwords.TrainingWordFragment;
import com.pv.vampyrian.mokinkiszodzius.ui.wordedit.WordEditFragment;
import com.pv.vampyrian.mokinkiszodzius.ui.wordlist.WordListFragment;
import com.pv.vampyrian.mokinkiszodzius.util.InjectorUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String LOG_TAG = "Main Activity";
    private static final String PREFERENCE = "myPreference";
    private static final String IS_FIRST_START = "firstStart";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        int a =1;

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        chechPreference();
        showLessonsListFragment();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            showPreferenceFragment();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_lesson) {
            showLessonsListFragment();
        } else if (id == R.id.nav_lerning) {
            showLerningFragment();
        } else if (id == R.id.nav_training) {
            showTrainingFragment();
        } else if (id == R.id.nav_log_in) {
            showSignInFragment();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void showToastFunctionNotWorking () {
        Toast.makeText(getApplicationContext(), R.string.toast_function_not_working, Toast.LENGTH_LONG).show();
    }

    private void hideKeyboard() {
        //Tiesiog durnas apdirbimas. Tegu pabando uzdaryti klaviatura ir tiek
        try {
            InputMethodManager imn = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imn.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
        }
    }





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

    /*
                          Navigacija tarp fragmentu kvieciama is paciu fragmentu
     */

    public void showLessonsListFragment () {
        getSupportActionBar().setTitle(R.string.toolbar_lesson_list);
        LessonListFragment fragment = new LessonListFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragments_container, fragment, null)
                .commit();
        hideKeyboard();
    }

    public void showWordsListFragment (long lessonId) {
        getSupportActionBar().setTitle(R.string.toolbar_word_list);
        WordListFragment fragment = WordListFragment.createWordListFragmentForLessonId(lessonId);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragments_container, fragment, null)
                .commit();
        hideKeyboard();
    }

    public void showLessonEditFragment (long lessonId) {
        getSupportActionBar().setTitle(R.string.toolbar_lesson_edit);
        LessonEditFragment fragment = LessonEditFragment.createLessonEditFragmnetForLessonId(lessonId);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragments_container, fragment, null)
                .commit();
        hideKeyboard();
    }

    public void showWordEditFragmentWithLessonIdAndWordId (long lessonId, long wordId) {
        getSupportActionBar().setTitle(R.string.toolbar_word_edit);
        WordEditFragment fragment = WordEditFragment.createWordEditFragmendWithLessonIdAndWordId(lessonId, wordId);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragments_container, fragment, null)
                .commit();
        hideKeyboard();
    }

    public void showTrainingFragment () {
        getSupportActionBar().setTitle(R.string.toolbar_training);
        TrainingWordFragment fragment = new TrainingWordFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragments_container, fragment, null)
                .commit();
        hideKeyboard();
    }

    public void showLerningFragment () {
        getSupportActionBar().setTitle(R.string.toolbar_learning);
        LerningWordFragment fragment = new LerningWordFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragments_container, fragment, null)
                .commit();
        hideKeyboard();
    }

    public void showPreferenceFragment () {
        getSupportActionBar().setTitle(R.string.toolbar_preference);
        MyPreferenceFragment fragment = new MyPreferenceFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragments_container, fragment, null)
                .commit();
        hideKeyboard();
    }

    public void showSignInFragment () {
        getSupportActionBar().setTitle(R.string.toolbar_sing_in);
        SignInFragment fragment = new SignInFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragments_container, fragment, null)
                .commit();
        hideKeyboard();
    }
}
