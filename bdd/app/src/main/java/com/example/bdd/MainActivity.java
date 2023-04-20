package com.example.bdd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {
    TextView mytextview;
    Button mybutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mytextview = findViewById(R.id.textviewid);
        mybutton = findViewById(R.id.button);

        mybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                call_server();
            }
        });
    }

    public void call_server()
    {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2/volley/simple_hello.php?val=helloThere";
        StringRequest stringRequest =
                new StringRequest(Request.Method.GET,
                        url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response)
                            { mytextview.setText(response.toString()); }
                        },

                        new Response.ErrorListener()  {
                            @Override
                            public void onErrorResponse(VolleyError error)
                            { mytextview.setText("the error is:" + error.toString()); }});

        queue.add(stringRequest);

    }
}