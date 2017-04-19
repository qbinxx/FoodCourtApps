package com.joker.foodcourtapp.views.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.joker.foodcourtapp.fragments.ListCategoryFragment;
import com.joker.foodcourtapp.utils.Constant;

/**
 * Created by rick on 31/08/16.
 */
public class ListCategoryFPA extends FragmentStatePagerAdapter{


    public ListCategoryFPA(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        ListCategoryFragment fragment = new ListCategoryFragment().newInstance();
        fragment.setArguments(bundle);
        return fragment;
    }

    // Jumlah Page yang akan ditampilkan
    @Override
    public int getCount() {
        return Constant.PARENT_CATEGORY_SIZE;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position % 3) {
            case 0:
                return Constant.TITLE_TABS[0];
            case 1:
                return Constant.TITLE_TABS[1];
            default:
                return Constant.TITLE_TABS[2];
        }
    }

}
