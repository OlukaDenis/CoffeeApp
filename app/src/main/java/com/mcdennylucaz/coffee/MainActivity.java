package com.mcdennylucaz.coffee;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;


public class MainActivity extends AppCompatActivity {
    int numberOfCoffee = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //The method that is called when order button is clicked
    public void submitOrder(View view){

        displayQuantity(numberOfCoffee);
        displayPrice(numberOfCoffee * 5);
    }

    //The method that is called when minus button is clicked
    public void decrement(View view){

        numberOfCoffee=numberOfCoffee-1;
        displayQuantity(numberOfCoffee);
    }

    //The method that is called when plus button is clicked
    public void increment(View view){

        numberOfCoffee=numberOfCoffee+1;
        displayQuantity(numberOfCoffee);
    }

    public void displayQuantity(int number){
        TextView quantity = (TextView)findViewById(R.id.quantity_text_view);
        quantity.setText("" +number);
    }
    public void displayPrice(int number){
        TextView price = (TextView)findViewById(R.id.price_text_view);
        price.setText(NumberFormat.getCurrencyInstance().format(number));
    }
}