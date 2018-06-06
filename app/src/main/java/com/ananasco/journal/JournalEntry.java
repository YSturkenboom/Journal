package com.ananasco.journal;

import android.support.annotation.Nullable;

import java.io.Serializable;

/**
 *
 */

public class JournalEntry implements Serializable {

    private int id;
    private String title;
    private String content;
    private String mood;
    private long timestamp;

    public JournalEntry(int id, String title, String content, String mood, long timestamp) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.mood = mood;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
