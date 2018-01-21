package com.komarov.calculator.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.komarov.calculator.R;
import com.komarov.calculator.utils.CalcPerformer;

public class MainCalcFragment extends Fragment {

    Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9, buttonPercent, buttonDot, buttonClear, buttonBackpace, buttonDiv, buttonMul, buttonAdd, buttonSub, buttonEq;

    TextView textInput, textResult;

    CalcPerformer calcPerformer;
    int operationPosition;

    private String getResourceText(int id) {
        String text = getResources().getString(id);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY).toString();
        } else {
            return Html.fromHtml(text).toString();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main_calc, container, false);
        calcPerformer = new CalcPerformer();

        initBasicButtons(view);

        return view;
    }

    private void initBasicButtons(View view) {
        button0 = view.findViewById(R.id.buttonZero);
        button1 = view.findViewById(R.id.buttonOne);
        button2 = view.findViewById(R.id.buttonTwo);
        button3 = view.findViewById(R.id.buttonThree);
        button4 = view.findViewById(R.id.buttonFour);
        button5 = view.findViewById(R.id.buttonFive);
        button6 = view.findViewById(R.id.buttonSix);
        button7 = view.findViewById(R.id.buttonSeven);
        button8 = view.findViewById(R.id.buttonEight);
        button9 = view.findViewById(R.id.buttonNine);
        buttonPercent = view.findViewById(R.id.buttonPercent);
        buttonDot = view.findViewById(R.id.buttonDot);
        buttonClear = view.findViewById(R.id.buttonClear);
        buttonBackpace = view.findViewById(R.id.buttonBackspace);
        buttonDiv = view.findViewById(R.id.buttonDiv);
        buttonMul = view.findViewById(R.id.buttonMul);
        buttonAdd = view.findViewById(R.id.buttonAdd);
        buttonSub = view.findViewById(R.id.buttonSubtract);
        buttonEq = view.findViewById(R.id.buttonEq);

        textInput = view.findViewById(R.id.txtInput);
        textResult = view.findViewById(R.id.txtSolution);
        textInput.setText("");
        textResult.setText("");

        buttonClear.setOnClickListener(v -> {
            textInput.setText("");
        });

        buttonBackpace.setOnClickListener(v -> {
            String text = textInput.getText().toString();
            if (text.length() > 0) {
                textInput.setText(text.substring(0, text.length() - 1));
            }
        });

        button0.setOnClickListener(v -> {
            textInput.append(getResourceText(R.string.button_number_0));
        });

        button1.setOnClickListener(v -> {
            textInput.append(getResourceText(R.string.button_number_1));
        });

        button2.setOnClickListener(v -> {
            textInput.append(getResourceText(R.string.button_number_2));
        });

        button3.setOnClickListener(v -> {
            textInput.append(getResourceText(R.string.button_number_3));
        });

        button4.setOnClickListener(v -> {
            textInput.append(getResourceText(R.string.button_number_4));
        });

        button5.setOnClickListener(v -> {
            textInput.append(getResourceText(R.string.button_number_5));
        });

        button6.setOnClickListener(v -> {
            textInput.append(getResourceText(R.string.button_number_6));
        });

        button7.setOnClickListener(v -> {
            textInput.append(getResourceText(R.string.button_number_7));
        });

        button8.setOnClickListener(v -> {
            textInput.append(getResourceText(R.string.button_number_8));
        });

        button9.setOnClickListener(v -> {
            textInput.append(getResourceText(R.string.button_number_9));
        });

        buttonDot.setOnClickListener(v -> {
            textInput.append(getResourceText(R.string.button_dot));
        });

        buttonAdd.setOnClickListener(v -> {
            if (isNumberLast()) {
                String s = textInput.getText().toString();
                calcPerformer.setA(Double.parseDouble(s));
                calcPerformer.setOperation(CalcPerformer.Operation.ADD);
                operationPosition = s.length();
                textInput.append(getResourceText(R.string.button_plus));
            }
        });

        buttonSub.setOnClickListener(v -> {
            if (isNumberLast()) {
                String s = textInput.getText().toString();
                calcPerformer.setA(Double.parseDouble(s));
                calcPerformer.setOperation(CalcPerformer.Operation.SUBTRACT);
                operationPosition = s.length();
                textInput.append(getResourceText(R.string.button_sub));
            }
        });

        buttonMul.setOnClickListener(v -> {
            if (isNumberLast()) {
                String s = textInput.getText().toString();
                calcPerformer.setA(Double.parseDouble(s));
                calcPerformer.setOperation(CalcPerformer.Operation.MULTIPLICATION);
                operationPosition = s.length();
                textInput.append(getResourceText(R.string.button_mul));
            }
        });

        buttonDiv.setOnClickListener(v -> {
            if (isNumberLast()) {
                String s = textInput.getText().toString();
                calcPerformer.setA(Double.parseDouble(s));
                calcPerformer.setOperation(CalcPerformer.Operation.DIVISION);
                operationPosition = s.length();
                textInput.append(getResourceText(R.string.button_div));
            }
        });

        buttonEq.setOnClickListener(v -> {
            String s = textInput.getText().toString();
            Double b = Double.parseDouble(s.substring(operationPosition + 1));
            calcPerformer.setB(b);
            Double calcPerformerResult = calcPerformer.getResult();
            String result = calcPerformerResult % 1 == 0 ? String.valueOf(calcPerformerResult.intValue()) : calcPerformerResult.toString();
            String historical = s.concat(getResourceText(R.string.button_eq)).concat(result);
            textResult.append("\n" + historical);
            textInput.setText(result);
        });

    }

    private char getLastSymbol() {
        CharSequence text = textInput.getText();
        return text.length() != 0 ? text.charAt(text.length() - 1) : '\0';
    }

    private boolean isNumberLast() {
        return Character.isDigit(getLastSymbol());
    }

    private CalcPerformer.Operation getLastOperation() {
        String opText = String.valueOf(getLastSymbol());
        if (opText.equals(getResourceText(R.string.button_plus)))
            return CalcPerformer.Operation.ADD;
        if (opText.equals(getResourceText(R.string.button_sub)))
            return CalcPerformer.Operation.SUBTRACT;
        if (opText.equals(getResourceText(R.string.button_mul)))
            return CalcPerformer.Operation.MULTIPLICATION;
        if (opText.equals(getResourceText(R.string.button_div)))
            return CalcPerformer.Operation.DIVISION;
        return null;
    }

}
