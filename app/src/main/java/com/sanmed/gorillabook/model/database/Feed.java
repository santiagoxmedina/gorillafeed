package com.sanmed.gorillabook.model.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "feed_table")
public class Feed {
    @PrimaryKey(autoGenerate = true)
    public int  id = 0;

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;

    @ColumnInfo(name = "post_body")
    public String postBody;

    @ColumnInfo(name = "unix_timestamp")
    public long unixTimestamp = System.currentTimeMillis();
}
