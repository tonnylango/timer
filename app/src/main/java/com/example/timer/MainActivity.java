package com.example.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.service.autofill.FillEventHistory;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

        while (running){ //TODO: check on variables that can be accessed by multiple threads
            try{
                Thread.sleep(1000);
                
            }catch (Exception e){
                Toast errorToast = Toast.makeText(MainActivity.this, "Error, Some error occurred {e.getMessage()}", Toast.LENGTH_SHORT);
                errorToast.show();
            }
        }
    }
}