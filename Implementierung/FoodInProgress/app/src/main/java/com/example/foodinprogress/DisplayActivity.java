package com.example.foodinprogress;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.foodinprogress.DisplayAdapterArray;
import com.example.foodinprogress.data.retrofit.AnzeigenInterface;
import com.example.foodinprogress.data.retrofit.Data;
import com.example.foodinprogress.data.retrofit.Example;
import com.example.foodinprogress.data.retrofit.Standort;
import com.example.foodinprogress.ui.display.DisplayAdapter;
import com.example.foodinprogress.ui.display.DisplayListItem;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.iid.FirebaseInstanceId;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dalvik.annotation.TestTarget;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class DisplayActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private ArrayList<Example> examples = new ArrayList<>();

    private TextView textViewResult;
    private String[] String;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_allinone);

        textViewResult = findViewById(R.id.textViewAllInOne);

        textPostObject();

        final RecyclerView recyclerView = findViewById(R.id.recyclerView_display);
        recyclerView.setHasFixedSize(true); // For the Performance
        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayout);

        DisplayAdapterArray displayAdapter = new DisplayAdapterArray(generateData());
        recyclerView.setAdapter(displayAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // TOKEN: cFmyTzrrnoc:APA91bGRVFiYe6GgjRdHLKaV7AwogLsZrcV_0DHcMR4LaHXlhEOD8Lxwtyx_ThnG_-L08i5OZr8xnNEE1BG8xzNCQbDj8zOwkRhRkglQfk0tpWzldYCqN5cDaIblUJix5vS7MTMMIM8M
        Log.d("FCM", "Token :" + FirebaseInstanceId.getInstance().getToken());

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout_act);
        NavigationView navigationView = findViewById(R.id.nav_view_act);
        navigationView.setNavigationItemSelectedListener(this);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_dispaly:
                setTitle("Eigene Anzeigen");
                break;
            case R.id.nav_storage:
                setTitle("Lager");
                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

/*
    public void getDisplay() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(IP_Kay_Mobil)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AnzeigenInterface api = retrofit.create(AnzeigenInterface.class);

        Call<List<Example>> call2 = api.getDisplayPosts();
        call2.enqueue(new Callback<List<Example>>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<List<Example>> call, Response<List<Example>> response) {

                String status = "Status Code: " + response.code() + response.message();

                textViewResult.setText("Friend");

                if (response.isSuccessful()) {

                    Log.d(SUCCESS, status);
                    Toast.makeText(getApplicationContext(), status, Toast.LENGTH_SHORT).show();
                    List<Example> examples = response.body();
                    textViewResult.setText("Hallo Welt");
                    textViewResult.setText(examples.toString());
                } else {
                    Log.d(ERROR, status);
                    Toast.makeText(getApplicationContext(), status, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Example>> call, Throwable t) {
                String error = t.getMessage();
                Log.d(ERROR, error);
                Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
            }
        });

    }
*/

    public void textPostObject() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.178.50:2000/")
                .addConverterFactory(GsonConverterFactory
                        .create()).build();

        AnzeigenInterface anzeigenInterface = retrofit.create(AnzeigenInterface.class);


        String[] strings = {"meat, fish"};
        Data data = new Data(new Standort(51.028720, 7.563900), true, "10 von 10 Grillgut", strings, "übriggebliebenes Grillgut");

        Call<Data> call3 = anzeigenInterface.postDisplayPost(data);

        call3.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                Toast.makeText(getApplicationContext(), "Yeah!" + response.code(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Post konnte nicht ausgeführt werden" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }


    public DisplayListItem[] generateData() {
        DisplayListItem[] displayListItems = {
                new DisplayListItem("Back", R.mipmap.ic_launcher),
                new DisplayListItem("Row", R.mipmap.ic_launcher),
                new DisplayListItem("Pin", R.mipmap.ic_launcher),
                new DisplayListItem("Info", R.mipmap.ic_launcher),

                new DisplayListItem("Back", R.mipmap.ic_launcher),
                new DisplayListItem("Row", R.mipmap.ic_launcher),
                new DisplayListItem("Pin", R.mipmap.ic_launcher),
                new DisplayListItem("Info", R.mipmap.ic_launcher)
        };
        return displayListItems;

    }

}
