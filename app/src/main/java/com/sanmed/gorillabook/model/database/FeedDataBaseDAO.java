package com.sanmed.gorillabook.model.database;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

import rx.Completable;


@Dao
public interface  FeedDataBaseDAO {

    @Insert
    Completable insert(Feed feed);

    @Update
    Completable update(Feed feed);


    @Query("SELECT * FROM feed_table ORDER BY id DESC")
    LiveData<List<Feed>> getAllFeed();


    @Query("SELECT * FROM feed_table ORDER BY id DESC LIMIT 1")
    Feed getLastFeed();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable saveFeed(Feed[] feeds);
}
