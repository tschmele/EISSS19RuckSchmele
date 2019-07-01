package com.example.foodinprogress.ui.display;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.foodinprogress.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import static com.example.foodinprogress.util.Constants.MAPVIEW_BUNDLE_KEY;

public class DisplayFragmentMap extends Fragment implements OnMapReadyCallback {

    private MapView mapView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_display_map, container, false);
        mapView = (MapView) view.findViewById(R.id.user_list_map);

        initGoogleMap(savedInstanceState);

        return view;
    }

    private void initGoogleMap(Bundle savedInstanceState){

        Bundle mapViewBundle = null;
        if (savedInstanceState != null){
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY);
        }
        mapView.onCreate(mapViewBundle);

        mapView.getMapAsync(this);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAPVIEW_BUNDLE_KEY, mapViewBundle);
        }

        mapView.onSaveInstanceState(mapViewBundle);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onPause() {
        mapView.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        mapView.onLowMemory();
        super.onLowMemory();
    }

/*
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.top_nav_menu, menu);

        menu.findItem(R.id.action_ic_map).setVisible(false);
        menu.findItem(R.id.action_ic_back).setVisible(false);
        menu.findItem(R.id.action_ic_search).setVisible(false);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.action_ic_row) {
            Toast.makeText(getActivity(), "Row", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

*/
}
