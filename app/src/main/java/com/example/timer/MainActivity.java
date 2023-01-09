package com.example.timer;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.service.autofill.FillEventHistory;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    TextView display;
    Button button;
    private volatile boolean running = true;
    int hours = 0, minutes = 0, seconds = 0;
    Thread timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = (TextView) findViewById(R.id.display);
        setDisplay();
        button = (Button) findViewById(R.id.button);

        timer = new Thread(this::startTimer);
        button.setOnClickListener(this::onClick);
    }

    private void onClick(View view){
        if (Objects.equals(button.getText().toString(), getString(R.string.start_timer))) {
            timer.start();
            button.setText(getString(R.string.stop_timer));
        }
        else{
            running = false;
            button.setText(getString(R.string.start_timer));
        }

    }

    /*
    method should be called on a thread. updates the button and start timer count down
     */
    private void startTimer(){
        //update button text
        running = true;

        while (running){ //TODO: check on variables that can be accessed by multiple threads
            try{
                Thread.sleep(1000);
                updateTime();
                setDisplay();
            }catch (Exception e){
                runOnUiThread(() -> Toast.makeText(MainActivity.this, "Error, Some error occurred {e.getMessage()}", Toast.LENGTH_SHORT).show());
            }
        }
    }

    private void updateTime(){
        seconds += 1;
        if (seconds >= 60 ){
            seconds = 0;
            minutes += 1;

            if (minutes >= 60){
                minutes = 0;
                hours += 1;

                if (hours >= 100 ){
                    hours = 0;
                    minutes = 0;
                    seconds = 0;
                }
            }
        }
    }

    private void setDisplay(){
        runOnUiThread(() -> display.setText(getString(R.string.displayed_text, hours, minutes, seconds)));
        //TODO display.setText(String.join( ":", new String[] {String.format("%02d", hours), String.format("%02d", minutes), String.format("%02d", seconds)}));
    }


}