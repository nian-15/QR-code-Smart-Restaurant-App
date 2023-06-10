package com.example.smartrestaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartrestaurant.databinding.ActivityFinalItemsBinding;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

public class FinalItems extends AppCompatActivity implements PaymentResultListener {
    ActivityFinalItemsBinding binding;
    // variables for our
    // edit text and button.
    private Button payBtn;
    EditText mName, mTable;
    TextView try1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityFinalItemsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mName = findViewById(R.id.name);
        mTable = findViewById(R.id.table);
        try1=findViewById(R.id.try1);
        Intent intent = this.getIntent();


        if (intent != null) {
            String name = intent.getStringExtra("name");
            String price = intent.getStringExtra("price");
            int imageId = intent.getIntExtra("imageId", R.drawable.biryani);

            binding.textView.setText(name);
            binding.textView2.setText(price);
            binding.imageView.setImageResource(imageId);


            // initializing all our variables
            payBtn = findViewById(R.id.button);

            // adding on click listener to our button.
            payBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // on below line we are getting
                    // amount that is entered by user.
                    String samount = binding.textView2.getText().toString();
                    int amount=13000;
                    // rounding off the amount.
                    try {
                         amount = Integer.parseInt(samount) * 100;
                    }catch(NumberFormatException ex){
                        ex.printStackTrace();// handle your exception
                         }
                    // initialize Razorpay account.
                    final String name = mName.getText().toString().trim();
                    final String table = mTable.getText().toString();

                    if (TextUtils.isEmpty(name)) {
                        mName.setError("Name is Required");
                        return;
                    }
                    if (TextUtils.isEmpty(table)) {
                        mTable.setError("Table No is Required");
                        return;
                    }
                    Checkout checkout = new Checkout();

                    // set your id as below
                    checkout.setKeyID("rzp_test_I7pG9CRtjuAqfN");

                    // set image
                    checkout.setImage(R.drawable.rzp_logo);

                    // initialize json object
                    JSONObject object = new JSONObject();
                    try {
                        // to put name
                        object.put("name", "Digital Menu");

                        // put description
                        object.put("description", "Test payment");

                        // to set theme color
                        object.put("theme.color", "");

                        // put the currency
                        object.put("currency", "INR");

                        // put amount
                        object.put("amount", amount);
                        // put mobile number
                        object.put("prefill.contact", "8980823738");

                        // put email
                        object.put("prefill.email", "jabirrayma@gmail.com");

                        // open razorpay to checkout activity
                        checkout.open(FinalItems.this, object);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
        @Override
        public void onPaymentSuccess (String s){
            // this method is called on payment success.
            Toast.makeText(this, "Payment is successful : " + s, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onPaymentError ( int i, String s){
            // on payment failed.
            Toast.makeText(this, "Payment Failed due to error : " + s, Toast.LENGTH_LONG).show();
        }

}
