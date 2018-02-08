package com.example.codagami.codagamichallenge.CustomLayouts;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.codagami.codagamichallenge.Objects.Person;
import com.example.codagami.codagamichallenge.R;

import java.util.List;

/**
 * Created by jakedunahee on 2/7/18.
 */

public class CustomListAdapter extends ArrayAdapter<Person> {

    private List<Person> mPersonList;

    public CustomListAdapter(Context context, int textViewResourceId, List<Person> personList) {
        super(context, textViewResourceId);
        mPersonList = personList;
    }

    @Override
    public int getCount() {
        return mPersonList.size();
    }

    @Nullable
    @Override
    public Person getItem(int position) {
        return mPersonList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater vi = LayoutInflater.from(getContext());
            view = vi.inflate(R.layout.custom_list_layout, null);
        }
        bindView(position, view);
        return view;
    }

    private void bindView(int position, View view) {
        Person person = mPersonList.get(position);
        if (person == null) {
            Log.e("bindView error", "Person we are trying to bind at index " + position + " is null");
            return;
        }

        TextView fullName = (TextView) view.findViewById(R.id.tvFullName);
        TextView age = (TextView) view.findViewById(R.id.tvAge);
        fullName.setText(
                new StringBuffer()
                        .append(person.getFirstName())
                        .append(" ")
                        .append(person.getLastName()));
        age.setText(person.getAge());
    }
}
