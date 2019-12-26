package com.angkut.pelangganp12;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import com.angkut.pelangganp12.dataOrder.ListActivity;
import com.angkut.pelangganp12.profil.ListActivityP;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class angkut_motor extends AppCompatActivity implements OnMapReadyCallback,  ModalOrder.BottomSheetListener, ModalOrderMobil.BottomSheetListener2, ModalService.BottomSheetListener3{
String id_pelangganT;
    private GoogleMap mMap;
   // private Location mMap;
    private static final int RC_BTN_A = 0;
    private Intent intentMaps;
    public static final String KEY_LAT = "key_lat";
    public static final String KEY_LNG = "key_lng";
    public static final String EXTRA_MESSAGE = "SEDANG MENHITUNG";

   double harga;
    int hasil;
    double Hasil_jarak,hasil1;
    String str,jarak,alamat;

    EditText txt_alamat,txt_jarak,texty,txt_harga_beratA,keteranganA;
    Button btn_orderX;
    Spinner txt_berat;
    private String HolderBerat,HolderHrgBerat,HolderKeterangan;
    String message ="...";
    String id_pelangganA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_angkut_motor);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById( R.id.map );
        mapFragment.getMapAsync( this );
        ButterKnife.bind(this);
        txt_jarak = (EditText) findViewById(R.id.txt_jarak);
        txt_alamat = (EditText) findViewById(R.id.txt_alamat);
        txt_harga_beratA = (EditText) findViewById(R.id.txt_harga_berat);

        txt_berat = (Spinner) findViewById(R.id.txt_berat);
        btn_orderX = (Button)findViewById(R.id .btn_order);
        jarak = getIntent().getStringExtra("jarak");
        alamat = getIntent().getStringExtra("alamat");
         id_pelangganA = getIntent().getStringExtra("id_pelanggan");
        intentMaps = new Intent(this, MapActivity.class);
        Locale localeID = new Locale("in", "ID");


        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        ///////


        /*filling the values in the Spinner*/
        List<String> spinnerArray = new ArrayList<String>();
        spinnerArray.add("5-10 kg");
        spinnerArray.add("10-15 kg");
        spinnerArray.add("15-20 kg");
        spinnerArray.add("20-25 kg");
        spinnerArray.add("25-30 kg");
        spinnerArray.add("35-40 kg");
        spinnerArray.add("45-50 kg");

        //Create the ArrayAdapter instance
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(angkut_motor.this,
                android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = (Spinner) findViewById(R.id.txt_berat);
        spinner.setAdapter(adapter);
        HolderBerat = txt_berat.getSelectedItem().toString();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {

                Object item = adapterView.getItemAtPosition(position);
                if (item != null) {



                    switch(position){
                        case 0:
                            harga = 2000;
                            String  hargaX = String.valueOf(harga);
                            txt_harga_beratA.setText(hargaX);
                            break;
                        case 1:
                            harga = 3000;
                            String  hargaX1 = String.valueOf(harga);
                            txt_harga_beratA.setText(hargaX1);
                            break;
                        case 2:
                            harga =4000;
                            String  hargaX2 = String.valueOf(harga);
                            txt_harga_beratA.setText(hargaX2);
                            break;

                        case 3:
                            harga =5000;
                            String  hargaX3 = String.valueOf(harga);
                            txt_harga_beratA.setText(hargaX3);
                            break;
                        case 4:
                            harga =5500;
                            String  hargaX4 = String.valueOf(harga);
                            txt_harga_beratA.setText(hargaX4);
                            break;

                        case 5:
                            harga =6500;
                            String  hargaX5 = String.valueOf(harga);
                            txt_harga_beratA.setText(hargaX5);
                            break;

                        case 6:
                            harga =7500;
                            String  hargaX6 = String.valueOf(harga);
                            txt_harga_beratA.setText(hargaX6);
                            break;

                        default:
                           String cname = "Pilih Berat";
                            texty.setText(cname);
                            break;


                    }
                    HolderHrgBerat = txt_harga_beratA.getText().toString();

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // TODO Auto-generated method stub
            }
        });

    }

    double lat;
    double lng;
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
     //   LatLng sydney = new LatLng( -0,0 );
     //   mMap.addMarker( new MarkerOptions().position( sydney ).title( "Lokasi Jemput" ) );
      //  mMap.moveCamera( CameraUpdateFactory.newLatLng( sydney ) );
      //  mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(  sydney , 15));
    }


    @OnClick(R.id.buka_map)
    void onBtnAClick() {
        startActivityForResult(intentMaps, RC_BTN_A);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            lat = data.getDoubleExtra(KEY_LAT, 0);
            lng = data.getDoubleExtra(KEY_LNG, 0);
            String alamat = null;
            id_pelangganT = getIntent().getStringExtra("id_pelanggan");
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            try {
                List<Address> list = geocoder.getFromLocation(lat,lng,1);
                alamat = list.get(0).getAddressLine(0)+", "+list.get(0).getCountryName();
            } catch (IOException e) {
                e.printStackTrace();
            }
            switch (requestCode) {
                case RC_BTN_A:

                    LatLng lokasi = new LatLng( lat,lng );
                    mMap.addMarker(new MarkerOptions().position(lokasi).title("Lokasi Jemput" ));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lokasi, 15));

                    Double latit =-1.815696;
                    Double longit  = 109.974283;

                    Location locationCurrent = new Location("Lokasi Sekarang");
                    locationCurrent.setLatitude(latit);
                    locationCurrent.setLongitude(longit);

                    Location locationB = new Location("Lokasi Tujuan");
                    locationB.setLatitude(lat);
                    locationB.setLongitude(lng);

                   double distance = locationCurrent.distanceTo(locationB) / 1000;
                    Hasil_jarak = (double) (Math.round(distance * 100)) / 100;
                    str = String.valueOf(Hasil_jarak);
                    txt_jarak.setText(str);
                    txt_alamat.setText(alamat);
                  String  latX = String.valueOf(lat);
                  String  lngX = String.valueOf(lng);
                    String  alamatX = String.valueOf(alamat);
                    keteranganA = (EditText) findViewById(R.id.txt_keterangan);

                    btn_orderX.setOnClickListener( new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(txt_alamat.getText().toString().length()==5){

                                txt_alamat.setError("Alamat doperlukan!");
                            } else  if(keteranganA.getText().toString().length()==0){
                                //jika form Username belum di isi / masih kosong
                                keteranganA.setError("Keterangan diperlukan!");
                            }else {

                                HolderKeterangan = keteranganA.getText().toString();

                            Intent intent = new Intent(angkut_motor.this, angkut_motor_proses.class);
                            intent.putExtra("jarak", str);
                            intent.putExtra("alamat", alamatX);
                            intent.putExtra("berat", HolderBerat);
                            intent.putExtra("harga_berat", HolderHrgBerat);
                                intent.putExtra("keterangan", HolderKeterangan);
                                intent.putExtra("langlong", latX+","+lngX);
                                intent.putExtra("id_pelanggan", id_pelangganA);


                                intent.putExtra(EXTRA_MESSAGE, message);
                            finish();
                            startActivity(intent);

                            }

                        }
                    });
                    break;


            }
        }



    }



    public void menu_orderan(MenuItem item) {
        Intent intent = new Intent(angkut_motor.this, ListActivity.class);
        intent.putExtra("id_pelanggan", id_pelangganA);

        startActivity(intent);
    }
    public void Home(MenuItem item) {
        Intent intent = new Intent( angkut_motor.this, Login.class);
        intent.putExtra("id_pelanggan", id_pelangganA);

        startActivity(intent);
    }

    public void menu_profil(MenuItem item) {
        Intent intent = new Intent( angkut_motor.this, ListActivityP.class);
        intent.putExtra("id_pelanggan", id_pelangganA);
        startActivity(intent);
    }

    @Override
    public void onButtonClicked(String text) {

    }

    public void btn_order(View view) {
    }
}
