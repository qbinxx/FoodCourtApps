package com.joker.foodcourtapp.views.adapters;

/**
 * Created by rick on 29/04/17.
 */
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import com.joker.foodcourtapp.R;

public class CategoryMenuViewHolder extends GroupViewHolder {

    private TextView osName;

    public CategoryMenuViewHolder(View itemView) {
        super(itemView);

        osName = (TextView) itemView.findViewById(R.id.category_name);
    }

    @Override
    public void expand() {
        osName.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_keyboard_arrow_up_black_24dp, 0);
        Log.i("Adapter", "expand");
    }

    @Override
    public void collapse() {
        Log.i("Adapter", "collapse");
        osName.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_keyboard_arrow_down_black_24dp, 0);
    }

    public void setGroupName(ExpandableGroup group) {
        osName.setText(group.getTitle());
    }
}
