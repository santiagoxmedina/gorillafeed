package com.sanmed.gorillabook.model.models;

import com.sanmed.gorillabook.model.database.Feed;
import com.sanmed.gorillabook.view.common.FeedUI;

import java.util.ArrayList;
import java.util.List;

public class FeedHelper {

    public static Feed[] parseResponses(FeedResponse[] responses){
        Feed[] feeds = new Feed[responses.length];
        for (int i = 0; i < feeds.length; i++) {
            feeds[i] = parseResponse(responses[i]);
        }
        return feeds;
    }

    private static Feed parseResponse(FeedResponse response) {
        Feed feed = new Feed();
        feed.id = response.id;
        feed.firstName = response.firstName;
        feed.lastName = response.lastName;
        feed.postBody = response.postBody;
        feed.unixTimestamp = Long.parseLong(response.unixTimestamp);
        return feed;
    }

    public static List<FeedUI> parseFeeds(List<Feed> feeds) {
        if(feeds!=null){
            List<FeedUI> result = new ArrayList<>();
            for (Feed feed : feeds) {
                result.add(parseFeed(feed));
            }
            return result;
        }
        return null;
    }

    public static FeedUI parseFeed(Feed feed) {
        return null;
    }
}
