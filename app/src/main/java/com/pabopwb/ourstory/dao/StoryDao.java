package com.pabopwb.ourstory.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.pabopwb.ourstory.entity.EntityStory;

import java.util.List;

@Dao
public interface StoryDao {
    @Query("SELECT * FROM EntityStory")
    List<EntityStory> getAllStory();


    @Query("SELECT * FROM EntityStory where story_user_Id=:story_userid")
    List<EntityStory> getAllStoryByUserId(long story_userid);

    @Query("SELECT * FROM EntityStory where story_id=:storyid")
    EntityStory getStoryById(long storyid);

    @Insert
    long insertStory(EntityStory note);

    @Delete
    void deleteStory(EntityStory note);

    @Query("DELETE FROM EntityStory where story_id=:storyId")
    void deleteStoryById(long storyId);

    @Update
    void updateStory(EntityStory note);
}
