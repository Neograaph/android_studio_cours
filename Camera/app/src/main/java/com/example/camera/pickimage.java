package com.example.camera;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class pickimage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickimage);
        FloatingActionButton fab = findViewById(R.id.fab);
        ImageView imageView = findViewById(R.id.imView);

        fab.setOnClickListener(new View.OnClickListener() {
            //preparing the activity launcher to be used for starting a new activity with result
            ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            if (result.getResultCode() == Activity.RESULT_OK && result.getData()!=null) {
                                // If we receive back some data, we can start working on it
                                Toast.makeText(pickimage.this, "We have a result", Toast.LENGTH_SHORT).show();
                                Uri URI = result.getData().getData(); //The URI contains the position of the image in the media folder
                                Log.d("pickimage_URI", URI.toString());
                                String[] FILE = { MediaStore.Images.Media.DATA };
                                Cursor cursor = getContentResolver().query(URI, FILE, null, null, null);
                                cursor.moveToFirst();
                                int columnIndex = cursor.getColumnIndex(FILE[0]);
                                String imageDecode = cursor.getString(columnIndex); // this is the absolute path of the image
                                Log.d("pickimage_imagedecode", imageDecode);
                                // it might be useful sometimes
                                // in our case, we will use it to decode the image to display it
                                cursor.close();
                                imageView.setImageBitmap(BitmapFactory.decodeFile(imageDecode));

                            }
                        }
                    });

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                someActivityResultLauncher.launch(intent);
            }
        });
    }
}