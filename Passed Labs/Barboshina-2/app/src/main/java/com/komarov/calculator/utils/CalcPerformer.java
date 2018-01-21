package com.komarov.calculator.utils;

/**
 * Created by Ilia on 13.10.2017.
 */

public class CalcPerformer {

    private Double a, b, result;
    private Operation operation;

    public CalcPerformer() {
    }

    public CalcPerformer(Double a, Double b, Operation operation) {
        this.a = a;
        this.b = b;
        this.operation = operation;
    }

    public Double getA() {
        return a;
    }

    public void setA(Double a) {
        this.a = a;
    }

    public Double getB() {
        return b;
    }

    public void setB(Double b) {
        this.b = b;
    }

    public Double getResult() {
        if (a != null && b != null && operation != null) {
            switch (operation) {
                case ADD:
                    result = a + b;
                    break;
                case SUBTRACT:
                    result = a - b;
                    break;
                case DIVISION:
                    result = a / b;
                    break;
                case MULTIPLICATION:
                    result = a * b;
                    break;
            }
        } else result = null;
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public enum Operation {
        ADD, SUBTRACT, DIVISION, MULTIPLICATION;
    }


}
