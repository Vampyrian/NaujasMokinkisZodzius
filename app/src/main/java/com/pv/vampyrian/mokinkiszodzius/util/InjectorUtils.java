package com.pv.vampyrian.mokinkiszodzius.util;

import android.content.Context;

import com.pv.vampyrian.mokinkiszodzius.executor.AppExecutor;
import com.pv.vampyrian.mokinkiszodzius.room.Repository;
import com.pv.vampyrian.mokinkiszodzius.room.appDatabase.AppDatabase;
import com.pv.vampyrian.mokinkiszodzius.ui.lerningwords.LerningWordViewModelFactory;
import com.pv.vampyrian.mokinkiszodzius.ui.lessonedit.LessonEditViewModelFactory;
import com.pv.vampyrian.mokinkiszodzius.ui.lessonlist.LessonListViewModelFactory;
import com.pv.vampyrian.mokinkiszodzius.ui.trainingwords.TrainingWordViewModelFactory;
import com.pv.vampyrian.mokinkiszodzius.ui.wordedit.WordEditViewModelFactory;
import com.pv.vampyrian.mokinkiszodzius.ui.wordlist.WordListViewModelFactory;

public class InjectorUtils {

    public static LessonListViewModelFactory provideLessonListViewModelFactory (Context context) {
        Repository repository = provideRepository(context);
        return new LessonListViewModelFactory(repository);
    }

    public static LessonEditViewModelFactory provideLessonEditViewModelFactory (Context context, long lessonId) {
        Repository repository = provideRepository(context);
        return new LessonEditViewModelFactory(repository, lessonId);
    }

    public static WordListViewModelFactory profideWordListViewModelFactory (Context context, long lessonId) {
        Repository repository = provideRepository(context);
        return new WordListViewModelFactory(repository, lessonId);
    }

    public static WordEditViewModelFactory provideWordEditViewMOdelFactory (Context context, long wordId) {
        Repository repository = provideRepository(context);
        return new WordEditViewModelFactory(repository, wordId);
    }

    public static LerningWordViewModelFactory provideLerningWordViewModelFactory (Context context) {
        Repository repository = provideRepository(context);
        return new LerningWordViewModelFactory(repository);
    }

    public static TrainingWordViewModelFactory provideTrainingWordViewModelFactory (Context context) {
        Repository repository = provideRepository(context);
        return new TrainingWordViewModelFactory(repository);
    }


    public static Repository provideRepository (Context context) {
        AppDatabase roomDb = AppDatabase.getInstance(context);
        AppExecutor appExecutor = AppExecutor.getInstance();
        return Repository.getInstance(roomDb.lessonDao(), roomDb.wordDao(), appExecutor);
    }
}
