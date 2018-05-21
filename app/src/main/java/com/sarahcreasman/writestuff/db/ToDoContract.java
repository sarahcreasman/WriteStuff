/* FILE NAME: ToDoContract.java
AUTHOR: Sarah Creasman
PURPOSE: Creates the database for the To Do List
 */
package com.sarahcreasman.writestuff.db;

import android.provider.BaseColumns;

public class ToDoContract {
    public static final String DB_NAME = "com.sarahcreasman.writestuff.db";
    public static final int DB_VERSION = 1;

    public class ToDoEntry implements BaseColumns {
        public static final String TABLE = "todo";
        public static final String COL_TODO_TITLE = "title";
    }
}