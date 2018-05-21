package com.sarahcreasman.writestuff;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

public class Notebook extends AppCompatActivity {
    ListView noteList;
    public static ArrayList<String> notes = new ArrayList<>();
    public static ArrayAdapter noteAdapter;
    public static int cur;
    Intent intent;
    SharedPreferences sharedPreferences;

    // Creates top menu to add notes
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.notebook_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        intent = new Intent(getApplicationContext(), NotebookPage.class);
        if(item.getItemId() == R.id.note) {
            notes.add("");
            noteAdapter.notifyDataSetChanged();
            cur = Notebook.notes.size() - 1;
            startActivity(intent);
            return true;
        }
        return false;
    }

    // Pulls up list of existing notes from memory
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notebook);
        sharedPreferences = this.getSharedPreferences("com.sarahcreasman.writestuff", Context.MODE_PRIVATE);
        noteList = (ListView) findViewById(R.id.notebook_list);
        intent = new Intent(getApplicationContext(), NotebookPage.class);
        if(notes.size() == 0) {
        }
        try {
            notes = (ArrayList<String>) NotebookSerializer.deserialize(sharedPreferences.getString("notes", NotebookSerializer.serialize(notes)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        noteAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, notes);
        noteList.setAdapter(noteAdapter);
        noteList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent.putExtra("note", notes.get(position));
                cur = position;
                startActivity(intent);
            }
        });

        // Sets notes to delete if title is held down
        noteList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                // Generates alert to confirm that deletion is what user wants
                new AlertDialog.Builder(Notebook.this)
                        .setIcon(android.R.drawable.ic_delete)
                        .setTitle("Delete Note")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                notes.remove(position);
                                noteAdapter.notifyDataSetChanged();
                                try {
                                    sharedPreferences.edit().putString("notes", NotebookSerializer.serialize(Notebook.notes)).apply();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
                return true;
            }
        });
    }
}