package com.mycompany.zqh;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface LessonDao {
    @Insert
    void insertLessons(Lesson... lessons);

    @Update
    void updateLessons(Lesson... lessons);

    @Delete
    void deleteLessons(Lesson... lessons);

    @Query("DELETE FROM LESSON")
    void deleteAllLessons();

    @Query("SELECT * FROM LESSON ORDER BY  shuxun DESC")
        //List<Lesson> getAllLessons();
    LiveData<List<Lesson>> getAllLessonsLive();

    /*@Query("SELECT * FROM LESSON WHERE neirong LIKE :patten ORDER BY ID DESC")
    LiveData<List<Lesson>> findWordsWithPattern(String patten);

    @Query("SELECT * FROM WORD WHERE dmonth = :dd AND dday = :bb  ORDER BY ID DESC ")
    LiveData<List<Word>> riqi(int dd,int bb);

    @Query("SELECT * FROM WORD WHERE tag = :qq OR tag = :pp  ORDER BY ID DESC ")
    LiveData<List<Word>> tagg(int qq,int pp);*/
}
