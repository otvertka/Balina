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
    private int position;

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

        Picasso.with(context) //передаем контекст приложения
                .load(Uri.parse(offers.get(position).getPicture()))
                .into(imageView);
        name.setText(offers.get(position).getName());
        price.setText(offers.get(position).getPrice().toString());
        description.setText(offers.get(position).getDescription());
        weight.setText(offers.get(position).getParamMap().get("Вес"));
        calorie.setText(offers.get(position).getParamMap().get("Калорийность"));
        fats.setText(offers.get(position).getParamMap().get("Жиры"));
        carbohydrates.setText(offers.get(position).getParamMap().get("Углеводы"));
        proteins.setText(offers.get(position).getParamMap().get("Белки"));

        return v;
    }

    public void setOffers(ArrayList<Offer> offers, int position, MainActivity context){
        this.context = context;
        this.offers = offers;
        this.position = position;
    }

    @Override
    public void onClick(View view) {
        dismiss();
    }
}
