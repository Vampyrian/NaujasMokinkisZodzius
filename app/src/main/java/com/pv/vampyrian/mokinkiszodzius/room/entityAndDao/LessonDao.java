package com.pv.vampyrian.mokinkiszodzius.room.entityAndDao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface LessonDao {

    @Query("SELECT * FROM LessonEntity")
    List<LessonEntity> getAllLesson();

    @Query("SELECT * FROM LessonEntity")
    LiveData<List<LessonEntity>> getAllLessonLiveData();

    @Query("SELECT * FROM LessonEntity WHERE lessonId = :id")
    List<LessonEntity> getLessonWithId(int id);

    @Query("SELECT * FROM LessonEntity WHERE lessonId = :id")
    LiveData<LessonEntity> getLessonWithId(long id);

    @Query("SELECT * FROM LessonEntity WHERE name = :name")
    List<LessonEntity> getLessonWithName(String name);

    @Insert()
    long insertLesson(LessonEntity lessson);

    @Insert()
    List<Long> insertLessonList(List<LessonEntity> lessson);

    @Update()
    int updateLesson(LessonEntity lesson);

    @Delete
    int deleteLesson(LessonEntity lesson);

}
