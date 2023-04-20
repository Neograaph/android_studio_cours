package com.example.customlistviews;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class testingalertdialog extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testingalertdialog);
    }

    public void show_dialog(View v){
        AlertDialog.Builder mb = new AlertDialog.Builder(testingalertdialog.this);
        mb.setIcon(R.drawable.ic_launcher_background);
        mb.setTitle("Confirm");
        mb.setMessage("Are you sure you want to go to Custom list activity?");
        mb.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
            Log.d("testingalertdialog", "Yes has been clicked");
            Toast.makeText(getApplicationContext(),"Yes has been clicked", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(testingalertdialog.this, MainActivity.class);
            startActivity(i);}} );

            mb.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d("testingalertdialog", "No has been clicked");
                Toast.makeText(getApplicationContext(),"No has been clicked", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(testingalertdialog.this, testingalertdialog.class);
                startActivity(i);
            //dialog.dismiss();
            }});
            mb.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d("testingalertdialog", "cancel has been clicked");
                Toast.makeText(getApplicationContext(),"Cancel has been clicked", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }});
            Dialog d = mb.create();
            d.show();
        }

    }