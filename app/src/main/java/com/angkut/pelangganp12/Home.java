package com.angkut.pelangganp12;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.angkut.pelangganp12.dataOrder.ListActivity;
import com.angkut.pelangganp12.profil.ListActivityP;
import com.bumptech.glide.Glide;

import android.view.MenuItem;
import java.util.ArrayList;
import java.util.HashMap;

public class Home extends AppCompatActivity implements ModalOrder.BottomSheetListener, ModalOrderMobil.BottomSheetListener2, ModalService.BottomSheetListener3{
    ImageView banner_1,banner_2,banner_3;
//url gambar banner
    public static String urlBanner_1 =Server.URL + "banner/banner-1.png";
    public static String urlBanner_2 =Server.URL + "banner/banner-2.png";
    public static String urlBanner_3 =Server.URL + "banner/banner-3.png";

    //flas
ImageView bgapp, clover;
    LinearLayout textsplash, texthome, menus;
    Animation frombottom;
    //end
    Button btn_logout;
    TextView txt_id, txt_email,txt_jarak;
    String id_pelanggan, email;
    SharedPreferences sharedpreferences;

    public static final String TAG_ID = "id_pelanggan";
    public static final String TAG_EMAIL = "email";
    public static final String TAG_JARAK = "jarak";
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    ArrayList<HashMap<String, String>> list_data;
    String jarak_tempuh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        banner_1 = (ImageView)findViewById(R.id.banner_1);
        banner_2 = (ImageView)findViewById(R.id.banner_2);
        banner_3 = (ImageView)findViewById(R.id.banner_3);
        Glide.with(Home.this)
                // LOAD URL DARI INTERNET
                .load(urlBanner_1)
                // LOAD GAMBAR AWAL SEBELUM GAMBAR UTAMA MUNCUL, BISA DARI LOKAL DAN INTERNET
                .placeholder(R.drawable.bgitem)
                //. LOAD GAMBAR SAAT TERJADI KESALAHAN MEMUAT GMBR UTAMA
                .error(R.drawable.bgitem)
                .into(banner_1);
        Glide.with(Home.this)
                // LOAD URL DARI INTERNET
                .load(urlBanner_2)
                // LOAD GAMBAR AWAL SEBELUM GAMBAR UTAMA MUNCUL, BISA DARI LOKAL DAN INTERNET
                .placeholder(R.drawable.bgitem)
                //. LOAD GAMBAR SAAT TERJADI KESALAHAN MEMUAT GMBR UTAMA
                .error(R.drawable.bgitem)
                .into(banner_2);
        Glide.with(Home.this)
                // LOAD URL DARI INTERNET
                .load(urlBanner_3)
                // LOAD GAMBAR AWAL SEBELUM GAMBAR UTAMA MUNCUL, BISA DARI LOKAL DAN INTERNET
                .placeholder(R.drawable.bgitem)
                //. LOAD GAMBAR SAAT TERJADI KESALAHAN MEMUAT GMBR UTAMA
                .error(R.drawable.bgitem)
                .into(banner_3);
        txt_email = (TextView) findViewById(R.id.txt_email);

        txt_jarak = (TextView) findViewById(R.id.txt_jarak);
        sharedpreferences = getSharedPreferences(Login.my_shared_preferences, Context.MODE_PRIVATE);

        id_pelanggan = getIntent().getStringExtra(TAG_ID);
        email = getIntent().getStringExtra(TAG_EMAIL);
        jarak_tempuh = getIntent().getStringExtra(TAG_JARAK);
        //txt_id.setText("ID : " + id);
        txt_email.setText(email);
        //tampil data pelanggan

        LinearLayout app_layer = (LinearLayout) findViewById (R.id.btn_order_motor);
        app_layer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Home.this, angkut_motor.class);
                intent.putExtra("id_pelanggan", id_pelanggan);
                intent.putExtra(TAG_EMAIL, email);
                intent.putExtra("jarak_tempuh", jarak_tempuh);
                intent.putExtra("jarak", "0");
                intent.putExtra("lang", "0");
                intent.putExtra("long", "0");
                startActivity(intent);
            }
        });
        LinearLayout app_layer2 = (LinearLayout) findViewById (R.id.btn_order_mobil);
        app_layer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModalOrderMobil bottomSheet = new ModalOrderMobil();
                bottomSheet.show(getSupportFragmentManager(), "exampleBottomSheet");

                Intent intent = new Intent(Home.this, ModalOrderMobil.class);
                intent.putExtra(TAG_ID, id_pelanggan);
                intent.putExtra(TAG_EMAIL, email);
                intent.putExtra("jarak_tempuh", jarak_tempuh);
            }
        });
        LinearLayout app_layer3 = (LinearLayout) findViewById (R.id.btn_order_servis);
        app_layer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModalService bottomSheet = new ModalService();
                bottomSheet.show(getSupportFragmentManager(), "exampleBottomSheet");

                Intent intent = new Intent(Home.this, ModalService.class);
                intent.putExtra("id_pelanggan", id_pelanggan);


            }
        });
        frombottom = AnimationUtils.loadAnimation(this, R.anim.frombottom);



        menus = (LinearLayout) findViewById(R.id.menus);




    }


    @Override
    public void onButtonClicked(String text) {

    }

    public void Logout(View view) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putBoolean(Login.session_status, false);
        editor.putString(TAG_ID, null);
        editor.putString(TAG_EMAIL, null);
        editor.commit();

        Intent intent = new Intent(Home.this, Login.class);
        finish();
        startActivity(intent);
    }

    public void menu_orderan(MenuItem item) {
        Intent intent = new Intent(Home.this, ListActivity.class);
                intent.putExtra("id_pelanggan", id_pelanggan);

               startActivity(intent);
    }
    public void Home(MenuItem item) {
        Intent intent = new Intent( Home.this, Login.class);
        intent.putExtra("id_pelanggan", id_pelanggan);

        startActivity(intent);
    }

    public void menu_profil(MenuItem item) {
        Intent intent = new Intent( Home.this, ListActivityP.class);
        intent.putExtra("id_pelanggan", id_pelanggan);
        startActivity(intent);
    }
}