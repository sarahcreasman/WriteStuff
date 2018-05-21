package com.sarahcreasman.writestuff;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class Timer extends AppCompatActivity  implements View.OnClickListener {

    private long timeCountMS = 1 * 60000;
    private enum TimerStatus {STARTED, STOPPED}
    private TimerStatus timerStatus = TimerStatus.STOPPED;
    private ProgressBar progressBar;
    private EditText editMinute;
    private TextView viewTime;
    private ImageView imgReset;
    private ImageView imgStart;
    private CountDownTimer timer;
    private MediaPlayer alarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        // Calls the methods to initialize views and listeners
        initViews();
        initListeners();

        alarm = MediaPlayer.create(Timer.this, R.raw.applause);
    }

    // Initializes the view
    private void initViews() {
        progressBar = (ProgressBar) findViewById(R.id.timer_circle);
        editMinute = (EditText) findViewById(R.id.timer_setTime);
        viewTime = (TextView) findViewById(R.id.timer_countDown);
        imgReset = (ImageView) findViewById(R.id.timer_reset);
        imgStart = (ImageView) findViewById(R.id.timer_start);
        progressBar.setVisibility(View.GONE);
    }

    // Initialize the listener
    private void initListeners() {
        imgReset.setOnClickListener(this);
        imgStart.setOnClickListener(this);
    }

    //Implements method to listen to clicks
    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.timer_reset:
                reset();
                break;
            case R.id.timer_start:
                startStop();
                break;
        }
    }

    // Resets timer
    private void reset() {
        stopCountDownTimer();
        startCountDownTimer();
    }

    // Starts and stops timer
    private void startStop() {
        if(timerStatus == TimerStatus.STOPPED) {
            setTimerValues();
            imgReset.setVisibility(View.VISIBLE);
            editMinute.setEnabled(false);
            timerStatus = TimerStatus.STARTED;
            startCountDownTimer();
        } else {
            imgReset.setVisibility(View.GONE);
            editMinute.setEnabled(true);
            timerStatus = TimerStatus.STOPPED;
            stopCountDownTimer();
        }
    }

    // Initialized values in timer
    private void setTimerValues() {
        int time = 0;
        if(!editMinute.getText().toString().isEmpty()) {
            time = Integer.parseInt(editMinute.getText().toString().trim());
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.timer_prompt), Toast.LENGTH_LONG).show();
        }
        timeCountMS = time * 60 * 1000;
    }

    // Starts timer
    private void startCountDownTimer() {
        timer = new CountDownTimer(timeCountMS, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                viewTime.setText(hmsTimeFormatter(millisUntilFinished));
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFinish() {
                viewTime.setText(hmsTimeFormatter(timeCountMS));
                imgReset.setVisibility(View.GONE);
                editMinute.setEnabled(true);
                timerStatus = TimerStatus.STOPPED;
                progressBar.setVisibility(View.GONE);
                alarm.start();
            }
        } .start();
        timer.start();
    }

    // Stops timer
    private void stopCountDownTimer() {
        timer.cancel();
        progressBar.setVisibility(View.GONE);
        viewTime.setText(hmsTimeFormatter(0));
    }

    private String hmsTimeFormatter(long milliSeconds) {
        String hms = String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(milliSeconds),
                TimeUnit.MILLISECONDS.toMinutes(milliSeconds) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(milliSeconds)),
                TimeUnit.MILLISECONDS.toSeconds(milliSeconds) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliSeconds)));
        return hms;
    }
}
