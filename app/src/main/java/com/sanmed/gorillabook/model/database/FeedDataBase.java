package com.sanmed.gorillabook.model.database;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Feed.class}, version = 2,exportSchema = false)
public abstract class FeedDataBase extends RoomDatabase {
    public abstract FeedDataBaseDAO getFeedDataBaseDAO();
}
