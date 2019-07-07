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

import com.example.foodinprogress.data.retrofit.Anzeigen;
import com.example.foodinprogress.data.retrofit.AnzeigenInterface;
import com.example.foodinprogress.data.retrofit.Data;
import com.example.foodinprogress.data.retrofit.Example;
import com.example.foodinprogress.ui.display.DisplayAdapter;
import com.example.foodinprogress.ui.display.DisplayListItem;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.iid.FirebaseInstanceId;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.foodinprogress.util.Constants.ERROR;
import static com.example.foodinprogress.util.Constants.IP_Kay_Mobil;
import static com.example.foodinprogress.util.Constants.SUCCESS;

public class DisplayActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private ArrayList<Example> examples = new ArrayList<>();

    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_allinone);

        textViewResult = findViewById(R.id.textViewAllInOne);


        final RecyclerView recyclerView = findViewById(R.id.recyclerView_display);
        recyclerView.setHasFixedSize(true); // For the Performance
        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayout);

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

        //Data data = new Data();

        //Call<Example> call3 = api.postDisplayPost();



        if (getApplicationContext() == null) Log.d(ERROR, "OnCreate: Context is Empty");

        DisplayAdapter displayAdapter = new DisplayAdapter(examples);
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


    public DisplayListItem[] generateData() {
        DisplayListItem[] displayListItems = {
                new DisplayListItem("Back", R.drawable.ic_back),
                new DisplayListItem("Row", R.drawable.ic_rows),
                new DisplayListItem("Pin", R.drawable.ic_pin),
                new DisplayListItem("Info", R.drawable.ic_information),

                new DisplayListItem("Back", R.drawable.ic_back),
                new DisplayListItem("Row", R.drawable.ic_rows),
                new DisplayListItem("Pin", R.drawable.ic_pin),
                new DisplayListItem("Info", R.drawable.ic_information),

                new DisplayListItem("Back", R.drawable.ic_back),
                new DisplayListItem("Row", R.drawable.ic_rows),
                new DisplayListItem("Pin", R.drawable.ic_pin),
                new DisplayListItem("Info", R.drawable.ic_information)
        };
        return displayListItems;

    }

}
