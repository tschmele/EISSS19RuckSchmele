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

import java.util.ArrayList;


public class DisplayAdapter extends RecyclerView.Adapter<DisplayAdapter.ViewHolder> {

    private static final String TAG = "DispalyAdapter";
    private ArrayList<DisplayListItem> items;
    //private OnNoteListener onItemClickListener;

    private OnNoteListener noteListener;
/*
    public void setOnItemClickListener(OnNoteListener listener){
        onItemClickListener = listener;
    }
*/
    public DisplayAdapter(ArrayList<DisplayListItem> items, OnNoteListener onNoteListener) {
        this.items = items;
        this.noteListener = onNoteListener;
    }

    @NonNull
    @Override
    public DisplayAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_recyclerview_displayitem, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, noteListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.textView.setText(items.get(position).getTitle());
        viewHolder.imageView.setImageResource(items.get(position).getImageURL());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textView;
        ImageView imageView;

        OnNoteListener onNoteListener;

        ViewHolder(View itemLayoutView, final OnNoteListener listener) {
            super(itemLayoutView);
            textView = itemLayoutView.findViewById(R.id.item_title);
            imageView = itemLayoutView.findViewById(R.id.item_icon);
            this.onNoteListener = listener;

            itemLayoutView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.d(TAG, "View Holder On Click");

            onNoteListener.onItemClick(getAdapterPosition());

        }
    }

    public interface OnNoteListener {
        void onItemClick(int position);
    }

}


    /*
    private List<DisplayListItem> displayList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView name, amount, category;

        private MyViewHolder(View view) {
            super(view);

            name = view.findViewById(R.id.relative_display_name);
            amount = view.findViewById(R.id.relative_display_amount);
            category = view.findViewById(R.id.relative_display_category);

        }
    }


    public DisplayAdapter(List<DisplayListItem> displayList) {
        this.displayList = displayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_recyclerview_displayitem, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        DisplayListItem displayItem = displayList.get(position);
        holder.name.setText(displayItem.getName());
        holder.amount.setText(displayItem.getAmount());
        holder.category.setText(displayItem.getCategory());
    }

    @Override
    public int getItemCount() {
        return displayList.size();
    }
    */