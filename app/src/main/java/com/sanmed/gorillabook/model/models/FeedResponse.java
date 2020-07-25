package com.sanmed.gorillabook.model.models;

import com.google.gson.annotations.SerializedName;

public class FeedResponse {
    @SerializedName("id")
    public int id;
    @SerializedName("first_name")
    public String firstName;
    @SerializedName("last_name")
    public String lastName;
    @SerializedName("post_body")
    public String postBody;
    @SerializedName("unix_timestamp")
    public String unixTimestamp;
}
