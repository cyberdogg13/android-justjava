package com.example.justjava2;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int numberofcoffee;
    String message = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitOrder(View view) {
        message = " Thank you!";
        displayPrice(numberofcoffee * 5);

    }

    private void display(int number) {
        TextView quantityTextview = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextview.setText(" " + number);
    }

    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number) + message);
    }

    public void addCoffee(View view) {
        numberofcoffee++;
        display(numberofcoffee);
        displayPrice(numberofcoffee *5);
    }
    public void subtractCoffee(View view) {
        numberofcoffee--;
        display(numberofcoffee);
        displayPrice(numberofcoffee *5);
    }
    private void diplaymessage(String message){
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);

    }
}
