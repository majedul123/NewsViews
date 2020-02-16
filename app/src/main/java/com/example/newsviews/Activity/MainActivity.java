package com.example.newsviews.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.newsviews.R;


import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;

public class MainActivity extends AppCompatActivity {
    public static final String PREFS_NAME = "LoginPrefs";
    private AppBarConfiguration mAppBarConfiguration;
    View navHeader;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.search)
    SearchView searchView;
    @BindView(R.id.ok)
    Button btn_ok;


//    @BindView(R.id.header_image)
//    ImageView headerImage;
//    @BindView(R.id.header_name)
//    TextView headerName;
//    @BindView(R.id.header_email)
//    ImageView headerEmail;

    @OnClick(R.id.ok)
    void okclick() {
        String quer = searchView.getQuery().toString();
        Intent intent = new Intent(MainActivity.this, NumberActivity.class).putExtra("query", quer);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);


        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);




        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, 0);
       // String name = prefs.getString("name", null);
       // headerName.setText(" " + name);


        navHeader = navigationView.getHeaderView(0);
        ImageView headerImage = navHeader.findViewById(R.id.header_image);
        TextView tv_name = navHeader.findViewById(R.id.header_name);
        TextView tv_email = navHeader.findViewById(R.id.header_email);


        //prefs = getSharedPreferences(PREFS_NAME, 0);
        String name = prefs.getString("name", null);
        String email = prefs.getString("email", null);
        String url = prefs.getString("url", null);


        tv_name.setText(name);
        tv_email.setText(email);
        Glide.with(getApplicationContext()).load(url)
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(headerImage);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main_drawer, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_home:
                Toast.makeText(getApplicationContext(), "Item 1 Selected", Toast.LENGTH_LONG).show();
                return true;
            case R.id.nav_gallery:
                Toast.makeText(getApplicationContext(), "Item 2 Selected", Toast.LENGTH_LONG).show();
                return true;
            case R.id.nav_slideshow:

                Toast.makeText(getApplicationContext(), "Item 3 Selected", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
