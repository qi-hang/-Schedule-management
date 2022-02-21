package com.mycompany.zqh;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Lesson.class},version = 1,exportSchema = false)
public abstract class LessonDatabase extends RoomDatabase {
    private static LessonDatabase INSTANCE;
    static synchronized LessonDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),LessonDatabase.class,"lesson_database")
                    //.fallbackToDestructiveMigration()
                    //.addMigrations(MIGRATION_N_M)
                    .build();
        }
        return INSTANCE;
    }
    public abstract LessonDao getLessonDao();

}
