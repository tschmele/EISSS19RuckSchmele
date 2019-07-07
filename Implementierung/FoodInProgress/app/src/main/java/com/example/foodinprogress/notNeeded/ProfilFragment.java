package com.example.foodinprogress.notNeeded;

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

import com.example.foodinprogress.R;

public class ProfilFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profil, null);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.top_nav_menu, menu);

        menu.findItem(R.id.action_ic_map).setVisible(false);
        menu.findItem(R.id.action_ic_row).setVisible(false);
        menu.findItem(R.id.action_ic_search).setVisible(false);
        menu.findItem(R.id.action_ic_profil_pic).setVisible(false);


        super.onCreateOptionsMenu(menu, inflater);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.action_ic_back) {
            Toast.makeText(getActivity(), "back", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }


}
