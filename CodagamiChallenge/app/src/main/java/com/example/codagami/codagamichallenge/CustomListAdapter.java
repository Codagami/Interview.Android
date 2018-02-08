package com.example.codagami.codagamichallenge;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jakedunahee on 2/7/18.
 */

public class CustomListAdapter extends ArrayAdapter {

    public CustomListAdapter(Context context, int textViewResourceId, List<Person> people) {
        super(context, textViewResourceId);
    }


    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            LayoutInflater vi = LayoutInflater.from(getContext());
            view = vi.inflate(R.layout.custom_list_layout, null);
        }

        return view;
    }
}
