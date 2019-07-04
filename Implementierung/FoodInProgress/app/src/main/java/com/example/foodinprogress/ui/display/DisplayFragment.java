package com.example.foodinprogress.ui.display;

import android.app.ProgressDialog;
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
import com.example.foodinprogress.R;
import com.example.foodinprogress.dispalydata.AnzeigenInterface;
import com.example.foodinprogress.dispalydata.Anzeigenliste;
import com.example.foodinprogress.dispalydata.Example;
import com.example.foodinprogress.dispalydata.RetroClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.Objects;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DisplayFragment extends Fragment implements DisplayAdapter.OnNoteListener {

    private ArrayList<Example> anzeigenliste;
    private RecyclerView recyclerView;

    private ProgressDialog pDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_display2, container, false);

        pDialog = new ProgressDialog(getContext());
        pDialog.setMessage("Loading Data... Please wait... ");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        AnzeigenInterface api = RetroClient.getAnzeigenService();
        final DisplayFragment current = this;


        //Calling JSON
        Call<Anzeigenliste> call = api.getAnzeigenJSON();

        call.enqueue(new Callback<Anzeigenliste>() {

            @Override
            public void onResponse(Call<Anzeigenliste> call, Response<Anzeigenliste> response) {
                pDialog.dismiss();

                if (response.isSuccessful()) {
                    anzeigenliste = Objects.requireNonNull(response.body()).getExamples();
                    recyclerView = view.findViewById(R.id.recyclerView_display);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                    recyclerView.setLayoutManager(layoutManager);


                    FloatingActionButton button = view.findViewById(R.id.displayButton);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            FragmentTransaction fragmentTransaction = Objects.requireNonNull(getFragmentManager()).beginTransaction();
                            fragmentTransaction.replace(R.id.fragment_container, new DisplayFragmentEdit()).commit();
                        }
                    });
                    DisplayAdapter adapter = new DisplayAdapter(anzeigenliste, current);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                }
            }

            @Override
            public void onFailure(Call<Anzeigenliste> call, Throwable t) {

            }
        });


        return view;
    }

    @Override
    public void onItemClick(int position) {

    }
}