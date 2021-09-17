package com.example.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.justjava.R;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int costOfCoffee = 5;
    int numberofcoffee = 1;
    int chocolateCost = 2;
    int creamCost = 1;
    boolean whippedCream = false;
    boolean chocolate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayquantity(numberofcoffee);
        displayPrice(calculatePrice(numberofcoffee));
    }

    public void addWhippedcream (View view){
        CheckBox creambox = (CheckBox) view;
        if (creambox.isChecked()){
            whippedCream =true;
            costOfCoffee += creamCost;
        }
        else {
            whippedCream =false;
            costOfCoffee -= creamCost;
        }
        displayPrice(calculatePrice(numberofcoffee));
    }

    public void addChocolate (View view){
        CheckBox creambox = (CheckBox) view;
        if (creambox.isChecked()){
            chocolate =true;
            costOfCoffee += chocolateCost;
        }
        else {
            chocolate =false;
            costOfCoffee -= chocolateCost;
        }
        displayPrice(calculatePrice(numberofcoffee));
    }

    public void submitOrder(View view) {
        displayPrice(calculatePrice(numberofcoffee));
        createOrderSumary(calculatePrice(numberofcoffee));

    }

    private void displayquantity(int orderedCoffee) {
        TextView quantityTextview = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextview.setText(" " + orderedCoffee);
    }

    private int calculatePrice(int quantity){
        int price = quantity * costOfCoffee;
        return price;
    }

    private void displayPrice(int price) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(price));
    }

    public void addCoffee(View view) {
        if (numberofcoffee < 100){
            numberofcoffee++;
            displayquantity(numberofcoffee);
            displayPrice(calculatePrice(numberofcoffee));
        }
    }
    public void subtractCoffee(View view) {
        if (numberofcoffee > 1){
            numberofcoffee--;
            displayquantity(numberofcoffee);
            displayPrice(calculatePrice(numberofcoffee));
        }
    }
    private void createOrderSumary (int orderPrice){
        EditText nameInput = (EditText) findViewById(R.id.naam);
        String ordertext = "Order sumary" + "\n ";
        ordertext +=
                "\n" + getString(R.string.name)+ " = " + nameInput.getText() +
                "\n" + getString(R.string.quantity)+ " = " + numberofcoffee +
                "\n" + getString(R.string.whipped_cream) + " = " + whippedCream +
                "\n" + getString(R.string.Chocolate) + " = " + chocolate +
                "\n" + getString(R.string.total)  + " = " + NumberFormat.getCurrencyInstance().format(orderPrice) +
                "\n" + getString(R.string.thank_you);
//        TextView priceTextView = (TextView) findViewById(R.id.fullOrder);
//        priceTextView.setText(ordertext);
        composeEmail("coffeeOrder", ordertext);

    }

    public void composeEmail(String subject, String fullOrder) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, fullOrder);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}
