package com.ua.project.android_homework_simplecalculator;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.ua.project.android_homework_simplecalculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private double calcResult = 0;
    private int operand1 = 0;
    private int operand2 = 0;
    private char currentAction = ' ';
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        binding.btnActionClear.setOnClickListener(v -> {
            clearOperandsData();

            calcResult = 0;
        });
    }

    public void numberBtn_OnClick(View view) {
        if(view instanceof Button) {
            Button numberBtn = (Button) view;

            try {
                operand1 *= 10;
                operand1 += Integer.parseInt(numberBtn.getText().toString());

                binding.tvOperand1.setText(String.valueOf(operand1));
            }
            catch (Exception ex) {
                displayErrorInAlertDialog(ex.getMessage());
            }
        }
    }

    public void actionBtn_OnClick(View view) {
        if(view instanceof Button) {
            try {
                char prevAction = ' ';
                Button actionBtn = (Button) view;

                if(currentAction != ' ') {
                    prevAction = currentAction;
                }

                currentAction = actionBtn.getText().charAt(0);

                if(operand2 != 0 && currentAction != '=') {
                    calcResult = calculate(currentAction);

                    binding.tvOperand1.setText(String.valueOf(calcResult));
                }
                else if(operand2 != 0 && prevAction != ' ') {
                    calcResult = calculate(prevAction);

                    clearOperandsData();

                    binding.tvOperand1.setText(String.valueOf(calcResult));
                }

                if(currentAction != '=') {
                    operand2 = operand1;
                    operand1 = 0;

                    binding.tvOperand2.setText(String.valueOf(operand2));
                    binding.tvOperand1.setText("0");
                }
            }
            catch (Exception ex){
                displayErrorInAlertDialog(ex.getMessage());
            }
        }
    }

    private void displayErrorInAlertDialog(String msg) {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Number button exception!")
                .setMessage(msg)
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss()).show();
    }

    private double calculate(char action) {
        switch (action) {
            case '+' :
                return this.operand2 + this.operand1;
            case '-' :
                return this.operand2 - this.operand1;
            case '*':
                return this.operand2 * this.operand1;
            case '/':
                if(this.operand2 == 0 || this.operand1 == 0) {
                    throw new ArithmeticException("Division by zero!");
                }

                return (double) this.operand2 / this.operand1;
            default:
                throw new ArithmeticException("Invalid action!");
        }
    }

    private void clearOperandsData() {
        operand1 = 0;
        operand2 = 0;

        binding.tvOperand1.setText("0");
        binding.tvOperand2.setText("0");
    }
}