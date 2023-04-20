package com.example.listviews;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView l;
        l = (ListView) findViewById(R.id.listView);
        ArrayAdapter<CharSequence> a = ArrayAdapter.createFromResource(this, R.array.countries, android.R.layout.simple_list_item_1);
        l.setAdapter(a);

        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                i.putExtra("seat", position);
                startActivity(i);
            }

        });

    }
}
