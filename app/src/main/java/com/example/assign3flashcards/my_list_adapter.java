package com.example.assign3flashcards;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class my_list_adapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] maintitle;
    private Integer[] imgid;

    public my_list_adapter(Activity context, String[] maintitle, Integer[] imgid) {
        super(context, R.layout.activity_my_list_adapter, maintitle);

        this.context=context;
        this.maintitle=maintitle;
        this.imgid=imgid;

    }
    public my_list_adapter(Activity context, String[] maintitle) {
        super(context, R.layout.activity_my_list_adapter, maintitle);

        this.context=context;
        this.maintitle=maintitle;

    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.activity_my_list_adapter, null,true);

        TextView titleText = (TextView) rowView.findViewById(R.id.title);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);

        titleText.setText(maintitle[position]);
        imageView.setImageResource(imgid[position]);

        return rowView;

    };
}