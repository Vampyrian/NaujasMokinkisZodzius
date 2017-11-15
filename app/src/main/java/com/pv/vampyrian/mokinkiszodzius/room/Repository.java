package com.pv.vampyrian.mokinkiszodzius.room;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.pv.vampyrian.mokinkiszodzius.executor.AppExecutor;
import com.pv.vampyrian.mokinkiszodzius.room.appDatabase.DatabaseInitializesUtil;
import com.pv.vampyrian.mokinkiszodzius.room.entityAndDao.LessonDao;
import com.pv.vampyrian.mokinkiszodzius.room.entityAndDao.LessonEntity;
import com.pv.vampyrian.mokinkiszodzius.room.entityAndDao.WordDao;
import com.pv.vampyrian.mokinkiszodzius.room.entityAndDao.WordEntity;

import java.util.List;

public class Repository {

    private static final String LOG_TAG = Repository.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static Repository sInstance;
    private final LessonDao mLessonDao;
    private final WordDao mWordDao;
    private final AppExecutor mAppExecutor;

    private Repository (LessonDao lessonDao, WordDao wordDao, AppExecutor appExecutor) {
        mLessonDao = lessonDao;
        mWordDao = wordDao;
        mAppExecutor = appExecutor;
    }

    public synchronized static Repository getInstance (LessonDao lessonDao, WordDao wordDao, AppExecutor appExecutor) {
        Log.d(LOG_TAG, "Uzprase repositorijos instanco is " + Thread.currentThread().getName());
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new Repository(lessonDao, wordDao, appExecutor);
                Log.d(LOG_TAG, "Padareme nauja Repositorija is " + Thread.currentThread().getName());
            }
        }
        return sInstance;
    }


    //TODO Cia kazkada reiketu apdirbti klaidas kai irasinejame ir updeitiname pamokas, nes juk pareina long su atsakymu kaip pavyko operacija
    /*
    Cia atliekamos visos uzklausos i ir is duombazes
     */
   public LiveData<List<LessonEntity>> getAllLessonList () {
       return mLessonDao.getAllLessonLiveData();
   }

   public LiveData<LessonEntity> getLessonWithId (long id) {
       return mLessonDao.getLessonWithId(id);
   }

    public int updateLesson (final LessonEntity lesson) {
       mAppExecutor.getDiskIO().execute(new Runnable() {
           @Override
           public void run() {
               Log.d(LOG_TAG, "Bandome updeitinti pamoka is " + Thread.currentThread().getName());
               long a = mLessonDao.updateLesson(lesson);
           }
       });
        return 0;
    }

    public int deleteLesson (final LessonEntity lesson){
       mAppExecutor.getDiskIO().execute(new Runnable() {
           @Override
           public void run() {
               Log.d(LOG_TAG, "Bandome istinti pamoka is " + Thread.currentThread().getName());
               long a = mLessonDao.deleteLesson(lesson);
           }
       });
        return 0;
    }

    public long insertLesson (final LessonEntity lesson) {
        mAppExecutor.getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                Log.d(LOG_TAG, "Bandome irasineti pamoka is " + Thread.currentThread().getName());
                long a = mLessonDao.insertLesson(lesson);
            }
        });
        return 0;
    }

   public LiveData<WordEntity> getWordWithId (long id){
        return mWordDao.getWordWithId(id);
   }

   public int deleteWord (final WordEntity word) {
       mAppExecutor.getDiskIO().execute(new Runnable() {
           @Override
           public void run() {
               Log.d(LOG_TAG, "Bandome istrinti zodi is " + Thread.currentThread().getName());
               long a = mWordDao.deteleWord(word);
           }
       });
       return 0;
   }

   public int updateWord (final WordEntity word) {
       mAppExecutor.getDiskIO().execute(new Runnable() {
           @Override
           public void run() {
               Log.d(LOG_TAG, "Bandome updeitinti zodi is " + Thread.currentThread().getName());
               long a = mWordDao.updateWord(word);
           }
       });
       return 0;
   }

   public long insertWord (final WordEntity word) {
       mAppExecutor.getDiskIO().execute(new Runnable() {
           @Override
           public void run() {
               Log.d(LOG_TAG, "Bandome insertinti zodi is " + Thread.currentThread().getName());
               long a = mWordDao.insertWord(word);
           }
       });
       return 0;
   }

   public LiveData<List<WordEntity>> getAllWordsFromSelectedLesson () {
       return mWordDao.getAllWordFromSelectedLesson();
   }


   public LiveData<List<WordEntity>> getWordWithLessonId (long id) {
       return mWordDao.getWordFromLessonWithId(id);
   }


   /*
   ******************************Kvailu duomenu irasymas i duombaze
    */
   public void initDummyData(final String lesson, final List<String> word, final List<String> translate) {
       mAppExecutor.getDiskIO().execute(new Runnable() {
           @Override
           public void run() {
               Log.d(LOG_TAG, "Sukuriame durnus duomenis is " + Thread.currentThread().getName());
               long lessonId = mLessonDao.insertLesson(DatabaseInitializesUtil.getLesson(lesson));
               int listSize = word.size();
               for (int i=0; i<listSize; i++) {
                   long id = mWordDao.insertWord(DatabaseInitializesUtil.getWord(word.get(i), translate.get(i), lessonId));
               }
               Log.d(LOG_TAG, "Baigėme įrašinėti durnus duomenis is " + Thread.currentThread().getName());
           }
       });

   }
}
