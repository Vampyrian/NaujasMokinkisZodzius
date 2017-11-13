package com.pv.vampyrian.mokinkiszodzius.room.entityAndDao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface WordDao {

    @Query("SELECT * FROM WordEntity")
    List<WordEntity> getAllWord();

    @Query("SELECT WordEntity.wordId, WordEntity.word, WordEntity.translateWord, WordEntity.rating, WordEntity.parentId, WordEntity.timeId "+
            "FROM WordEntity "+
            "INNER JOIN LessonEntity ON LessonEntity.lessonId = WordEntity.parentId " +
            "WHERE WordEntity.parentId = :id")
    List<WordEntity> getAllWordFromLessonWithId (long id);

    @Query("SELECT * FROM WordEntity WHERE WordEntity.parentId = :id")
    LiveData<List<WordEntity>> getWordFromLessonWithId (long id);

    @Query("SELECT * FROM WordEntity WHERE WordEntity.wordId = :id")
    LiveData<WordEntity> getWordWithId (long id);


    @Query("SELECT WordEntity.wordId, WordEntity.word, WordEntity.translateWord, WordEntity.rating, WordEntity.parentId, WordEntity.timeId "+
            "FROM WordEntity "+
            "INNER JOIN LessonEntity ON LessonEntity.lessonId = WordEntity.parentId " +
            "WHERE LessonEntity.selected = 1")
    LiveData<List<WordEntity>> getAllWordFromSelectedLesson ();

    @Update
    int updateWord(WordEntity word);

    @Insert (onConflict = 2)
    long insertWord(WordEntity word);

    @Insert (onConflict = 2)
    List<Long> insertWordList(List<WordEntity> word);

    @Delete
    int deteleWord(WordEntity word);
}
