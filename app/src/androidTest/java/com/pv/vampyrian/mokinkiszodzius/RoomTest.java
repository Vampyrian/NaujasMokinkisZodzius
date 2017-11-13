package com.pv.vampyrian.mokinkiszodzius;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.pv.vampyrian.mokinkiszodzius.room.appDatabase.AppDatabase;
import com.pv.vampyrian.mokinkiszodzius.room.entityAndDao.LessonDao;
import com.pv.vampyrian.mokinkiszodzius.room.entityAndDao.LessonEntity;
import com.pv.vampyrian.mokinkiszodzius.room.entityAndDao.WordDao;
import com.pv.vampyrian.mokinkiszodzius.room.entityAndDao.WordEntity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class RoomTest {
    private LessonDao lessonDao;
    private WordDao wordDao;
    private AppDatabase testDb;

    @Before
    public void createDB() {
        Context context = InstrumentationRegistry.getTargetContext();
        testDb = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        lessonDao = testDb.lessonDao();
        wordDao = testDb.wordDao();
    }

    @After
    public void closeDB() {
        testDb.close();
    }



    @Test
    public void testingAllTables (){
        tikrinamLesson();
        tikrinamWord();
    }

    private void tikrinamWord() {
        //Pabandom irasyti zodi su blogu id
        WordEntity word = new WordEntity();
        word.setParentId(10);
        word.setRating(0);
        word.setTranslateWord("vienas");
        word.setWord("one");

        //Idejau, nes kitaip ismeta runtime exception
        try {
            wordDao.insertWord(word);
        } catch (Exception e) {

        }

        List<WordEntity> wordList = wordDao.getAllWord();
        assertEquals("neturejo isirasyti", 0, wordList.size());

        List<LessonEntity> lessonList = lessonDao.getAllLesson();
        LessonEntity lessonEntity = lessonList.get(0);

        word.setParentId(lessonEntity.getLessonId());

        wordDao.insertWord(word);
        wordList = wordDao.getAllWord();
        assertEquals("neisirase", 1, wordList.size());

        wordDao.insertWord(word);
        wordList = wordDao.getAllWord();
        assertEquals("neisirase", 2, wordList.size());

        //Tikrinu kaip isiraso listas is zodziu
        List<WordEntity> wordList2 = new ArrayList<>();
        wordList2.add(word);
        wordList2.add(word);
        wordList2.add(word);
        wordDao.insertWordList(wordList2);
        wordList = wordDao.getAllWord();
        assertEquals("neisirase", 5, wordList.size());


    }

    private void tikrinamLesson() {
        LessonEntity lesson = new LessonEntity();
        lesson.setName("Pirma");
        lesson.setRating(0);
        lesson.setSelected(0);

        List<LessonEntity> lessonList = lessonDao.getAllLesson();

        //Tikrinam pamokos idejima
        assertEquals("Kazkas irasyta", 0, lessonList.size());
        long a = lessonDao.insertLesson(lesson);
        assertEquals("Neisirase 1", 1, a);
        long aa = lessonDao.insertLesson(lesson);
        assertEquals("Neisirase 2", 2, aa);
        long aaa = lessonDao.insertLesson(lesson);
        assertEquals("Neisirase 3", 3, aaa);


        //Tikrinam kiek pamoku yra su Pirma zodziu
        lessonList = lessonDao.getLessonWithName("Pirma");
        assertEquals("Pamoku skaicius", 3, lessonList.size());


        //Tikirinam pamokos pavadinimo pakeitima
        lesson.setName("Antra");
        lesson.setLessonId(1);
        int b = lessonDao.updateLesson(lesson);
        lessonList = lessonDao.getAllLesson();
        assertEquals("Nepasikeite vardas", "Antra", lessonList.get(0).getName());

        //Tikrinam pamokos istrynima
        lessonList = lessonDao.getAllLesson();
        assertEquals("Turi buti 3", 3, lessonList.size());
        int skaicius = lessonDao.deleteLesson(lesson);
        lessonList = lessonDao.getAllLesson();
        assertEquals("Neissitryne", 2, lessonList.size());
    }


}
