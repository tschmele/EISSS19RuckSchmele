package com.example.foodinprogress;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.foodinprogress.data.retrofit.Example;

import com.example.foodinprogress.notNeeded.DisplayFragmentMap;
import com.example.foodinprogress.notNeeded.InformationFragment;
import com.example.foodinprogress.notNeeded.StorageFragment;
import com.example.foodinprogress.ui.display.DisplayFragment;
import com.example.foodinprogress.ui.display.DisplayFragmentDetail;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.iid.FirebaseInstanceId;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;



public class DisplayActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private ArrayList<Example> examples = new ArrayList<>();

    private TextView textViewResult;
    private String[] String;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
/*
        textPostObject();
        getDisplay();

        final RecyclerView recyclerView = findViewById(R.id.recyclerView_display);
        recyclerView.setHasFixedSize(true); // For the Performance
        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayout);

        DisplayAdapterArray displayAdapter = new DisplayAdapterArray(generateData());
        recyclerView.setAdapter(displayAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
*/
        // TOKEN: cFmyTzrrnoc:APA91bGRVFiYe6GgjRdHLKaV7AwogLsZrcV_0DHcMR4LaHXlhEOD8Lxwtyx_ThnG_-L08i5OZr8xnNEE1BG8xzNCQbDj8zOwkRhRkglQfk0tpWzldYCqN5cDaIblUJix5vS7MTMMIM8M
        Log.d("FCM", "Token :" + FirebaseInstanceId.getInstance().getToken());

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new DisplayFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_dispaly);
        }

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_dispaly:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new DisplayFragment()).commit();
                break;
            case R.id.nav_storage:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new StorageFragment()).commit();
                break;
            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new InformationFragment()).commit();
                break;
            case R.id.nav_maps_display:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new DisplayFragmentMap()).commit();
                break;
            case R.id.nav_rows_display:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new DisplayFragmentDetail()).commit();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);


        return true;
    }


}
