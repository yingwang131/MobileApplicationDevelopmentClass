package com.example.hm1bmiclaculation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

// Homework Assignment #01 / Mobile Application Development 5180
// Group32_HW01
// Group 32: Ying Wang, Heidi Hao, Nick DeBakey

public class MainActivity extends AppCompatActivity {

    private TextView tv_title;
    private TextView tv_weight;
    private TextView tv_height;
    private TextView tv_lb;
    private TextView tv_inches;
    private TextView tv_feet;


    private EditText weight1;
    private EditText height1;
    private EditText height2;


    private TextView tv_result1;
    private TextView tv_result2;

    private Button buttonCal;

    double weight_len;
    double height_len1;
    double height_len2;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("BMI Claculator");

        tv_title = findViewById(R.id.tv_title);
        tv_weight = findViewById(R.id.tv_weight);
        tv_height = findViewById(R.id.tv_height);
        tv_lb = findViewById(R.id.tv_lb);
        tv_inches = findViewById(R.id.tv_inches);
        tv_feet = findViewById(R.id.tv_feet);


        weight1 = findViewById(R.id.weight1);
        height1 = findViewById(R.id.height1);
        height2 = findViewById(R.id.height2);

        tv_result1 = findViewById(R.id.tv_result1);
        tv_result2 = findViewById(R.id.tv_result2);

        buttonCal = findViewById(R.id.buttonCal);



        // on click for Calculation button
        buttonCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tempWeight1 = weight1.getText().toString();
                String tempHeight1 = height1.getText().toString();
                String tempHeight2 = height2.getText().toString();
                if (tempWeight1 != null && !tempWeight1.equals("")){
                    weight_len = Double.parseDouble(tempWeight1);
                }
                if (tempHeight1 != null && !tempHeight1.equals("")){
                    height_len1 = Double.parseDouble(tempHeight1);
                }
                if (tempHeight2 != null && !tempHeight2.equals("")){
                    height_len2 = Double.parseDouble(tempHeight2);
                }

                if (weight1.getText().toString().equals("")){
                    weight1.setError("Hey I need a value");
                    if (height1.getText().toString().equals((""))){
                        height1.setError("Hey I need a value!");
                        if (height2.getText().toString().equals("")){
                            height2.setError("Hey, I need a value");
                        }
                    }
                } else {
//                    double bmi = (weight_len / (height_len2 * height_len2)) * 703;
//                    tv_result1.setText(bmi + "");

                    //convert measurements from imperial to metric
                    double weightInKilos = weight_len * 0.453592;
                    double heightInMeters = (((height_len1 * 12) + height_len2) * .0254);
                    double bmi = weightInKilos / Math.pow(heightInMeters, 2.0);
                    tv_result1.setText(bmi + "");
                    tv_result1.setText(String.format("%.2f", bmi));
                    //double bmi = weightInKilos / (heightInMeters * heightInMeters);
                    if (bmi < 18.5){
                        tv_result2.setText("You are Underweight.");
                    } else if (bmi >= 18.5 && bmi < 24.9){
                        tv_result2.setText("You are Normal weight.");
                    } else if (bmi >= 25 && bmi < 29.9){
                        tv_result2.setText("You are Overweight.");
                    }else if (bmi >= 30){
                        tv_result2.setText("You are Obese.");
                    }
                }
            }
        });
    }
}
