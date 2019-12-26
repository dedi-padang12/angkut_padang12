package com.angkut.pelangganp12;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.angkut.pelangganp12.dataOrder.ListActivity;
import com.angkut.pelangganp12.dataOrder.RequestHandler;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigInteger;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ModalOrder extends  BottomSheetDialogFragment  {
    private BottomSheetListener mListener;

    EditText Atxt_keterangan,harga_jarakV,texty,jarak_tempuhV ;
    TextView org;
Spinner Atxt_berat;
    String id_pelanggan, email,Aroom_chat;
    SharedPreferences sharedpreferences;
    public static final String my_shared_preferences = "my_shared_preferences";
    public static final String session_status = "session_status";
    public static final String TAG_ID = "id_pelanggan";
    public static final String TAG_EMAIL = "email";
    public static final String TAG_JARAK= "harga_jarak";
    // Creating Volley RequestQueue.
    com.android.volley.RequestQueue requestQueue;

    // Create string variable to hold the EditText Value.
    String KeteangabnHolder ,TotalHolder;
    String BeratHolder ;
    String Aharga_jarak,Ajarak_tempuh;
    // Creating Progress dialog.
    ProgressDialog progressDialog;
    private String HttpUrl = Server.URL + "order_motor.php";
    // Storing server url into String variable.
   // String HttpUrl = "http://192.168.1.4/bang_angkut/order_motor.php";
    int harga;
    int hasil;
    private String cname;
    Locale localeID = new Locale("in", "ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
    int jarak_int = 0;
   // private RequestQueue requestQueue;
    private StringRequest stringRequest;
    ArrayList<HashMap<String, String>> list_data;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.order_motor, container, false);
        Atxt_keterangan = v.findViewById(R.id.txt_keterangan);
       harga_jarakV = (EditText)v.findViewById(R.id .txt_harga_jarak);
         texty = (EditText)v.findViewById(R.id .txt_total_harga);
       jarak_tempuhV = (EditText)v.findViewById(R.id .txt_jarak);
        Atxt_berat = v.findViewById(R.id.txt_berat);
        Button button1 = v.findViewById(R.id.btn_order);
        requestQueue = Volley.newRequestQueue(getActivity());
//seasson
        Long tsLong = System.currentTimeMillis()/1000;
        String room_chat = tsLong.toString();

      //  sharedpreferences = getSharedPreferences(Login.my_shared_preferences, Context.MODE_PRIVATE);
        sharedpreferences = getActivity().getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        sharedpreferences.getString("yourtextvalueKey", null);

        email = getActivity().getIntent().getExtras().getString(TAG_EMAIL);
        id_pelanggan = getActivity().getIntent().getExtras().getString(TAG_ID);
        String url = com.angkut.pelangganp12.Server.URL + "tampil_pelanggan.php?id="+id_pelanggan;
        requestQueue = Volley.newRequestQueue(getContext());

        list_data = new ArrayList<HashMap<String, String>>();

        stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("tampil_pelanggan");
                    for (int a = 0; a < jsonArray.length(); a ++){
                        JSONObject json = jsonArray.getJSONObject(a);
                        HashMap<String, String> map  = new HashMap<String, String>();


                        map.put("jarak", json.getString("jarak"));
                        map.put("harga_jarak", json.getString("harga_jarak"));
                        list_data.add(map);
                    }



                     Aharga_jarak = list_data.get(0).get("harga_jarak");
                     Ajarak_tempuh = list_data.get(0).get("jarak");
                    jarak_tempuhV.setText(Ajarak_tempuh +"KM");
                    harga_jarakV.setText(Aharga_jarak);



                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.hide();
                Toast.makeText(getContext(), "Failed",Toast.LENGTH_SHORT).show();
            }
        });

        RequestHandler.getInstance( getContext()).addToRequestQueue(stringRequest);

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
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        //Set the Spinner with the adapter
        Spinner spinner = (Spinner) v.findViewById(R.id.txt_berat);
        spinner.setAdapter(adapter);


        //Action to perform on functions - onItemSelected and onNothing selected

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                Object item = adapterView.getItemAtPosition(position);
                if (item != null) {


                    int Bharga_jarak = Integer.parseInt(harga_jarakV.getText().toString());

                    switch(position){
                        case 0:
                            harga = 2000;
                            hasil = harga+Bharga_jarak;

                            texty.setText(formatRupiah.format((double)hasil));

                            break;
                        case 1:
                            harga = 3000;
                            hasil = harga+Bharga_jarak;
                            texty.setText(formatRupiah.format((double)hasil));
                            break;
                        case 2:
                            harga =4000;

                            hasil = harga+Bharga_jarak;
                            texty.setText(formatRupiah.format((double)hasil));
                            break;

                        case 3:
                            harga =5000;

                            hasil = harga+Bharga_jarak;
                            texty.setText(formatRupiah.format((double)hasil));
                            break;
                        case 4:
                            harga =5500;

                            hasil = harga+Bharga_jarak;
                            texty.setText(formatRupiah.format((double)hasil));
                            break;

                        case 5:
                            harga =6500;

                            hasil = harga+Bharga_jarak;
                            texty.setText(formatRupiah.format((double)hasil));
                            break;

                        case 6:
                            harga =7500;
                            hasil = harga+Bharga_jarak;
                            texty.setText(formatRupiah.format((double)hasil));
                            break;
                        default:
                            cname = "Pilih Berat";
                            texty.setText(cname);
                            break;
                    }

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // TODO Auto-generated method stub
            }
        });



