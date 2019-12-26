package com.angkut.pelangganp12.dataOrder;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.angkut.pelangganp12.R;


public class DetailData extends AppCompatActivity {
    TextView keterangan, berat, tanggal, status,total_harga,driver,lokasi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_detail_data);

        keterangan = (TextView) findViewById(R.id.keterangan);
        berat = (TextView) findViewById( R.id.berat);
        tanggal = (TextView) findViewById(R.id.tanggal);
        status = (TextView) findViewById(R.id.status);
        total_harga = (TextView) findViewById(R.id.total);
        driver = (TextView) findViewById(R.id.driver);
        lokasi = (TextView) findViewById(R.id.lokasi);
        keterangan.setText(getIntent().getStringExtra("keterangan"));
        berat.setText(getIntent().getStringExtra("berat"));
        tanggal.setText(getIntent().getStringExtra("tanggal"));
        status.setText(getIntent().getStringExtra("status"));
        total_harga.setText(getIntent().getStringExtra("total_harga"));
        driver.setText(getIntent().getStringExtra("driver"));
        lokasi.setText(getIntent().getStringExtra("lokasi"));
    }
}
