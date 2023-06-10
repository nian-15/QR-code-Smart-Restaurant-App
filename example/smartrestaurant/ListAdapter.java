package com.example.smartrestaurant;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<Items> {
    private int layout;
    public ListAdapter(Context context, int res, ArrayList<Items> itemsArrayList){
        super(context,R.layout.list_item,itemsArrayList);
        layout=res;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);


        Items items = getItem(position);

        ImageView imageView = convertView.findViewById(R.id.itemImage);
        TextView itemName = convertView.findViewById(R.id.itemName);
        TextView itemPrice= convertView.findViewById(R.id.itemPrice);

        imageView.setImageResource(items.imageId);
        itemName.setText(items.itemName);
        itemPrice.setText(items.itemPrice);


        }
        return convertView;
    }
}
