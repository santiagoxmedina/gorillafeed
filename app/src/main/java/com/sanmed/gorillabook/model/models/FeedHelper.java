package com.sanmed.gorillabook.model.models;

import com.sanmed.gorillabook.model.database.Feed;

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
}
