package com.mycompany.zqh;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class LessonResposity {
    private LiveData<List<Lesson>>allLessonsLive;
    private LessonDao lessonDao;

    public LessonResposity(Context context) {
        LessonDatabase lessonDatabase = LessonDatabase.getDatabase(context.getApplicationContext());
        lessonDao=lessonDatabase.getLessonDao();
        allLessonsLive = lessonDao.getAllLessonsLive();
    }

    public LiveData<List<Lesson>> getAllLessonsLive() {
        return allLessonsLive;
    }

    void insertLessons(Lesson... lessons) {
        new InsertAsyncTask(lessonDao).execute(lessons);
    }

    void updateLessons(Lesson... lessons) {
        new UpdateAsyncTask(lessonDao).execute(lessons);
    }

    void deleteLessons(Lesson... lessons) {
        new DeleteAsyncTask(lessonDao).execute(lessons);
    }

    void deleteAllLessons(Lesson... lessons) {
        new DeleteAllAsyncTask(lessonDao).execute();
    }


    static class InsertAsyncTask extends AsyncTask<Lesson, Void, Void> {
        private LessonDao lessonDao;

        InsertAsyncTask(LessonDao lessonDao) {
            this.lessonDao = lessonDao;
        }

        @Override
        protected Void doInBackground(Lesson... lessons) {
            lessonDao.insertLessons(lessons);
            return null;
        }

    }

    static class UpdateAsyncTask extends AsyncTask<Lesson, Void, Void> {
        private LessonDao lessonDao;

        UpdateAsyncTask(LessonDao lessonDao) {
            this.lessonDao = lessonDao;
        }

        @Override
        protected Void doInBackground(Lesson... lessons) {
            lessonDao.updateLessons(lessons);
            return null;
        }
    }

    static class DeleteAsyncTask extends AsyncTask<Lesson, Void, Void> {
        private LessonDao lessonDao;
        DeleteAsyncTask(LessonDao lessonDao) {
            this.lessonDao = lessonDao;
        }
        @Override
        protected Void doInBackground(Lesson... lessons) {
            lessonDao.deleteLessons(lessons);
            return null;
        }
    }

    static class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
        private LessonDao lessonDao;
        DeleteAllAsyncTask(LessonDao lessonDao) {
            this.lessonDao = lessonDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            lessonDao.deleteAllLessons();
            return null;
        }
    }


}
