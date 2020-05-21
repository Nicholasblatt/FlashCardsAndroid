package com.example.assign3flashcards;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class cardListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList<String> maintitle;

    public cardListAdapter(Activity context, ArrayList<String> maintitle) {
        super(context, R.layout.activity_my_list_adapter, maintitle);

        this.context=context;
        this.maintitle=maintitle;

    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.activity_my_list_adapter, null,true);

        TextView titleText = (TextView) rowView.findViewById(R.id.title);

        titleText.setText(maintitle.get(position));

        return rowView;

    };
}