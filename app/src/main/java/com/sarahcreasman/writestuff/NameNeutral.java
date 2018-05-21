package com.sarahcreasman.writestuff;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class NameNeutral extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neutral_names);

        // Activates ListView and sets it to neutral name list
        ListView neutral = (ListView) findViewById(R.id.lv_neutralNames);
        String[] neutralNames = getResources().getStringArray(R.array.array_names_neutral);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, neutralNames);
        neutral.setAdapter(adapter);
    }
}
