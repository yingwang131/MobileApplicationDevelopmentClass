package com.example.group32_homework02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.Serializable;

public class OrderActivity extends AppCompatActivity {
    private TextView textView_baseM;
    private TextView textView_topM;
    private TextView textView_alltop;
    private TextView textView_delCostM;
    private TextView textView_totalM;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        setTitle("Pizza Stroe");

        Pizza pizza = (Pizza) getIntent().getExtras().getSerializable("pizzaCheckout");
        Log.d("demo", "onCreate: " + pizza.toppings.toString());

        TextView tvPrice = findViewById(R.id.textView_topM);
        Double totalCost = pizza.toppings.size() * 1.5;
        tvPrice.setText(String.valueOf(totalCost));

        TextView basePrice = findViewById(R.id.textView_baseM);
        basePrice.setText("6.5");


        TextView tv = findViewById(R.id.textView_alltop);
        tv.setText(pizza.toppings.toString());
        TextView tvTotal = findViewById(R.id.textView_totalM);

        TextView deliveryP = findViewById(R.id.textView_delCostM);

        if (pizza.deliveryBool){
            deliveryP.setText("4.0");
            tvTotal.setText(totalCost + 6.5 + 4.0 + "");
        } else{
            deliveryP.setText("0.0");
            tvTotal.setText(totalCost + 6.5 + "");

        }
    }
}
