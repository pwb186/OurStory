package com.pabopwb.ourstory.room;

import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
/**
 * 创建一个迁移脚本来更新数据库结构。Room允许在迁移中定义SQL操作
 */
public class MyMigration {
    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // 添加新的email列到user表
            database.execSQL("ALTER TABLE EntityStory ADD COLUMN story_image TEXT");
        }
    };
}

