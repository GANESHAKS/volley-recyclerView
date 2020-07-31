package com.pro.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String url="https://jsonplaceholder.typicode.com/posts";
 RecyclerView recyclerView;
 RecyclerView.Adapter adapter;
 List<ListItem> listItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView)findViewById(R.id.rv_names);
        recyclerView.setHasFixedSize(false);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listItems=new ArrayList<ListItem>();

        loadRecyclerViewData();



    }

    private void loadRecyclerViewData() {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading data ");
        progressDialog.show();

        StringRequest stringRequest=new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                       progressDialog.dismiss();
                        try {
                           JSONArray jsonArray= new JSONArray(response);


                            for (int i=0;i<jsonArray.length();i++){
                                JSONObject o=jsonArray.getJSONObject(i);
                                ListItem item=new ListItem(o.getString("title"),
                                        o.getString("body")

                                );
                                Log.d(" json ",o.getString("body"));
                                listItems.add(item);
                            }
                            adapter=new MyAdapter(listItems,getApplicationContext());
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"Error  "+error.getMessage(),Toast.LENGTH_SHORT).show();

                    }
                }
    );
 RequestQueue requestQueue= Volley.newRequestQueue(this);
 requestQueue.add(stringRequest);

    }
}
