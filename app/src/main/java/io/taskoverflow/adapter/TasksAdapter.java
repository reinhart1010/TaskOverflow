package io.taskoverflow.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import io.taskoverflow.R;
import io.taskoverflow.Task;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.TasksViewHolder> {
    ArrayList<Task> tasks;
    Context ctx;

    public static class TasksViewHolder extends RecyclerView.ViewHolder {
        private final TextView transactionIdTV, productIdTV;

        public TasksViewHolder(View view) {
            super(view);
//            transactionIdTV = view.findViewById(R.id.transaction_id);
//            productIdTV = view.findViewById(R.id.product_id);
        }

        public TextView getTransactionIdTV() { return transactionIdTV; }

        public TextView getProductIdTV() { return productIdTV; }
    }

    // Public constructor
    /**
     * Creates a new RecyclerViewAdapter based on the given ArrayList.
     */
    public TasksAdapter(Context ctx) { this.ctx = ctx; }

    public void setTransactions(ArrayList<Task> tasks) { this.tasks = tasks; }

    @Override
    public int getItemCount() { return tasks.size(); }

    @NonNull
    @Override
    public TasksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_item, parent, false);
//        return new TasksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TasksAdapter.TasksViewHolder holder, int position) {
//        holder.getTransactionIdTV().setText(String.valueOf(tasks.get(position).id));
//        holder.getProductIdTV().setText(String.valueOf(tasks.get(position).getProductId()));
    }
}
