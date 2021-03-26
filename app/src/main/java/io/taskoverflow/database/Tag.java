package io.taskoverflow.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tags")
public class Tag {
    @PrimaryKey
    @ColumnInfo(name = "tag_id")
    public int id;
    @ColumnInfo(name = "tag_name")
    public String name;
    @ColumnInfo(name = "tag_color")
    public String color;
}
