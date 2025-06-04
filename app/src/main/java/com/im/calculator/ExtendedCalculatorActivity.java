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

    private Button btnRoot, btnPercent, btnPower, btnPi;
    TextView solutionTv, resultTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_extended);


        // Initialize the new buttons
        btnRoot = findViewById(R.id.button_root);
        btnPercent = findViewById(R.id.button_percent);
        btnPower = findViewById(R.id.button_power);
        btnPi = findViewById(R.id.button_pi);

        // Set onClick listeners for the new buttons
        btnRoot.setOnClickListener(view -> handleRoot());
        btnPercent.setOnClickListener(view -> handlePercent());
        btnPower.setOnClickListener(view -> handlePower());
        btnPi.setOnClickListener(view -> handlePi());

        // A better way to handle these kind of works easily
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
        } else if (buttonText.equals("x")) {
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
        // If the value is '(', '√', or 'π' and the last character is a digit or ')', add implicit multiplication
        if ((value.equals("(") || value.equals("√") || value.equals(String.valueOf(Math.PI))) && dataToCalculate.length() > 0) {
            char lastChar = dataToCalculate.charAt(dataToCalculate.length() - 1);

            // Check if last character is a digit or closing bracket ')'
            if (Character.isDigit(lastChar) || lastChar == ')') {
                dataToCalculate += "*"; // Add multiplication sign
            }
        }

        // Append the current value (either '(', '√', 'π', or any other digit/operator)
        dataToCalculate += value;

        // Update the TextView with the current calculation
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

                // Process square root, pi, and power operations
                formattedData = formattedData.replaceAll("√", "Math.sqrt")
                        .replaceAll("π", String.valueOf(Math.PI))
                        .replaceAll("\\^2", "**2");

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

//    // Method to handle square root operation
//    private void handleRoot() {
//        // Implement your logic for square root here
//        // Example: Get the current input, calculate square root, and display result
//        String currentInput = getCurrentInput(); // assuming you have a method to get current input
//        try {
//            double result = Math.sqrt(Double.parseDouble(currentInput));
//            displayResult(result); // assuming you have a method to display results
//        } catch (NumberFormatException e) {
//            showError("Invalid Input"); // method to display error if needed
//        }
//    }
    private void handleRoot() {
        if (isResultShown) {
            dataToCalculate = "√(" + resultTv.getText().toString() + ")";
            isResultShown = false;
        } else {
            dataToCalculate += "√(";
            openBracketsCount++;
        }
        solutionTv.setText(dataToCalculate);
    }

    // Method to handle percent operation
    private void handlePercent() {
        // Implement your logic for percent operation here
        String currentInput = getCurrentInput();
        try {
            double result = Double.parseDouble(currentInput) / 100;
            displayResult(result);
        } catch (NumberFormatException e) {
            showError("Invalid Input");
        }
    }

    // Method to handle power operation (assuming power of 2)
    private void handlePower() {
        String baseInput = getCurrentInput();
        try {
            double base = Double.parseDouble(baseInput);
            double result = Math.pow(base, 2); // Squaring the base (exponent of 2)
            displayResult(result);
        } catch (NumberFormatException e) {
            showError("Invalid Input");
        }
    }

    // Method to handle Pi (π) operation
    private void handlePi() {
        // Insert the value of Pi into the display
        appendToCalculation(String.valueOf(Math.PI));
        solutionTv.setText(dataToCalculate);
        displayResult(Double.valueOf(solutionTv.getText().toString())); // assuming displayResult handles showing output
    }

    // Method to get the current input from display
    private String getCurrentInput() {
        return solutionTv.getText().toString();
    }

    // Method to display result in display TextView
    private void displayResult(double result) {
        resultTv.setText(String.valueOf(result));
    }

    // Method to display an error in display TextView
    private void showError(String message) {
        resultTv.setText(message);
    }

}
