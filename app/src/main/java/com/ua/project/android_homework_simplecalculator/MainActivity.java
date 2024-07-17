package com.ua.project.android_homework_simplecalculator;

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
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
    }

    public void numberBtn_OnClick(View view) {
        Button numberBtn = (Button) view;

        try {
            operand1 *= 10;
            operand1 += Integer.parseInt(numberBtn.getText().toString());

            binding.tvOperand1.setText(String.valueOf(operand1));
        }
        catch (Exception ex) {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Number button exception!")
                    .setMessage(ex.getMessage())
                    .setPositiveButton("OK", (dialog, which) -> {
                        dialog.dismiss();
                    }).show();
        }
    }

    public void actionBtn_OnClick(View view) {
        Button actionBtn = (Button) view;


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
                    throw new ArithmeticException();
                }

                return this.operand2 / this.operand1;
            default:
                throw new ArithmeticException();
        }
    }
}