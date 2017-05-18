package com.joker.foodcourtapp.views.adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.joker.foodcourtapp.R;
import com.joker.foodcourtapp.models.Category;
import com.joker.foodcourtapp.utils.CustomLinearLayoutManager;

import java.util.ArrayList;

/**
 * Created by rick on 29/04/17.
 */
public class ListMenuAdapter extends RecyclerView.Adapter<ListMenuAdapter.ViewHolder>{

    private ArrayList<Category> categories;
    private Context context;

    public ListMenuAdapter(ArrayList<Category> catArrayList){
        this.categories = catArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_menu, viewGroup,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewholder,final int position){
        final Category category = categories.get(position);
        viewholder.setData(category);

    }

    @Override
    public int getItemCount(){
        return categories.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView ctgry_name;
        private RecyclerView recyclerView;

        private ViewHolder (View itemView){
            super(itemView);

            ctgry_name = (TextView) itemView.findViewById(R.id.ctgry_name);
//            recyclerView = (RecyclerView) itemView.findViewById(R.id.recycler_menu);
        }

        private void setData(Category category){
            MenuAdapter adapter = new MenuAdapter(category.getListMenu());
//            RecyclerView.LayoutManager layoutManager = new CustomLinearLayoutManager(itemView.getContext());
//            recyclerView.setHasFixedSize(false);
//            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext().getApplicationContext()));
            recyclerView.setAdapter(adapter);
            ctgry_name.setText(category.getName());
        }
    }
}
