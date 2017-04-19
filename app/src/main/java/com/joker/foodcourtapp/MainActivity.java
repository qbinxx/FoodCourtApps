package com.joker.foodcourtapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;
import com.pnikosis.materialishprogress.ProgressWheel;
import com.joker.foodcourtapp.views.adapters.ListCategoryFPA;
import com.joker.foodcourtapp.models.Category;
import com.joker.foodcourtapp.network.SquareService;
import com.joker.foodcourtapp.utils.CircleTransform;
import com.joker.foodcourtapp.utils.Constant;
import com.joker.foodcourtapp.utils.FontManager;
import com.joker.foodcourtapp.utils.Variable;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends DrawerActivity{

    private MaterialViewPager mViewPager;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private ProgressWheel wheel;

    private TextView failLoad,logo,hUsername,hUserIconText;
    private Button retry;
    private ImageView hUserPhoto;

    private ProgressDialog pLoadData;

    private ArrayList<Category> categories = new ArrayList<>();
    private List<String> url_category = new ArrayList<>();
    private List<String> category = new ArrayList<>();
    public static Activity fa;

    int lastPosition = 0;


    private Context c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        c = getApplicationContext();
        fa = this;
        setTitle("");

        initComponents();

        // Components
        mViewPager = (MaterialViewPager) findViewById(R.id.materialViewPager);
        navigationView = (NavigationView) findViewById(R.id.nvView);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        View headerview = navigationView.getHeaderView(0);
        hUsername = (TextView) headerview.findViewById(R.id.nav_header_UserName);
        hUserPhoto = (ImageView) headerview.findViewById(R.id.nav_header_PhotoUser);
        hUserIconText = (TextView) headerview.findViewById(R.id.nav_text_user_photo);

        // Set a Toolbar
        toolbar = mViewPager.getToolbar();
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        //Button Retry
        retry.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                retry.setVisibility(View.GONE);
                failLoad.setVisibility(View.GONE);
                loadData();
            }
        });

        //EDIT HERE
        setUpMaterialViewPager();

//        if (Variable.getMainPageData(c).equals("")) {
//            loadData();
//        }else{
//            setData();
//        }

        LinearLayout header = (LinearLayout) headerview.findViewById(R.id.header);
        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this,ProfileActivity.class));
            }
        });

