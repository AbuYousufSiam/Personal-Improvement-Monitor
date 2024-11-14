package com.im.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.improvementmonitor.R;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import java.text.DecimalFormat;
import java.util.Stack;
import java.util.regex.Pattern;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {

    private String dataToCalculate = "";
    private int openBracketsCount = 0;

    TextView solutionTv, resultTv;
    Button buttonC, buttonBracketOpen, buttonBracketClose;
    Button buttonDivide, buttonMultiply, buttonSubtract, buttonAddition, buttonEquals;
    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    Button buttonAC, buttonDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_calculator);
        setContentView(R.layout.activity_calculator);

        resultTv = findViewById(R.id.result_textview);
        solutionTv = findViewById(R.id.calculate_textview);

        buttonC = findViewById(R.id.button_c);
        buttonBracketOpen = findViewById(R.id.button_bracketOpen);
        buttonBracketClose = findViewById(R.id.button_bracketClose);
        buttonDivide = findViewById(R.id.button_divide);
        buttonMultiply = findViewById(R.id.button_multiply);
        buttonSubtract = findViewById(R.id.button_subtract);
        buttonAddition = findViewById(R.id.button_addition);
        buttonEquals = findViewById(R.id.button_equal);
        btn0 = findViewById(R.id.button_zero);
        btn1 = findViewById(R.id.button_one);
        btn2 = findViewById(R.id.button_two);
        btn3 = findViewById(R.id.button_three);
        btn4 = findViewById(R.id.button_four);
        btn5 = findViewById(R.id.button_five);
        btn6 = findViewById(R.id.button_six);
        btn7 = findViewById(R.id.button_seven);
        btn8 = findViewById(R.id.button_eight);
        btn9 = findViewById(R.id.button_nine);
        buttonAC = findViewById(R.id.button_all_clear);
        buttonDot = findViewById(R.id.button_point_dot);

        buttonC.setOnClickListener(this);
        buttonBracketOpen.setOnClickListener(this);
        buttonBracketClose.setOnClickListener(this);
        buttonDivide.setOnClickListener(this);
        buttonMultiply.setOnClickListener(this);
        buttonSubtract.setOnClickListener(this);
        buttonAddition.setOnClickListener(this);
        buttonEquals.setOnClickListener(this);
        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        buttonAC.setOnClickListener(this);
        buttonDot.setOnClickListener(this);

        buttonBracketOpen.setOnClickListener(v -> {
            appendToCalculation("(");
            openBracketsCount++;
        });

        buttonBracketClose.setOnClickListener(v -> {
            if (openBracketsCount > 0) {
                appendToCalculation(")");
                openBracketsCount--;
            }
        });

        buttonEquals.setOnClickListener(v -> {
            if (isBracketsBalanced(dataToCalculate) && isValidExpression(dataToCalculate)) {
                calculateResult(dataToCalculate);
            } else {
                resultTv.setText("Error: Invalid Expression");
            }
        });
    }

    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        String buttonText = button.getText().toString();
        dataToCalculate = solutionTv.getText().toString();

        if (buttonText.equals("AC")) {
            solutionTv.setText("");
            resultTv.setText("");
            dataToCalculate = "";
            openBracketsCount = 0;
        } else if (buttonText.equals("X")) {
            if (dataToCalculate.length() > 0) {
                dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
                solutionTv.setText(dataToCalculate);
            }
        } else {
            // If a result is already shown, start a new calculation if a digit or dot is pressed
            if (resultTv.getText().length() > 0 && !buttonText.matches("[+\\-*/()]")) {
                solutionTv.setText("");
                dataToCalculate = "";
                resultTv.setText("");
            }

            appendToCalculation(buttonText);
        }
    }

    private void appendToCalculation(String value) {
        // Add implicit multiplication when opening bracket follows a number
        if (value.equals("(") && dataToCalculate.length() > 0) {
            char lastChar = dataToCalculate.charAt(dataToCalculate.length() - 1);
            if (Character.isDigit(lastChar)) {
                dataToCalculate += "*"; // Implicit multiplication
            }
        }
        dataToCalculate += value;
        solutionTv.setText(dataToCalculate);
    }

    private void calculateResult(String dataToCalculate) {
        try {
            // Replace custom symbols with JavaScript-compatible operators
            String formattedData = dataToCalculate.replaceAll("×", "*").replaceAll("÷", "/");

            // Evaluate the expression using Rhino
            Context rhino = Context.enter();
            rhino.setOptimizationLevel(-1);
            String result;

            try {
                Scriptable scriptable = rhino.initStandardObjects();
                result = rhino.evaluateString(scriptable, formattedData, "JavaScript", 1, null).toString();
            } finally {
                Context.exit();
            }

            double resultValue = Double.parseDouble(result);
            DecimalFormat df = new DecimalFormat("#.##########");
            String formattedResult = df.format(resultValue);

            // Update resultTv only if the result is not null or empty
            if (resultValue == 0) {
                resultTv.setText("0");
            } else {
                resultTv.setText(formattedResult);
            }

            // Reset bracket count for new calculation
            openBracketsCount = 0;

        } catch (Exception e) {
            resultTv.setText("Error: Invalid Expression");
        }
    }

    private boolean isBracketsBalanced(String expression) {
        Stack<Character> stack = new Stack<>();
        for (char ch : expression.toCharArray()) {
            if (ch == '(') {
                stack.push(ch);
            } else if (ch == ')') {
                if (stack.isEmpty()) {
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    // Method to validate expression for ambiguous inputs
    private boolean isValidExpression(String expression) {
        // Check for consecutive operators (e.g., ++, --, +-)
        if (Pattern.compile("[+\\-*/]{2,}").matcher(expression).find()) {
            return false;
        }

        // Check if expression starts or ends with an operator
        if (expression.startsWith("+") || expression.startsWith("-") || expression.startsWith("*") || expression.startsWith("/") ||
                expression.endsWith("+") || expression.endsWith("-") || expression.endsWith("*") || expression.endsWith("/")) {
            return false;
        }

        // Check for empty brackets ()
        if (Pattern.compile("\\(\\)").matcher(expression).find()) {
            return false;
        }

        return true;
    }
}
