package com.example.foodinprogress.ui.display;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodinprogress.R;
import com.example.foodinprogress.data.retrofit.Example;

import java.util.ArrayList;
import java.util.List;

//import android.widget.ImageView;


public class DisplayAdapter extends RecyclerView.Adapter<DisplayAdapter.ViewHolder> {

    private static final String TAG = "DispalyAdapter";
    // private ArrayList<DisplayListItem> items;
    private List<Example> anzeigen;

   // private OnNoteListener noteListener;

    public DisplayAdapter(List<Example> anzeigen/*, OnNoteListener onNoteListener*/) {
        //this.items = items;
        this.anzeigen = anzeigen;
       // this.noteListener = onNoteListener;
    }

    @NonNull
    @Override
    public DisplayAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_recyclerview_displayitem, parent, false);
        return new ViewHolder(view/*, noteListener*/);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Example anzeige = anzeigen.get(position);
        viewHolder.textView.setText(anzeige.getAnzeigen().get(position).getId());
        viewHolder.imageView.setImageResource(R.mipmap.ic_launcher);
    }

    @Override
    public int getItemCount() {
        return anzeigen.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textView;
        ImageView imageView;

        //OnNoteListener onNoteListener;

        ViewHolder(View itemLayoutView/*, final OnNoteListener listener*/) {
            super(itemLayoutView);
            textView = itemLayoutView.findViewById(R.id.item_title);
            imageView = itemLayoutView.findViewById(R.id.item_icon);
            //this.onNoteListener = listener;

            itemLayoutView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.d(TAG, "View Holder On Click");
  //          onNoteListener.onItemClick(getAdapterPosition());

        }
    }
/*
    public interface OnNoteListener {
        void onItemClick(int position);
    }
*/
}

