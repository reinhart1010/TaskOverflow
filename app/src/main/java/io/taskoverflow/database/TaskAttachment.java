package io.taskoverflow.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "task_attachments")
public class TaskAttachment {
    @PrimaryKey
    @ColumnInfo(name = "task_id")
    public int taskId;
    @PrimaryKey
    @ColumnInfo(name = "attachment_id")
    public int attachmentId;
}