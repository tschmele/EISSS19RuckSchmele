package com.example.foodinprogress;

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

import com.example.foodinprogress.data.retrofit.DisplayPost;
import com.example.foodinprogress.data.retrofit.JsonPlaceholderAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import static com.example.foodinprogress.util.Constants.ERROR;
import static com.example.foodinprogress.util.Constants.SUCCSESS;

public class DisplayFragment extends Fragment {

    private TextView textViewResult;

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
        ViewPager viewPager = view.findViewById(R.id.view_pager);
        setupViewPager(viewPager);
        TabLayout tabs = view.findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
*/

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://127.0.0.1:2000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceholderAPI jsonPlaceholderAPI = retrofit.create(JsonPlaceholderAPI.class);

        Call<List<DisplayPost>> call = jsonPlaceholderAPI.getDisplayPosts();

        call.enqueue(new Callback<List<DisplayPost>>() {
            @Override
            public void onResponse(Call<List<DisplayPost>> call, Response<List<DisplayPost>> response) {
                String succsess = (String) t.getMessage();
                Log.d(SUCCSESS, error);
                Toast.makeText(getContext(), Su , Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<List<DisplayPost>> call, Throwable t) {

                String error = (String) t.getMessage();
                Log.d(ERROR, error);
                Toast.makeText(getContext(), error , Toast.LENGTH_SHORT).show();

            }
        });

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_display);
        LinearLayoutManager linearLayout = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayout);
        DisplayAdapter displayAdapter = new DisplayAdapter(generateData());
        recyclerView.setAdapter(displayAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return view;
    }

    /*
        @Override
        public boolean onOptionsItemSelected(MenuItem item){
            int id = item.getItemId();

            if(id == R.id.action_ic_map) {
                Toast.makeText(getActivity(), "Map", Toast.LENGTH_SHORT).show();
            }
            return super.onOptionsItemSelected(item);
        }

    */
    public DisplayListItem[] generateData() {
        DisplayListItem[] displayListItems = {
                new DisplayListItem("Back", R.drawable.ic_back),
                new DisplayListItem("Row", R.drawable.ic_rows),
                new DisplayListItem("Pin", R.drawable.ic_pin),
                new DisplayListItem("Info", R.drawable.ic_information)
        };
        return displayListItems;

    }
}
