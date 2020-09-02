package com.deepshikha.clock;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.icu.text.RelativeDateTimeFormatter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    TextView time,date,day;
    private int TimeInterval = 1000;//5 sec
    private static Timer timer = new Timer();
    String currentTime,currentDate;
    Calendar sCalendar;
    String dayLongName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        time = findViewById(R.id.time); time.setTextColor(getResources().getColor(R.color.colorAccent));
        date = findViewById(R.id.date);  date.setTextColor(getResources().getColor(R.color.colorPrimary));
        day = findViewById(R.id.day);  date.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

//        tv.setText(c.toString());
    }

    @Override
    protected void onResume() {
        super.onResume();
        timer.scheduleAtFixedRate(new clockTimer(), 0, TimeInterval);

    }

    private  class clockTimer extends TimerTask{

        @Override
        public void run() {
             currentTime   = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
             currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
            sCalendar = Calendar.getInstance();
            dayLongName = sCalendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
            runOnUiThread(new Runnable() {

                @Override
                public void run() {

                    // Stuff that updates the UI
                    date.setText(currentDate);
                    time.setText(currentTime);
                    day.setText(dayLongName);
                    Log.d(TAG, "onCreate: "+ currentDate);
                    Log.d(TAG, "onCreate: "+ currentTime);

                }
            });

        }
    }


}
