package com.example.codagami.codagamichallenge.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.codagami.codagamichallenge.CustomLayouts.CustomListAdapter;
import com.example.codagami.codagamichallenge.R;

public class MainActivity extends AppCompatActivity {

    private ListView peopleList;
    private CustomListAdapter listAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
