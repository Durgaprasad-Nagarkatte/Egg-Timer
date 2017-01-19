package com.dpcompany.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar timerSeekBar;
    TextView timerTextView;

    public void updateTimer(int secondsLeft){
        int minutes = (int) secondsLeft/60;
        int seconds = secondsLeft - minutes*60;

        String secondString = Integer.toString(seconds);

        if(seconds <= 9){
            secondString = "0" + secondString;
        }

        timerTextView.setText(Integer.toString(minutes) + ":" + secondString);

    }

    public void controlTimer(View view){
        new CountDownTimer(timerSeekBar.getProgress()*1000 + 100,1000){

            @Override
            public void onTick(long l) {
                updateTimer((int) l/1000);
            }

            @Override
            public void onFinish() {
                timerTextView.setText("0:00");
                MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.music);
                mplayer.start();
            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSeekBar = (SeekBar)findViewById(R.id.timerSeekBar);
        timerTextView = (TextView)findViewById(R.id.timerTextView);

        // Max time is 10 min
        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
