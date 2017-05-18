package com.joker.foodcourtapp.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;
import com.joker.foodcourtapp.DetailMenuActivity;
import com.joker.foodcourtapp.R;
import com.joker.foodcourtapp.TenantActivity;
import com.joker.foodcourtapp.models.Menu;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by rick on 11/08/16.
 */
public class MenuListFragment extends Fragment {

    private ArrayList<Menu> menus = new ArrayList<>();

    public static MenuListFragment newInstance() {
        return new MenuListFragment();
    }

    public MenuListFragment() {
    }

    class ExploreAdapter extends RecyclerView.Adapter<ViewHolder>{

        private LayoutInflater mLayoutInflater;

        public ExploreAdapter(){


        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup,int viewType){

            View itemView = LayoutInflater.from(getContext()).inflate(R.layout.recycle_menu_list, viewGroup,false);

            // Set minimum height
            int height = viewGroup.getMeasuredHeight() / 3;
            itemView.setMinimumHeight(height);
            return new ViewHolder(itemView);

        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder,final int position){

            final Menu menu = menus.get(position);
            final String foodName = menu.getName();
            final String imgUrl = menu.getImgUrl();
            viewHolder.setData(imgUrl, foodName);
            viewHolder.itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Intent intent = new Intent(getActivity(),DetailMenuActivity.class);
                    intent.putExtra("food_name",foodName);
                    startActivity(intent);
                }
            });

        }

        @Override
        public int getItemCount(){
            return menus.size();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        //View
        private ImageView mImageView;
        private TextView mNameTextView;

        private ViewHolder(View itemView) {
            super(itemView);

            // Get references to image and name.
            mImageView = (ImageView) itemView.findViewById(R.id.iv_food_img);
            mNameTextView = (TextView) itemView.findViewById(R.id.tv_food_name);
        }

        private void setData(String imgUrl, String name) {
            Picasso.with(itemView.getContext()).load(imgUrl).into(mImageView);
            mNameTextView.setText(name);
        }
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        addDummyDataMenu();
    }

    public void addDummyDataMenu(){
//        Menu menu1 = new Menu("Asian Food","http://img.huffingtonpost.com/asset/scalefit_970_noupscale/585be1aa1600002400bdf2a6.jpeg");
//        Menu menu2 = new Menu("Mie","http://www.suratkabar.id/wp-content/uploads/2017/01/mie-instan.jpg");
//        Menu menu3 = new Menu("Nasi Goreng","http://sejutafakta.com/wp-content/uploads/2016/11/nasi_goreng_enak.jpg");
//
//        menus.add(menu1);
//        menus.add(menu2);
//        menus.add(menu3);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_list_menu, container, false);

        final Activity activity = getActivity();
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
        recyclerView.setLayoutManager(new GridLayoutManager(activity, 1));
        recyclerView.setAdapter(new ExploreAdapter());
        return view;
    }


}
