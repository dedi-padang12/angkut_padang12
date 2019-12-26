package com.angkut.pelangganp12;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.angkut.pelangganp12.dataOrder.ListActivity;

public class Menunggu extends AppCompatActivity {
    AlertDialog dialog_verifying;
    private String id_pelanggan,room_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        id_pelanggan = getIntent().getExtras().get("id_pelanggan").toString();

        LayoutInflater inflater = getLayoutInflater();
        View alertLayout= inflater.inflate(R.layout.menunggu,null);
        AlertDialog.Builder show = new AlertDialog.Builder(Menunggu.this);
        show.setView(alertLayout);
        show.setCancelable(false);
        dialog_verifying = show.create();
        dialog_verifying.show();
        Handler handler = new Handler();


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Menunggu.this, ListActivity.class);
                intent.putExtra("id_pelanggan", id_pelanggan);

                startActivity(intent);
            }
        },3000);
    }



    public void btn_home(View view) {
        startActivity(new Intent(Menunggu.this,Login.class));
    }


}
