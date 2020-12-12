package com.example.personalrestaurantguide.helper;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personalrestaurantguide.R;
import com.example.personalrestaurantguide.model.Restaurant;
import com.example.personalrestaurantguide.ui.detail.DetailFragment;

import java.util.List;

public class RestaurantListAdapter extends RecyclerView.Adapter<RestaurantListAdapter.MyViewHolder> {

    private List<Restaurant> restaurantList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView rName, rTag, rDescription;
        public AppCompatButton btnView,btnShare;

        public MyViewHolder(View view) {
            super(view);
            rName = (TextView) view.findViewById(R.id.itemName);
            rTag = (TextView) view.findViewById(R.id.itemTag);
            rDescription = (TextView) view.findViewById(R.id.itemDescription);
            btnShare=(AppCompatButton)view.findViewById(R.id.appCompatButtonShare);
            btnView=(AppCompatButton)view.findViewById(R.id.appCompatButtonView);
        }
    }

    public RestaurantListAdapter(List<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
    }

    @NonNull
    @Override
    public RestaurantListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final RestaurantListAdapter.MyViewHolder holder, final int position) {
        final Restaurant restaurant = restaurantList.get(position);
        holder.rName.setText(restaurant.getName());
        holder.rTag.setText(restaurant.getTag());
        holder.rDescription.setText(restaurant.getDescription());
        holder.btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              showDetail(restaurant,view);
            }
        });
        holder.btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareDetail(restaurant,view);
            }
        });
    }

    private void showDetail(Restaurant restaurant,View v) {
        SharedPreferences sharedpreferences;
        sharedpreferences = v.getContext().getSharedPreferences("prgSP", Context.MODE_PRIVATE);
        SharedPreferences.Editor ed=sharedpreferences.edit();
        ed.putInt("currentRestId",restaurant.getRestid());
        ed.commit();
        AppCompatActivity activity = (AppCompatActivity) v.getContext();
        DetailFragment df = new DetailFragment();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, df).addToBackStack(null).commit();
    }

    private void shareDetail(Restaurant restaurant,View v) {
        final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        emailIntent.setType("text/plain");
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String []{ "serveroverloadofficial@gmail.com"});
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Restaurant review");
        String mail="Please vist below restaurant\n";
        String name=restaurant.getName();
        String address=restaurant.getAddress()+","+restaurant.getCity()+","+restaurant.getState()+","+restaurant.getCountry();

        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, mail);
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, name+"\n");
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, address);


        emailIntent.setType("message/rfc822");
        AppCompatActivity activity = (AppCompatActivity) v.getContext();
        try {

            activity.startActivity(Intent.createChooser(emailIntent,
                    "Send email using..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(v.getContext(),
                    "No email clients installed.",
                    Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public int getItemCount() {
        return restaurantList.size();
    }
}

