package com.example.listviews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView t = (TextView) findViewById(R.id.textView);
        Intent i = getIntent();
        int pos = i.getIntExtra("seat",-1);
        if (pos!=-1){
            t.setText(getResources().getTextArray(R.array.countries)[pos]);
        }
    }
}