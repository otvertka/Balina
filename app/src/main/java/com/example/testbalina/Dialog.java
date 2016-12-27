package com.example.testbalina;

import android.app.DialogFragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class Dialog extends DialogFragment implements View.OnClickListener {

    private MainActivity context;

    private ArrayList<Offer> offers = new ArrayList<>();
    private ArrayList<Offers> offersBd = new ArrayList<>();
    private int position;
    private int status = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.dialog_fragment, null);

        TextView name = (TextView) v.findViewById(R.id.name_offer_dialog);
        TextView calorie = (TextView) v.findViewById(R.id.calorie_offer_dialog);
        TextView carbohydrates = (TextView) v.findViewById(R.id.carbohydrates_offer_dialog);
        TextView description = (TextView) v.findViewById(R.id.description_offer_dialog);
        TextView weight = (TextView) v.findViewById(R.id.weight_offer_dialog);
        TextView price = (TextView) v.findViewById(R.id.price_offer_dialog);
        TextView fats = (TextView) v.findViewById(R.id.fats_offer_dialog);
        TextView proteins = (TextView) v.findViewById(R.id.proteins_offer_dialog);
        ImageView imageView = (ImageView) v.findViewById(R.id.image_offer_dialog);

        v.findViewById(R.id.button_dialog).setOnClickListener(this);

        if (status == 1) {
            Picasso.with(context) //передаем контекст приложения
                    .load(Uri.parse(offers.get(position).getPicture()))
                    .into(imageView);
            name.setText(offers.get(position).getName());
            price.setText(offers.get(position).getPrice().toString());
            description.setText(offers.get(position).getDescription());
            weight.setText(offers.get(position).getParamMap().get("Вес"));
            calorie.setText(offers.get(position).getParamMap().get("Каллорийность"));
            fats.setText(offers.get(position).getParamMap().get("Жиры"));
            carbohydrates.setText(offers.get(position).getParamMap().get("Углеводы"));
            proteins.setText(offers.get(position).getParamMap().get("Белки"));
        } else {
            Picasso.with(context) //передаем контекст приложения
                    .load(Uri.parse(offersBd.get(position).picture))
                    .into(imageView);
            name.setText(offersBd.get(position).name);
            price.setText(offersBd.get(position).price.toString());
            description.setText(offersBd.get(position).description);
            weight.setText(offersBd.get(position).weight);
            calorie.setText(offersBd.get(position).calorie);
            fats.setText(offersBd.get(position).fats);
            carbohydrates.setText(offersBd.get(position).carbohydrates);
            proteins.setText(offersBd.get(position).proteins);
        }

        return v;
    }

    public void setOffers(ArrayList<Offer> offers, ArrayList<Offers> offersBd, int position, MainActivity context, int status){
        this.offersBd = offersBd;
        this.context = context;
        this.offers = offers;
        this.position = position;
        this.status = status;
    }

    @Override
    public void onClick(View view) {
        dismiss();
    }
}
