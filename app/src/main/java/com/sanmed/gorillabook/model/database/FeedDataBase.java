package com.sanmed.gorillabook.model.database;


import android.app.Application;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Feed.class, version = 2, exportSchema = false)
public abstract class FeedDataBase extends RoomDatabase {

    private static volatile FeedDataBase INSTANCE;

    public static FeedDataBase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (FeedDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            FeedDataBase.class, "Feed.db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract FeedDataBaseDAO getFeedDataBaseDAO();
}
