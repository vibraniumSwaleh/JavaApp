package com.example.android.justjava;

import android.content.Context;
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

public class MainActivity extends AppCompatActivity {
    int quantity = 1;
    int price = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayQuantity(quantity);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream);
        boolean whippedCreamValue = whippedCreamCheckBox.isChecked();

        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate);
        boolean chocolateValue = chocolateCheckBox.isChecked();

        EditText textName = (EditText) findViewById(R.id.edit_text);
        String customerName = textName.getText().toString();

        price = calculatePrice(whippedCreamValue, chocolateValue);
        String message = createOrderSummary(customerName, whippedCreamValue, chocolateValue, quantity, price);
        //displayMessage(message);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:hus.swaleh@yahoo.com"));
        intent.putExtra(intent.EXTRA_SUBJECT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * Calculates the price of the order.
     *@param whippedCreamValue is if the whipped cream is selected
     *@param chocolateValue is if the chocolate is selected
     * @return quantity is the number of cups of coffee ordered
     */
    private int calculatePrice(boolean whippedCreamValue, boolean chocolateValue) {
        int basePrice = 5;

        if (whippedCreamValue){
            basePrice = basePrice + 1;
        }

        if (chocolateValue){
            basePrice = basePrice + 2;
        }

        return quantity * basePrice;
    }

    /**
     * This method is called when the + button is clicked.
     */
    public void increment(View view) {
        if (quantity >= 5){
            //Show an error message as toast
            Toast.makeText(this, "Order can't be more than 5!", Toast.LENGTH_LONG).show();
            //exit this method early because there is noting else to do
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the - button is clicked.
     */
    public void decrement(View view) {
        if (quantity <= 1){
            //Show an error message as toast
            Toast.makeText(this, "Order can't be less than 1!", Toast.LENGTH_LONG).show();
            //exit this method early because there is noting else to do
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

//    /**
//     * This method displays the given text on the screen.
//     */
//    private void displayMessage(String message) {
//        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
//        orderSummaryTextView.setText(message);
//    }

    /**
     * This method creates order summary for the user.
     *
     * @return order summary as per the order input by user
     */
    private String createOrderSummary(String customerName, boolean whippedCreamValue, boolean chocolateValue, int quantityValue, int priceValue) {
        return "Name: " + customerName
                + "\nAdd whipped cream? "
                + whippedCreamValue
                + "\nAdd chocolate? "
                + chocolateValue
                + "\nQuantity "
                + quantityValue + "\nTotal: $ "
                + priceValue + getString(R.string.thank_you) ;
    }
//<TextView
//    android:id="@+id/order_summary_text_view"
//    android:layout_width="wrap_content"
//    android:layout_height="wrap_content"
//    android:layout_margin="10dp"
//    android:text="$0"
//    android:textColor="#FFFF"
//    android:textSize="16sp" />

//    <TextView
//    android:layout_width="wrap_content"
//    android:layout_height="wrap_content"
//    android:layout_marginLeft="10dp"
//    android:text="Order Summary"
//    android:textAllCaps="true"
//    android:textColor="#FFFFFF"
//    android:textSize="16sp" />
}
