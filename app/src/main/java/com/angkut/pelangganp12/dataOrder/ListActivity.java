package com.angkut.pelangganp12.dataOrder;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.angkut.pelangganp12.Login;
import com.angkut.pelangganp12.R;
import com.angkut.pelangganp12.Server;
import com.angkut.pelangganp12.profil.ListActivityP;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.view.MenuItem;

public class ListActivity extends AppCompatActivity {

    public static ListActivity ma;
    protected Cursor cursor;
    ArrayList<Model> thelist;
    ListView listview;
    List<Model> listItems;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    ProgressDialog progressDialog;
    String id_pelanggan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_list);

        id_pelanggan = getIntent().getStringExtra("id_pelanggan");
        getSupportActionBar().setTitle("Data Orderan");
        recyclerView = (RecyclerView) findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(ListActivity.this));
        progressDialog = new ProgressDialog(this);
        listItems = new ArrayList<>();
        ma = this;
        refresh_list();


    }





    public void refresh_list(){

        String url = Server.URL + "tampil_order_pelanggan.php?id="+id_pelanggan;
        listItems.clear();
        adapter = new MyAdapter(listItems,getApplicationContext());
        recyclerView.setAdapter(adapter);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        progressDialog.setMessage("Loading");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest( Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try{

                    progressDialog.hide();
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    Toast.makeText(ListActivity.this,jsonObject.getString("message"),Toast.LENGTH_SHORT).show();
                    for (int i = 0; i<jsonArray.length(); i++){
                        JSONObject o = jsonArray.getJSONObject(i);
                       Model item = new Model(
                                o.getString("id"),
                                o.getString("keterangan"),
                                o.getString("berat"),
                                o.getString("tanggal"),
                                o.getString("status"),
                                o.getString("room_chat"),
                                o.getString("nama"),
                                o.getString("total_harga"),
                                o.getString("driver"),
                                o.getString("lokasi")

                        );
                        listItems.add(item);

                        adapter = new MyAdapter(listItems,getApplicationContext());
                        recyclerView.setAdapter(adapter);

                    }
                }catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.hide();
                Toast.makeText(ListActivity.this, "Failed",Toast.LENGTH_SHORT).show();
            }
        }){
            protected Map<String , String> getParams() throws AuthFailureError {
                Map<String , String> params = new HashMap<>();
                params.put("name", "kl");
                return params;
            }
        };
        RequestHandler.getInstance(ListActivity.this).addToRequestQueue(stringRequest);
    }


    public void menu_orderan(MenuItem item) {
        Intent intent = new Intent( ListActivity.this, ListActivity.class);
        intent.putExtra("id_pelanggan", id_pelanggan);

        startActivity(intent);
    }

    public void Home(MenuItem item) {
        Intent intent = new Intent( ListActivity.this, Login.class);
        intent.putExtra("id_pelanggan", id_pelanggan);

        startActivity(intent);
    }

    public void menu_profil(MenuItem item) {
        Intent intent = new Intent( ListActivity.this, ListActivityP.class);
        intent.putExtra("id_pelanggan", id_pelanggan);

        startActivity(intent);
    }
}
