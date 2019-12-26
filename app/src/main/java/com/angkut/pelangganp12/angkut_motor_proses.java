package com.angkut.pelangganp12;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.angkut.pelangganp12.dataOrder.ListActivity;
import com.angkut.pelangganp12.profil.ListActivityP;

import java.util.HashMap;
import java.util.Map;

import static com.angkut.pelangganp12.Home.TAG_ID;

public class angkut_motor_proses extends AppCompatActivity {
    ProgressDialog progressDialog;
    private String HttpUrl = Server.URL + "order_motor.php";
    com.android.volley.RequestQueue requestQueue;
   // String keteranganA;
   public static final String TAG_ID = "id_pelanggan";
   public String id_pelangganA ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_angkut_motor_proses );
        EditText lokasi = (EditText) findViewById( R.id.lokasi );
        EditText berat = (EditText) findViewById( R.id.txt_berat );
        EditText jarak = (EditText) findViewById( R.id.txt_jarak );
        EditText harga_angkutan = (EditText) findViewById( R.id.txt_harga_angkutan );
        EditText harga_jarak = (EditText) findViewById( R.id.txt_harga_jarak );
        EditText diskon = (EditText) findViewById( R.id.txt_harga_diskon );
        EditText total = (EditText) findViewById( R.id.txt_harga_total );
        EditText keterangan = (EditText) findViewById( R.id.keterangan );
        Button btn_proses = (Button) findViewById( R.id.btn_proses );
        //menu

        //end menu
        Long tsLong = System.currentTimeMillis() / 1000;
        String room_chat = tsLong.toString();
        String lokasiA = getIntent().getStringExtra( "alamat" );
        String beratA = getIntent().getStringExtra( "berat" );
        String jarakA = getIntent().getStringExtra( "jarak" );
        String harga_angkutanA = getIntent().getStringExtra( "harga_berat" );
        String keteranganA = getIntent().getStringExtra( "keterangan" );

        String langlongA = getIntent().getStringExtra( "langlong" );
        String id_pelangganA = getIntent().getStringExtra( "id_pelanggan" );
        lokasi.setText( lokasiA );
        berat.setText( beratA );
        jarak.setText( jarakA );
        keterangan.setText( keteranganA );
        harga_angkutan.setText( harga_angkutanA );
        double jarak_a = Double.parseDouble( jarakA );
        double harga_berat = Double.parseDouble( harga_angkutanA );

        double harga_a = 0;
        double total_a = 0;
        if (jarak_a >= 1) {
            harga_a = 8000;
            total_a = harga_a + harga_berat;
            String harga_b = String.valueOf( harga_a );
            String total_b = String.valueOf( total_a );
            harga_jarak.setText( harga_b );
            total.setText( total_b );
        }
        if (jarak_a >= 2) {
            harga_a = 10000;
            total_a = harga_a + harga_berat;
            String harga_b = String.valueOf( harga_a );
            String total_b = String.valueOf( total_a );
            harga_jarak.setText( harga_b );
            total.setText( total_b );
        }
        if (jarak_a >= 4) {
            harga_a = 12000;
            total_a = harga_a + harga_berat;
            String harga_b = String.valueOf( harga_a );
            String total_b = String.valueOf( total_a );
            harga_jarak.setText( harga_b );
            total.setText( total_b );
        }
        if (jarak_a >= 6) {
            harga_a = 15000;
            total_a = harga_a + harga_berat;
            String harga_b = String.valueOf( harga_a );
            String total_b = String.valueOf( total_a );
            harga_jarak.setText( harga_b );
            total.setText( total_b );
        }
        if (jarak_a >= 8) {
            harga_a = 17000;
            total_a = harga_a + harga_berat;
            String harga_b = String.valueOf( harga_a );
            String total_b = String.valueOf( total_a );
            harga_jarak.setText( harga_b );
            total.setText( total_b );
        }
        if (jarak_a >= 10) {
            harga_a = 20000;
            total_a = harga_a + harga_berat;
            String harga_b = String.valueOf( harga_a );
            String total_b = String.valueOf( total_a );
            harga_jarak.setText( harga_b );
            total.setText( total_b );
        }



        progressDialog = new ProgressDialog(angkut_motor_proses.this);
        btn_proses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                progressDialog.setMessage( "Loading..." );
                progressDialog.show();
                // Calling method to get value from EditText.


                // Creating string request with post method.
                StringRequest stringRequest = new StringRequest( Request.Method.POST, HttpUrl,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String ServerResponse) {

                                // Hiding the progress dialog after all task complete.
                                progressDialog.dismiss();

                                // Showing response message coming from server.
                                // Toast.makeText( angkut_motor_proses.this, ServerResponse, Toast.LENGTH_LONG ).show();
                                Intent I = new Intent( angkut_motor_proses.this, ListActivity.class );
                                I.putExtra("id_pelanggan",id_pelangganA);

                                startActivity(I);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {

                                // Hiding the progress dialog after all task complete.
                                progressDialog.dismiss();

                                Intent I = new Intent( angkut_motor_proses.this, ListActivity.class );
                                I.putExtra("id_pelanggan",id_pelangganA);

                                startActivity(I);
                            }
                        } ) {

                    String t_harga_jarak = harga_jarak.getText().toString();
                    String t_harga_total = total.getText().toString();

                    String t_lokasi = lokasi.getText().toString();
                    @Override
                    protected Map<String, String> getParams() {

                        // Creating Map String Params.
                        Map<String, String> params = new HashMap<String, String>();

                        // Adding All values to Params.
                        params.put( "id_pelanggan", id_pelangganA );
                        params.put( "keterangan", keteranganA );
                        params.put( "berat_angkutan", beratA );
                        params.put( "jarak_angkutan", jarakA );
                        params.put( "harga_angkutan", harga_angkutanA );
                        params.put( "harga_jarak", t_harga_jarak );
                        params.put( "total", t_harga_total );
                        params.put( "room_chat", room_chat );
                        params.put( "langlong", langlongA );
                        params.put( "lokasi", t_lokasi );
                        return params;
                    }

                };

                // Creating RequestQueue.
                RequestQueue requestQueue = Volley.newRequestQueue( angkut_motor_proses.this );

                // Adding the StringRequest object into requestQueue.
                requestQueue.add( stringRequest );
                //end simpan pelanggan

            }
        });



}

    public void menu_orderan(MenuItem item) {
        Intent intent = new Intent(angkut_motor_proses.this, ListActivity.class);
        intent.putExtra("id_pelanggan", id_pelangganA);

        startActivity(intent);
    }
    public void Home(MenuItem item) {
        Intent intent = new Intent( angkut_motor_proses.this, Login.class);
        intent.putExtra("id_pelanggan", id_pelangganA);

        startActivity(intent);
    }

    public void menu_profil(MenuItem item) {
        Intent intent = new Intent( angkut_motor_proses.this, ListActivityP.class);

        intent.putExtra("id_pelanggan", id_pelangganA);
        startActivity(intent);
    }
}
