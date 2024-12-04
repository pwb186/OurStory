package com.pabopwb.ourstory.room;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.pabopwb.ourstory.entity.EntityStory;


/**
 * 数据库 每次迭代version加1
 * @Database(entities = {EntityStory.class}, version = 2, exportSchema = false)
 * public abstract class InitDataBase extends RoomDatabase {
 *     public abstract StoryDao storyDao();
 *
 *     static final Migration MIGRATION_1_2 = new Migration(1, 2) {
 *         @Override
 *         public void migrate(@NonNull SupportSQLiteDatabase database) {
 *             // 添加新的列 'story_image'
 *             database.execSQL("ALTER TABLE EntityStory ADD COLUMN story_image TEXT");
 *         }
 *     };
 * }
 * InitDataBase database = Room.databaseBuilder(context, InitDataBase.class, "your_database_name")
 *     .addMigrations(InitDataBase.MIGRATION_1_2) // 应用迁移策略
 *     .build();
 * */
@Database(entities = {EntityStory.class}, version = 2, exportSchema = false)
public abstract class InitDataBase extends RoomDatabase {
    public abstract StoryDao storyDao();

    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            // 添加新的列 'story_image'
            database.execSQL("ALTER TABLE EntityStory ADD COLUMN story_image TEXT");
        }
    };
}
