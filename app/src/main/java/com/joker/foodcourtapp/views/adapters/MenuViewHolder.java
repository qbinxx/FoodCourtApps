package com.joker.foodcourtapp.views.adapters;

/**
 * Created by rick on 29/04/17.
 */
import android.content.Context;
import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.joker.foodcourtapp.utils.CircleTransform;
import com.squareup.picasso.Picasso;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

import com.joker.foodcourtapp.R;
import com.joker.foodcourtapp.models.Menu2;

public class MenuViewHolder extends ChildViewHolder {

    private Context context;
    private TextView phoneName,price;
    private ImageView foodImage;

    public MenuViewHolder(View itemView) {
        super(itemView);
        context = itemView.getContext();
        phoneName = (TextView) itemView.findViewById(R.id.menu_name);
        price = (TextView) itemView.findViewById(R.id.price);
        foodImage = (ImageView) itemView.findViewById(R.id.food_img);

    }

    public void onBind(Menu2 menu2, ExpandableGroup group) {
        phoneName.setText(menu2.getName());
        price.setText("Rp "+Float.toString(menu2.getPrice()));
        Picasso.with(itemView.getContext()).load("http://i.hungrygowhere.com/cms/e8/f4/06/9e/20000569/best-nasi-padang-singapore_566x424_fillbg_643ad287bf.jpg").transform(new CircleTransform()).into(foodImage);
    }
}
