package com.example.foodinprogress;

import android.content.Intent;
import android.os.Bundle;

import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.foodinprogress.ui.display.DisplayFragment;
import com.example.foodinprogress.ui.display.DisplayFragmentMap;


public class MainActivity extends AppCompatActivity /*implements BottomNavigationView.OnNavigationItemSelectedListener */{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        BottomNavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setOnNavigationItemSelectedListener(this);

        Toolbar toolbar = findViewById(R.id.actionbar_display);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimary));
        toolbar.setSubtitleTextColor(getResources().getColor(R.color.colorPrimary));
        toolbar.setSubtitle("Hallo Rabe");
*/
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();

            return true;
        }
        return false;

    }

    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        Fragment fragment = null;
        switch (menuItem.getItemId()) {
            case R.id.navigation_display:
                Intent intenten = new Intent(this, DisplayActivity.class);
                startActivity(intenten);
                setTitle("Normale Anzeige");
                break;
            case R.id.navigation_storage:
                fragment = new StorageFragment();
                setTitle("Lager");
                FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                        .beginTransaction();
                fragmentTransaction.add(R.id.fragment_container, new StorageFragment()).commit();

                break;
            case R.id.navigation_information:
                fragment = new InformationFragment();
                setTitle("Informationen");
                break;
        }

        return loadFragment(fragment);
    }


    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        Fragment fragment = null;

        switch (id) {
            case R.id.action_ic_map:
                fragment = new DisplayFragmentMap();
                setTitle("Karten Anzeige");
                break;
            case R.id.action_ic_row:
                setTitle("Normale Anzeige");
                break;
            case R.id.action_ic_back:
                fragment = new DisplayFragment();
                setTitle("Normale Anzeige");
                break;
            case R.id.action_ic_profil_pic:
                fragment = new ProfilFragment();
                setTitle("Your Profil");
                break;
            case R.id.action_ic_search:
                item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        setSearchDialog();
                        return true;
                    }
                });

            break;

        }

        return loadFragment(fragment);
    }

    public void setSearchDialog(){
        SearchDialog dialog = new SearchDialog();
        dialog.show(getSupportFragmentManager(), "Search Dialog" );

    }
}