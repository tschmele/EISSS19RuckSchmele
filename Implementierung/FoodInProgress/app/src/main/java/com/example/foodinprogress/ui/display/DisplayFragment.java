package com.example.foodinprogress.ui.display;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodinprogress.R;
import com.example.foodinprogress.data.retrofit.AnzeigenInterface;
import com.example.foodinprogress.data.retrofit.Data;
import com.example.foodinprogress.data.retrofit.Example;
import com.example.foodinprogress.data.retrofit.Standort;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.foodinprogress.util.Constants.ERROR;
import static com.example.foodinprogress.util.Constants.IP_Kay_Mobil;
import static com.example.foodinprogress.util.Constants.SUCCESS;

public class DisplayFragment extends Fragment implements DisplayAdapterNode.OnNoteListener {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_display, container, false);
        buildRecyclerView(view);
        getDisplays();
        postDisplay();


        return view;
    }


    public void buildRecyclerView(View view) {


        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_display_activity);
        LinearLayoutManager linearLayout = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayout);

        FloatingActionButton button = view.findViewById(R.id.displayButton_l);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, new DisplayFragmentEdit()).commit();
            }
        });
        FloatingActionButton button2 = view.findViewById(R.id.displayButton_l2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, new DisplayFragmentDetail()).commit();
            }
        });

        DisplayAdapterArray displayAdapter = new DisplayAdapterArray(generateData());
        recyclerView.setAdapter(displayAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


    }

    @Override
    public void onItemClick(int position) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, new DisplayFragmentDetail()).commit();
    }


    public void getDisplays() {
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

                if (response.isSuccessful()) {

                    Log.d(SUCCESS, status);
                    Toast.makeText(getContext(), status, Toast.LENGTH_SHORT).show();

                } else {
                    Log.d(ERROR, status);
                    Toast.makeText(getContext(), status, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Example>> call, Throwable t) {
                String error = t.getMessage();
                Log.d(ERROR, error);
                Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void postDisplay() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(IP_Kay_Mobil)
                .addConverterFactory(GsonConverterFactory
                        .create()).build();

        AnzeigenInterface anzeigenInterface = retrofit.create(AnzeigenInterface.class);


        String[] strings = {"vegan"};
        Data data = new Data(new Standort(51.018393, 7.567614), true, "Alles muss raus", strings, "Mein Kühlschrankinhalt muss ausgebraucht werden, da ich das aber nicht schaffe, möchte ich gerne alles verschenken");

        Call<Data> call3 = anzeigenInterface.postDisplayPost(data);

        call3.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                Toast.makeText(getContext(), "Yeah!" + response.code(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                Toast.makeText(getContext(), "Post konnte nicht ausgeführt werden" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public DisplayListItem[] generateData() {

        DisplayListItem[] displayListItems = {
                new DisplayListItem("Grillgut", "meat, fish", R.drawable.ic_launcher_background),
                new DisplayListItem("Fisch in Sauce Hollandaise", "laktose, fish", R.drawable.ic_launcher_background),
                new DisplayListItem("BBQ Chips", "meat", R.drawable.ic_launcher_background),
                new DisplayListItem("Tofu", "vegan", R.drawable.ic_launcher_background),
                new DisplayListItem("Birnen", "obst", R.drawable.ic_launcher_background),
                new DisplayListItem("Brokkoli", "gemüse", R.drawable.pic_brok)
        };
        return displayListItems;

    }


}

