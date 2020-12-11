package com.example.personalrestaurantguide.ui.show;

import androidx.lifecycle.ViewModelProviders;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.personalrestaurantguide.R;
import com.example.personalrestaurantguide.helper.RestaurantController;
import com.example.personalrestaurantguide.helper.RestaurantListAdapter;
import com.example.personalrestaurantguide.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class ShowFragment extends Fragment {

    //private ShowViewModel mViewModel;

    private List<Restaurant> restaurantList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RestaurantListAdapter adapter;


    public static ShowFragment newInstance() {
        return new ShowFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.show_fragment, container, false);
        recyclerView = root.findViewById(R.id.recycler_view);
        getRestaurantList();
        adapter = new RestaurantListAdapter(restaurantList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        return root;
    }

    private void getRestaurantList() {
        RestaurantController rc=new RestaurantController(getContext());
        restaurantList=rc.getAllRestaurant();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //mViewModel = ViewModelProviders.of(this).get(ShowViewModel.class);
        // TODO: Use the ViewModel
    }

}