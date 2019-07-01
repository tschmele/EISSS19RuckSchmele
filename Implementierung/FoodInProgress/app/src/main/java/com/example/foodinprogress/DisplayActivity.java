package com.example.foodinprogress;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.foodinprogress.InformationFragment;
import com.example.foodinprogress.R;
import com.example.foodinprogress.StorageFragment;
import com.example.foodinprogress.ui.display.DisplayFragment;
import com.example.foodinprogress.ui.display.DisplayFragmentMap;
import com.example.foodinprogress.ui.display.DisplayFragmentOwn;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.iid.FirebaseInstanceId;

import androidx.appcompat.widget.Toolbar;

public class DisplayActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

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

        // TOKEN: cFmyTzrrnoc:APA91bGRVFiYe6GgjRdHLKaV7AwogLsZrcV_0DHcMR4LaHXlhEOD8Lxwtyx_ThnG_-L08i5OZr8xnNEE1BG8xzNCQbDj8zOwkRhRkglQfk0tpWzldYCqN5cDaIblUJix5vS7MTMMIM8M
        Log.d("FCM", "Token :" + FirebaseInstanceId.getInstance().getToken());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_dispaly:
                setTitle("Eigene Anzeigen");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new DisplayFragment()).commit();
                break;
            case R.id.nav_storage:
                setTitle("Lager");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new StorageFragment()).commit();
                break;
            case R.id.nav_infor:
                setTitle("Informationen");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new InformationFragment()).commit();
                break;
            case R.id.nav_maps_display:
                setTitle("Allgemeine Anzeige Karten");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new DisplayFragmentMap()).commit();
                break;
            case R.id.nav_rows_display:
                setTitle("Allgemeine Anzeigen Liste");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new DisplayFragmentOwn()).commit();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);


        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
