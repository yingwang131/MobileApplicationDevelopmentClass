package com.example.group32_homework02;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TableRow;
import android.widget.Toast;

import java.sql.RowId;

public class MainActivity extends AppCompatActivity {


    String[] toppings;
    Button button_addTopping;
    Button button_clearPizza;
    CheckBox cb_delivery;
    ProgressBar progressBar;
    TableRow row1;
    TableRow row2;
    Button button_checkout;


    private boolean addTopping(int topping, final String toppingName) {

        if (row1.getChildCount() + row2.getChildCount() < 10) {
            ImageView iv = new ImageView(MainActivity.this);
            iv.setImageResource(topping);
            newPizza.toppings.add(toppingName);

            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    newPizza.toppings.remove(toppingName);
                    ImageView iv = (ImageView) view;
                    ((LinearLayout) view.getParent()).removeView(view);

                    if (row1.getChildCount() < 5 && row2.getChildCount() > 0) {
                        View vc = row2.getChildAt(0);
                        row2.removeView(vc);
                        row1.addView(vc);
                    }
                }
            });

            if (row1.getChildCount() < 5) {
                row1.addView(iv);
            } else {
                row2.addView(iv);
            }
            return true;
        }

        return false;
    }

    Pizza newPizza = new Pizza();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Pizza Store");

        button_addTopping = findViewById(R.id.button_addTopping);
        button_clearPizza = findViewById(R.id.button_clearPizza);
        progressBar = findViewById(R.id.progressBar);
        row1 = findViewById(R.id.row1);
        row2 = findViewById(R.id.row2);

        toppings = new String[]{"Bacon", "Cheese", "Garlic", "Green Pepper", "Mushroom", "Olives", "Onions", "Red Pepper"};

        // set progress bar max value
        progressBar.setMax(10);
        button_addTopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // create list of items and adapter

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, toppings);

                // create the alert dialog
                AlertDialog.Builder toppingsBuilder = new AlertDialog.Builder(MainActivity.this);
