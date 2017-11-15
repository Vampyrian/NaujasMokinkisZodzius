package com.pv.vampyrian.mokinkiszodzius.util;

import android.content.Context;

import com.pv.vampyrian.mokinkiszodzius.executor.AppExecutor;
import com.pv.vampyrian.mokinkiszodzius.room.Repository;
import com.pv.vampyrian.mokinkiszodzius.room.appDatabase.AppDatabase;
import com.pv.vampyrian.mokinkiszodzius.viewmodel.SharedViewModelFactory;

public class InjectorUtils {

    public static SharedViewModelFactory provideSharedViewModelFactory (Context context) {
        Repository repository = provideRepository(context);
        return new SharedViewModelFactory(repository);
    }


    public static Repository provideRepository (Context context) {
        AppDatabase roomDb = AppDatabase.getInstance(context);
        AppExecutor appExecutor = AppExecutor.getInstance();
        return Repository.getInstance(roomDb.lessonDao(), roomDb.wordDao(), appExecutor);
    }
}
