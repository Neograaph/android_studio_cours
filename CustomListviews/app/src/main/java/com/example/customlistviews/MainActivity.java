package com.example.customlistviews;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView list;

        String[] maintitle ={
                "Airplane", "Car", "Train", "Bike","Airplane", "Car", "Train", "Bike","Airplane", "Car", "Train", "Bike","Airplane", "Car", "Train", "Bike","Airplane", "Car", "Train", "Bike","Airplane", "Car", "Train", "Bike","Airplane", "Car", "Train", "Bike","Airplane", "Car", "Train", "Bike","Airplane", "Car", "Train", "Bike","Airplane", "Car", "Train", "Bike","Airplane", "Car", "Train", "Bike","Airplane", "Car", "Train", "Bike"
        };

        String[] subtitle ={
                "T1","T2",
                "T3","T4","T1","T2",
                "T3","T4","T1","T2",
                "T3","T4","T1","T2",
                "T3","T4","T1","T2",
                "T3","T4","T1","T2",
                "T3","T4","T1","T2",
                "T3","T4","T1","T2",
                "T3","T4","T1","T2",
                "T3","T4","T1","T2",
                "T3","T4","T1","T2",
                "T3","T4","T1","T2",
                "T3","T4"
        };

        Integer[] imgid={
                R.drawable.ic_baseline_airplanemode_active_24, R.drawable.ic_baseline_directions_car_24, R.drawable.ic_baseline_train_24, R.drawable.ic_baseline_electric_bike_24,R.drawable.ic_baseline_airplanemode_active_24, R.drawable.ic_baseline_directions_car_24, R.drawable.ic_baseline_train_24, R.drawable.ic_baseline_electric_bike_24,R.drawable.ic_baseline_airplanemode_active_24, R.drawable.ic_baseline_directions_car_24, R.drawable.ic_baseline_train_24, R.drawable.ic_baseline_electric_bike_24,R.drawable.ic_baseline_airplanemode_active_24, R.drawable.ic_baseline_directions_car_24, R.drawable.ic_baseline_train_24, R.drawable.ic_baseline_electric_bike_24,R.drawable.ic_baseline_airplanemode_active_24, R.drawable.ic_baseline_directions_car_24, R.drawable.ic_baseline_train_24, R.drawable.ic_baseline_electric_bike_24,R.drawable.ic_baseline_airplanemode_active_24, R.drawable.ic_baseline_directions_car_24, R.drawable.ic_baseline_train_24, R.drawable.ic_baseline_electric_bike_24, R.drawable.ic_baseline_airplanemode_active_24, R.drawable.ic_baseline_directions_car_24, R.drawable.ic_baseline_train_24, R.drawable.ic_baseline_electric_bike_24,R.drawable.ic_baseline_airplanemode_active_24, R.drawable.ic_baseline_directions_car_24, R.drawable.ic_baseline_train_24, R.drawable.ic_baseline_electric_bike_24,R.drawable.ic_baseline_airplanemode_active_24, R.drawable.ic_baseline_directions_car_24, R.drawable.ic_baseline_train_24, R.drawable.ic_baseline_electric_bike_24,R.drawable.ic_baseline_airplanemode_active_24, R.drawable.ic_baseline_directions_car_24, R.drawable.ic_baseline_train_24, R.drawable.ic_baseline_electric_bike_24,R.drawable.ic_baseline_airplanemode_active_24, R.drawable.ic_baseline_directions_car_24, R.drawable.ic_baseline_train_24, R.drawable.ic_baseline_electric_bike_24,R.drawable.ic_baseline_airplanemode_active_24, R.drawable.ic_baseline_directions_car_24, R.drawable.ic_baseline_train_24, R.drawable.ic_baseline_electric_bike_24
        };
        MyListAdapter adapter=new MyListAdapter(this, maintitle, subtitle,imgid);
        list=(ListView)findViewById(R.id.list);
        Log.d("customlistview2", "before set adapter");
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                //Toast.makeText(getApplicationContext(),"Row number "+position+" has been clicked",Toast.LENGTH_SHORT).show();
                //Toast.makeText(getApplicationContext(),"We have "+list.getCount()+" items",Toast.LENGTH_SHORT).show();

                //just to have fun:
                //changing the first item's image when we click on any of the rows
                imgid[0]=R.drawable.ic_baseline_train_24;
                adapter.notifyDataSetChanged(); //check the difference between this method and the one after it
                //list.deferNotifyDataSetChanged();

                //getting the content of the element at position number 5
                View v = list.getAdapter().getView(position, null, list);
                TextView titleView = (TextView) v.findViewById(R.id.title);
                TextView subtitleView = (TextView) v.findViewById(R.id.subtitle);
                String titletext = ""+titleView.getText();
                String subtitletext ="" + subtitleView.getText();
                Toast.makeText(getApplicationContext(),"The element at position "+ position +" has Title "+ titletext +" subtitle " + subtitletext,Toast.LENGTH_SHORT).show();
            }
        });
    }
}