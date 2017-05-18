package com.joker.foodcourtapp.views.adapters;

/**
 * Created by rick on 29/04/17.
 */
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joker.foodcourtapp.DetailMenuActivity;
import com.joker.foodcourtapp.MenuActivity;
import com.joker.foodcourtapp.TenantActivity;
import com.joker.foodcourtapp.models.CategoryMenu;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

import com.joker.foodcourtapp.R;
import com.joker.foodcourtapp.models.Menu2;

public class RecyclerAdapter extends ExpandableRecyclerViewAdapter<CategoryMenuViewHolder, MenuViewHolder> {

    private Activity activity;

    public RecyclerAdapter(Activity activity, List<? extends ExpandableGroup> groups) {
        super(groups);
        this.activity = activity;
    }

    @Override
    public CategoryMenuViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.group_view_holder, parent, false);

        return new CategoryMenuViewHolder(view);
    }

    @Override
    public MenuViewHolder onCreateChildViewHolder(ViewGroup parent, final int viewType) {
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.child_view_holder, parent, false);

        return new MenuViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(final MenuViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        final Menu2 menu2 = ((CategoryMenu) group).getItems().get(childIndex);
        holder.onBind(menu2,group);
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(holder.itemView.getContext(),DetailMenuActivity.class);
                intent.putExtra("food_name",menu2.getName());
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }


    @Override
    public void onBindGroupViewHolder(CategoryMenuViewHolder holder, int flatPosition, ExpandableGroup group) {
        holder.setGroupName(group);
    }
}