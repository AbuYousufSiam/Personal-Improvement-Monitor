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

public class ExtendedCalculatorActivity extends AppCompatActivity implements View.OnClickListener {

    private String dataToCalculate = "";
    private int openBracketsCount = 0;
    private boolean isResultShown = false; // Track if "=" was just pressed

    TextView solutionTv, resultTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_extended);

        initializeViewsAndListeners();

        // Specific listeners for bracket buttons
        findViewById(R.id.button_bracketOpen).setOnClickListener(v -> {
            appendToCalculation("(");
            openBracketsCount++;
        });

        findViewById(R.id.button_bracketClose).setOnClickListener(v -> {
            if (openBracketsCount > 0) {
                appendToCalculation(")");
                openBracketsCount--;
            }
        });

        // Listener for equals button
        findViewById(R.id.button_equal).setOnClickListener(v -> {
            if (isBracketsBalanced(dataToCalculate) && isValidExpression(dataToCalculate)) {
                calculateResult(dataToCalculate);
                isResultShown = true; // Set flag to true after pressing "="
            } else {
                resultTv.setText(R.string.syntax_error);
            }
        });
    }

    private void initializeViewsAndListeners() {
        int[] buttonIds = {
                R.id.button_c, R.id.button_bracketOpen, R.id.button_bracketClose,
                R.id.button_divide, R.id.button_multiply, R.id.button_subtract,
                R.id.button_addition, R.id.button_equal, R.id.button_zero,
                R.id.button_one, R.id.button_two, R.id.button_three,
                R.id.button_four, R.id.button_five, R.id.button_six,
                R.id.button_seven, R.id.button_eight, R.id.button_nine,
                R.id.button_all_clear, R.id.button_dot
        };

        for (int id : buttonIds) {
            Button button = findViewById(id);
            button.setOnClickListener(this);
        }

        solutionTv = findViewById(R.id.calculate_textview);
        resultTv = findViewById(R.id.result_textview);
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
            isResultShown = false; // Reset the flag
        } else if (buttonText.equals("X")) {
            if (dataToCalculate.length() > 0) {
                dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
                solutionTv.setText(dataToCalculate);
            }
        } else {
            // Handle operators following a result display
            if (isResultShown && buttonText.matches("[+\\-*/]")) {
                dataToCalculate = resultTv.getText().toString() + buttonText; // Use previous result as starting value
                solutionTv.setText(dataToCalculate);
                isResultShown = false; // Reset flag after appending operator
            } else if (isResultShown && !buttonText.matches("[+\\-*/]")) {
                solutionTv.setText(""); // Clear calculation area if result was shown
                dataToCalculate = "";
                resultTv.setText("");
                isResultShown = false;
                appendToCalculation(buttonText);
            } else {
                appendToCalculation(buttonText);
            }
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
            String formattedData = dataToCalculate.replaceAll("×", "*").replaceAll("÷", "/");

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

            if (resultValue == 0) {
                resultTv.setText("0");
            } else {
                resultTv.setText(formattedResult);
            }

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

    private boolean isValidExpression(String expression) {
        if (Pattern.compile("[+\\-*/]{2,}").matcher(expression).find()) {
            return false;
        }

        if (expression.startsWith("+") || expression.startsWith("-") || expression.startsWith("*") || expression.startsWith("/") ||
                expression.endsWith("+") || expression.endsWith("-") || expression.endsWith("*") || expression.endsWith("/")) {
            return false;
        }

        if (Pattern.compile("\\(\\)").matcher(expression).find()) {
            return false;
        }
        return true;
    }
}
