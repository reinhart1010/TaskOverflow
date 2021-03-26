package io.taskoverflow;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "task_tags")
public class TaskTag {
    @PrimaryKey
    @ColumnInfo(name = "task_id")
    public int taskId;
    @PrimaryKey
    @ColumnInfo(name = "tag_id")
    public int attachmentId;
}