package io.taskoverflow;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tasks")
public class Task {
    @PrimaryKey
    @ColumnInfo(name = "task_id")
    public int id;
    @ColumnInfo(name = "task_name")
    public String name;
    @ColumnInfo(name = "category_id")
    public int categoryId;
    /**
     * Pake Unix Timestamp (https://en.wikipedia.org/wiki/Unix_time)
     */
    @ColumnInfo(name = "task_due_date")
    public int dueDate;
    @ColumnInfo(name = "task_reminder_date")
    public int reminderDate;
    @ColumnInfo(name = "task_location_lat")
    public double locationLat;
    @ColumnInfo(name = "task_location_lng")
    public double locationLng;
    @ColumnInfo(name = "task_location_name")
    public String locationName;
    @ColumnInfo(name = "task_location_url")
    public String locationURL;
    @ColumnInfo(name = "task_note")
    public String note;
}
