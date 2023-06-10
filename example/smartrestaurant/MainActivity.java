package com.example.smartrestaurant;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartrestaurant.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();

        int[] imageId = {R.drawable.biryani, R.drawable.chicken, R.drawable.pizza, R.drawable.kabab, R.drawable.burger, R.drawable.fries, R.drawable.pasta,};
        String[] name = {"Biryani", "Chicken", "Pizza", "Kabab", "Burger", "Fries", "Pasta"};
        String[] price = {"₹130", "₹150", "₹200", "₹50", "₹75", "₹80", "₹120"};

        ArrayList<Items> itemsArrayList = new ArrayList<>();

        for (int i = 0; i < imageId.length; i++) {
            Items item = new Items(name[i], price[i], imageId[i]);
            itemsArrayList.add(item);
        }

        ListAdapter listAdapter = new ListAdapter(MainActivity.this, R.layout.list_item, itemsArrayList);

        binding.listView.setAdapter(listAdapter);
        binding.listView.setClickable(false);
        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent i = new Intent(MainActivity.this, FinalItems.class);
                i.putExtra("name", name[position]);
                i.putExtra("price", price[position]);
                i.putExtra("imageId", imageId[position]);
                startActivity(i);
            }
        });
    }
}
