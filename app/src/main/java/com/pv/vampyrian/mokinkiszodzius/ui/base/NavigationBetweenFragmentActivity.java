package com.pv.vampyrian.mokinkiszodzius.ui.base;

import com.pv.vampyrian.mokinkiszodzius.R;
import com.pv.vampyrian.mokinkiszodzius.ui.lessonedit.LessonEditFragment;
import com.pv.vampyrian.mokinkiszodzius.ui.lessonlist.LessonListFragment;
import com.pv.vampyrian.mokinkiszodzius.ui.preference.MyPreferenceFragment;
import com.pv.vampyrian.mokinkiszodzius.ui.signin.SignInFragment;
import com.pv.vampyrian.mokinkiszodzius.ui.trainingwords.TrainingWordFragment;
import com.pv.vampyrian.mokinkiszodzius.ui.wordedit.WordEditFragment;
import com.pv.vampyrian.mokinkiszodzius.ui.wordlist.WordListFragment;

public abstract class NavigationBetweenFragmentActivity extends BaseActivity {



    @Override
    public boolean onSupportNavigateUp() {
        showLessonsListFragment();
        return true;
    }
    /*
        Navigacija tarp fragmentu
         */
    public void showLessonsListFragment () {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        LessonListFragment fragment = new LessonListFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragments_container, fragment, null)
                .commit();
        hideKeyboard();
    }

    public void showWordsListFragment (long lessonId) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        WordListFragment fragment = WordListFragment.createWordListFragmentForLessonId(lessonId);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragments_container, fragment, null)
                .commit();
        hideKeyboard();
    }

    public void showLessonEditFragment (long lessonId) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        LessonEditFragment fragment = LessonEditFragment.createLessonEditFragmnetForLessonId(lessonId);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragments_container, fragment, null)
                .commit();
        hideKeyboard();
    }

    public void showWordEditFragmentWithLessonIdAndWordId (long lessonId, long wordId) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        WordEditFragment fragment = WordEditFragment.createWordEditFragmendWithLessonIdAndWordId(lessonId, wordId);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragments_container, fragment, null)
                .commit();
        hideKeyboard();
    }

    public void showTrainingFragment () {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        TrainingWordFragment fragment = new TrainingWordFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragments_container, fragment, null)
                .commit();
        hideKeyboard();
    }

    public void showPreferenceFragment () {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        MyPreferenceFragment fragment = new MyPreferenceFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragments_container, fragment, null)
                .commit();
        hideKeyboard();
    }

    public void showSignInFragment () {
        SignInFragment fragment = new SignInFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragments_container, fragment, null)
                .commit();
        hideKeyboard();
    }
}