//
        progressDialog = new ProgressDialog(getActivity());
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                    progressDialog.setMessage( "Loading..." );
                    progressDialog.show();
                    // Calling method to get value from EditText.
                    GetValueFromEditText();

                    // Creating string request with post method.
                    StringRequest stringRequest = new StringRequest( Request.Method.POST, HttpUrl,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String ServerResponse) {

                                    // Hiding the progress dialog after all task complete.
                                    progressDialog.dismiss();

                                    // Showing response message coming from server.
                                   // Toast.makeText( getActivity(), ServerResponse, Toast.LENGTH_LONG ).show();
                                    Intent I = new Intent( getActivity(), Menunggu.class );
                                    I.putExtra("id_pelanggan",id_pelanggan);

                                    startActivity(I);
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError volleyError) {

                                    // Hiding the progress dialog after all task complete.
                                    progressDialog.dismiss();

                                    Intent I = new Intent( getActivity(), Menunggu.class );
                                    I.putExtra("id_pelanggan",id_pelanggan);

                                    startActivity(I);
                                }
                            } ) {
                        @Override
                        protected Map<String, String> getParams() {

                            // Creating Map String Params.
                            Map<String, String> params = new HashMap<String, String>();

                            // Adding All values to Params.
                            params.put( "keterangan", KeteangabnHolder );
                            params.put( "id_pelanggan", id_pelanggan );
                            params.put( "berat", BeratHolder );
                            params.put( "total", TotalHolder );
                            params.put( "room_chat", room_chat );
                            return params;
                        }

                    };

                    // Creating RequestQueue.
                    RequestQueue requestQueue = Volley.newRequestQueue( getActivity() );

                    // Adding the StringRequest object into requestQueue.
                    requestQueue.add( stringRequest );
                    //end simpan pelanggan

            }
        });


        return v;
    }


    public interface BottomSheetListener {
        void onButtonClicked(String text);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mListener = (BottomSheetListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " ");
        }
    }
    public void GetValueFromEditText(){
        KeteangabnHolder = Atxt_keterangan.getText().toString();
        BeratHolder = Atxt_berat.getSelectedItem().toString();
        TotalHolder = texty.getText().toString();
    }
    public void cari_lokasi(View view) {

    }
}

