package com.joker.foodcourtapp.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.joker.foodcourtapp.R;
import com.joker.foodcourtapp.models.Menu;
import com.joker.foodcourtapp.models.User;
import com.joker.foodcourtapp.utils.CircleTransform;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by rick on 31/08/16.
 */
public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

    private ArrayList<Menu> menuArrayList;

    public MenuAdapter(ArrayList<Menu> menuArrayList){
        this.menuArrayList = menuArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_user, viewGroup,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewholder,final int position){
        final Menu menu = menuArrayList.get(position);
//        viewholder.user_photo.setImageDrawable(null);
        viewholder.setData(menu);

//        viewholder.itemView.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                Listener.onUserSelected(user);
//            }
//        });
    }

    @Override
    public int getItemCount(){
        return menuArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView user_photo;
        private TextView user_name,user_text;

        private ViewHolder (View itemView){
            super(itemView);

            user_photo = (ImageView) itemView.findViewById(R.id.user_photo);
            user_name = (TextView) itemView.findViewById(R.id.food_name);
            user_text = (TextView) itemView.findViewById(R.id.text_user_photo);
        }

        private void setData(Menu menu){
            Picasso.with(itemView.getContext()).load(menu.getImgUrl()).transform(new CircleTransform()).into(user_photo);
            user_name.setText(menu.getName());
        }
    }
}
