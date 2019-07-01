package com.example.foodinprogress.ui.display;

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
import com.example.foodinprogress.data.retrofit.DisplayPost;
import com.example.foodinprogress.data.retrofit.JsonPlaceholderAPI;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.foodinprogress.util.Constants.ERROR;
import static com.example.foodinprogress.util.Constants.SUCCSESS;
import static com.example.foodinprogress.util.Constants.TAG;

public class DisplayFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_display2, container, false);
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
                Log.d(SUCCSESS, succsess);
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
            buildRecyclerView(view);


        return view;
    }


    private DisplayListItem[] generateData() {
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

    public void buildRecyclerView(View view){
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_display);
        recyclerView.setHasFixedSize(true); // For Performance
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

        DisplayAdapter displayAdapter = new DisplayAdapter(generateData());
        recyclerView.setAdapter(displayAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        displayAdapter.setOnItemClickListener(new DisplayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Log.d(TAG, "buildRV OnItemClick");

            }
        });
    }
}
