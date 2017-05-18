package com.joker.foodcourtapp;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.florent37.materialviewpager.MaterialViewPager;
import com.squareup.picasso.Picasso;

public class DetailMenuActivity extends AppCompatActivity {

    private MaterialViewPager mViewPager;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout = null;

    private TextView menu_name,dtl_publisher,menu_desc,dtl_date,dtl_text_publisher;
    private ImageView dtl_logo_publisher,vFoodImg;

    private String menuName,publisher,description,date,text_icon,logo_publisher,bg_icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_menu);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);

        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        initComponents();
        initComponentsData();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initComponents(){
        menu_name = (TextView) findViewById(R.id.menu_name);
        vFoodImg = (ImageView) findViewById(R.id.dtl_pict);
        dtl_publisher = (TextView) findViewById(R.id.dtl_publisher);
//        menu_desc = (TextView) findViewById(R.id.menu_desc);
        dtl_date = (TextView) findViewById(R.id.dtl_date_post);
        dtl_text_publisher = (TextView) findViewById(R.id.dtl_publisher_fn);
        dtl_logo_publisher = (ImageView) findViewById(R.id.dtl_logo_publisher);

//        menu_desc.setMovementMethod(LinkMovementMethod.getInstance());
//        menu_desc.setClickable(true);
    }

    private void initComponentsData(){
        menuName = getIntent().getExtras().getString("food_name");
        menu_name.setText(menuName);
        Picasso.with(this).load("https://www.bbcgoodfood.com/sites/default/files/recipe_images/quick-spicy-nasi-goreng.jpg").into(vFoodImg);
    }
}
