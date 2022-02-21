package com.mycompany.zqh;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class WordViewModel extends AndroidViewModel {

    private WordRepository wordRepository;
    private LessonResposity lessonResposity;

    public WordViewModel(@NonNull Application application) {
        super(application);
        wordRepository = new WordRepository(application);
        lessonResposity =new LessonResposity(application);
    }


    public LiveData<List<Word>> getAllWordsLive() {
        return wordRepository.getAllWordsLive();
    }
    public LiveData<List<Lesson>> getAllLessonsLive(){return lessonResposity.getAllLessonsLive();}

    public LiveData<List<Word>> findWordsWithPatten(String patten){
        return wordRepository.findWordsWithPatten(patten);
    }
    public LiveData<List<Word>> riqi(int aa,int bb){
        return wordRepository.riqi(aa,bb);
    }
    public LiveData<List<Word>> tagg(int mm,int nn){
        return wordRepository.tagg(mm,nn);
    }

    public void insertWords(Word... words) {
        wordRepository.insertWords(words);
    }
    void updateWords(Word... words) {
        wordRepository.updateWords(words);
    }
    void deleteWords(Word... words) {
        wordRepository.deleteWords(words);
    }
    public void deleteAllWords() {
        wordRepository.deleteAllWords();
    }

    void insertLessons(Lesson... lessons) {
        lessonResposity.insertLessons(lessons);
    }
    void updateLessons(Lesson... lessons) {
        lessonResposity.updateLessons(lessons);
    }
    void deleteLessons(Lesson... lessons) {
        lessonResposity.deleteLessons(lessons);
    }
    void deleteAllLessons() { lessonResposity.deleteAllLessons(); }
}
