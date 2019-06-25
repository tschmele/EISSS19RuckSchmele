package com.example.foodinprogress;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class DisplayAdapter extends RecyclerView.Adapter<DisplayAdapter.ViewHolder> {

    private DisplayListItem[] items;

    public DisplayAdapter(DisplayListItem[] items) {
        this.items = items;
    }

    @NonNull
    @Override
    public DisplayAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_recyclerview_displayitem, null);
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