package com.example.camera;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class high_quality_camera extends AppCompatActivity {

    Bitmap taken_picture;
    // Define the button and imageview type variable
    Button camera_open_id;
    ImageView click_image_id;
    Button picture_save_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_quality_camera);

        // By ID we can get each component which id is assigned in XML file get Buttons and imageview.
        camera_open_id = findViewById(R.id.camera_button_new);
        click_image_id = findViewById(R.id.click_image_new);
        picture_save_id = findViewById(R.id.save_image_button);

        // You can do the assignment inside onAttach or onCreate, i.e, before the activity is displayed
        ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                            // If we receive back some data, we can start working on it
                            Bundle bundel = result.getData().getExtras();
                            taken_picture = (Bitmap) bundel.get("data");
                            click_image_id.setImageBitmap(taken_picture);
                        }
                    }
                });

        camera_open_id.setOnClickListener(view -> {
            Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            someActivityResultLauncher.launch(camera_intent);
        });

        //let's create the listener for the Save picture button
        picture_save_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Let's check the permissions first
                askforwritepermission();
            }
        });

    }

    public void askforwritepermission(){
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            {
                Toast.makeText(this, "Permission already granted", Toast.LENGTH_SHORT).show();
                saveImage();
            }
        }
        else  // permission already granted previously
        { // the code you wanted to do.
            Toast.makeText(this, "Granting permission", Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 101);
        }
    }

    @Override
    public void onRequestPermissionsResult (int requestCode, String[] permissions, int[] grantResults) {

        if (requestCode == 101) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                saveImage();
            }
            else {
                Toast.makeText(this, "Please provide required permission", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void saveImage()
    {
        String filename = "name.png";
        File directory = new File (Environment.getExternalStorageDirectory()+"/download");
        if (!directory.exists())
        {
            directory.mkdir();
        }
        try{
            File file = new File (directory,"myimage.jpg");
            FileOutputStream out = new FileOutputStream(file);
            taken_picture.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
            //making the photo appear in the gallery
            getApplicationContext().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}