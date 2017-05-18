package com.joker.foodcourtapp.models;

import android.annotation.SuppressLint;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import java.util.List;

@SuppressLint("ParcelCreator")
public class CategoryMenu extends ExpandableGroup<Menu2> {

    public CategoryMenu(String title, List<Menu2> items) {
        super(title, items);
    }
}