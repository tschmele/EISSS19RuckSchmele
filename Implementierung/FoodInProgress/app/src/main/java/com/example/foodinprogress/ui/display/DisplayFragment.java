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
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodinprogress.DisplayAdapterListe;
import com.example.foodinprogress.R;
import com.example.foodinprogress.data.retrofit.AnzeigenInterface;
import com.example.foodinprogress.data.retrofit.Example;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.foodinprogress.util.Constants.ERROR;
import static com.example.foodinprogress.util.Constants.SUCCESS;

public class DisplayFragment extends Fragment /*implements DisplayAdapter.OnNoteListener */{


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);
        retroGETALL();
        buildRecyclerView(view);
        return view;
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

    public void buildRecyclerView(View view){
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_display);
        recyclerView.setHasFixedSize(true); // For the Performance
        LinearLayoutManager linearLayout = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayout);
/*
        FloatingActionButton button = view.findViewById(R.id.displayButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, new DisplayFragmentEdit()).commit();
            }
        });
*/
        DisplayAdapterListe displayAdapter = new DisplayAdapterListe(generateData());
        recyclerView.setAdapter(displayAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());



    }
/*
    @Override
    public void onItemClick(int position) {
        Log.d(TAG, "onItemClick: work?");
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, new DisplayFragmentDetail()).commit();
    }
*/
    public void retroGETALL(){
        final  TextView textViewResult = null;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://127.0.0.1:2000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        AnzeigenInterface api = retrofit.create(AnzeigenInterface.class);

        Call<List<Example>> call = api.getDisplayPosts();

        call.enqueue(new Callback<List<Example>>() {
            @Override
            public void onResponse(Call<List<Example>> call, Response<List<Example>> response) {
                String success = "Funktioniert";
                Log.d(SUCCESS, success);
                Toast.makeText(getContext(), success, Toast.LENGTH_SHORT).show();

                if(!response.isSuccessful()){
                    String result = "Status Code: " + response.code();

                    textViewResult.setText(result);
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

}