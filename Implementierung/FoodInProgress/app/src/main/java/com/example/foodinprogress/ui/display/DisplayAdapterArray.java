package com.example.foodinprogress;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodinprogress.ui.display.DisplayListItem;


public class DisplayAdapterArray extends RecyclerView.Adapter<DisplayAdapterArray.ViewHolder> {

    private DisplayListItem[] items;

    public DisplayAdapterArray(DisplayListItem[] items) {
        this.items = items;
    }

    @NonNull
    @Override
    public DisplayAdapterArray.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_recyclerview_storageitem, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.textView.setText(items[position].getTitle());
        viewHolder.imageView.setImageResource(items[position].getImageURL());
    }

    @Override
    public int getItemCount() {
        return items.length;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;

        ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            textView = itemLayoutView.findViewById(R.id.item_title);
            imageView = itemLayoutView.findViewById(R.id.item_icon);


        }
    }


}