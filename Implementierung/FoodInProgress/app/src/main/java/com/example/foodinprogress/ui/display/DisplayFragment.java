package com.example.foodinprogress.ui.display;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodinprogress.R;
import com.example.foodinprogress.data.retrofit.DisplayPost;
import com.example.foodinprogress.data.retrofit.JsonPlaceholderAPI;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.foodinprogress.util.Constants.ERROR;
import static com.example.foodinprogress.util.Constants.SUCCESS;
import static com.example.foodinprogress.util.Constants.TAG;

public class DisplayFragment extends Fragment implements DisplayAdapter.OnNoteListener {

    private TextView textViewResult;

    private ArrayList<DisplayListItem> displayListItems = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_display2, container, false);
        retroGETALL();
        buildRecyclerView(view);
        return view;
    }


    private ArrayList<DisplayListItem> generateData() {
        displayListItems.add(new DisplayListItem("Back", R.drawable.ic_back));
        displayListItems.add(new DisplayListItem("Row", R.drawable.ic_rows));
        displayListItems.add(new DisplayListItem("Pin", R.drawable.ic_pin));
        displayListItems.add(new DisplayListItem("Info", R.drawable.ic_information));

        displayListItems.add(new DisplayListItem("Back", R.drawable.ic_back));
        displayListItems.add(new DisplayListItem("Row", R.drawable.ic_rows));
        displayListItems.add(new DisplayListItem("Pin", R.drawable.ic_pin));
        displayListItems.add(new DisplayListItem("Info", R.drawable.ic_information));

        displayListItems.add(new DisplayListItem("Back", R.drawable.ic_back));
        displayListItems.add(new DisplayListItem("Row", R.drawable.ic_rows));
        displayListItems.add(new DisplayListItem("Pin", R.drawable.ic_pin));
        displayListItems.add(new DisplayListItem("Info", R.drawable.ic_information));
        return displayListItems;

    }

    public void buildRecyclerView(View view){
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_display);
        recyclerView.setHasFixedSize(true); // For the Performance
        LinearLayoutManager linearLayout = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayout);

        FloatingActionButton button = view.findViewById(R.id.displayButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, new DisplayFragmentEdit()).commit();
            }
        });

        DisplayAdapter displayAdapter = new DisplayAdapter(generateData(), this);
        recyclerView.setAdapter(displayAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());



    }

    @Override
    public void onItemClick(int position) {
        Log.d(TAG, "onItemClick: work?");
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, new DisplayFragmentDetail()).commit();
    }

    public void retroGETALL(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://127.0.0.1:2000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceholderAPI jsonPlaceholderAPI = retrofit.create(JsonPlaceholderAPI.class);

        Call<List<DisplayPost>> call = jsonPlaceholderAPI.getDisplayPosts();

        call.enqueue(new Callback<List<DisplayPost>>() {
            @Override
            public void onResponse(Call<List<DisplayPost>> call, Response<List<DisplayPost>> response) {
                String success = "Funktioniert";
                Log.d(SUCCESS, success);
                Toast.makeText(getContext(), success, Toast.LENGTH_SHORT).show();

                if(!response.isSuccessful()){
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                List<DisplayPost> displayPosts = response.body();

                for (DisplayPost displayPost : displayPosts){
                    String content = "";
                    content += "ID: " + displayPost.getId() + "\n";
                    content += " Autor: " + displayPost.getAuthor() + "\n";

                }


            }

            @Override
            public void onFailure(Call<List<DisplayPost>> call, Throwable t) {

                String error = t.getMessage();
                Log.d(ERROR, error);
                Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();

            }
        });
    }

}

/*
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://127.0.0.1:2000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceholderAPI jsonPlaceholderAPI = retrofit.create(JsonPlaceholderAPI.class);

        Call<List<DisplayPost>> call = jsonPlaceholderAPI.getDisplayPosts();

        call.enqueue(new Callback<List<DisplayPost>>() {
            @Override
            public void onResponse(Call<List<DisplayPost>> call, Response<List<DisplayPost>> response) {
                String succsess = "Funktioniert";
                Log.d(SUCCESS, succsess);
                Toast.makeText(getContext(), succsess, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<List<DisplayPost>> call, Throwable t) {

                String error = t.getMessage();
                Log.d(ERROR, error);
                Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();

            }
        });

*/
