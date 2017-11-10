package com.example.jenek.turtrleapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import stanford.androidlib.SimpleActivity;

public class TurtleActivity extends SimpleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turtle);
    }

    public void radioClick(View view) {
        ImageView img = (ImageView) findViewById(R.id.turtle_image);

        int id = view.getId();
        if (id == R.id.leo_button) {
            img.setImageResource(R.drawable.tmntleo);

        } else if (id == R.id.mike_button) {
            img.setImageResource(R.drawable.tmntmike);
        } else if (id == R.id.don_button) {
            img.setImageResource(R.drawable.tmntdon);
        } else if (id == R.id.raph_button) {
            img.setImageResource(R.drawable.tmntraph);
        }


    }
}
