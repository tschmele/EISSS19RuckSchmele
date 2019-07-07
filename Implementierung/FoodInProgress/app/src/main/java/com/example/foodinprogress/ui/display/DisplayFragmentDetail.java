package com.example.foodinprogress.ui.display;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.foodinprogress.R;

public class DisplayFragmentDetail extends Fragment {
    private static final String TAG = "DisplayFragmentDetail";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_display_detail, container, false);
        Log.d(TAG, "onCreate started");
        setHardCodedDisplay(view);
        return view;
    }

    @SuppressLint("SetTextI18n")
    public void setHardCodedDisplay(View view){
        TextView textViewName = view.findViewById(R.id.textView_detail_name);
        textViewName.setText("Grillgut");
        TextView textViewDura = view.findViewById(R.id.textView_detail_durability);
        textViewDura.setText("27.07.2019");
        TextView textViewMobi = view.findViewById(R.id.textView_detail_mobility);
        textViewMobi.setText("Große Aluschale");
        TextView textViewCate = view.findViewById(R.id.textView_detail_category);
        textViewCate.setText("meat");
        TextView textViewAmou = view.findViewById(R.id.textView_detail_amount);
        textViewAmou.setText("5 Kilo");
        TextView textViewDesc = view.findViewById(R.id.textView_detail_description);
        textViewDesc.setText("Nach einer großen Feier ist ncoh viel Grillzeug übrig gebliegen.");




    }

}
