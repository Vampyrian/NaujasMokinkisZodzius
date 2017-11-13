package com.pv.vampyrian.mokinkiszodzius.room.appDatabase;


import com.pv.vampyrian.mokinkiszodzius.room.entityAndDao.LessonEntity;
import com.pv.vampyrian.mokinkiszodzius.room.entityAndDao.WordEntity;

import java.util.Date;

/*
                   Sita klase naudojama tik durniems duomenis irasyti
 */
public class DatabaseInitializesUtil {

    public static LessonEntity getLesson(String lessonName) {
        LessonEntity newLesson = new LessonEntity();
        newLesson.setTimeId(new Date());
        newLesson.setRating(0);
        newLesson.setSelected(1);
        newLesson.setName(lessonName);
        return newLesson;
    }

    public static WordEntity getWord(String word, String translate, long lessonId) {
        WordEntity newWord = new WordEntity();
        newWord.setWord(word);
        newWord.setTranslateWord(translate);
        newWord.setRating(0);
        newWord.setTimeId(new Date());
        newWord.setParentId(lessonId);
        return newWord;
    }




}
