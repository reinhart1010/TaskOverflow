package io.taskoverflow.adapter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import io.taskoverflow.R;
import io.taskoverflow.Task;
import io.taskoverflow.database.DatabaseOpenHelper;

public class TasksAdapter {
    TasksAdapter(){
        int i = 0;
    }
}

// public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.TasksViewHolder> {
//     private Context ctx;
//     private SQLiteDatabase db;
//     private Cursor cursor;
//     private int total;
//
//     public TasksAdapter(Context ctx){
//         this.ctx = ctx;
//         this.db = new DatabaseOpenHelper(ctx).getReadableDatabase();
//         this.cursor = db.query(DatabaseOpenHelper.DATABASE_TABLE_TASKS, null, null, null, null, null, null);
//         this.total = cursor.getCount();
//     }
//
//     public static class TasksViewHolder extends RecyclerView.ViewHolder {
//         private final LinearLayout expenseItemContainer;
//         private final TextView expenseNameTV, expenseNominalTV, expenseDateTV;
//
//         public TasksViewHolder(@NonNull View view) {
//             super(view);
//             expenseItemContainer = view.findViewById(R.id.expense_item_container);
//             expenseNameTV = view.findViewById(R.id.expense_name);
//             expenseNominalTV = view.findViewById(R.id.expense_nominal);
//             expenseDateTV = view.findViewById(R.id.expense_date);
//         }
//
//         public LinearLayout getExpenseItemContainer() { return expenseItemContainer; }
//
//         public TextView getExpenseNameTV() { return expenseNameTV; }
//
//         public TextView getExpenseNominalTV() { return expenseNominalTV; }
//
//         public TextView getExpenseDateTV() { return expenseDateTV; }
//     }
//
//     @Override
//     public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull TasksAdapter.TasksViewHolder holder, int position) {
//
//     }
//
//     @Override
//     public int getItemCount() { return total; }
// }
