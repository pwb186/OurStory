package com.pabopwb.ourstory.entity;


public class EntityCard {
    private long cardID;
    private String introduction;  //创建用户的个性签名
    private String storyCreateTime;
    private String title;
    private String text;

    public EntityCard() {
        this.cardID = cardID;
        this.introduction = introduction;
        this.storyCreateTime = storyCreateTime;
        this.title = title;
        this.text = text;
    }

    public long getCardID() {
        return cardID;
    }

    public void setCardID(long cardID) {
        this.cardID = cardID;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getStoryCreateTime() {
        return storyCreateTime;
    }

    public void setStoryCreateTime(String storyCreateTime) {
        this.storyCreateTime = storyCreateTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
