package com.joker.foodcourtapp.fragments;

import android.app.Activity;
import android.content.Context;
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
import com.joker.foodcourtapp.R;

/**
 * Created by rick on 11/08/16.
 */
public class MenuListFragment extends Fragment {

    private int[] mImageResIds;
    private String[] mNames;

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

            final int imageResId = mImageResIds[position];
            final String name = mNames[position];
            viewHolder.setData(imageResId, name);

        }

        @Override
        public int getItemCount(){
            return mNames.length;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        //View
        private ImageView mImageView;
        private TextView mNameTextView;

        private ViewHolder(View itemView) {
            super(itemView);

            // Get references to image and name.
            mImageView = (ImageView) itemView.findViewById(R.id.comic_image);
            mNameTextView = (TextView) itemView.findViewById(R.id.name);
        }

        private void setData(int imageResId, String name) {
            mImageView.setImageResource(imageResId);
            mNameTextView.setText(name);
        }
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        //Get Data from resource
        final Resources resources = context.getResources();
        mNames = resources.getStringArray(R.array.names);

        // Get rage face images.
        final TypedArray typedArray = resources.obtainTypedArray(R.array.images);
        final int imageCount = mNames.length;
        mImageResIds = new int[imageCount];
        for (int i = 0; i < imageCount; i++) {
            mImageResIds[i] = typedArray.getResourceId(i, 0);
        }
        typedArray.recycle();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_list_menu, container, false);

        final Activity activity = getActivity();
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
        recyclerView.setLayoutManager(new GridLayoutManager(activity, 2));
        recyclerView.setAdapter(new ExploreAdapter());
        return view;
    }


}
