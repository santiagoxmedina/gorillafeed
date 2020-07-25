package com.sanmed.gorillabook.model.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Feed.class, version = 2, exportSchema = false)
public abstract class FeedDataBase extends RoomDatabase {
        public abstract FeedDataBaseDAO getFeedDataBaseDAO();
        private static FeedDataBase INSTANCE;

        public static FeedDataBase getInstance(Context context){
            if(INSTANCE==null){
                INSTANCE= Room.databaseBuilder(context.getApplicationContext(),FeedDataBase.class,"feed_database").fallbackToDestructiveMigration().build();
            }
            return INSTANCE;

        }
}
