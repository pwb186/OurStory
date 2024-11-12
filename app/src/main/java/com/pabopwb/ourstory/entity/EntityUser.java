package com.pabopwb.ourstory.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class EntityUser {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_is")
    public long userId;

    @ColumnInfo(name = "user_name")
    public String userName;

    @ColumnInfo(name = "user_create_time")
    public String userCreateTime;

    @ColumnInfo(name = "user_slogan")
    public String userSlogan;  //个性签名


}