/*
                // instantiate the images outside of method so they can be called on later
                final ImageView bacon = new ImageView(MainActivity.this);
                bacon.setImageResource(R.drawable.bacon);
                bacon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view)
                    {

                        boolean flag=false;
                        if (bacon.getTag().equals("row1"))
                        {
                            row1.removeView(bacon);
                            flag=true;
                        }else
                        {
                            row2.removeView(bacon);
                        }

                        if (flag)
                        {
                            if(row2.getChildAt(0)!=null){
                                View v = row2.getChildAt(0);
                                row2.removeView(v);
                                row1.addView(v);
                            }

                        }

                    }

                });
                final ImageView cheese = new ImageView(MainActivity.this);
                cheese.setImageResource(R.drawable.cheese);
                cheese.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean flag=false;
                        if (cheese.getTag().equals("row1"))
                        {
                            row1.removeView(cheese);
                            flag=true;
                        }else
                        {
                            row2.removeView(cheese);
                        }

                        if (flag)
                        {
                            if(row2.getChildAt(0)!=null){
                                View v = row2.getChildAt(0);
                                row2.removeView(v);
                                row1.addView(v);
                            }

                        }
                    }
                });
                final ImageView garlic = new ImageView(MainActivity.this);
                garlic.setImageResource(R.drawable.garlic);
                garlic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean flag=false;
                        if (garlic.getTag().equals("row1"))
                        {
                            row1.removeView(garlic);
                            flag=true;
                        }else
                        {
                            row2.removeView(garlic);
                        }

                        if (flag)
                        {
                            if(row2.getChildAt(0)!=null){
                                View v = row2.getChildAt(0);
                                row2.removeView(v);
                                row1.addView(v);
                            }

                        }
                    }
                });
                final ImageView greenPepper = new ImageView(MainActivity.this);
                greenPepper.setImageResource(R.drawable.green_pepper);
                greenPepper.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean flag=false;
                        if (greenPepper.getTag().equals("row1"))
                        {
                            row1.removeView(greenPepper);
                            flag=true;
                        }else
                        {
                            row2.removeView(greenPepper);
                        }
                        if (flag)
                        {
                            if(row2.getChildAt(0)!=null){
                                View v = row2.getChildAt(0);
                                row2.removeView(v);
                                row1.addView(v);
                            }

                        }
                    }
                });
                final ImageView mushroom = new ImageView(MainActivity.this);
                mushroom.setImageResource(R.drawable.mashroom);
                mushroom.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean flag=false;
                        if (mushroom.getTag().equals("row1"))
                        {
                            row1.removeView(mushroom);
                            flag=true;
                        }else
                        {
                            row2.removeView(mushroom);
                        }

                        if (flag)
                        {
                            if(row2.getChildAt(0)!=null){
                                View v = row2.getChildAt(0);
                                row2.removeView(v);
                                row1.addView(v);
                            }

                        }
                    }
                });
                final ImageView olives = new ImageView(MainActivity.this);
                olives.setImageResource(R.drawable.olive);
                olives.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean flag=false;
                        if (olives.getTag().equals("row1"))
                        {
                            row1.removeView(olives);
                            flag=true;
                        }else
                        {
                            row2.removeView(olives);
                        }

                        if (flag)
                        {
                            if(row2.getChildAt(0)!=null){
                                View v = row2.getChildAt(0);
                                row2.removeView(v);
                                row1.addView(v);
                            }

                        }
                    }
                });
                final ImageView onions = new ImageView(MainActivity.this);
                onions.setImageResource(R.drawable.onion);
                onions.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean flag=false;
                        if (onions.getTag().equals("row1"))
                        {
                            row1.removeView(onions);
                            flag=true;
                        }else
                        {
                            row2.removeView(onions);
                        }

                        if (flag)
                        {
                            if(row2.getChildAt(0)!=null){
                                View v = row2.getChildAt(0);
                                row2.removeView(v);
                                row1.addView(v);
                            }

                        }
                    }
                });
                final ImageView redPepper = new ImageView(MainActivity.this);
                redPepper.setImageResource(R.drawable.red_pepper);
                redPepper.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean flag=false;
                        if (redPepper.getTag().equals("row1"))
                        {
                            row1.removeView(redPepper);
                            flag=true;
                        }else
                        {
                            row2.removeView(redPepper);
                        }

                        if (flag)
                        {
                            if(row2.getChildAt(0)!=null){
                                View v = row2.getChildAt(0);
                                row2.removeView(v);
                                row1.addView(v);
                            }

                        }
                    }
                });
*/
                // set title of Alert Dialog
                toppingsBuilder.setTitle("Choose A Topping");

                // add the items to the list
                toppingsBuilder.setAdapter(adapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // if statement for if row 1 is still empty

                        boolean flag = false;

                        switch (i) {
                            case 0:
                                flag = addTopping(R.drawable.bacon, "bacon");
                                break;
                            case 1:
                                flag = addTopping(R.drawable.cheese, "cheese");
                                break;
                            case 2:
                                flag = addTopping(R.drawable.garlic, "garlic");
                                break;
                            case 3:
                                flag = addTopping(R.drawable.green_pepper, "green pepper");
                                break;
                            case 4:
                                flag = addTopping(R.drawable.mashroom, "mushroom");
                                break;
                            case 5:
                                flag = addTopping(R.drawable.olive, "olive");
                                break;
                            case 6:
                                flag = addTopping(R.drawable.onion, "onion");
                                break;
                            case 7:
                                flag = addTopping(R.drawable.red_pepper, "red pepper");
                                break;
                        }

                        if (flag) {
                            progressBar.setProgress(row1.getChildCount() + row2.getChildCount());
                        } else {
                            Toast toast = Toast.makeText(MainActivity.this,
                                    "Maximum Topping Capacity Reached!",
                                    Toast.LENGTH_SHORT);
                            toast.show();
                        }


                        /*
                            if (row1.getChildAt(4) == null) {
                                switch (i) {
                                    case 0:
                                        bacon.setTag("row1");
                                        row1.addView(bacon);
                                        break;
                                    case 1:
                                        cheese.setTag("row1");
                                        row1.addView(cheese);
                                        break;
                                    case 2:
                                        garlic.setTag("row1");
                                        row1.addView(garlic);
                                        break;
                                    case 3:
                                        greenPepper.setTag("row1");
                                        row1.addView(greenPepper);
                                        break;
                                    case 4:
                                        mushroom.setTag("row1");
                                        row1.addView(mushroom);
                                        break;
                                    case 5:
                                        olives.setTag("row1");
                                        row1.addView(olives);
                                        break;
                                    case 6:
                                        onions.setTag("row1");
                                        row1.addView(onions);
                                        break;
                                    case 7:
                                        redPepper.setTag("row1");
                                        row1.addView(redPepper);
                                        break;
                                }
                                progressBar.setProgress(row1.getChildCount());
                                dialogInterface.dismiss();
                            } else if (row2.getChildAt(4) == null) {
                                switch (i) {
                                    case 0:
                                        bacon.setTag("row2");
                                        row2.addView(bacon);
                                        break;
                                    case 1:
                                        cheese.setTag("row2");
                                        row2.addView(cheese);
                                        break;
                                    case 2:
                                        garlic.setTag("row2");
                                        row2.addView(garlic);
                                        break;
                                    case 3:
                                        greenPepper.setTag("row2");
                                        row2.addView(greenPepper);
                                        break;
                                    case 4:
                                        mushroom.setTag("row2");
                                        row2.addView(mushroom);
                                        break;
                                    case 5:
                                        olives.setTag("row2");
                                        row2.addView(olives);
                                        break;
                                    case 6:
                                        onions.setTag("row2");
                                        row2.addView(onions);
                                        break;
                                    case 7:
                                        redPepper.setTag("row2");
                                        row2.addView(redPepper);
                                        break;
                                }
                                progressBar.setProgress(row1.getChildCount() + row2.getChildCount());
                                dialogInterface.dismiss();
                            } else {
                                Toast toast = Toast.makeText(MainActivity.this,
                                        "Maximum Topping Capacity Reached!",
                                        Toast.LENGTH_SHORT);
                                toast.show();
                            }
                            */
                    }
                });
                // can click out of it
                toppingsBuilder.setCancelable(true);
                // show Alert Dialog
                AlertDialog toppingsDialog = toppingsBuilder.create();
                toppingsDialog.show();
            }
        });

        // on click for 10 images


        button_clearPizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row1.removeAllViews();
                row2.removeAllViews();
                progressBar.setProgress(0);
                newPizza.toppings.clear();
            }
        });
        cb_delivery = findViewById(R.id.cb_delivery);
        cb_delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cb_delivery.isChecked()){
                    newPizza.deliveryBool = true;
                } else {
                    newPizza.deliveryBool = false;
                }
            }
        });

        findViewById(R.id.button_checkout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, OrderActivity.class);
//                final String add_Topping =  button_addTopping.getText().toString();
//                final String clear_Pizza = button_clearPizza.getText().toString();
//                final String delivery = cb_delivery.getText().toString();
//                Pizza pizza = new Pizza(add_Topping,clear_Pizza, delivery);

//                Log.d("demo", "onClick: " + newPizza.toppings.toString());
//                Bundle sendData = new Bundle();
//                sendData.putSerializable("pizza", newPizza);
                intent.putExtra("pizzaCheckout", newPizza);
                startActivity(intent);
            }
        });
    }
}
