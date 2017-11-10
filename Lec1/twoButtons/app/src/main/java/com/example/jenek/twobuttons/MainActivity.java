package com.example.jenek.twobuttons;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import stanford.androidlib.SimpleActivity;

public class MainActivity extends SimpleActivity {
    private int rand1;
    private int rand2;
    private int points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        points = 0;
        pickRandomNumbers();
    }
    private void pickRandomNumbers() {
        Random randy = new Random();
        rand1 = randy.nextInt(10);
        while (true) {
            rand2 = randy.nextInt(10);
            if (rand1 != rand2) break;
        }


        $B(R.id.LeftButton).setText(Integer.toString(rand1));
        Button rbutt = (Button) findViewById(R.id.RightButton);
        rbutt.setText(Integer.toString(rand2));
    }



    public void leftButtonClick(View view) {
        if (rand1 >= rand2){
            points++;
           // Toast.makeText(this, "Hurray!", Toast.LENGTH_SHORT).show();
            toast("Hurray!");
        }
        else
        {
            points--;
            Toast.makeText(this, "So bad", Toast.LENGTH_SHORT).show();
        }

        TextView tv = (TextView) findViewById(R.id.Points);
        tv.setText("Points: " + points);
        pickRandomNumbers();
    }
    public void rightButtonClick(View view) {
        if (rand2 >= rand1){
            points++;
            Toast.makeText(this, "Hurray!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            points--;
            Toast.makeText(this, "So bad", Toast.LENGTH_SHORT).show();
        }
        TextView tv = (TextView) findViewById(R.id.Points);
        tv.setText("Points: " + points);
        pickRandomNumbers();

    }
}
