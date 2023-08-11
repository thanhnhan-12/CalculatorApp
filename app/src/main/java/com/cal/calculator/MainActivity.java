package com.cal.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String currentInput = "";
    private double firstValue = Double.NaN;
    private double secondValue = Double.NaN;
    private char currentOperator;
    private double tempResult = Double.NaN;

    TextView calc, result ;

    // Row 1
    Button btnNumberSeven, btnNumberEight, btnNumberNine, btnDivide;

    // Row 2
    Button btnNumberFour, btnNumberFive, btnNumberSix, btnMultiply;

    // Row 3
    Button btnNumberOne, btnNumberTwo, btnNumberThree, btnSubtract;

    // Row 4
    Button btnNumberZero, btnEqual, btnSum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calc = findViewById(R.id.calc);
        result = findViewById(R.id.result);

        // Row 1
        btnNumberSeven = findViewById(R.id.btnNumberSeven);
        btnNumberEight = findViewById(R.id.btnNumberEight);
        btnNumberNine = findViewById(R.id.btnNumberNine);
        btnDivide = findViewById(R.id.btnDivide);

        // Row 2
        btnNumberFour = findViewById(R.id.btnNumberFour);
        btnNumberFive = findViewById(R.id.btnNumberFive);
        btnNumberSix = findViewById(R.id.btnNumberSix);
        btnMultiply = findViewById(R.id.btnMultiply);

        // Row 3
        btnNumberOne = findViewById(R.id.btnNumberOne);
        btnNumberTwo = findViewById(R.id.btnNumberTwo);
        btnNumberThree = findViewById(R.id.btnNumberThree);
        btnSubtract = findViewById(R.id.btnSubtract);

        // Row 4
        btnNumberZero = findViewById(R.id.btnNumberZero);
        btnEqual = findViewById(R.id.btnEqual);
        btnSum = findViewById(R.id.btnSum);

        btnNumberOne.setOnClickListener(new View.OnClickListener() {        // 1
            @Override
            public void onClick(View v) {
                appendToInput("1");
            }
        });

        btnNumberTwo.setOnClickListener(new View.OnClickListener() {        // 2
            @Override
            public void onClick(View v) {
                appendToInput("2");
            }
        });

        btnNumberThree.setOnClickListener(new View.OnClickListener() {      // 3
            @Override
            public void onClick(View v) {
                appendToInput("3");
            }
        });

        btnNumberFour.setOnClickListener(new View.OnClickListener() {       // 4
            @Override
            public void onClick(View v) {
                appendToInput("4");
            }
        });

        btnNumberFive.setOnClickListener(new View.OnClickListener() {       // 5
            @Override
            public void onClick(View v) {
                appendToInput("5");
            }
        });

        btnNumberSix.setOnClickListener(new View.OnClickListener() {       // 6
            @Override
            public void onClick(View v) {
                appendToInput("6");
            }
        });

        btnNumberSeven.setOnClickListener(new View.OnClickListener() {       // 7
            @Override
            public void onClick(View v) {
                appendToInput("7");
            }
        });

        btnNumberEight.setOnClickListener(new View.OnClickListener() {       // 8
            @Override
            public void onClick(View v) {
                appendToInput("8");
            }
        });

        btnNumberNine.setOnClickListener(new View.OnClickListener() {       // 9
            @Override
            public void onClick(View v) {
                appendToInput("9");
            }
        });

        btnSum.setOnClickListener(new View.OnClickListener() {       // Sum
            @Override
            public void onClick(View v) {
                handleOperator('+');
                appendToInput("+");
            }
        });

        btnSubtract.setOnClickListener(new View.OnClickListener() {       // Subtract
            @Override
            public void onClick(View v) {
                handleOperator('-');
                appendToInput("-");
            }
        });

        btnMultiply.setOnClickListener(new View.OnClickListener() {       // Multiply
            @Override
            public void onClick(View v) {
                handleOperator('x');
                appendToInput("x");
            }
        });

        btnDivide.setOnClickListener(new View.OnClickListener() {       // Divide
            @Override
            public void onClick(View v) {
                handleOperator('/');
                appendToInput("/");
            }
        });

        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateResult();
            }
        });

    }

    // Add Numbers
    private void appendToInput(String input) {
        if (Double.isNaN(tempResult)) {
            currentInput += input;
        } else {
            currentInput = Double.toString(tempResult) + input;
            tempResult = Double.NaN;
        }
        updateCalcDisplay();
    }

    // Update Display Number and Result
    private void updateCalcDisplay() {
        calc.setText(currentInput);

        if (!Double.isNaN(tempResult)) {
            result.setText(Double.toString(tempResult));
        } else {
            result.setText("");
        }
    }

    private void calculateResult() {
        if (!Double.isNaN(firstValue)) {
            String[] parts = currentInput.split("[+\\-x/]");

            if (parts.length >= 2) {
                try {
                    double currentValue = Double.parseDouble(parts[0]);

                    for (int i = 1; i < parts.length; i++) {
                        char operator = currentInput.charAt(parts[i - 1].length());
                        double operand = Double.parseDouble(parts[i]);

                        switch (operator) {
                            case '+':
                                currentValue += operand;
                                break;
                            case '-':
                                currentValue -= operand;
                                break;
                            case 'x':
                                currentValue *= operand;
                                break;
                            case '/':
                                currentValue /= operand;
                                break;
                        }
                    }

                    tempResult = currentValue;
                    currentInput = Double.toString(tempResult);
                    firstValue = tempResult;
                    currentOperator = '\0';
                    updateCalcDisplay();

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    return;
                }
            } else {
                try {
                    double currentValue = Double.parseDouble(currentInput);
                    tempResult = currentValue;
                    currentInput = Double.toString(tempResult);
                    firstValue = tempResult;
                    currentOperator = '\0';
                    updateCalcDisplay();

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    return;
                }
            }
        }
    }

    private void handleOperator(char operator) {
        if (!currentInput.isEmpty()) {
            if (Double.isNaN(firstValue)) {
                firstValue = Double.parseDouble(currentInput);
                currentOperator = operator;
            } else {
                if (!Double.isNaN(secondValue)) {
                    calculateResult();
                }
                secondValue = Double.parseDouble(currentInput);
                currentOperator = operator;
                currentInput = "";
            }
        }
    }

}