//        checkingLogin();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        final MenuItem miniCard = menu.findItem(R.id.mini_card_settings);
        if(Variable.getMiniCardConf(c)) {
            miniCard.setChecked(true);
        } else {
            miniCard.setChecked(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh:
//                page_position = mViewPager.getViewPager().getCurrentItem();
//                setMViewPager(TYPE,page_position);
                lastPosition = mViewPager.getViewPager().getCurrentItem();
                loadData();
                return true;
            case R.id.mini_card_settings:
                lastPosition = mViewPager.getViewPager().getCurrentItem();
                if (item.isChecked()){
                    item.setChecked(false);
                    Variable.setMiniCardConf(c,false);
                    setUpMaterialViewPager();
                }
                else {
                    item.setChecked(true);
                    Variable.setMiniCardConf(c,true);
                    setUpMaterialViewPager();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        navigationView.getMenu().getItem(0).setChecked(true);
        if (drawer.isDrawerOpen(GravityCompat.START)) drawer.closeDrawer(GravityCompat.START);

        LocalBroadcastManager.getInstance(c).registerReceiver(mMessageReceiverMain,
                new IntentFilter("my-event-main"));
    }

    @Override
    public void onPause() {
        LocalBroadcastManager.getInstance(c).unregisterReceiver(mMessageReceiverMain);

        super.onPause();
    }

    private void checkingLogin(){
        if(Variable.getLoginStatus(c)){
            navigationView.getMenu().getItem(1).setVisible(false);
            for(int i=2;i<=7;i++){
                navigationView.getMenu().getItem(i).setVisible(true);
            }
            setNavHeader();
        }else{
            hUserPhoto.setImageResource(R.drawable.logo_ppni);
            hUsername.setText("Qepon Square PPNI");
            hUserPhoto.setBackgroundColor(0x00000000);
        }
    }

    private void setNavHeader(){

        try {
            JSONObject json = new JSONObject(Variable.getUserDataLogged(c));
            hUsername.setText(json.getString("username"));
            if(!json.getString("picture").isEmpty()){
                if(json.getString("picture").charAt(0)=='/') Picasso.with(c).load(Constant.ROOT_URL +json.getString("picture")).transform(new CircleTransform()).into(hUserPhoto);
                else Picasso.with(c).load(json.getString("picture")).transform(new CircleTransform()).into(hUserPhoto);
                hUserIconText.setText("");
            }
            else {
                Drawable background = hUserPhoto.getBackground();

                if (background instanceof ShapeDrawable) {
                    ShapeDrawable shapeDrawable = (ShapeDrawable)background;
                    shapeDrawable.getPaint().setColor(Color.parseColor(json.getString("icon:bgColor")));
                } else if (background instanceof GradientDrawable) {
                    // cast to 'GradientDrawable'
                    GradientDrawable gradientDrawable = (GradientDrawable)background;
                    gradientDrawable.setColor(Color.parseColor(json.getString("icon:bgColor")));
                }
                hUserIconText.setText(json.getString("icon:text"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void initComponents(){
        failLoad = (TextView) findViewById(R.id.text_fail_load);
        retry = (Button) findViewById(R.id.btn_retry);

        pLoadData = new ProgressDialog(MainActivity.this);
        pLoadData.setMessage("Loading Data...");
        pLoadData.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        logo = (TextView) findViewById(R.id.main_logo);
        logo.setTypeface(FontManager.getTypeface(this,FontManager.FONTAWESOME));
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        wheel = (ProgressWheel) findViewById(R.id.progress_wheel);
        wheel.setBarColor(Color.GREEN);


    }

    private void loadData(){
        showLoading(wheel,true);
        SquareService.actionLoadMainPageData(c);
    }

    private void failLoadData(){
        retry.setVisibility(View.VISIBLE);
        failLoad.setVisibility(View.VISIBLE);
    }

    private BroadcastReceiver mMessageReceiverMain = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String message = intent.getStringExtra("message");
            Log.d("receiver", "Got message: main " + message);
            if (!message.contains("error")) {
                Variable.setMainPageData(c, message);
                setData();
                showLoading(wheel,false);
            } else {
                if(Variable.getMainPageData(c).isEmpty()){
                    showLoading(wheel,false);
                    failLoadData();
                }else{
                    showLoading(wheel,false);
                    Toast.makeText(c, "Refresh failed", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    // Modul show/hide progressDialog
    private void showLoading(ProgressWheel p,boolean b){
        if(b){
            p.setVisibility(View.VISIBLE);
            p.spin();
        }else{
            p.setVisibility(View.GONE);
            p.stopSpinning();
        }
    }

    private void setData(){
            try {
                categories.clear();
//                Log.d("receiver","check main page data :"+Variable.getMainPageData(c));
                JSONObject json = new JSONObject(Variable.getMainPageData(c));
                JSONArray categoriesJson = json.getJSONArray("categories");

                for (int i = 0; i < categoriesJson.length(); i++) {
                    JSONObject categori = categoriesJson.getJSONObject(i);
                    Category category = new Category(categori);
//                    Log.d("receiver","category content : "+category.getName());
                    categories.add(category);
                }

                setUpMaterialViewPager();

            } catch (JSONException e) {
                e.printStackTrace();
            }
    }

    private void setUpMaterialViewPager(){
        Log.d("vpager","setup");
        Log.d("LOGIN","creating material -> "+String.valueOf(Variable.getMiniCardConf(c)));

        mViewPager.getViewPager().setAdapter(new ListCategoryFPA(getSupportFragmentManager()));

//        if(Variable.getMiniCardConf(c)){
//            mViewPager.getViewPager().setAdapter(new ListPostFragmentStateAdapter(getSupportFragmentManager(),true,categories));
//        }
//        else mViewPager.getViewPager().setAdapter(new ListPostFragmentStateAdapter(getSupportFragmentManager(),false,categories));

//        if(Variable.getMiniCardConf(c)){
//            mViewPager.getViewPager().setAdapter(new NewListPostFragmentStateAdapter(getSupportFragmentManager(),true,categories));
//        }
//        else mViewPager.getViewPager().setAdapter(new NewListPostFragmentStateAdapter(getSupportFragmentManager(),false,categories));

        mViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {
            @Override
            public HeaderDesign getHeaderDesign(int page) {
                switch (page) {
                    case 0:
                        logo.setText(R.string.fa_bullhorn);
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.colorAccent,
                                "http://cdn1.tnwcdn.com/wp-content/blogs.dir/1/files/2014/06/wallpaper_51.jpg");
                    case 1:
                        logo.setText(R.string.fa_comments);

                        return HeaderDesign.fromColorResAndUrl(
                                R.color.colorAccent,
                                "http://cdn1.tnwcdn.com/wp-content/blogs.dir/1/files/2014/06/wallpaper_51.jpg");
                    case 2:
                        logo.setText(R.string.fa_bullhorn);
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.colorAccent,
                                "http://www.droid-life.com/wp-content/uploads/2014/10/lollipop-wallpapers10.jpg");
//                    case 3:
//                        return HeaderDesign.fromColorResAndUrl(
//                                R.color.red,
//                                "http://www.tothemobile.com/wp-content/uploads/2014/07/original.jpg");
                }

                //execute others actions if needed (ex : modify your header logo)

                return null;
            }
        });

        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());

//        mViewPager.getViewPager().setCurrentItem(lastPosition);
        if(lastPosition == 0){
            mViewPager.getViewPager().setCurrentItem(1);
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mViewPager.getViewPager().setCurrentItem(lastPosition);

            }
        },1);
    }
}
