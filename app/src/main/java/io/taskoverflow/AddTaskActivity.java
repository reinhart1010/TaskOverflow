package io.taskoverflow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

import io.taskoverflow.database.DatabaseOpenHelper;

public class AddTaskActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    private Cursor categoryCursor;

    Spinner spinnerCategory;
    Spinner spinnerDueDate;
    Spinner spinnerDueHour;
    Spinner spinnerReminder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int i;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        db = new DatabaseOpenHelper(getApplicationContext()).getReadableDatabase();
        categoryCursor = db.query("categories", null, null, null, null, null, null, null);

        spinnerCategory = findViewById(R.id.spinnerCategory);
        spinnerDueDate = findViewById(R.id.spinnerDueDate);
        spinnerDueHour = findViewById(R.id.spinnerDueHour);
        spinnerReminder = findViewById(R.id.spinnerReminder);

        ArrayList<String> items1 = new ArrayList<>();
        items1.add("None");
        for (i = 0; i < categoryCursor.getCount(); i++){
            categoryCursor.moveToPosition(i);
            items1.add("" + categoryCursor.getLong(DatabaseOpenHelper.DATABASE_COLUMN_INDEX_CATEGORIES.get("category_id")) + " - " + categoryCursor.getString(DatabaseOpenHelper.DATABASE_COLUMN_INDEX_CATEGORIES.get("category_name")));
        }

        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items1);
        spinnerCategory.setAdapter(adapter1);

        String[] items2 = new String[]{"Day, dd month yyyy", "2", "3"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items2);
        spinnerDueDate.setAdapter(adapter2);

        String[] items3 = new String[]{"hh : mm", "2", "3"};
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items3);
        spinnerDueHour.setAdapter(adapter3);

        String[] items4 = new String[]{"1 day before", "2", "3"};
        ArrayAdapter<String> adapter4 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items4);
        spinnerReminder.setAdapter(adapter4);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void add(View view){
        EditText taskTitle = findViewById(R.id.add_task_title);
        EditText taskNotes = findViewById(R.id.add_task_notes);
        ContentValues values = new ContentValues();

        if (taskTitle.getText().toString().length() > 0) values.put("task_name",  taskTitle.getText().toString());
        if (taskNotes.getText().toString().length() > 0) values.put("task_note",  taskNotes.getText().toString());

        String[] category = spinnerCategory.getSelectedItem().toString().split(" - ", 2);
        if (category.length > 1){
            long categoryId = Long.parseLong(category[0]);
            values.put("category_id", categoryId);
        }

        db.insert("tasks", null, values);

        // Clear back stack
        Intent intent = new Intent(AddTaskActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}