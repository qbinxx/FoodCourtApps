package com.joker.foodcourtapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.joker.foodcourtapp.utils.Variable;


/**
 * Created by rick on 10/08/16.
 */
public class DrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView nvDrawer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        if (!BuildConfig.DEBUG) {
//            Fabric.with(this, new Crashlytics());
//        }

    }

    @Override
    protected void onStart() {
        super.onStart();

        //saat OnStart set Drawer ke View dengan ID drawer_layout
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        // Menunjukan adanya navigation drawer pada action bar
        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawer, 0, 0);
        mDrawer.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        // List Action yang dilakukan pada saat menggunakan navigasi
//        final ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(true); // pengguna yang klik tombol home akan kembali satu level
//            actionBar.setDisplayShowHomeEnabled(true);
//            actionBar.setDisplayShowTitleEnabled(true);
//            actionBar.setDisplayUseLogoEnabled(false);
//            actionBar.setHomeButtonEnabled(true);
//        }

        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        nvDrawer.setNavigationItemSelectedListener(this);
    }

    // Harus ada saat menggunakan drawer toggle
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

    }

    // Harus ada saat menggunakan drawer toggle
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mDrawerToggle.onOptionsItemSelected(item) ||
                super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.news_feed) {
            // Handle the camera action
        } else if (id == R.id.explore) {

            Intent intent = new Intent(this,MenuActivity.class);
            startActivity(intent);

        } else if (id == R.id.bookmark) {

        } else if (id == R.id.contacts) {

        } else if (id == R.id.forum) {

        }else if (id == R.id.log_out) {
            MainActivity.fa.finish();
            Variable.setLoginStatus(this,false);
            Log.d("login status",String.valueOf(Variable.getLoginStatus(this)));
            Variable.setUserDataLogged(this,"");
            startActivity(new Intent(this,MainActivity.class));

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}

