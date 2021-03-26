package io.taskoverflow.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "categories")
public class Category {
    @PrimaryKey
    @ColumnInfo(name = "category_id")
    public int id;
    @ColumnInfo(name = "category_name")
    public String name;
    @ColumnInfo(name = "category_color")
    public String color;
}
