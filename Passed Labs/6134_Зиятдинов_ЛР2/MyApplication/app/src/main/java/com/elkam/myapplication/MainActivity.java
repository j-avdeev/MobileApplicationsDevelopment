package com.elkam.myapplication;


import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button button0;
    private Button buttonac;
    private Button buttonp;
    private Button buttona;
    private Button buttons;
    private Button buttond;
    private Button buttonm;
    private Button buttonas;
    private Button buttont;
    private Button buttonr;
    private EditText editText;
    private Operation operation = Operation.None;

    private double a=0;
    private double b=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);
        button0 = (Button) findViewById(R.id.button0);
        buttonac = (Button) findViewById(R.id.buttonac);
        buttonp = (Button) findViewById(R.id.buttonp);
        buttona = (Button) findViewById(R.id.buttona);
        buttons = (Button) findViewById(R.id.buttons);
        buttond = (Button) findViewById(R.id.buttond);
        buttonm = (Button) findViewById(R.id.buttonm);
        buttonas = (Button) findViewById(R.id.buttonas);
        buttont = (Button) findViewById(R.id.buttont);
        buttonr = (Button) findViewById(R.id.buttonr);
        editText = (EditText) findViewById(R.id.texte);
        buttonac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
            }
        });
        buttonp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a = Double.parseDouble(String.valueOf(editText.getText()));
                a /= 100;
                editText.setText(String.valueOf(a));
                operation = Operation.None;
            }
        });
        buttonas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double aa;
                try {
                    aa = Double.parseDouble(String.valueOf(editText.getText()));
                    aa *= -1;
                    operation = Operation.None;
                    editText.setText(String.valueOf(aa));
                }catch (Throwable e){  }
            }
        });
        buttont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.append(".");
            }
        });
        buttonr.setOnClickListener(butres);
        buttona.setOnClickListener(butadd);
        buttond.setOnClickListener(butdiv);
        buttonm.setOnClickListener(butmul);
        buttons.setOnClickListener(butsub);
        button0.setOnClickListener(buttonList);
        button1.setOnClickListener(buttonList);
        button2.setOnClickListener(buttonList);
        button3.setOnClickListener(buttonList);
        button4.setOnClickListener(buttonList);
        button5.setOnClickListener(buttonList);
        button6.setOnClickListener(buttonList);
        button7.setOnClickListener(buttonList);
        button8.setOnClickListener(buttonList);
        button9.setOnClickListener(buttonList);

    }


    private View.OnClickListener butres = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(operation!=Operation.None) {

                b = Double.parseDouble(String.valueOf(editText.getText()));

                switch (operation) {
                    case ADD:
                        a += b;
                        operation = Operation.None;
                        editText.setText(String.valueOf(a));
                        break;
                    case SUB:
                        a -= b;
                        operation = Operation.None;
                        editText.setText(String.valueOf(a));
                        break;
                    case MUL:
                        a *= b;
                        operation = Operation.None;
                        editText.setText(String.valueOf(a));
                        break;
                    case DIV:
                        if(b==0)
                            return;
                        a /= b;
                        operation = Operation.None;
                        editText.setText(String.valueOf(a));
                        break;
                }
            }
        }
    };

    private View.OnClickListener buttonList = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button0:
                    editText.append("0");
                    break;
                case R.id.button1:
                    editText.append("1");
                    break;
                case R.id.button2:
                    editText.append("2");
                    break;
                case R.id.button3:
                    editText.append("3");
                    break;
                case R.id.button4:
                    editText.append("4");
                    break;
                case R.id.button5:
                    editText.append("5");
                    break;
                case R.id.button6:
                    editText.append("6");
                    break;
                case R.id.button7:
                    editText.append("7");
                    break;
                case R.id.button8:
                    editText.append("8");
                    break;
                case R.id.button9:
                    editText.append("9");
                    break;
            }
        }
    };

    private View.OnClickListener butdiv = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                String text = String.valueOf(editText.getText());
                if(!text.equals("")) {
                    a = Double.parseDouble(String.valueOf(editText.getText()));
                    editText.setText("");
                }
            } catch (Throwable e){
                a = 0;
                editText.setText("0");
            }
            operation = Operation.DIV;
        }
    };

    private View.OnClickListener butmul = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                String text = String.valueOf(editText.getText());
                if(!text.equals("")) {
                    a = Double.parseDouble(String.valueOf(editText.getText()));
                    editText.setText("");
                }
            } catch (Throwable e){
                a = 0;
                editText.setText("0");
            }
            operation = Operation.MUL;
        }
    };

    private View.OnClickListener butadd = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                String text = String.valueOf(editText.getText());
                if(!text.equals("")) {
                    a = Double.parseDouble(String.valueOf(editText.getText()));
                    editText.setText("");
                }
            } catch (Throwable e){
                a = 0;
                editText.setText("0");
            }
            operation = Operation.ADD;
        }
    };

    private View.OnClickListener butsub = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                String text = String.valueOf(editText.getText());
                if(!text.equals("")) {
                    a = Double.parseDouble(String.valueOf(editText.getText()));
                    editText.setText("");
                }
            } catch (Throwable e){
                a = 0;
                editText.setText("0");
            }
            operation = Operation.SUB;
        }
    };

}
