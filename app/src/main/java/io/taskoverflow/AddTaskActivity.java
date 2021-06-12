package io.taskoverflow;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class AddTaskActivity extends AppCompatActivity {

    Spinner spinnerCategory;
    Spinner spinnerDueDate;
    Spinner spinnerDueHour;
    Spinner spinnerReminder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        spinnerCategory = findViewById(R.id.spinnerCategory);
        spinnerDueDate = findViewById(R.id.spinnerDueDate);
        spinnerDueHour = findViewById(R.id.spinnerDueHour);
        spinnerReminder = findViewById(R.id.spinnerReminder);

        String[] items1 = new String[]{"-- Choose your category --", "2", "3"};
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


    }
}