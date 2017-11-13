package com.pv.vampyrian.mokinkiszodzius.room.entityAndDao;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity
public class LessonEntity {

    @PrimaryKey (autoGenerate = true)
    private long lessonId;
    private String name;
    private int rating;
    private int selected;
    private Date timeId;






    public void setTimeId(Date timeId) {this.timeId = timeId;}

    public Date getTimeId() {return timeId;}

    public long getLessonId() {
        return lessonId;
    }

    public void setLessonId(long lessonId) {
        this.lessonId = lessonId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getSelected() {
        return selected;
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }


}
