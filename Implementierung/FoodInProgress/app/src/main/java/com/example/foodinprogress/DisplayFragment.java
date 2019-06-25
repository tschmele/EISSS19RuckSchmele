package com.example.foodinprogress;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class DisplayFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_display, container, false);
    }

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

    /*
    private List<DisplayListItem> displayListItems = new ArrayList<>();

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        Log.d("HIER", "Funktioiert nach view und vor erstellen recyclerView");
        RecyclerView recyclerView;
        recyclerView = view.findViewById(R.id.recyc_display);

        Log.d("HIER", "Funktioiert nach recyclerView und vor init adapter\"");
        DisplayAdapter displayAdapter = new DisplayAdapter(displayListItems);
        Log.d("HIER", "Funktioiert nach init adapter und vor SetLayoutmanager\"");


        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);

        Log.d("HIER", "Funktioiert nach adakter und vor Item\"");

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(displayAdapter);
        prepareData();

    }

    private void prepareData(){
        DisplayListItem displayListItem0 = new DisplayListItem("Erdbeere", "2 Kilo", "Jens Hegenkranz");
        DisplayListItem displayListItem1 = new DisplayListItem("Kartoffel", "10 Kilo", "Rabenstolz");
        DisplayListItem displayListItem2 = new DisplayListItem("Pflaumen", "500 Gramm", "DerDeca");
        DisplayListItem displayListItem3 = new DisplayListItem("Wassermelone", "5 Stück", "Joschi");
        DisplayListItem displayListItem4 = new DisplayListItem("Zitronen", "15 Stück", "Seneake");

        displayListItems.add(displayListItem0);
        displayListItems.add(displayListItem1);
        displayListItems.add(displayListItem2);
        displayListItems.add(displayListItem3);
        displayListItems.add(displayListItem4);
    }
    */

}
