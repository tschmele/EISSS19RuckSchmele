package com.example.foodinprogress.ui.display;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.foodinprogress.R;
import com.google.gson.Gson;

import java.util.Calendar;

import static com.example.foodinprogress.util.Constants.TAG;

public class DisplayFragmentEdit extends Fragment {

    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_display_detail_edit, container, false);

        final TextView textView = view.findViewById(R.id.textView_displayEdit_detail_durability);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDatePickerDialog(textView);
            }
        });
        Log.d(TAG, "Edit nach Text view");


        final Button buttonOk = view.findViewById(R.id.button_detail_displayEdit_ok);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson g = new Gson();
/*
                EditText viewName = view.findViewById(R.id.textView_displayEdit_detail_name);
                EditText viewMob = view.findViewById(R.id.textView_displayEdit_detail_mobility);
                EditText viewCat = view.findViewById(R.id.textView_displayEdit_detail_category);
                EditText viewAmount = view.findViewById(R.id.textView_displayEdit_detail_amount);
                EditText viewDec = view.findViewById(R.id.textView_detail_displayEdit_description);

                int[] date = separateStringIntoInt(textView.getText().toString());
                String[] strings = separateString(viewCat.getText().toString());



                /*
                setTitle(viewName.getText().toString());
                setAmount(viewAmount.getText().toString());
                setMobility(viewMob.getText().toString());
                setDecription(viewDec.getText().toString());
                setTags(strings);
                setDay(date[0]);
                setMonth(date[1]);
                setYear(date[2]);

                g.toJson(displayPost);
*/
                Toast.makeText(getContext(), "OK", Toast.LENGTH_LONG).show();
            }
        });

        final Button buttonCancel = view.findViewById(R.id.button_detail_displayEdit_cancel);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Cancel", Toast.LENGTH_LONG).show();
            }
        });

        final ImageView imageViewCamera = view.findViewById(R.id.icon_display_camera);
        imageViewCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Camera", Toast.LENGTH_LONG).show();
            }
        });

        final ImageView imageViewUpload = view.findViewById(R.id.icon_display_upload);
        imageViewUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Upload", Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    private void setDatePickerDialog(final TextView textView) {

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

                String date = day + "." + month + "." + year;

                Log.d(TAG, "onDateSet: DD/MM/YYYY: " + date);

                textView.setText(date);

            }
        };

    }

    private int[] separateStringIntoInt(String string){

        String[] dateInString = string.split("\\.");

        int[] dateInInt = new int[dateInString.length];

            for (int i = 0; i < dateInString.length; i++) {
                dateInInt[i] = Integer.parseInt(dateInString[i]);
                Log.d("Hier", "String : " + dateInString[i]);
                Log.d("Hier", "Int: " + dateInInt[i]);
            }

        return dateInInt;
    }

    private String[] separateString(String string){

        String[] tagsInStrings = string.split(", ");

        String[] tags = new String[tagsInStrings.length];

        for(int i = 0; i < tagsInStrings.length; i++){
            tags[i] = tagsInStrings[i];
        }

        return tags;
    }

}
