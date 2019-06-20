package com.example.foodinprogress;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class CustomListview extends ArrayAdapter<String> {

    private String[] name;
    private String[] amount;
    private String[] user;
    private Integer[] img;

    private Activity context;

    public CustomListview(@NonNull Activity context, String[] name, String[] amount, String[] user, Integer[] img) {
        super(context, R.layout.listview_layout, name);

        this.context = context;
        this.name = name;
        this.amount = amount;
        this.user = user;
        this.img = img;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
       View view = convertView;
       ViewHolder viewHolder;

       if(view == null){
           LayoutInflater layoutInflater = context.getLayoutInflater();
           view = layoutInflater.inflate(R.layout.listview_layout, null, true);
           viewHolder = new ViewHolder(view);
           view.setTag(viewHolder);
       }
       else {
           viewHolder = (ViewHolder) view.getTag();
       }

       /*
       TODO: Change the hardcoded Code to Code with Data from the Database
        */
       viewHolder.imageView.setImageResource(img[position]);
       viewHolder.textView1.setText(name[position]);
       viewHolder.textView2.setText(amount[position]);
       viewHolder.textView3.setText(user[position]);

       return view;
    }

    class ViewHolder
    {
        TextView textView1;
        TextView textView2;
        TextView textView3;
        ImageView imageView;

        ViewHolder(View v)
        {
            textView1 = v.findViewById(R.id.textView_name);
            textView2 = v.findViewById(R.id.textView_amout);
            textView3 = v.findViewById(R.id.textView_user);
            imageView = v.findViewById(R.id.imageView);
        }


    }

}
