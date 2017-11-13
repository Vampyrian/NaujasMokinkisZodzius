package com.pv.vampyrian.mokinkiszodzius.room.appDatabase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.util.Log;

import com.pv.vampyrian.mokinkiszodzius.room.entityAndDao.TimeConverter;
import com.pv.vampyrian.mokinkiszodzius.room.entityAndDao.LessonDao;
import com.pv.vampyrian.mokinkiszodzius.room.entityAndDao.WordDao;

@Database(entities = {com.pv.vampyrian.mokinkiszodzius.room.entityAndDao.LessonEntity.class, com.pv.vampyrian.mokinkiszodzius.room.entityAndDao.WordEntity.class}, version = 1)
@TypeConverters({TimeConverter.class})
public abstract class AppDatabase extends RoomDatabase {

    private static final String TAG = AppDatabase.class.getSimpleName();
    static final private String DATABASE_NAME = "words_db";

    //Singleton
    private static final Object LOCK = new Object();
    private static AppDatabase sInstance;

    public static AppDatabase getInstance (Context context) {
        Log.d(TAG, "Prasideda duombazes gamyba");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class,
                        AppDatabase.DATABASE_NAME)
                        //.allowMainThreadQueries()
                        .build();
                Log.d(TAG, "Duombaze sukurta");
            }
        }
        return sInstance;
    }

    public abstract LessonDao lessonDao();
    public abstract WordDao wordDao();
}
