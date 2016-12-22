package com.example.testbalina;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Callback;


public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private static final String TAG = "myLogs";
    private Context mContext;
    public Yml_catalog mCatalog;


    Shop mShop;
    Category categoryMap;
    Map<Integer, String> map;

    private Callback<Yml_catalog> mCallback;
    private List keyList;

    ListCatalog listCatalog;
    FragmentTransaction fTrans;


    public PostsAdapter(/*Callback<Yml_catalog> callback,*/ Shop shop)  {
        //mCallback = callback;
        //mCatalog = catalog;

        //mShop = mCatalog.getShop();
        mShop = shop;
        categoryMap = mShop.getCategoryMap();
        map = categoryMap.getMap();

        keyList = new ArrayList(map.keySet());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder...");

        return new ViewHolder(mContext, LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment1, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder... ");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.post.setText(Html.fromHtml(mShop.getCategoryMap().getMap().get(0), Html.FROM_HTML_MODE_LEGACY));
        } else {
            holder.post.setText(map.get(keyList.get(position)));
            holder.imageView.setImageResource(R.mipmap.ic_launcher);
        }
    }

    @Override
    public int getItemCount() {
        if (map == null) {
            //Log.d(TAG, "getItemCount: " + map.size());
            return 0;
        } else return map.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView post;
        private ImageView imageView;

        public TextView getPost() {
            return post;
        }
        public ImageView getImageView() {
            return imageView;
        }

        public ViewHolder(Context context, View itemView) {
            super(itemView);
            post = (TextView) itemView.findViewById(R.id.userFirstNameProfile);
            imageView = (ImageView) itemView.findViewById(R.id.ic_launcher);
            mContext = context;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //int position = getLayoutPosition();
            Log.d(TAG, "onClick: " + getLayoutPosition());
            //listCatalog = new ListCatalog();
            /*fTrans = getFragmentManager().beginTransaction();
            fTrans.add(R.id.frgmCont, listCatalog);
            fTrans.commit();*/
        }


    }
}