package com.mycompany.zqh;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//singleton
@Database(entities = {Word.class},version = 1,exportSchema = false)
public abstract class WordDatabase extends RoomDatabase {
    private static WordDatabase INSTANCE;
    static synchronized WordDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),WordDatabase.class,"word_database")
                   //.fallbackToDestructiveMigration()
                    //.addMigrations(MIGRATION_N_M)
                    .build();
        }
        return INSTANCE;
    }
    public abstract WordDao getWordDao();

    /*static  final Migration MIGRATION_N_M =new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE word ADD COLUMN bar_data INTEGER NOT NULL DEFAULYT 1");
        }版本迁移
    }*/

   /* static  final Migration MIGRATION_N_M =new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE word_temp(id INTEGER PRIMARY KEY NOT NULL,liudelie TEXT,"+
                    "shili TEXT)");//建新表
            database.execSQL("INSERT INTO word_temp(id,liudelie,shili)"+
                    "SELECT id,liudelie,shili FROM word");//迁移
            database.execSQL("DROP TABLE word");//删旧的
            database.execSQL("ALTER TABLE word_temp RENAME to word");//重命名
        }*/
}
