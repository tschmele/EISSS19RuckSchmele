package com.example.foodinprogress;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


    ListView list;

    String[]  name = {"Erdbeere", "Banana", "Blaubeeren", "Limette", "Brine"};
    String[]  amount = {"2 Kilogramm", "3 Stück", "Beliebig", "500 Gramm", "500 Gramm"};
    String[]  user = {"HoneyBunny", "DerRabe", "Pumpkin", "Deca", "Joschi"};
    Integer[] imgrid = {R.drawable.pic_obst_smoll, R.drawable.pic_obst_smoll, R.drawable.pic_obst_smoll, R.drawable.pic_obst_smoll, R.drawable.pic_obst_smoll};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setOnNavigationItemSelectedListener(this);

        loadFragment(new DisplayFragment());

        Toolbar toolbar = findViewById(R.id.actionbar_display);
        setSupportActionBar(toolbar);
        toolbar.setSubtitle("Hallo Kay");

        list = (ListView) findViewById(R.id.listview);
        CustomListview customListview = new CustomListview(this, name, amount, user, imgrid);
        list.setAdapter(customListview);
    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }
*/
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        Fragment fragment = null;
        switch (menuItem.getItemId()) {
            case R.id.navigation_display:
                list.setVisibility(View.VISIBLE);
                fragment = new DisplayFragment();
                setTitle("Normale Anzeige");
                break;
            case R.id.navigation_storage:
                list.setVisibility(View.INVISIBLE);
                fragment = new StorageFragment();
                setTitle("Lager");
                break;
            case R.id.navigation_information:
                list.setVisibility(View.INVISIBLE);
                fragment = new InformationFragment();
                setTitle("Informationen");
                break;
        }

        return loadFragment(fragment);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        Fragment fragment = null;

        switch (id) {
            case R.id.action_ic_map:
                list.setVisibility(View.INVISIBLE);
                fragment = new DisplayFragmentMap();
                setTitle("Karten Anzeige");
                break;
            case R.id.action_ic_search:
                list.setVisibility(View.INVISIBLE);
                fragment = new DisplayFragment();
                setTitle("Search");
                break;
            case R.id.action_ic_row:
                list.setVisibility(View.VISIBLE);
                fragment = new DisplayFragment();
                setTitle("Normale Anzeige");
                break;
        }

        return loadFragment(fragment);
    }

}