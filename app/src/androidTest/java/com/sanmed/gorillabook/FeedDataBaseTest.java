package com.sanmed.gorillabook;

import android.content.Context;

import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.sanmed.gorillabook.model.database.Feed;
import com.sanmed.gorillabook.model.database.FeedDataBase;
import com.sanmed.gorillabook.model.database.FeedDataBaseDAO;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import kotlin.jvm.Throws;

import static org.junit.Assert.*;
public class FeedDataBaseTest {

    private FeedDataBaseDAO feedDataBaseDAO;
    private FeedDataBase dataBase;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        dataBase = Room.inMemoryDatabaseBuilder(context, FeedDataBase.class).allowMainThreadQueries().build();
        feedDataBaseDAO = dataBase.getFeedDataBaseDAO();
    }

    @After
    @Throws(exceptionClasses = IOException.class)
    public void closeDb() {
        dataBase.close();
    }

    @Test
    @Throws(exceptionClasses = IOException.class)
    public void insertAndGetFeed() {
        Feed feed = new Feed();
        feed.firstName = "test first name";
        feedDataBaseDAO.insert(feed);
        Feed lastFeed = feedDataBaseDAO.getLastFeed();
        assertEquals(lastFeed.firstName, feed.firstName);
    }
}