package com.joker.foodcourtapp;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.florent37.materialviewpager.MaterialViewPager;
import com.joker.foodcourtapp.models.Menu;
import com.joker.foodcourtapp.models.User;
import com.joker.foodcourtapp.views.adapters.MenuAdapter;

import java.util.ArrayList;

public class ListMenuActivity extends AppCompatActivity {

    private MaterialViewPager mViewPager;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout = null;

    private TextView tenant_name,dtl_publisher,tenant_desc,dtl_date,dtl_text_publisher;
    private ImageView dtl_logo_publisher;

    private String tenantName,publisher,description,date,text_icon,logo_publisher,bg_icon;

    private ArrayList<Menu> menus = new ArrayList<>();;
    private MenuAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_menu);

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

        tenant_name.setText(tenantName);

//        for (int i = 0 ; i < 2; i++){
//            User user = new User();
//            user.setFullName("Float Beer");
//            menus.add(user);
//        }

//        adapter = new MenuAdapter(userArrayList, new MenuAdapter.onUserSelected(){
//            @Override
//            public void onUserSelected(User user){
//                Intent intent = new Intent(getApplicationContext(),DetailMenuActivity.class);
////                intent.putExtra("username",user.getUsername());
////                intent.putExtra("picture",user.getPicture());
////                intent.putExtra("iconText",user.getIconText());
////                intent.putExtra("iconBgColor",user.getIconBgColor());
////
//                startActivity(intent);
//            }
//        });
        adapter = new MenuAdapter(menus);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_users);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);

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
        tenant_name = (TextView) findViewById(R.id.tenant_name);
        dtl_publisher = (TextView) findViewById(R.id.dtl_publisher);
        tenant_desc = (TextView) findViewById(R.id.tenant_desc);
        dtl_date = (TextView) findViewById(R.id.dtl_date_post);
        dtl_text_publisher = (TextView) findViewById(R.id.dtl_publisher_fn);
        dtl_logo_publisher = (ImageView) findViewById(R.id.dtl_logo_publisher);

        tenant_desc.setMovementMethod(LinkMovementMethod.getInstance());
        tenant_desc.setClickable(true);
    }

    private void initComponentsData(){
        tenantName = getIntent().getExtras().getString("tenantName");
    }

}
