package com.im.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import com.example.improvementmonitor.R;

import java.text.DecimalFormat;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener{

    DecimalFormat df = new DecimalFormat("#");

    TextView solutionTv, resultTv;
    Button buttonC, buttonBracketOpen, buttonBracketClose;
    Button buttonDivide, buttonMultiply, buttonSubtract, buttonAddition, buttonEquals;
    Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9;
    Button buttonAC, buttonDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        resultTv = findViewById(R.id.result_textview);
        solutionTv = findViewById(R.id.calculate_textview); // can be done with method calling too, see below

        assignID(buttonC, R.id.button_c);
        assignID(buttonBracketOpen, R.id.button_bracketOpen);
        assignID(buttonBracketClose, R.id.button_bracketClose);

        assignID(buttonDivide, R.id.button_divide);
        assignID(buttonMultiply, R.id.button_multiply);
        assignID(buttonSubtract, R.id.button_subtract);
        assignID(buttonAddition, R.id.button_addition);
        assignID(buttonEquals, R.id.button_equal);

        assignID(btn0, R.id.button_zero);
        assignID(btn1, R.id.button_one);
        assignID(btn2, R.id.button_two);
        assignID(btn3, R.id.button_three);
        assignID(btn4, R.id.button_four);
        assignID(btn5, R.id.button_five);
        assignID(btn6, R.id.button_six);
        assignID(btn7, R.id.button_seven);
        assignID(btn8, R.id.button_eight);
        assignID(btn9, R.id.button_nine);

        assignID(buttonAC, R.id.button_all_clear);
        assignID(buttonDot, R.id.button_point_dot);
    }

    @Override
    public void onClick(View v) {// whatever button is clicked, will be processed by this method if called
        Button button = (Button) v;

        String buttonText = button.getText().toString();
//      solutionTv.setText(buttonText);
        String dataToCalculate = solutionTv.getText().toString();

        if (buttonText.equals("AC")){
            solutionTv.setText("");
            resultTv.setText("");
            return;
        }
        else if(buttonText.equals("=")){
            solutionTv.setText(resultTv.getText());
//            solutionTv.setText("");
            resultTv.setText("");
            return;

        }
        else if (buttonText.equals("X")){
            if(dataToCalculate.length()==1){
                solutionTv.setText("");
                resultTv.setText("");
                return;
            }
            else if(dataToCalculate.isEmpty()){
                solutionTv.setText("");
                resultTv.setText("");
                return;
            }
            else {
            dataToCalculate = dataToCalculate.substring(0, (dataToCalculate.length()-1));
            }
        }else{
            dataToCalculate = dataToCalculate+buttonText;
        }
        solutionTv.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);

        if(!finalResult.equals("Error")){
            resultTv.setText(finalResult);
        }
    }

    String getResult(String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);

            Scriptable scriptable = context.initStandardObjects();
            String c = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();

            // Convert result to a double, format it, and return the formatted result
            double resultValue = Double.parseDouble(c);

            // Format the result to avoid scientific notation
            String finalResult = df.format(resultValue);

            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0", "");
            }
            return finalResult;
        }catch (Exception e){
            return "Error";
        }
    }

    void assignID(Button btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }
}