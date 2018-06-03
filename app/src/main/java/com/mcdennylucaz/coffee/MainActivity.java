package com.mcdennylucaz.coffee;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;


public class MainActivity extends AppCompatActivity {
    int quantity = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**The method that is called when order button is clicked
     *
     * @param view
     */
    public void submitOrder(View view){

        EditText nameField = (EditText) findViewById(R.id.name_field);
        String name = nameField.getText().toString();

        //Checks whether chocolate is added to the coffee
        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate_checkbox);
            boolean hasChocolate= chocolate.isChecked();
            Log.v("MainActivity", "Has Pizza:" + hasChocolate);

        //Check whether ice cream is added to the order list
        CheckBox IceCream = (CheckBox) findViewById(R.id.ice_cream_checkbox);
            boolean hasIceCream = IceCream.isChecked();

        int price = calculatePrice(hasChocolate, hasIceCream);
        String priceMessage = createOrderSummary(name, price, hasChocolate, hasIceCream);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee order for " +name);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
       // displayMessage(priceMessage);

    }

    /**Creates the summary of the order
     *
     * @param price of the order
     * @param addChocolate
     * @return the text summary
     */
    /*private*/public String createOrderSummary(String addName, int price, boolean addChocolate, boolean addIceCream){
        String priceMessage = "Name: " + addName;
        priceMessage +=  "\nAdd Chocolate? " +addChocolate;
        priceMessage +=  "\nAdd Ice cream? " +addIceCream;
        priceMessage +=  "\nQuantity: " +quantity;
        priceMessage += "\nTotal: UGX " +price;
        priceMessage += "\nThank you!";
        return priceMessage;
    }

    /**method to calculate the price
     * @param addChocolate if a customer wants chocolate on top of coffee
     * @param addIceCream if a custoemr wants ice cream
    *@return total price
     */
    public int calculatePrice(boolean addChocolate, boolean addIceCream){

        int pricePerCup = 3000;
        //If chocolate is added at ugx 1500
        if(addChocolate){
            pricePerCup+=1000;
        }
        //If ice cream is added at ugx 3000
        if(addIceCream){
            pricePerCup+=1500;
        }
        int price = quantity * pricePerCup;
        return (price);
    }

    //The method that is called when minus button is clicked
    public void decrement(View view){
        //Quantity should not go below 1
        if(quantity==1){
            Toast.makeText(this, "You cannot order less than 1 cup of coffee",Toast.LENGTH_SHORT).show();
            return;
            //Exit this method early
        }
        quantity = quantity-1;
        displayQuantity(quantity);

            /*The following will be shown when the decrement button is clicked */
            EditText nameField = (EditText) findViewById(R.id.name_field);
            String name = nameField.getText().toString();

            //Checks whether chocolate is added to the coffee
            CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate_checkbox);
            boolean hasChocolate= chocolate.isChecked();
           // Log.v("MainActivity", "Has Pizza:" + hasChocolate);

            //Check whether ice cream is added to the order list
            CheckBox IceCream = (CheckBox) findViewById(R.id.ice_cream_checkbox);
            boolean hasIceCream = IceCream.isChecked();

            int price = calculatePrice(hasChocolate, hasIceCream);
            String priceMessage = createOrderSummary(name, price, hasChocolate, hasIceCream);
            displayMessage(priceMessage);
    }


    //The method that is called when plus button is clicked
    public void increment(View view){
        //When quantity goes beyond 100, toast an error message
        if(quantity==100){
            Toast.makeText(this, "You cannot order more than 100 cups of coffee",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity+1;
        displayQuantity(quantity);

            /*The following will be shown when the decrement button is clicked */
            EditText nameField = (EditText) findViewById(R.id.name_field);
            String name = nameField.getText().toString();

            //Checks whether chocolate is added to the coffee
            CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate_checkbox);
            boolean hasChocolate= chocolate.isChecked();
            Log.v("MainActivity", "Has Pizza:" + hasChocolate);

            //Check whether ice cream is added to the order list
            CheckBox IceCream = (CheckBox) findViewById(R.id.ice_cream_checkbox);
            boolean hasIceCream = IceCream.isChecked();

            int price = calculatePrice(hasChocolate, hasIceCream);
            String priceMessage = createOrderSummary(name, price, hasChocolate, hasIceCream);
            displayMessage(priceMessage);

    }

    public void displayQuantity(int number){
        TextView quantity = (TextView)findViewById(R.id.quantity_text_view);
        quantity.setText("" +number);
    }

    /*
    public void displayPrice(int number){
        TextView price = (TextView)findViewById(R.id.price_text_view);
        price.setText(NumberFormat.getCurrencyInstance().format(number));
    }
    */
    /**The method shows any text on the screen
     *
     * @param message
     */
    public void displayMessage(String message){
        TextView price = (TextView)findViewById(R.id.order_summary_text_view);
        price.setText(message);
    }
}