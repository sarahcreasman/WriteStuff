package com.sarahcreasman.writestuff;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class NameFemales extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_female_names);

        // Activates List View and sets it the list of female names
        ListView female = (ListView) findViewById(R.id.lv_femaleNames);
        String[] femaleNames = getResources().getStringArray(R.array.array_names_female);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, femaleNames);
        female.setAdapter(adapter);
    }
}
