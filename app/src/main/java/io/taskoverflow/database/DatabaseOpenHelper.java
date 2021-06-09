package io.taskoverflow.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOpenHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "taskoverflow";
    public static final String DATABASE_TABLE_ATTACHMENTS = "attachments";
    public static final String DATABASE_TABLE_CATEGORIES = "categories";
    public static final String DATABASE_TABLE_TAGS = "tags";
    public static final String DATABASE_TABLE_TASKS = "tasks";
    public static final String DATABASE_TABLE_TASK_ATTACHMENTS = "task_attachments";
    public static final String DATABASE_TABLE_TASK_TAGS = "task_tags";

    public DatabaseOpenHelper(Context ctx){
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createAttachmentsTable(db, DATABASE_TABLE_ATTACHMENTS);
        createCategoriesTable(db, DATABASE_TABLE_CATEGORIES);
        createTagsTable(db, DATABASE_TABLE_TAGS);
        createTasksTable(db, DATABASE_TABLE_TASKS);
        createTaskAttachmentsTable(db, DATABASE_TABLE_TASK_ATTACHMENTS);
        createTaskTagsTable(db, DATABASE_TABLE_TASK_TAGS);

        // Add default categories
        String[] defaultCategories = {"Campus", "Work", "Personal", "Events"};
        String[] defaultCategoryColors = {"#C6009A", "#0D9488", "#F2BA4E", "#9EA27B"};
        for (int i = 0; i < defaultCategories.length; i++){
            ContentValues values = new ContentValues();
            values.put("category_name", defaultCategories[i]);
            values.put("category_color", defaultCategoryColors[i]);
            db.insert(DATABASE_TABLE_CATEGORIES, null, values);
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
                "FOREIGN KEY(task_id) REFERENCES tasks(task_id))," +
                "FOREIGN KEY(attachment_id) REFERENCES attachments(attachment_id));";
        db.execSQL(sb);
    }

    private void createTaskTagsTable(SQLiteDatabase db, String tableName){
        String sb = "CREATE TABLE " + tableName + " (" +
                "task_id INTEGER NOT NULL," +
                "tag_id INTEGER NOT NULL," +
                "PRIMARY KEY(task_id, tag_id)," +
                "FOREIGN KEY(task_id) REFERENCES tasks(task_id))," +
                "FOREIGN KEY(tag_id) REFERENCES tags(tag_id));";
        db.execSQL(sb);
    }
}
