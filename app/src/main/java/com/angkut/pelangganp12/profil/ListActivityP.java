package com.angkut.pelangganp12.profil;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.angkut.pelangganp12.Home;
import com.angkut.pelangganp12.Login;
import com.angkut.pelangganp12.R;
import com.angkut.pelangganp12.Server;
import com.angkut.pelangganp12.dataOrder.ListActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ListActivityP extends AppCompatActivity {

    private TextView nama, no_hp, email,alamat;
    private String nama_ku,id_pelanggan;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    ArrayList<HashMap<String, String>> list_data;

    SharedPreferences sharedpreferences;

    public static final String TAG_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.profil_detail);
        nama_ku = "dedi";
        id_pelanggan = getIntent().getExtras().get("id_pelanggan").toString();

        String url = Server.URL + "profil/index.php?id="+id_pelanggan;

        nama = (TextView)findViewById(R.id.nama);
        no_hp = (TextView)findViewById(R.id.no_hp);
        email = (TextView)findViewById(R.id.email);
        alamat = (TextView) findViewById(R.id.alamat);
        requestQueue = Volley.newRequestQueue(ListActivityP.this);
        sharedpreferences = getSharedPreferences(Login.my_shared_preferences, Context.MODE_PRIVATE);

        list_data = new ArrayList<HashMap<String, String>>();

        stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data_pelanggan");
                    for (int a = 0; a < jsonArray.length(); a ++){
                        JSONObject json = jsonArray.getJSONObject(a);
                        HashMap<String, String> map  = new HashMap<String, String>();

                        map.put("nama", json.getString("nama"));
                        map.put("no_hp", json.getString("no_hp"));
                        map.put("email", json.getString("email"));
                        map.put("alamat", json.getString("alamat"));
                        list_data.add(map);
                    }


                    nama.setText(list_data.get(0).get("nama"));
                    no_hp.setText(list_data.get(0).get("no_hp"));
                    email.setText(list_data.get(0).get("email"));
                    alamat.setText(list_data.get(0).get("alamat"));


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ListActivityP.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(stringRequest);

    }

    public void menu_orderan(MenuItem item) {
        Intent intent = new Intent( ListActivityP.this, ListActivity.class);
        intent.putExtra("id_pelanggan", id_pelanggan);

        startActivity(intent);
    }
    public void Home(MenuItem item) {
        Intent intent = new Intent( ListActivityP.this, Login.class);
        intent.putExtra("id_pelanggan", id_pelanggan);

        startActivity(intent);
    }

    public void menu_profil(MenuItem item) {
        Intent intent = new Intent( ListActivityP.this, ListActivityP.class);
        intent.putExtra("id_pelanggan", id_pelanggan);
        startActivity(intent);
    }

    public void Logout(View view) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putBoolean(Login.session_status, false);
        editor.putString(id_pelanggan, null);
      
        editor.commit();

        Intent intent = new Intent(ListActivityP.this, Login.class);
        finish();
        startActivity(intent);
    }
}
