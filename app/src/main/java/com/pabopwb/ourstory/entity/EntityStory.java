package com.pabopwb.ourstory.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class EntityStory {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "story_id")
    private long storyId;

    @ColumnInfo(name = "story_user_Id")
    private long userId;

    @ColumnInfo(name = "story_user_name")
    private String userName;

    @ColumnInfo(name = "story_user_slogan")
    private String userSlogan;  //创建用户的个性签名

    @ColumnInfo(name = "story_create_time")
    private String storyCreateTime;

    @ColumnInfo(name = "story_timestamp")
    private long timestamp; //时间戳

    @ColumnInfo(name = "story_title")
    private String title;

    @ColumnInfo(name = "story_text")
    private String text;

    public EntityStory(long storyId, long userId, String userName, String userSlogan, String storyCreateTime, long timestamp, String title, String text) {
        this.storyId = storyId;
        this.userId = userId;
        this.userName = userName;
        this.userSlogan = userSlogan;
        this.storyCreateTime = storyCreateTime;
        this.timestamp = timestamp;
        this.title = title;
        this.text = text;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserSlogan(String userSlogan) {
        this.userSlogan = userSlogan;
    }

    public void setStoryCreateTime(String storyCreateTime) {
        this.storyCreateTime = storyCreateTime;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setStoryId(long storyId) {
        this.storyId = storyId;
    }

    public long getStoryId() {
        return storyId;
    }

    public long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserSlogan() {
        return userSlogan;
    }

    public String getStoryCreateTime() {
        return storyCreateTime;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
