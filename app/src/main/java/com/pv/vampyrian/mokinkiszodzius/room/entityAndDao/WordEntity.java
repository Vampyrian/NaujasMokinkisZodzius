package com.pv.vampyrian.mokinkiszodzius.room.entityAndDao;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity (foreignKeys = @ForeignKey( entity = LessonEntity.class,
                                    parentColumns = "lessonId",
                                    childColumns = "parentId",
                                    onDelete = ForeignKey.CASCADE,
                                    onUpdate = ForeignKey.RESTRICT),
        indices = {@Index("parentId")})

public class WordEntity {

    @PrimaryKey (autoGenerate = true)
    private long wordId;
    private String word;
    private String translateWord;
    private int rating;
    private Date timeId;
    private long parentId;


    public void setTimeId(Date timeId) {this.timeId = timeId;}

    public Date getTimeId() {return timeId;}

    public long getWordId() {
        return wordId;
    }

    public void setWordId(long wordId) {
        this.wordId = wordId;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTranslateWord() {
        return translateWord;
    }

    public void setTranslateWord(String translateWord) {
        this.translateWord = translateWord;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

}
