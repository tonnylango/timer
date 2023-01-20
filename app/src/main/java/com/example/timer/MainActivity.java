package com.example.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;
//todo check stop button
//Todo add refresh button
//todo add list of time stamps. and save.
//todo save to db and retrieve

public class MainActivity extends AppCompatActivity {

    TextView display;
    Button button;
    private volatile boolean running = true;
    int hours = 0, minutes = 0, seconds = 0;
    Thread timer;
    ListView laps; //todo record time after refresh button is hit.
    String[] time = {"00:00:00", "00:00:00", "00:00:00", "00:00:00", "00:00:00"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        laps  = (ListView) findViewById(R.id.laps);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.activity_list_view, R.id.textView, time);

        // Bind the adapter to the ListView
        laps.setAdapter(adapter);

        display = (TextView) findViewById(R.id.display);
        setDisplay();
        button = (Button) findViewById(R.id.startButton);

        timer = new Thread(this::startTimer);
        button.setOnClickListener(this::onClick);
    }

    private void onClick(View view){
        if (Objects.equals(button.getText().toString(), getString(R.string.start_timer))) { //Start
            timer.start();
            button.setText(getString(R.string.stop_timer));
        }
        else{
            running = false;
            button.setText(getString(R.string.start_timer));
        }

    }

    public void captureTime(View view){
        String timeShown = display.g
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
                if (running) {
                    updateTime();
                    setDisplay();
                }
            }catch (InterruptedException e){
                showToast("Error, Some error occurred {e.getMessage()}");
            }
            catch (Exception e){
                showToast("Error, Some error occurred {e.getMessage()}");
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

    /*
    called when refresh button is pressed. starts timer to 00:00:00
     */
    public void refreshTimer(View view){
        hours = minutes = seconds = 0;
        setDisplay();
    }


    private void showToast(String message) {
        runOnUiThread(() -> Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show());
    }
}