package com.example.foodinprogress;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class InformationFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_informatioon, null);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }
/*
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.action_ic_back) {
            Toast.makeText(getActivity(), "back", Toast.LENGTH_SHORT).show();
        }
        if(id == R.id.action_ic_search){
            //Toast.makeText(getActivity(), "search", Toast.LENGTH_SHORT).show();
            item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    setSearchDialog();
                    Toast.makeText(getContext(), "In Search", Toast.LENGTH_SHORT).show();
                    return true;
                }
            });
        }
        return super.onOptionsItemSelected(item);
    }
*/
    public void setSearchDialog(){
        SearchDialog dialog = new SearchDialog();
        dialog.show(getFragmentManager(), "Search Dialog" );

    }



}
