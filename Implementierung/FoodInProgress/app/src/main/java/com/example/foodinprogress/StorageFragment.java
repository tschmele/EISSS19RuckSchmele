package com.example.foodinprogress;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodinprogress.ui.display.DisplayListItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class StorageFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_storage, container, false);

        buildRecyclierView(view);

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

    private void buildRecyclierView(View view){
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_storage);
        LinearLayoutManager linearLayout = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayout);

        FloatingActionButton button = view.findViewById(R.id.storage_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, new EditStorageFragment()).commit();
            }
        });

        FloatingActionButton buttonGrocery = view.findViewById(R.id.grocery_button);
        buttonGrocery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, new EditGroceryFragment()).commit();
            }
        });
        StorageAdapter storageAdapter = new StorageAdapter(generateData());
        recyclerView.setAdapter(storageAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

}


