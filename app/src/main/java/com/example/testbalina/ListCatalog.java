package com.example.testbalina;

import android.app.Fragment;
import android.os.Bundle;
import android.app.ListFragment;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.os.Build.VERSION_CODES.M;


public class ListCatalog extends Fragment {

    private static final String TAG = "myLogs";

    public Yml_catalog catalog;

    private Shop shop;

    private List keyList;
    Category categoryMap;
    Map<Integer, String> map;
    //public RecyclerView recyclerView;

    private RecyclerView mRecyclerView;


    //    Shop mShop = m.getShop();

    //private List<ViewModel> viewModel;

    public void setShopObject(Shop shop){
        Log.d(TAG, "setShopObject ListCatalog..1. " + this.shop);
        this.shop = shop;
        Log.d(TAG, "setShopObject ListCatalog..2. " + this.shop);
    }


    /*@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate ListCatalog...");

        App.getInf().getData("ukAXxeJYZN").enqueue(new Callback<Yml_catalog>() {
            @Override
            public void onResponse(Call<Yml_catalog> call, Response<Yml_catalog> response) {
                if(response.isSuccessful()) {

                    catalog = response.body();
                    shop = catalog.getShop();
                    Log.d(TAG, "onResponse... " + shop.getCategoryMap().getMap().get(0));


                    *//*mPostAdapter = new PostsAdapter(shop);
                    mLinearLayoutManager = new LinearLayoutManager(getActivity());
                    Log.d(TAG, "onResponse1... " + mPostAdapter);
                    mRecyclerView.setAdapter(mPostAdapter);
                    mRecyclerView.setLayoutManager(mLinearLayoutManager);
                    mRecyclerView.setItemAnimator(new DefaultItemAnimator());


                    PostsAdapter adapter = new PostsAdapter(shop);
                    recyclerView.setAdapter(adapter);

                    catalog = response.body();
                    shop = catalog.getShop();
                    categoryMap = mShop.getCategoryMap();
                    map = categoryMap.getMap();

                    keyList = new ArrayList(map.keySet());*//*

                } else {
                    int statusCode = response.code();
                }
            }

            @Override
            public void onFailure(Call<Yml_catalog> call, Throwable t) {
                Log.d(TAG, "onFailure... ");

                Toast.makeText(getActivity(), "An error occurred during networking" , Toast.LENGTH_SHORT).show();
            }
        });
    }*/




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView ListCatalog...");
        View rootView = inflater.inflate(R.layout.content_main, container, false);
        Log.d(TAG, "mRecyclerView..1. " + mRecyclerView);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.posts_recycle_view);
        Log.d(TAG, "mRecyclerView..2. " + mRecyclerView);

        return rootView;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated ListCatalog...");

        PostsAdapter mPostAdapter = new PostsAdapter(shop);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this.getActivity());
        mRecyclerView.setAdapter(mPostAdapter);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());


    }




}
