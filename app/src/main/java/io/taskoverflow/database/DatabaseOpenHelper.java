package io.taskoverflow.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;

public class DatabaseOpenHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "taskoverflow";
    public static final String DATABASE_TABLE_ATTACHMENTS = "attachments";
    public static final String DATABASE_TABLE_CATEGORIES = "categories";
    public static final String DATABASE_TABLE_TAGS = "tags";
    public static final String DATABASE_TABLE_TASKS = "tasks";
    public static final String DATABASE_TABLE_TASK_ATTACHMENTS = "task_attachments";
    public static final String DATABASE_TABLE_TASK_TAGS = "task_tags";

    public static final HashMap<String, Integer> DATABASE_COLUMN_INDEX_ATTACHMENTS = new HashMap<>();
    public static final String[] DATABASE_COLUMNS_ATTACHMENTS = {"attachment_id", "attachment_name", "attachment_type", "attachment_plaintext", "attachment_blob"};

    public static final HashMap<String, Integer> DATABASE_COLUMN_INDEX_CATEGORIES = new HashMap<>();
    public static final String[] DATABASE_COLUMNS_CATEGORIES = {"category_id", "category_name", "category_color"};

    public static final HashMap<String, Integer> DATABASE_COLUMN_INDEX_TAGS = new HashMap<>();
    public static final String[] DATABASE_COLUMNS_TAGS = {"tag_id", "tag_name", "tag_color"};

    public static final HashMap<String, Integer> DATABASE_COLUMN_INDEX_TASKS = new HashMap<>();
    public static final String[] DATABASE_COLUMNS_TASKS = {"task_id", "task_name", "category_id", "task_due_date", "task_reminder_date", "task_location_lat", "task_location_lng", "task_location_name", "task_location_url", "task_note"};

    public static final HashMap<String, Integer> DATABASE_COLUMN_INDEX_TASK_ATTACHMENTS = new HashMap<>();
    public static final String[] DATABASE_COLUMNS_TASK_ATTACHMENTS = {"task_id", "attachment_id"};

    public static final HashMap<String, Integer> DATABASE_COLUMN_INDEX_TASK_TAGS = new HashMap<>();
    public static final String[] DATABASE_COLUMNS_TASK_TAGS = {"task_id", "tag_id"};

    public DatabaseOpenHelper(Context ctx){
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
        int i;
        for (i = 0; i < DATABASE_COLUMNS_ATTACHMENTS.length; i++) DATABASE_COLUMN_INDEX_ATTACHMENTS.put(DATABASE_COLUMNS_ATTACHMENTS[i], i);
        for (i = 0; i < DATABASE_COLUMNS_CATEGORIES.length; i++) DATABASE_COLUMN_INDEX_CATEGORIES.put(DATABASE_COLUMNS_CATEGORIES[i], i);
        for (i = 0; i < DATABASE_COLUMNS_TAGS.length; i++) DATABASE_COLUMN_INDEX_TAGS.put(DATABASE_COLUMNS_TAGS[i], i);
        for (i = 0; i < DATABASE_COLUMNS_TASKS.length; i++) DATABASE_COLUMN_INDEX_TASKS.put(DATABASE_COLUMNS_TASKS[i], i);
        for (i = 0; i < DATABASE_COLUMNS_TASK_ATTACHMENTS.length; i++) DATABASE_COLUMN_INDEX_TASK_ATTACHMENTS.put(DATABASE_COLUMNS_TASK_ATTACHMENTS[i], i);
        for (i = 0; i < DATABASE_COLUMNS_TASK_TAGS.length; i++) DATABASE_COLUMN_INDEX_TASK_TAGS.put(DATABASE_COLUMNS_TASK_TAGS[i], i);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createAttachmentsTable(db, DATABASE_TABLE_ATTACHMENTS);
        createCategoriesTable(db, DATABASE_TABLE_CATEGORIES);
        createTagsTable(db, DATABASE_TABLE_TAGS);
        createTasksTable(db, DATABASE_TABLE_TASKS);
        createTaskAttachmentsTable(db, DATABASE_TABLE_TASK_ATTACHMENTS);
        createTaskTagsTable(db, DATABASE_TABLE_TASK_TAGS);

        int i;

        // Add default categories
        String[] defaultCategories = {"Campus", "Work", "Personal", "Events"};
        String[] defaultCategoryColors = {"#C6009A", "#0D9488", "#F2BA4E", "#9EA27B"};
        for (i = 0; i < defaultCategories.length; i++){
            ContentValues values = new ContentValues();
            values.put("category_name", defaultCategories[i]);
            values.put("category_color", defaultCategoryColors[i]);
            db.insert(DATABASE_TABLE_CATEGORIES, null, values);
        }

        // Add default tasks
        String[] defaultTasks = {"Task 1", "Task 2", "Task 3"};
        for (i = 0; i < defaultTasks.length; i++){
            ContentValues values = new ContentValues();
            values.put("task_name", defaultTasks[i]);
            db.insert(DATABASE_TABLE_TASKS, null, values);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void createAttachmentsTable(SQLiteDatabase db, String tableName){
        String sb = "CREATE TABLE " + tableName + " (" +
                "attachment_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "attachment_name TEXT NOT NULL," +
                "attachment_type INT NOT NULL," +
                "attachment_plaintext TEXT," +
                "attachment_blob BLOB);";
        db.execSQL(sb);
    }

    private void createCategoriesTable(SQLiteDatabase db, String tableName){
        String sb = "CREATE TABLE " + tableName + " (" +
                "category_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "category_name TEXT NOT NULL," +
                "category_color TEXT NOT NULL);";
        db.execSQL(sb);
    }

    private void createTagsTable(SQLiteDatabase db, String tableName){
        String sb = "CREATE TABLE " + tableName + " (" +
                "tag_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "tag_name TEXT NOT NULL," +
                "tag_color TEXT NOT NULL);";
        db.execSQL(sb);
    }

    private void createTasksTable(SQLiteDatabase db, String tableName){
        String sb = "CREATE TABLE " + tableName + " (" +
                "task_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "task_name TEXT NOT NULL," +
                "category_id INTEGER," +
                "task_due_date TEXT," +
                "task_reminder_date TEXT," +
                "task_location_lat REAL," +
                "task_location_lng REAL" +
                "task_location_name TEXT," +
                "task_location_url TEXT," +
                "task_note TEXT," +
                "FOREIGN KEY(category_id) REFERENCES categories(category_id));";
        db.execSQL(sb);
    }

    private void createTaskAttachmentsTable(SQLiteDatabase db, String tableName){
        String sb = "CREATE TABLE " + tableName + " (" +
                "task_id INTEGER NOT NULL," +
                "attachment_id INTEGER NOT NULL," +
                "PRIMARY KEY(task_id, attachment_id)," +
                "FOREIGN KEY(task_id) REFERENCES tasks(task_id)," +
                "FOREIGN KEY(attachment_id) REFERENCES attachments(attachment_id));";
        db.execSQL(sb);
    }

    private void createTaskTagsTable(SQLiteDatabase db, String tableName){
        String sb = "CREATE TABLE " + tableName + " (" +
                "task_id INTEGER NOT NULL," +
                "tag_id INTEGER NOT NULL," +
                "PRIMARY KEY(task_id, tag_id)," +
                "FOREIGN KEY(task_id) REFERENCES tasks(task_id)," +
                "FOREIGN KEY(tag_id) REFERENCES tags(tag_id));";
        db.execSQL(sb);
    }
}
