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

    /*
    Navigacija tarp fragmentu
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
