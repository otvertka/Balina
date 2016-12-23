package com.example.testbalina;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class OfferCatalog extends Fragment {

    private static final String TAG = "myLogs";

    private Shop shop;
    private RecyclerView mRecyclerView;
    private int mPosition;

    public void setShopObject(Shop shop, int position){
        this.shop = shop;
        mPosition = position;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.content_main, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.posts_recycle_view);
        return rootView;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MainActivity act = (MainActivity ) getActivity();

        OfferAdapter mOfferAdapter = new OfferAdapter(shop, mPosition, act);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this.getActivity());
        mRecyclerView.setAdapter(mOfferAdapter);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

    }
}
