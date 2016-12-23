package com.example.testbalina;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.ViewHolderOffer> {

    private static final String TAG = "myLogs";
    private Context mContext;
    private int mPosition;

    private ArrayList<Offer> offers = new ArrayList<>();

    ArrayList<Offer> allOffers;
    private MainActivity context;



    public OfferAdapter(Shop shop, int position, MainActivity context){
        this.context = context;
        mPosition = position;
        allOffers = shop.getOffers();
        for (final Offer f: allOffers) {
            if (mPosition == f.getCategoryId()){
                offers.add(f);
            }
        }
    }

    @Override
    public ViewHolderOffer onCreateViewHolder(ViewGroup parent, int viewType) {
        //Log.d(TAG, "onCreateViewHolder(Offer)... ");
        return new ViewHolderOffer(mContext, LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment2, parent, false));
    }


    @Override
    public void onBindViewHolder(ViewHolderOffer holder, int position) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

        } else {
            holder.getPost().setText(offers.get(position).getName());
            holder.getWeight().setText(offers.get(position).getParamMap().get("Вес"));
            holder.getPrice().setText(offers.get(position).getPrice().toString());

            Picasso.with(context) //передаем контекст приложения
                    .load(Uri.parse(offers.get(position).getPicture()))
                    .into(holder.getImageView());
        }

    }

    @Override
    public int getItemCount() {
              if (offers == null){
            return 0;
        } else return offers.size();
    }


    class ViewHolderOffer extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView post;
        private TextView price;
        private TextView weight;
        private ImageView imageView;

        public TextView getPost() {
            return post;
        }
        public ImageView getImageView() {
            return imageView;
        }
        public TextView getWeight() {
            return weight;
        }
        public TextView getPrice() {
            return price;
        }

        public ViewHolderOffer(Context context, View itemView) {
            super(itemView);
            price = (TextView) itemView.findViewById(R.id.price_offer);
            post = (TextView) itemView.findViewById(R.id.name_offer);
            weight = (TextView) itemView.findViewById(R.id.weight_offer);
            imageView = (ImageView) itemView.findViewById(R.id.ic_offer);
            mContext = context;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            Dialog dialog = new Dialog();
            //dialog.cont.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setOffers(offers, getAdapterPosition(), context);
            dialog.show(context.getFragmentManager(), "dialog");

        }


    }
}
