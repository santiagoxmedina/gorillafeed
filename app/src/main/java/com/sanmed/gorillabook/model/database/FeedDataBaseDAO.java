package com.sanmed.gorillabook.model.database;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;


@Dao
public interface  FeedDataBaseDAO {

    @Insert
    void insert(Feed feed);

    @Update
    void update(Feed feed);


    @Query("SELECT * FROM feed_table ORDER BY id DESC")
    Flowable<List<Feed>> getAllFeed();


    @Query("SELECT * FROM feed_table ORDER BY id DESC LIMIT 1")
    Feed getLastFeed();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable saveFeed(Feed[] feeds);
}
