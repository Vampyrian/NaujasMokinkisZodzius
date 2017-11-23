package com.pv.vampyrian.mokinkiszodzius.ui.base;

import android.support.v4.app.FragmentManager;

import com.pv.vampyrian.mokinkiszodzius.R;
import com.pv.vampyrian.mokinkiszodzius.ui.lessonlist.LessonListFragment;
import com.pv.vampyrian.mokinkiszodzius.ui.preference.MyPreferenceFragment;
import com.pv.vampyrian.mokinkiszodzius.ui.signin.SignInFragment;
import com.pv.vampyrian.mokinkiszodzius.ui.trainingwords.TrainingWordFragment;
import com.pv.vampyrian.mokinkiszodzius.ui.wordlist.WordListFragment;

public abstract class NavigationBetweenFragmentActivity extends BaseActivity {



    @Override
    public boolean onSupportNavigateUp() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        int backStackSize = fragmentManager.getBackStackEntryCount();
        if (backStackSize > 0) {
            fragmentManager.popBackStack();
        } else {
            showLessonsListFragment();
        }
        return true;
    }

    private void clearBackStack() {
        FragmentManager fragmentManager = getSupportFragmentManager();

    }


    /*
    Navigacija tarp fragmentu
    */

    public void showWordsListFragment (long lessonId) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        WordListFragment fragment = WordListFragment.createWordListFragmentForLessonId(lessonId);

        getSupportFragmentManager()
                .beginTransaction()
//                .setCustomAnimations(R.anim.slide_right_in, R.anim.slide_right_out)
                .replace(R.id.main_fragments_container, fragment, null)
                .commit();
        hideKeyboard();
    }

//    public void showLessonEditFragment (long lessonId) {
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        LessonEditFragment fragment = LessonEditFragment.createLessonEditFragmnetForLessonId(lessonId);
//
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.main_fragments_container, fragment, null)
//                .commit();
//        hideKeyboard();
//    }
//
//    public void showWordEditFragmentWithLessonIdAndWordId (long lessonId, long wordId) {
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        WordEditFragment fragment = WordEditFragment.createWordEditFragmendWithLessonIdAndWordId(lessonId, wordId);
//
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.main_fragments_container, fragment, null)
//                .commit();
//        hideKeyboard();
//    }

    public void showLessonsListFragment () {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        LessonListFragment fragment = new LessonListFragment();

        getSupportFragmentManager()
                .beginTransaction()
//                .setCustomAnimations(R.anim.appear, R.anim.disapier)
                .replace(R.id.main_fragments_container, fragment, null)
                .commit();
        hideKeyboard();
    }

    public void showLessonsListFragment1 () {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        LessonListFragment fragment = new LessonListFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_left_in, R.anim.slide_left_out)
                .replace(R.id.main_fragments_container, fragment, null)
                .commit();
        hideKeyboard();
    }

    public void showTrainingFragment () {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        TrainingWordFragment fragment = new TrainingWordFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_right_in, R.anim.slide_right_out)
                .replace(R.id.main_fragments_container, fragment, null)
                .commit();
        hideKeyboard();
    }

    public void showPreferenceFragment () {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        MyPreferenceFragment fragment = new MyPreferenceFragment();

        getSupportFragmentManager()
                .beginTransaction()
//                .setCustomAnimations(R.anim.appear, R.anim.disapier)
                .replace(R.id.main_fragments_container, fragment, null)
                .commit();
        hideKeyboard();
    }

    public void showSignInFragment () {
        SignInFragment fragment = new SignInFragment();

        getSupportFragmentManager()
                .beginTransaction()
//                .setCustomAnimations(R.anim.appear, R.anim.disapier)
                .replace(R.id.main_fragments_container, fragment, null)
                .commit();
        hideKeyboard();
    }
}
