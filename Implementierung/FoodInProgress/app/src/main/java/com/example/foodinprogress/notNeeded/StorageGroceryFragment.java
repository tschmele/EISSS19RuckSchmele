package com.example.foodinprogress.notNeeded;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodinprogress.R;
import com.example.foodinprogress.ui.display.DisplayListItem;


public class StorageGroceryFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_storage, container, false);


        return view;
    }
/*
RecyclerView recyclerView = view.findViewById(R.id.recyclerView_grocery);
        LinearLayoutManager linearLayout =  new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayout);
        StorageAdapter storageAdapter = new StorageAdapter(generateData());
        recyclerView.setAdapter(storageAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.top_nav_menu, menu);

        menu.findItem(R.id.action_ic_row).setVisible(false);
        menu.findItem(R.id.action_ic_back).setVisible(false);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.action_ic_map) {
            Toast.makeText(getActivity(), "Map", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private DisplayListItem[] generateData(){
        DisplayListItem[] displayListItems = {
                new DisplayListItem("Pin", R.drawable.ic_pin),
                new DisplayListItem("Info", R.drawable.ic_information),
                new DisplayListItem("Pin", R.drawable.ic_pin),
                new DisplayListItem("Pin", R.drawable.ic_pin)

        };
        return displayListItems;

    }
*/
}
