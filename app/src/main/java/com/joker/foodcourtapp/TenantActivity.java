package com.joker.foodcourtapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.joker.foodcourtapp.models.Category;
import com.joker.foodcourtapp.models.Menu;
import com.joker.foodcourtapp.models.CategoryMenu;
import com.joker.foodcourtapp.models.Menu2;
import com.joker.foodcourtapp.views.adapters.RecyclerAdapter;

import java.util.ArrayList;

public class TenantActivity extends AppCompatActivity {

    private ArrayList<Category> categories = new ArrayList<>();

    private TextView tenant_name;

    private RecyclerView recyclerView;
    private ArrayList<CategoryMenu> categoryMenus;
    private RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenant);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        categoryMenus = new ArrayList<>();

        setData();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerAdapter(this, categoryMenus);
        recyclerView.setAdapter(adapter);

//        initComponents();
//        addDummyDataCategories();

//        ListMenuAdapter adapter = new ListMenuAdapter(categories);
//        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_menu);
//        RecyclerView.LayoutManager layoutManager = new CustomLinearLayoutManager(getApplicationContext());
//        recyclerView.setLayoutManager(layoutManager);
////        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        recyclerView.setAdapter(adapter);
    }

    private void initComponents(){
        tenant_name = (TextView) findViewById(R.id.tenant_name);
        tenant_name.setText(getIntent().getExtras().getString("tenantName"));
    }

    private void addDummyDataCategories(){
        ArrayList<Menu> menus = new ArrayList<>();
        Menu menu1 = new Menu();
        menu1.setName("Nasi Goreng");
        menu1.setImgUrl("http://sejutafakta.com/wp-content/uploads/2016/11/nasi_goreng_enak.jpg");
        menus.add(menu1);

        Category c1 = new Category();
        c1.setName("Most Popular");
        c1.setListMenu(menus);
        Category c2 = new Category();
        c2.setName("Food");
        c2.setListMenu(menus);

        categories.add(c1);
        categories.add(c2);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        adapter.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        adapter.onRestoreInstanceState(savedInstanceState);
    }

    private void setData() {
        ArrayList<Menu2> popularMenu = new ArrayList<>();
        popularMenu.add(new Menu2("Nasi Goreng",10000));
        popularMenu.add(new Menu2("Nasi Padang",5000));
        popularMenu.add(new Menu2("Nasi Putih",2000));

        ArrayList<Menu2> mie = new ArrayList<>();
        mie.add(new Menu2("Mie Ramen"));
        mie.add(new Menu2("Mie Ayam"));
        mie.add(new Menu2("Mie Baso"));

        ArrayList<Menu2> drink = new ArrayList<>();
        drink.add(new Menu2("Air Putih"));
        drink.add(new Menu2("Juice Alpukat"));
        drink.add(new Menu2("Socking Soda"));

        categoryMenus.add(new CategoryMenu("Most Popular", popularMenu));
        categoryMenus.add(new CategoryMenu("Mie", mie));
        categoryMenus.add(new CategoryMenu("Drink", drink));
    }

}
