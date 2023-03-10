package com.example.calculator;

import android.os.Bundle;
import android.text.InputFilter;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    boolean isAddition, isMultiply, isDivide, isSubtract;
    double firstValue, secondValue;
    double answer;
    Button buttonOne, buttonTwo, buttonThree, buttonFour, buttonFive, buttonSix, buttonSeven, buttonEight, buttonNine, buttonDot, buttonEqual, buttonSub;
    Button buttonAdd, buttonMultiple, buttonDivide, buttonPercentage, buttonDelete, buttonZero, buttonAC;
    EditText etInput, etResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpViews();
        setClickListener();
    }

    private void setUpViews() {
        buttonOne = findViewById(R.id.one);
        buttonTwo = findViewById(R.id.two);
        buttonThree = findViewById(R.id.three);
        buttonFour = findViewById(R.id.four);
        buttonFive = findViewById(R.id.five);
        buttonSix = findViewById(R.id.six);
        buttonSeven = findViewById(R.id.seven);
        buttonEight = findViewById(R.id.eight);
        buttonNine = findViewById(R.id.nine);
        buttonZero = findViewById(R.id.zero);
        buttonEqual = findViewById(R.id.equal);
        buttonDot = findViewById(R.id.dot);
        buttonPercentage = findViewById(R.id.percentage);
        buttonAdd = findViewById(R.id.plus);
        buttonSub = findViewById(R.id.sub);
        buttonDivide = findViewById(R.id.divide);
        buttonMultiple = findViewById(R.id.multiple);
        buttonDelete = findViewById(R.id.delete);
        buttonAC = findViewById(R.id.ac);
        etInput = findViewById(R.id.etInput);
        etResult = findViewById(R.id.etResult);

        etInput.setShowSoftInputOnFocus(false);
        etResult.setShowSoftInputOnFocus(false);
        etInput.setFilters(new InputFilter[]{new DecimalPlaceInputFilter(8,2)});
    }

    private void setClickListener() {
        buttonZero.setOnClickListener(view -> etInput.setText(String.format("%s0", etInput.getText())));
        buttonOne.setOnClickListener(view -> etInput.setText(String.format("%s1", etInput.getText())));
        buttonTwo.setOnClickListener(view -> etInput.setText(String.format("%s2", etInput.getText())));
        buttonThree.setOnClickListener(view -> etInput.setText(String.format("%s3", etInput.getText())));
        buttonFour.setOnClickListener(view -> etInput.setText(String.format("%s4", etInput.getText())));
        buttonFive.setOnClickListener(view -> etInput.setText(String.format("%s5", etInput.getText())));
        buttonSix.setOnClickListener(view -> etInput.setText(String.format("%s6", etInput.getText())));
        buttonSeven.setOnClickListener(view -> etInput.setText(String.format("%s7", etInput.getText())));
        buttonEight.setOnClickListener(view -> etInput.setText(String.format("%s8", etInput.getText())));
        buttonNine.setOnClickListener(view -> etInput.setText(String.format("%s9", etInput.getText())));
        buttonDot.setOnClickListener(view -> etInput.setText(String.format("%s.", etInput.getText())));

        buttonAC.setOnClickListener(view -> clearInputAndResult());

        buttonDelete.setOnClickListener(view -> {
            String enteredText = etInput.getText().toString();
            if (isInputNotEmpty()) {
                String stringAfterDelete = enteredText.substring(0, enteredText.length() - 1);
                etInput.setText(stringAfterDelete);
            }
        });

        buttonAdd.setOnClickListener(view -> {
            if (isInputNotEmpty()) {
                isAddition = true;
                String enteredText = etInput.getText().toString();
                firstValue = Double.parseDouble(enteredText);
                clearInput();
            }
        });
        buttonSub.setOnClickListener(view -> {
            if (isInputNotEmpty()) {
                isSubtract = true;
                String enteredText = etInput.getText().toString();
                firstValue = Double.parseDouble(enteredText);
                etInput.setText(null);
            }
        });

        buttonMultiple.setOnClickListener(view -> {
            if (isInputNotEmpty()) {
                isMultiply = true;
                String enteredText = etInput.getText().toString();
                firstValue = Double.parseDouble(enteredText);
                etInput.setText(null);
            }
        });

        buttonDivide.setOnClickListener(view -> {
            if (isInputNotEmpty()) {
                isDivide = true;
                String enteredText = etInput.getText().toString();
                firstValue = Double.parseDouble(enteredText);
                etInput.setText(null);
            }
        });

        buttonEqual.setOnClickListener(view -> {
            String enteredText = etInput.getText().toString();
            if (enteredText.length() > 0) {
                secondValue = Double.parseDouble(enteredText);
                if (isAddition) {
                    answer = firstValue + secondValue;
                    isAddition = false;
                } else if (isSubtract) {
                    answer = firstValue - secondValue;
                    isSubtract = false;
                } else if (isMultiply) {
                    answer = firstValue * secondValue;
                    isMultiply = false;
                } else if (isDivide) {
                    answer = firstValue / secondValue;
                    isDivide = false;
                }

                etResult.setText(getFormattedResult(answer));
                clearInput();
            }
        });
    }

    private String getFormattedResult(Double answer) {
        DecimalFormat format = new DecimalFormat("0.#");
        return format.format(answer);
    }

    private void clearInput() {
        etInput.setText(null);
    }

    private void clearInputAndResult() {
        etInput.setText(null);
        etResult.setText(null);
    }

    private boolean isInputNotEmpty() {
        return etInput.getText().length() > 0;
    }
}
