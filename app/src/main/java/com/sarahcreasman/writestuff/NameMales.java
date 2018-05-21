package com.sarahcreasman.writestuff;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class NameMales extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_male_names);

        // Activates list view and displays male names
        ListView male = (ListView) findViewById(R.id.lv_maleNames);
        String[] maleNames = getResources().getStringArray(R.array.array_names_male);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, maleNames);
        male.setAdapter(adapter);
    }
}
