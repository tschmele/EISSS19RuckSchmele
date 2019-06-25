package com.example.foodinprogress;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Calendar;

public class EditDisplayFragment extends Fragment {

    private final static String TAG = "Hier";
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_display_detail_edit, container, false);

        final TextView textView = view.findViewById(R.id.textView_detail_durability_edit);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDatePickerDialog(textView);
            }
        });

        final Spinner spinner = view.findViewById(R.id.spinner_display_detail_edit);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.my_spinner,
               getResources().getStringArray( R.array.spinner_elements));
        spinner.setAdapter(adapter);
        return view;
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
        menu.findItem(R.id.action_ic_back).setVisible(false);


        super.onCreateOptionsMenu(menu, inflater);
    }

    public void setDatePickerDialog(final TextView textView){

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                // TODO: Irgendwie die Warinings raus bekommen!!

                DatePickerDialog dialog = new DatePickerDialog(getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);

                ColorDrawable colorDrawable = new ColorDrawable(Color.TRANSPARENT);

                dialog.getWindow().setBackgroundDrawable(colorDrawable);
                dialog.show();

            }
        });

        /*
        Variable month muss hier um eines erhöht werden, damit der richtige Monat ausgegen wird
        Ohne Erhöhung: Januar 0 / Februar 1 / März 2 / etc.
        Mit Erhöhung:  Januar 1 / Februar 2 / März 3 / etc.
        */
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;

                String date = day + " / " + month + " / " + year;
                Log.d(TAG, "onDateSet: DD/MM/YYYY: " + date);

                textView.setText(date);

            }
        };

    }

}
