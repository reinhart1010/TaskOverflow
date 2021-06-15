package io.taskoverflow.adapter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.media.Image;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;

import com.google.android.flexbox.FlexboxLayout;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Pattern;

import io.taskoverflow.R;
import io.taskoverflow.Task;
import io.taskoverflow.database.DatabaseOpenHelper;
import io.taskoverflow.util.Badge;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.TasksViewHolder> {
     private Context ctx;
     private SQLiteDatabase db;
     private Cursor cursor;
     private int total;
     private boolean showCategoryBadge;

     private String colorRegex = "(#[0-9A-Fa-f]{6,8}|red|blue|green|black|white|gray|cyan|magenta|yellow|lightgray|darkgray)";
     private Pattern colorRegexPattern = Pattern.compile(colorRegex);

     private ViewGroup parent;

     public TasksAdapter(@NonNull @NotNull Context ctx, @NonNull @NotNull Boolean showCategoryBadge, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit){
         this.ctx = ctx;
         this.db = new DatabaseOpenHelper(ctx).getReadableDatabase();
         this.cursor = db.query(DatabaseOpenHelper.DATABASE_TABLE_TASKS, null, selection, selectionArgs, groupBy, having, orderBy, limit);
         this.total = cursor.getCount();
         this.showCategoryBadge = showCategoryBadge;
     }

     public TasksAdapter(@NonNull @NotNull Context ctx, @NonNull @NotNull Boolean showCategoryBadge){
         this(ctx, showCategoryBadge, null, null, null, null, null, null);
     }

     public TasksAdapter(@NonNull @NotNull Context ctx){
         this(ctx, true, null, null, null, null, null, null);
     }

     public static class TasksViewHolder extends RecyclerView.ViewHolder {
         public final CardView taskCard;
         public final FrameLayout taskCardOutline;
         public final TextView taskCardTitle;
         public final FlexboxLayout taskCardBadges;

         public TasksViewHolder(@NonNull @NotNull View view) {
             super(view);
             taskCard = view.findViewById(R.id.task_card);
             taskCardOutline = view.findViewById(R.id.task_card_outline);
             taskCardTitle = view.findViewById(R.id.task_card_title);
             taskCardBadges = view.findViewById(R.id.task_card_badges);
         }
     }

     @NonNull
     @NotNull
     @Override
     public TasksViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
         View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_card, parent, false);
         this.parent = parent;
         return new TasksViewHolder(view);
     }

     @Override
     public void onBindViewHolder(@NonNull @NotNull TasksAdapter.TasksViewHolder holder, int position) {
         ArrayList<Badge> badges = new ArrayList<>();
         int i;
         // Set up cursor to move into a position
         cursor.moveToPosition(position);

         holder.taskCardTitle.setText(cursor.getString(DatabaseOpenHelper.DATABASE_COLUMN_INDEX_TASKS.get("task_name")));
         Long categoryId = cursor.getLong(DatabaseOpenHelper.DATABASE_COLUMN_INDEX_TASKS.get("category_id"));
         if (categoryId != null){
             Cursor categoryCursor = db.query("categories", null, "category_id = " + categoryId, null, null, null, null, null);
             categoryCursor.moveToFirst();
             // Make sure that the category exists
             if (categoryCursor.getCount() > 0){
                 // Set category color
                 String categoryColor = categoryCursor.getString(DatabaseOpenHelper.DATABASE_COLUMN_INDEX_CATEGORIES.get("category_color"));
                 if (categoryColor != null && colorRegexPattern.matcher(categoryColor).find()) holder.taskCardOutline.setBackgroundColor(Color.parseColor(categoryColor));

                 // Assign a new badge
                 if (showCategoryBadge){
                     badges.add(new Badge(
                             categoryCursor.getString(DatabaseOpenHelper.DATABASE_COLUMN_INDEX_CATEGORIES.get("category_name")),
                             categoryCursor.getString(DatabaseOpenHelper.DATABASE_COLUMN_INDEX_CATEGORIES.get("category_color")),
                             R.drawable.ic_baseline_list_alt_24
                     ));
                 }
             }
             categoryCursor.close();
         }


         // TODO: Add due date and reminder badge

         // Assign task badges if available
         Cursor taskTagsCursor = db.query("task_tags", new String[]{"task_id"}, "task_id = " + cursor.getLong(DatabaseOpenHelper.DATABASE_COLUMN_INDEX_TASKS.get("task_id")), null, null, null, null, null);
         for (i = 0; i < taskTagsCursor.getCount(); i++){
             taskTagsCursor.moveToPosition(i);
             Cursor tagCursor = db.query("tags", null, "tag_id = " + taskTagsCursor.getLong(DatabaseOpenHelper.DATABASE_COLUMN_INDEX_TASK_TAGS.get("tag_id")), null, null, null, null, null);
             badges.add(new Badge(
                 tagCursor.getString(DatabaseOpenHelper.DATABASE_COLUMN_INDEX_TAGS.get("tag_name")),
                 tagCursor.getString(DatabaseOpenHelper.DATABASE_COLUMN_INDEX_TAGS.get("tag_color")),
                 R.drawable.ic_baseline_tag_24
             ));
             tagCursor.close();
         }
         taskTagsCursor.close();

         // Inflate all tags into FlexboxLayout
         for (i = 0; i < badges.size(); i++){
             Badge badge = badges.get(i);
             View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_card_badge, parent, false);
             holder.taskCardBadges.addView(view);

             // TODO: Set color
             ((ImageView) view.findViewById(R.id.task_card_badge_icon)).setImageResource(badge.icon);
             ((TextView) view.findViewById(R.id.task_card_badge_label)).setText(badge.label);
         }
     }

     @Override
     public int getItemCount() { return total; }
 }
