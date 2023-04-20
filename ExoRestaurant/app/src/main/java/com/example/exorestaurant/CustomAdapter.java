package com.example.exorestaurant;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CustomAdapter extends BaseAdapter {
    Context con;
    JSONArray data;
    LayoutInflater inflater;

    public CustomAdapter(Context c, JSONArray data) {
        this.con = c;
        Log.d("custom_adapter", con.toString());
        this.data = data;
        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public class Holder {
        TextView nametxtv, typetxtv, pricetxtv;
        ImageView deleteimage;
    }
    @Override
    public int getCount() {
        return data.length();
    }
    @Override
    public Object getItem(int i) {
        return null;
    }
    @Override
    public long getItemId(int i) {
        return 0;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final Holder holder = new Holder();
        final View rowView;
        rowView = inflater.inflate(R.layout.row, null);
        holder.nametxtv = rowView.findViewById(R.id.dname);
        holder.pricetxtv = rowView.findViewById(R.id.dprice);
        holder.typetxtv = rowView.findViewById(R.id.dtype);
        holder.deleteimage = rowView.findViewById(R.id.delete);
        JSONObject obj = data.optJSONObject(i);
        try {
            holder.nametxtv.setText(obj.getString("name"));
            holder.typetxtv.setText(obj.getString("type"));
            holder.pricetxtv.setText(obj.getString("price"));
            holder.deleteimage.setTag(obj.getInt("id"));
            holder.deleteimage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url = "http://10.0.2.2/restaurantapp/deletedish.php?id=" + holder.deleteimage.getTag();
                    RequestQueue queue = Volley.newRequestQueue(con);
                    StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if(response.equals("sucess")){
                                ((MainActivity)con).onResume();
                            }else { Toast.makeText(con, "fail", Toast.LENGTH_SHORT).show();
                            }
                        }
                        }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(con, "Error" + error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    queue.add(request);
                }
            });
        } catch (JSONException e) {

        }
        return rowView; } }
