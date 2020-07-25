package com.sanmed.gorillabook.model.database;

import android.content.Context;

import androidx.room.Room;

public class FeedDataBaseHelper {
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
}
