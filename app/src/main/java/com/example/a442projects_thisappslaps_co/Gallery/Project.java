package com.example.a442projects_thisappslaps_co.Gallery;

public class Project {

    private String mUri;
    private long mTimestamp;

    public Project(String uri, long timestamp) {
        mUri = uri;
        mTimestamp = timestamp;
    }

    public String getUri() {
        return mUri;
    }

    public long getTimestamp() {
        return mTimestamp;
    }
}
