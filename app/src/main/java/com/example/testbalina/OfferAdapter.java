package com.example.testbalina;

import android.content.Context;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;



public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.ViewHolderOffer> {

    private static final String TAG = "myLogs";
    private Context mContext;

    private ArrayList<Offer> offers = new ArrayList<>();
    private ArrayList<Offers> offersBd = new ArrayList<>();

    private MainActivity context;
    private int status = 1; // 1 - online, 0 - offline



    public OfferAdapter(Shop shop, int position, MainActivity act){ //online
        status = 1;
        this.context = act;
        ArrayList<Offer> allOffers = shop.getOffers();
        for (final Offer f: allOffers) {
            if (position == f.getCategoryId()){
                offers.add(f);
            }
        }
    }

    public OfferAdapter(List<Offers> listOffers, int position, MainActivity act) { //из БД
        status = 0;
        this.context = act;
        for (Offers f: listOffers) {
            if (position == f.categoryId){
                offersBd.add(f);
            }
        }
        //Log.d(TAG, "OfferAdapter status(offline) " + status);

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
            if (status == 1){
                //Log.d(TAG, "status- 1");
                holder.getPost().setText(offers.get(position).getName());
                holder.getWeight().setText(offers.get(position).getParamMap().get("Вес"));
                holder.getPrice().setText(offers.get(position).getPrice().toString());

                Picasso.with(context) //передаем контекст приложения
                        .load(Uri.parse(offers.get(position).getPicture()))
                        .into(holder.getImageView());
            } else {
                //Log.d(TAG, "status- 0");

                holder.getPost().setText(offersBd.get(position).name);
                holder.getWeight().setText(offersBd.get(position).weight);
                holder.getPrice().setText(offersBd.get(position).price.toString());

                Picasso.with(context) //передаем контекст приложения
                        .load(Uri.parse(offersBd.get(position).picture))
                        .into(holder.getImageView());
            }
        }

    }

    @Override
    public int getItemCount() {
        if (status == 1) {
            if (offers == null) {
                return 0;
            } else return offers.size();
        } else {
            if (offersBd == null) {
                return 0;
            } else return offersBd.size();
        }
    }


    class ViewHolderOffer extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView post;
        private TextView price;
        private TextView weight;
        private ImageView imageView;

        TextView getPost() {
            return post;
        }
        ImageView getImageView() {
            return imageView;
        }
        TextView getWeight() {
            return weight;
        }
        TextView getPrice() {
            return price;
        }

        ViewHolderOffer(Context context, View itemView) {
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
            dialog.setOffers(offers, offersBd, getAdapterPosition(), context, status);
            dialog.show(context.getFragmentManager(), "dialog");

        }


    }
}
