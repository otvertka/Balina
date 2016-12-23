package com.example.testbalina;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    public static final String TAG = "myLogs";
    private Context mContext;
    private MainActivity context;

    private Shop mShop;
    private Map<Integer, String> map;

    private List keyList;
    Category categoryMap;


    public PostsAdapter(Shop shop, MainActivity context)  {

        this.context = context;
        mShop = shop;
        categoryMap = mShop.getCategoryMap();
        map = categoryMap.getMap();

        keyList = new ArrayList(map.keySet());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Log.d(TAG, "onCreateViewHolder...");
        return new ViewHolder(mContext, LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment1, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //Log.d(TAG, "onBindViewHolder... ");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.post.setText(Html.fromHtml(mShop.getCategoryMap().getMap().get(0), Html.FROM_HTML_MODE_LEGACY));
        } else {
            holder.getPost().setText(map.get(keyList.get(position)));
            holder.getImageView().setImageResource(R.mipmap.ic_launcher);
        }
    }

    @Override
    public int getItemCount() {
        if (map == null) {
            return 0;
        } else return map.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private static final String TAG = "myLogs";
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
            int categoryId = 0;
            for (Map.Entry<Integer, String> set: map.entrySet()
                 ) {
                if (map.get(keyList.get(getLayoutPosition())) == set.getValue()){
                    categoryId = set.getKey();
                    break;
                }
            }
            context.fragmentMethod(categoryId);
        }
    }
}