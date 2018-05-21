package com.sarahcreasman.writestuff;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Feedback extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
    }

    @Override
    public void onStart() {
        super.onStart();

        // Populate Subject spinner
        Spinner subjectSpinner = (Spinner) findViewById(R.id.feedback_spinSubject);
        ArrayAdapter<CharSequence> subjectAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_fb_subject, android.R.layout.simple_spinner_item);
        subjectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subjectSpinner.setAdapter(subjectAdapter);

        // Determines what happens when send button is clicked
        Button feedback_sendBtn = (Button) findViewById(R.id.feedback_btnSend);
        feedback_sendBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Declarations
                String name;
                String email;
                String subject;
                String message;
                String endMessage;

                // Get objects from GUI
                EditText fbName = (EditText) findViewById(R.id.feedback_inputName);
                EditText fbEmail = (EditText) findViewById(R.id.feedback_inputEmail);
                EditText fbMessage = (EditText) findViewById(R.id.feedback_inputMessage);
                Spinner fbSubject = (Spinner) findViewById(R.id.feedback_spinSubject);

                // Get information from GUI objects
                name = fbName.getText().toString();
                email = fbEmail.getText().toString();
                subject = fbSubject.getSelectedItem().toString();
                message = fbMessage.getText().toString();

                // Prepares Message for email
                endMessage = "Email from: " + name + " (" + email + ") \n \n" + message;

                // Prepare Email to send Feedback
                String[] to = {"sarah.creasman@yahoo.com"};
                sendEmail(to, subject, endMessage);

                // Clears values from objects in GUI
                fbName.setText("");
                fbName.requestFocus();
                fbEmail.setText("");
                fbSubject.setSelection(0);
                fbMessage.setText("");
            }

            // Method that sends email
            public void sendEmail(String[] emailAddresses, String subject, String message) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("mailto:"));
                String[] to = emailAddresses;
                emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
                emailIntent.putExtra(Intent.EXTRA_TEXT, message);
                emailIntent.setType("message/rfc822");
                startActivity(Intent.createChooser(emailIntent,"Email"));
            }
        });

        // Determines what happens when Clear button is clicked
        Button feedback_clearBtn = (Button) findViewById(R.id.feedback_btnClear);
        feedback_clearBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Get objects from GUI
                EditText fbName = (EditText) findViewById(R.id.feedback_inputName);
                EditText fbEmail = (EditText) findViewById(R.id.feedback_inputEmail);
                EditText fbMessage = (EditText) findViewById(R.id.feedback_inputMessage);
                Spinner fbSubject = (Spinner) findViewById(R.id.feedback_spinSubject);

                // Clears values from objects in GUI
                fbName.setText("");
                fbName.requestFocus();
                fbEmail.setText("");
                fbSubject.setSelection(0);
                fbMessage.setText("");
            }
        });
    }
}
