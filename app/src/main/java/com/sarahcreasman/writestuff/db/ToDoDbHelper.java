/* FILE NAME: ToDoDbHelper.java
AUTHOR: Sarah Creasman
PURPOSE: Opens the database
 */

package com.sarahcreasman.writestuff.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ToDoDbHelper extends SQLiteOpenHelper {
    public ToDoDbHelper(Context context) {
        super(context, ToDoContract.DB_NAME, null, ToDoContract.DB_VERSION);
    }

    // Creates table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + ToDoContract.ToDoEntry.TABLE + " ( " +
                ToDoContract.ToDoEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ToDoContract.ToDoEntry.COL_TODO_TITLE + " TEXT NOT NULL);";
        db.execSQL(createTable);
    }

    // Alters table
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ToDoContract.ToDoEntry.TABLE);
        onCreate(db);
    }
}
