package com.sarahcreasman.writestuff;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onStart() {
        super.onStart();

        // Calls the To Do List activity
        Button toDo_btn = (Button) findViewById(R.id.btn_toDoList);
        toDo_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent toDoList = new Intent("com.sarahcreasman.writestuff.ToDoList");
                startActivity(toDoList);
            }
        });

        // Calls the Notebook activity
        Button notebook_btn = (Button) findViewById(R.id.btn_notes);
        notebook_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent notebook = new Intent("com.sarahcreasman.writestuff.Notebook");
                startActivity(notebook);
            }
        });

        // Calls the Timer activity
        Button timer_btn = (Button) findViewById(R.id.btn_timer);
        timer_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent timer = new Intent("com.sarahcreasman.writestuff.Timer");
                startActivity(timer);
            }
        });

        // Calls the Name Dictionary activity
        Button names_btn = (Button) findViewById(R.id.btn_names);
        names_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent names = new Intent("com.sarahcreasman.writestuff.NameDictionary");
                startActivity(names);
            }
        });

        // Calls the Feedback Activity
        Button feedback_btn = (Button) findViewById(R.id.btn_feedback);
        feedback_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent feedback = new Intent("com.sarahcreasman.writestuff.Feedback");
                startActivity(feedback);
            }
        });
    }
}
