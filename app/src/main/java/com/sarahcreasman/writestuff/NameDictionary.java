package com.sarahcreasman.writestuff;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;

public class NameDictionary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_dictionary);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Determines what happens when search button is hit
        Button nameSearch_btn = (Button) findViewById(R.id.name_btnSearch);
        nameSearch_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Get Elements from GUI
                final RadioButton rb_male = (RadioButton) findViewById(R.id.name_genderMale);
                final RadioButton rb_female = (RadioButton) findViewById(R.id.name_genderFemale);
                final RadioButton rb_neutral = (RadioButton) findViewById(R.id.name_genderEither);

                // Set TextView based on user choice
                if (rb_male.isChecked()) {
                    Intent maleName = new Intent("com.sarahcreasman.writestuff.NameMales");
                    startActivity(maleName);
                }
                else if (rb_female.isChecked()) {
                    Intent femaleName = new Intent("com.sarahcreasman.writestuff.NameFemales");
                    startActivity(femaleName);
                }
                else if (rb_neutral.isChecked()){
                    Intent neutralName = new Intent("com.sarahcreasman.writestuff.NameNeutral");
                    startActivity(neutralName);
                }
                // Creates a Toast message if no radio button is selected
                else {
                    Toast.makeText(NameDictionary.this, "Choose a gender to view dictionary", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}