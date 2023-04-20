package com.example.exorestaurant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


public class AddActivity extends AppCompatActivity {

    ProgressBar pb;
    EditText n, p;
    Spinner s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_add);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pb = findViewById();
        pb.setVisibility(View.INVISIBLE);
        n = findViewById(R.id.editname);
        p = findViewById(R.id.editprice);
        s = findViewById(R.id.spinner);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected (@NonNull MenuItem item){
        if (item.getItemId() == R.id.save) {
                pb.setVisibility(View.VISIBLE);
                String name = n.getText().toString();
                String price = p.getText().toString();
                String type = s.getSelectedItem().toString();
                if (name.equals("") || price.equals("")) {
                    Toast.makeText(getApplicationContext(), "Empty fields!", Toast.LENGTH_SHORT).show();
                } else {
                    String url = "http://10.0.2.2/restaurantapp/adddish.php?name=%22+name+%22&price=" + price + "&type=" + type;
                    RequestQueue queue = Volley.newRequestQueue(this);
                    StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                            pb.setVisibility(View.INVISIBLE);
                            n.setText("");
                            p.setText("");
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), "Error:" + error.toString(), Toast.LENGTH_SHORT).show();
                            pb.setVisibility(View.INVISIBLE);
                        }
                    });
                    queue.add(request);
                }
            }
            return super.onOptionsItemSelected(item);
        }
    }