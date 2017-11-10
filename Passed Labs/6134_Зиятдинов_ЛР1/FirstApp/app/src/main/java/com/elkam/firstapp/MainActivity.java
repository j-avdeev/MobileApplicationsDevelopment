package com.elkam.firstapp;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Random random;
    private int count;
    private ArrayList<String> list;
    private Button buttonR;
    private Button buttonB;
    private Button buttonY;
    private Button buttonG;
    private Button butNewGame;
    private TextView textCount;
    private TextView res;
    private EditText editText;
    final String r = "Красный";
    final String b = "Синий";
    final String y = "Желтый";
    final String g = "Зеленый";
    final String rr = "Red";
    final String bb = "Blue";
    final String yy = "Yellow";
    final String gg = "Green";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        init();
    }

    private int randIndex() {
        return random.nextInt(8);
    }

    private void init() {
        random = new Random();
        list = new ArrayList<>();
        list.add(r);
        list.add(b);
        list.add(y);
        list.add(g);
        list.add(rr);
        list.add(gg);
        list.add(bb);
        list.add(yy);
        buttonR = (Button) findViewById(R.id.button);
        buttonB = (Button) findViewById(R.id.button2);
        buttonY = (Button) findViewById(R.id.button3);
        buttonG = (Button) findViewById(R.id.button4);
        butNewGame = (Button) findViewById(R.id.button5);
        textCount = (TextView) findViewById(R.id.textView);
        editText = (EditText) findViewById(R.id.edit);
        res = (TextView) findViewById(R.id.res);

        butNewGame.setOnClickListener(newGame);
        buttonR.setOnClickListener(red);
        buttonB.setOnClickListener(blue);
        buttonG.setOnClickListener(green);
        buttonY.setOnClickListener(yellow);

    }

    View.OnClickListener newGame = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            buttonR.setEnabled(true);
            buttonG.setEnabled(true);
            buttonB.setEnabled(true);
            buttonY.setEnabled(true);
            butNewGame.setEnabled(false);
            count = 0;
            res.setText("");
            editText.setText(list.get(randIndex()));
        }
    };

    private void endGame() {
        buttonY.setEnabled(false);
        buttonR.setEnabled(false);
        buttonG.setEnabled(false);
        buttonB.setEnabled(false);
        butNewGame.setEnabled(true);
        editText.setText("");
        textCount.setText("");
        if(count<5){
            res.setText("Very bad! Your count: "+count);
        } else if(count>=5 && count<=10){
            res.setText("Very well! Your count: "+count);
        } else {
            res.setText("Very good! Your count: "+count);
        }
    }

    View.OnClickListener blue = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String color = String.valueOf(editText.getText());
            if (color.equals(b) || color.equals(bb)) {
                count++;
                textCount.setText(String.valueOf(count));
                editText.setText(list.get(randIndex()));
            } else {
                endGame();
            }
        }
    };

    View.OnClickListener red = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String color = String.valueOf(editText.getText());
            if (color.equals(r) || color.equals(rr)) {
                count++;
                textCount.setText(String.valueOf(count));
                editText.setText(list.get(randIndex()));
            } else {
                endGame();
            }
        }
    };

    View.OnClickListener yellow = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String color = String.valueOf(editText.getText());
            if (color.equals(y) || color.equals(yy)) {
                count++;
                textCount.setText(String.valueOf(count));
                editText.setText(list.get(randIndex()));
            } else {
                endGame();
            }
        }
    };

    View.OnClickListener green = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String color = String.valueOf(editText.getText());
            if (color.equals(g) || color.equals(gg)) {
                count++;
                textCount.setText(String.valueOf(count));
                editText.setText(list.get(randIndex()));
            } else {
                endGame();
            }
        }
    };


}
