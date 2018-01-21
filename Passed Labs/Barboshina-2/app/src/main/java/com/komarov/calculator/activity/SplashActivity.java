package com.komarov.calculator.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

import com.komarov.calculator.R;

public class SplashActivity extends AppCompatActivity {

    protected int _splashTime = 2000;

    private Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        thread = new Thread() {
            @Override
            public void run() {
                try {
                    synchronized (this) {
                        wait(_splashTime);
                    }
                } catch (InterruptedException ignored) {
                } finally {
                    finish();

                    Intent i = new Intent();
                    i.setClass(SplashActivity.this, MainActivity.class);
                    startActivity(i);

                    finish();
                }
            }
        };

        thread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            synchronized (thread) {
                thread.notifyAll();
            }
        }
        return true;
    }
}
