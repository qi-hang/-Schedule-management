package com.mycompany.zqh;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao   // Database access object
public interface WordDao {
    @Insert
    void insertWords(Word... words);

    @Update
    void updateWords(Word... words);

    @Delete
    void deleteWords(Word... words);

    @Query("DELETE FROM WORD")
    void deleteAllWords();

    @Query("SELECT * FROM WORD ORDER BY ID DESC")
    //List<Word> getAllWords();
    LiveData<List<Word>>getAllWordsLive();

    @Query("SELECT * FROM WORD WHERE neirong LIKE :patten ORDER BY ID DESC")
    LiveData<List<Word>> findWordsWithPattern(String patten);

    @Query("SELECT * FROM WORD WHERE dmonth = :dd AND dday = :bb  ORDER BY ID DESC ")
    LiveData<List<Word>> riqi(int dd,int bb);

    @Query("SELECT * FROM WORD WHERE tag = :qq OR tag = :pp  ORDER BY ID DESC ")
    LiveData<List<Word>> tagg(int qq,int pp);

}
