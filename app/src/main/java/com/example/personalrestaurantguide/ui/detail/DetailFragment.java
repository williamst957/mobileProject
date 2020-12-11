package com.example.personalrestaurantguide.ui.detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.personalrestaurantguide.R;
import com.example.personalrestaurantguide.helper.RestaurantController;
import com.example.personalrestaurantguide.model.Restaurant;
import com.example.personalrestaurantguide.ui.manage.ManageFragment;
import com.google.android.material.textfield.TextInputEditText;

public class DetailFragment extends Fragment {

    //private DetailViewModel mViewModel;
    TextView edName,edAddress,edDescription,edCity,edState,edZip,edCountry,edPhone,edTag;
    AppCompatButton btnRate,btnUpadte,btnDelete,btnLocate;
    RatingBar ratingBar;
    public static DetailFragment newInstance() {
        return new DetailFragment();
    }
    SharedPreferences sharedPreferences;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.detail_fragment, container, false);
        sharedPreferences=root.getContext().getSharedPreferences("prgSP", Context.MODE_PRIVATE);
        int id=sharedPreferences.getInt("currentRestId",0);
        if(id!=0) {
            btnRate=root.findViewById(R.id.appCompatButtonRate);
            btnUpadte=root.findViewById(R.id.appCompatButtonUpdate);
            btnDelete=root.findViewById(R.id.appCompatButtonDelete);
            btnLocate=root.findViewById(R.id.appCompatButtonMap);
            edName=root.findViewById(R.id.txtRestaurantName);
            edAddress=root.findViewById(R.id.txtRestaurantAddress);
            edDescription=root.findViewById(R.id.txtRestaurantDescription);
            edCity=root.findViewById(R.id.txtRestaurantCity);
            edState=root.findViewById(R.id.txtRestaurantState);
            edZip=root.findViewById(R.id.txtRestaurantZip);
            edCountry=root.findViewById(R.id.txtRestaurantCountry);
            edPhone=root.findViewById(R.id.txtRestaurantPhone);
            edTag=root.findViewById(R.id.txtRestaurantTag);
            ratingBar=root.findViewById(R.id.restaurantRating);
            final RestaurantController restaurantController=new RestaurantController(root.getContext());

            final Restaurant restaurant=restaurantController.getRestaurantById(id);

            edName.setText(restaurant.getName());
            edAddress.setText(restaurant.getAddress());
            edDescription.setText(restaurant.getDescription());
            edCity.setText(restaurant.getCity());
            edState.setText(restaurant.getState());
            edZip.setText(restaurant.getZip());
            edCountry.setText(restaurant.getCountry());
            edPhone.setText(restaurant.getPhone());
            edTag.setText(restaurant.getTag());
            ratingBar.setRating(restaurant.getRate());

            ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                    restaurant.setRate(ratingBar.getRating());
                    restaurantController.manageRestaurant(restaurant);
                }
            });

            btnUpadte.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    ManageFragment df = new ManageFragment();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, df).addToBackStack(null).commit();
                }
            });

            return root;
        }
        else
            return null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //mViewModel = ViewModelProviders.of(this).get(DetailViewModel.class);
        // TODO: Use the ViewModel
    }

}