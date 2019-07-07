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


import static com.example.foodinprogress.util.Constants.TAG;

public class DisplayAdapterNode extends RecyclerView.Adapter<DisplayAdapterNode.ViewHolder> {



    private DisplayListItem[] items;
    private OnNoteListener noteListener;

    public DisplayAdapterNode(DisplayListItem[] items, OnNoteListener onNoteListener) {
        this.items = items;
        this.noteListener = onNoteListener;
    }

    @NonNull
    @Override
    public DisplayAdapterNode.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_recyclerview_displayitem, parent, false);
        return new ViewHolder(view, noteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.textView.setText(items[position].getTitle());
        viewHolder.textViewTags.setText(items[position].getTags());
        viewHolder.imageView.setImageResource(items[position].getImageURL());
    }

    @Override
    public int getItemCount() {
        return items.length;
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textView;
        TextView textViewTags;
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
