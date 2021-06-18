package io.taskoverflow;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import io.taskoverflow.database.DatabaseOpenHelper;

public class DetailTaskActivity extends AppCompatActivity {
    private long taskId;
    private SQLiteDatabase db;
    private Cursor taskCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_task);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        taskId = extras.getLong("taskId", -1);
        db = new DatabaseOpenHelper(getApplicationContext()).getReadableDatabase();
        taskCursor = db.query("tasks", null, "task_id = " + taskId, null, null, null, null, null);
        taskCursor.moveToFirst();

        TextView title = findViewById(R.id.tvTitle2);
        title.setText(taskCursor.getString(DatabaseOpenHelper.DATABASE_COLUMN_INDEX_TASKS.get("task_name")));

        TextView notes = findViewById(R.id.tvNotes);
        title.setText(taskCursor.getString(DatabaseOpenHelper.DATABASE_COLUMN_INDEX_TASKS.get("task_note")));

        Cursor categoryCursor = db.query("categories", null, "category_id = " + taskCursor.getLong(DatabaseOpenHelper.DATABASE_COLUMN_INDEX_TASKS.get("category_id")), null, null, null, null, null);
        categoryCursor.moveToFirst();
        TextView category = findViewById(R.id.tvCategory2);
        category.setText(categoryCursor.getString(DatabaseOpenHelper.DATABASE_COLUMN_INDEX_CATEGORIES.get("category_id")));
    }

    // Make sure that the back button is working
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}