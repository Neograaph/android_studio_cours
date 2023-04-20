package com.example.listviews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        String [] data = new String[] {"France","Lebanon", "Germany", "Jordan", "Sweden", "UAE","USA","Belgium","Finland","Brazil","UAE","KSA"};
        ListView l= findViewById(R.id.listView2);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data);
        l.setAdapter(adapter);
    }
}