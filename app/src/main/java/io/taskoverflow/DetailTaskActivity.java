package io.taskoverflow;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
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
        String titleDb = taskCursor.getString(DatabaseOpenHelper.DATABASE_COLUMN_INDEX_TASKS.get("task_name"));
        if (titleDb != null && titleDb.length() > 0) title.setText(titleDb);

        TextView notes = findViewById(R.id.tvNotes);
        String notesDb = taskCursor.getString(DatabaseOpenHelper.DATABASE_COLUMN_INDEX_TASKS.get("task_note"));
        if (notes != null && notes.length() > 0) notes.setText(notesDb);

        if (!taskCursor.isNull(DatabaseOpenHelper.DATABASE_COLUMN_INDEX_TASKS.get("category_id"))) {
            Long categoryId = taskCursor.getLong(DatabaseOpenHelper.DATABASE_COLUMN_INDEX_TASKS.get("category_id"));
            Cursor categoryCursor = db.query("categories", null, "category_id = " + categoryId, null, null, null, null, null);
            categoryCursor.moveToFirst();
            TextView category = findViewById(R.id.tvCategory2);
            category.setText(categoryCursor.getString(DatabaseOpenHelper.DATABASE_COLUMN_INDEX_CATEGORIES.get("category_name")));
            categoryCursor.close();
        }
    }

    // Make sure that the back button is working
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return true;
    }

    public void delete(View view){
        db.delete("tasks", "task_id = " + taskId, null);
        // Clear back stack, force RecyclerView update
        Intent intent = new Intent(DetailTaskActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}