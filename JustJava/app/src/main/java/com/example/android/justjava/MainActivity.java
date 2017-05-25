/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 */

package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display(quantity);
        displayPrice(calculatePrice());
    }

    int quantity = 1;
    int cost=5;
    boolean cstate()
    {
        CheckBox cbox=(CheckBox) findViewById(R.id.check);
        if(cbox.isChecked())
        {
            return true;
        }

        return false;
    }

    public String getName()
    {
        EditText etext=(EditText) findViewById(R.id.name);
        String tbp=etext.getText().toString();
        return tbp;
    }
    String chocolate()
    {
        CheckBox cbox=(CheckBox) findViewById(R.id.checkchoc);
        if(cbox.isChecked())
        {
            return "\n+ Chocolate Toppings";
        }
        return "";
    }
    //This method is called when the order button is clicked.
    public void submitOrder(View view) {
        //int price=calculatePrice();
        //String mess="\nTotal = $" + price + "\nThankyou!";
        //displayMessage(mess);


        //Intent for opening Maps
        /*Intent intent= new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:47.6,-122.3"));
        if(intent.resolveActivity(getPackageManager())!=null)
        {
            startActivity(intent);
        }*/

        //intent for emailing the order
        String body=createOrderSummary();
        Intent intent=new Intent(Intent.ACTION_SENDTO);
        intent.setType("*/*");
        intent.setData(Uri.parse("mailto:"));//to send to an email app
        intent.putExtra(intent.EXTRA_SUBJECT,"Order Summary");
        intent.putExtra(Intent.EXTRA_TEXT,body);
        if(intent.resolveActivity(getPackageManager())!=null)
        {
            startActivity(intent);
        }
        //displayMessage(body);
    }

    //creates a summary of the order.
    public String createOrderSummary()
    {
        int price = calculatePrice();
        if(cstate())
        return "Name : "+getName()+"\nQuantity : "+quantity+"\n + Whipped Cream"+chocolate()+"\nTotal = $" + price + "\nThankyou!";
        else
            return "Name : "+getName()+"\nQuantity : "+quantity+chocolate()+"\nTotal = $" + price + "\nThankyou!";
    }



     //This method displays the given quantity value on the screen.
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    //This method displays the price.
    private void displayPrice(int number) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(NumberFormat.getCurrencyInstance(Locale.US).format(number));
    }

    //This method displays a message.
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    //This method is called when + button is clicked.
    public void plus(View view)
    {
        if(quantity==100)
        {
            Toast.makeText(this, "Order Limit Exceeded", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity++;
        display(quantity);
        displayPrice(calculatePrice());
    }

    //This method is called when - button is clicked.
    public void minus(View view)
    {
        if(quantity==1)
        {
            Toast.makeText(this, "You cannot have less than 1 Cup .", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity--;
        display(quantity);
        displayPrice(calculatePrice());
    }

    ///computes the price using cost and quantity
    int pricePerCup=cost;
    public int calculatePrice()
    {
        pricePerCup=cost;
        CheckBox choco=(CheckBox) findViewById(R.id.check);
        if(choco.isChecked())
            pricePerCup+=1;
        choco=(CheckBox) findViewById(R.id.checkchoc);
        if(choco.isChecked())
            pricePerCup+=2;
        return quantity*pricePerCup;
    }

    public void wDisplay(View view)
    {
        displayPrice(calculatePrice());
    }
    public void cDisplay(View view)
    {
        displayPrice(calculatePrice());
    }
}