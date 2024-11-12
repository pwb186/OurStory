package com.pabopwb.ourstory.dao;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.pabopwb.ourstory.entity.EntityStory;

/**
 * 每次迭代version加1
 * */
@Database(entities = {EntityStory.class}, version = 1, exportSchema = false)
public abstract class InitDataBase extends RoomDatabase {
    public abstract StoryDao storyDao();
}
