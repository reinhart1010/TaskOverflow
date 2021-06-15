package io.taskoverflow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import io.taskoverflow.adapter.TasksAdapter;
import io.taskoverflow.database.DatabaseOpenHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        SQLiteDatabase db = new DatabaseOpenHelper(getApplicationContext()).getReadableDatabase();

        // Initialize RecyclerView
        RecyclerView rv = findViewById(R.id.home_tasks_recyclerview);
        TasksAdapter adapter = new TasksAdapter(this);

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
    }
}