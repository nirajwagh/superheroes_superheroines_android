package com.theminimaldeveloper.superheroes;

public class ModelVideoDetails {

    String videoID, title, channelName, thumbnailUrl;

    public ModelVideoDetails(String videoID, String title, String channelName, String thumbnailUrl) {
        this.videoID = videoID;
        this.title = title;
        this.channelName = channelName;
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getVideoID() {
        return videoID;
    }

    public String getTitle() {
        return title;
    }

    public String getChannelName() {
        return channelName;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }
}
