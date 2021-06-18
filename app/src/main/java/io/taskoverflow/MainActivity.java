package io.taskoverflow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;

import io.taskoverflow.adapter.TasksAdapter;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private RecyclerView rv;
    private TasksAdapter adapter;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        SQLiteDatabase db = new DatabaseOpenHelper(getApplicationContext()).getReadableDatabase();

        // Initialize RecyclerView
        rv = findViewById(R.id.home_tasks_recyclerview);
        adapter = new TasksAdapter(this);

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

        // ini cuman buat tes layout detail task.. hapus aja kalo udah gabutuh, tenkyuu :D
        Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
        startActivity(intent);

        // ini untuk navigation bar
        drawerLayout = findViewById(R.id.drawerLayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_open, R.string.navigation_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull @NotNull MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.Today:
                adapter.refreshByToday();
                break;
            case R.id.AllTasks:
                adapter.refresh();
                break;
            case R.id.Campus:
                adapter.refresh((long) 1);
                break;
            case R.id.Work:
                adapter.refresh((long) 2);
                break;
            case R.id.Personal:
                adapter.refresh((long) 3);
                break;
            case R.id.Events:
                adapter.refresh((long) 4);
                break;
        }
        return true;
    }
}