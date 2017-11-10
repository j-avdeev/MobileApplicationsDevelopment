package com.example.jenek.turtle2application;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import stanford.androidlib.SimpleActivity;

public class Turtle2Activity extends SimpleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turtle2);
    }
    public void radioClick(View view){
        ImageView img = $IV(R.id.turtle_image);

        int id = view.getId();
        if (id == R.id.leo_button){
            img.setImageResource(R.drawable.tmntleo);
            toast("Leo");
        } else if (id == R.id.mike_button){
            img.setImageResource(R.drawable.tmntmike);
            toast("Mike");
        } else if (id == R.id.don_button){
            img.setImageResource(R.drawable.tmntdon);
            toast("Don");
        }else if (id == R.id.raph_button){
            img.setImageResource(R.drawable.tmntraph);
            toast("Raph");
        }

    }
}
