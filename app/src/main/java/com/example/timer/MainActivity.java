package com.example.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.service.autofill.FillEventHistory;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    TextView display;
    Button button;
    boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = (TextView) findViewById(R.id.display);
        button = (Button) findViewById(R.id.button);
    }

    /*
    method should be called on a thread. updates the button and start timer count down
     */
    private void startTimer(){
        //update button text
        button.setText("Stop");
        HashMap<String, Integer> time = new HashMap<String, Integer>();
        String timeString = display.getText().toString();
        String [] timeValues = timeString.split(":");
        String [] timeValue = timeString.split(":");
        time.put("Hour", Integer.valueOf(timeValue[0]));
        time.put("Minutes", Integer.valueOf(timeValue[1]));
        time.put("Seconds", Integer.valueOf(timeValue[2]));

        while (running){ //TODO: check on variables that can be accessed by multiple threads
            try{
                Thread.sleep(1000);

            }catch (Exception e){
                Toast errorToast = Toast.makeText(MainActivity.this, "Error, Some error occurred {e.getMessage()}", Toast.LENGTH_SHORT);
                errorToast.show();
            }
        }
    }

    private void updateDisplay(){
        HashMap<String, Integer> time = new HashMap<String, Integer>();
        String timeString = display.getText().toString();
        String [] timeValues = timeString.split(":");
        String [] timeValue = timeString.split(":");
        time.put("Hour", Integer.valueOf(timeValue[0]));
        time.put("Minutes", Integer.valueOf(timeValue[1]));
        time.put("Seconds", Integer.valueOf(timeValue[2]));
    }


}