package com.sarahcreasman.writestuff;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.io.IOException;

public class NotebookPage extends AppCompatActivity {
    EditText editNote;
    Intent intent;
    String note;
    SharedPreferences sharedPreferences;

    public void textReader() {
        editNote.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            // Saves note
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                note = s.toString();
                sharedPreferences.edit().putString("note", note).apply();
                Notebook.notes.set(Notebook.cur, sharedPreferences.getString("note",""));
                Notebook.noteAdapter.notifyDataSetChanged();
                try {
                    sharedPreferences.edit().putString("notes", NotebookSerializer.serialize(Notebook.notes)).apply();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    // Loads the note or blank page to write note
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notebook_page);
        sharedPreferences = this.getSharedPreferences("com.sarahcreasman.writestuff", Context.MODE_PRIVATE);
        editNote = (EditText) findViewById(R.id.notebook_edit);
        intent = getIntent();
        textReader();
        editNote.setText(intent.getStringExtra("note"));
    }
}