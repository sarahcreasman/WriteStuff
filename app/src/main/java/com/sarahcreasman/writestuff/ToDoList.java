/* FILE NAME: ToDoList.java
AUTHOR: Sarah Creasman
PURPOSE: Provides a to do list for users to edit and keep track of their tasks.
 */

package com.sarahcreasman.writestuff;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.sarahcreasman.writestuff.db.ToDoContract;
import com.sarahcreasman.writestuff.db.ToDoDbHelper;

import java.util.ArrayList;

public class ToDoList extends AppCompatActivity {
    private static final String TAG = "ToDoList";
    private ToDoDbHelper mHelper;
    private ListView mToDoListView;
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);
        mHelper = new ToDoDbHelper(this);
        mToDoListView = (ListView) findViewById(R.id.lv_toDo);
        updateUI();
    }

    // Creates top menu so user can add items
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.todo_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // Users top menu button to add items to to do list
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.todo_add_task:
                final EditText toDoEditText = new EditText(this);
                // Creates dialog box to add items to the to do list
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("New To Do Item")
                        .setView(toDoEditText)
                        // Adds content of dialog box to to do list
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String task = String.valueOf(toDoEditText.getText());
                                SQLiteDatabase db = mHelper.getWritableDatabase();
                                ContentValues values = new ContentValues();
                                values.put(ToDoContract.ToDoEntry.COL_TODO_TITLE, task);
                                db.insertWithOnConflict(ToDoContract.ToDoEntry.TABLE,
                                        null,
                                        values,
                                        SQLiteDatabase.CONFLICT_REPLACE);
                                db.close();
                                updateUI();
                            }
                        })
                        // Closes dialog box cancelling any user input
                        .setNegativeButton("Cancel", null)
                        .create();

                dialog.show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Updates the UI with anything the user puts on their to do list
    private void updateUI() {
        ArrayList<String> toDoList = new ArrayList<>();
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.query(ToDoContract.ToDoEntry.TABLE,
                new String[]{ToDoContract.ToDoEntry._ID,
                        ToDoContract.ToDoEntry.COL_TODO_TITLE},
                null, null, null, null, null);
        while (cursor.moveToNext()) {
            int idx = cursor.getColumnIndex(ToDoContract.ToDoEntry.COL_TODO_TITLE);
            toDoList.add(cursor.getString(idx));
        }

        if (mAdapter == null) {
            mAdapter = new ArrayAdapter<>(this,
                    R.layout.item_todo,
                    R.id.todo_title,
                    toDoList);
            mToDoListView.setAdapter(mAdapter);
        } else {
            mAdapter.clear();
            mAdapter.addAll(toDoList);
            mAdapter.notifyDataSetChanged();
        }

        cursor.close();
        db.close();
    }

    // Deletes items from to do list when finished
    public void deleteTask(View view) {
        View parent = (View) view.getParent();
        TextView toDoTextView = (TextView) parent.findViewById(R.id.todo_title);
        String task = String.valueOf(toDoTextView.getText());
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.delete(ToDoContract.ToDoEntry.TABLE,
                ToDoContract.ToDoEntry.COL_TODO_TITLE + " = ?",
                new String[]{task});
        db.close();
        updateUI();
    }
}
