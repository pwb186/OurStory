package com.pabopwb.ourstory.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
/**
 * 表
 **/
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
    private long timeStamp; //时间戳

    @ColumnInfo(name = "story_title")
    private String title;

    @ColumnInfo(name = "story_text")
    private String text;

    @ColumnInfo(name = "story_image")
    private String imgUrl;

    public EntityStory(long userId, String userName, String userSlogan, String storyCreateTime, long timeStamp, String title, String text, String imgUrl) {
        this.userId = userId;
        this.userName = userName;
        this.userSlogan = userSlogan;
        this.storyCreateTime = storyCreateTime;
        this.timeStamp = timeStamp;
        this.title = title;
        this.text = text;
        this.imgUrl = imgUrl;
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

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
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

    public long getTimeStamp() {
        return timeStamp;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getImgUrl() { return imgUrl; }

    public void setImgUrl(String imgUrl) { this.imgUrl = imgUrl; }
}
