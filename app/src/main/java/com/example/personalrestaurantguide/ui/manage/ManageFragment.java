package com.example.personalrestaurantguide.ui.manage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.personalrestaurantguide.R;
import com.example.personalrestaurantguide.helper.RestaurantController;
import com.example.personalrestaurantguide.model.Restaurant;
import com.example.personalrestaurantguide.ui.home.HomeFragment;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class ManageFragment extends Fragment {
   // String [] tags=new String[]{"vegetarian", "vegan", "organic", "Italian", "Thai"};
    public static ManageFragment newInstance() {
        return new ManageFragment();
    }
    Restaurant restaurant=new Restaurant();
    AppCompatButton btnDone;
    SharedPreferences sharedPreferences;
    TextInputEditText edName,edAddress,edDescription,edCity,edState,edZip,edCountry,edPhone,edTag;
    AppCompatSpinner sp;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.manage_fragment, container, false);
        btnDone=root.findViewById(R.id.appCompatButtonDone);
        edName=root.findViewById(R.id.textInputEditTextRestaurantName);
        edAddress=root.findViewById(R.id.textInputEditTextRestaurantAddress);
        edDescription=root.findViewById(R.id.textInputEditTextRestaurantDescription);
        edCity=root.findViewById(R.id.textInputEditTextRestaurantCity);
        edState=root.findViewById(R.id.textInputEditTextRestaurantState);
        edZip=root.findViewById(R.id.textInputEditTextRestaurantZip);
        edZip.setInputType(InputType.TYPE_CLASS_NUMBER);
        edCountry=root.findViewById(R.id.textInputEditTextRestaurantCountry);
        edPhone=root.findViewById(R.id.textInputEditTextRestaurantPhone);
        edPhone.setInputType(InputType.TYPE_CLASS_PHONE);
        edTag=root.findViewById(R.id.textInputEditTextRestaurantTag);
        sharedPreferences=root.getContext().getSharedPreferences("prgSP", Context.MODE_PRIVATE);
        final int id=sharedPreferences.getInt("currentRestId",0);
        if(id!=0) {
            restaurant=new RestaurantController(getContext()).getRestaurantById(id);
            edName.setText(restaurant.getName());
            edAddress.setText(restaurant.getAddress());
            edDescription.setText(restaurant.getDescription());
            edCity.setText(restaurant.getCity());
            edState.setText(restaurant.getState());
            edZip.setText(restaurant.getZip());
            edCountry.setText(restaurant.getCountry());
            edPhone.setText(restaurant.getPhone());
            edTag.setText(restaurant.getTag());
        }

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restaurant.setRestid(id);
                restaurant.setName(edName.getText().toString().trim());
                restaurant.setAddress(edAddress.getText().toString().trim());
                restaurant.setDescription(edDescription.getText().toString().trim());
                restaurant.setCity(edCity.getText().toString().trim());
                restaurant.setState(edState.getText().toString().trim());
                restaurant.setZip(edZip.getText().toString().trim());
                restaurant.setCountry(edCountry.getText().toString().trim());
                restaurant.setPhone(edPhone.getText().toString().trim());
                restaurant.setTag(edTag.getText().toString().trim());
                restaurant.setRate(restaurant.getRate()<0.0?(float)0.0:restaurant.getRate());
                restaurant.setActive(1);
                if(validateData(restaurant))
                {
                    Toast.makeText(getContext(),"Successfully done.",Toast.LENGTH_LONG).show();
                    emptyAllFiled();
                }
                else {
                    Toast.makeText(getContext(),"Please try again.",Toast.LENGTH_LONG).show();
                }
            }
        });
        return root;
    }

    private void emptyAllFiled() {
        edName.setText("");
        edAddress.setText("");
        edDescription.setText("");
        edCity.setText("");
        edState.setText("");
        edZip.setText("");
        edCountry.setText("");
        edPhone.setText("");
        edTag.setText("");
        FragmentManager fm = getActivity().getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.nav_host_fragment, new HomeFragment()).addToBackStack(null).commit();
    }

    private boolean validateData(Restaurant restaurant) {
        RestaurantController controller =new RestaurantController(getContext());
        return controller.manageRestaurant(restaurant);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}