package com.angkut.pelangganp12;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.angkut.pelangganp12.R;
import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.shuhart.stepview.StepView;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class Authentication extends AppCompatActivity {
    /// perintah maps
    @BindView(R.id.txt_alamat)
    TextView txt_alamat;

    private static final int RC_BTN_A = 0;
    private Intent intentMaps;
    public static final String KEY_LAT = "key_lat";
    public static final String KEY_LNG = "key_lng";



    //end maps



    EditText Atxt_nama, Atxt_email,Atxt_pass,Atxt_alamat ;


    // Creating Volley RequestQueue.
    RequestQueue requestQueue;

    // Create string variable to hold the EditText Value.
    String NameHolder ;
    String EmailHolder ;
    String PassHolder  ;
    String LokasiHolder  ;
    String AlamatHolder  ;
    String No_hpHolder;
    // Creating Progress dialog.
    ProgressDialog progressDialog;

    // Storing server url into String variable.
    String HttpUrl = "http://192.168.1.4/bang_angkut/insert.php";


    ////
    private int currentStep = 0;
    LinearLayout layout1,layout2,layout3;
    StepView stepView;
    AlertDialog dialog_verifying,profile_dialog;

    private static String uniqueIdentifier = null;
    private static final String UNIQUE_ID = "+62";
    private static final long ONE_HOUR_MILLI = 60*60*1000;

    private static final String TAG = "FirebasePhoneNumAuth";

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private FirebaseAuth firebaseAuth;

    private String phoneNumber;
    private Button sendCodeButton;
    private Button verifyCodeButton;
    private Button signOutButton;
    private Button button3;

    private EditText phoneNum;
    private PinView verifyCodeET;
    private TextView phonenumberText;

    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;


    private FirebaseAuth mAuth;
    private EditText PassInput;
    private CheckBox ShowPass;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_authentication);
///
        PassInput = findViewById( R.id.txt_pass);
        ShowPass = findViewById( R.id.showPass);
///maps
        ButterKnife.bind(this);

        intentMaps = new Intent(this, MapActivity.class);
        //Set onClickListener, untuk menangani kejadian saat Checkbox diklik
        ShowPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ShowPass.isChecked()){
                    //Saat Checkbox dalam keadaan Checked, maka password akan di tampilkan
                    PassInput.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else {
                    //Jika tidak, maka password akan di sembuyikan
                    PassInput.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        // Assigning ID's to EditText.
        Atxt_nama = (EditText) findViewById( R.id.txt_nama);
        Atxt_alamat = (EditText) findViewById( R.id.txt_alamat);
        Atxt_email = (EditText) findViewById( R.id.txt_email);
        Atxt_pass = (EditText) findViewById( R.id.txt_pass);



        // Assigning ID's to Button.

        // Creating Volley newRequestQueue .
        requestQueue = Volley.newRequestQueue( Authentication.this);

        progressDialog = new ProgressDialog( Authentication.this);
        ///
        mAuth = FirebaseAuth.getInstance();

        layout1 = (LinearLayout) findViewById( R.id.layout1);
        layout2 = (LinearLayout) findViewById( R.id.layout2);
        layout3 = (LinearLayout) findViewById( R.id.layout3);
        sendCodeButton = (Button) findViewById( R.id.submit1);
        verifyCodeButton = (Button) findViewById( R.id.submit2);
        button3 = (Button) findViewById( R.id.submit3);
        firebaseAuth = FirebaseAuth.getInstance();
        phoneNum = (EditText) findViewById( R.id.phonenumber);
        verifyCodeET = (PinView) findViewById( R.id.pinView);
        phonenumberText = (TextView) findViewById( R.id.phonenumberText);


        stepView = findViewById( R.id.step_view);
        stepView.setStepsNumber(3);
        stepView.go(0, true);
        layout1.setVisibility(View.VISIBLE);

        sendCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String phoneNumber = phoneNum.getText().toString();
                String NameHolder = Atxt_nama.getText().toString();

                phonenumberText.setText(phoneNumber);
                Atxt_nama.setText(NameHolder);

                if (TextUtils.isEmpty(phoneNumber)) {
                    phoneNum.setError("Masukan no hp");
                    phoneNum.requestFocus();
                }if (TextUtils.isEmpty(NameHolder)) {
                    Atxt_nama.setError("Masukan Nama");
                    Atxt_nama.requestFocus();

                } else if (phoneNumber.length() < 10) {
                    phoneNum.setError("Masukan no hp yang valid");
                    phoneNum.requestFocus();
                } else {

                    if (currentStep < stepView.getStepCount() - 1) {
                        currentStep++;
                        stepView.go(currentStep, true);
                    } else {
                        stepView.done(true);
                    }
                    layout1.setVisibility(View.GONE);
                    layout2.setVisibility(View.VISIBLE);
                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            phoneNumber,        // Phone number to verify
                            60,                 // Timeout duration
                            TimeUnit.SECONDS,   // Unit of timeout
                            Authentication.this,               // Activity (for callback binding)
                            mCallbacks);        // OnVerificationStateChangedCallbacks
                }
            }
        });

        mCallbacks =new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                LayoutInflater inflater = getLayoutInflater();
                View alertLayout= inflater.inflate( R.layout.processing_dialog,null);
                AlertDialog.Builder show = new AlertDialog.Builder( Authentication.this);

                show.setView(alertLayout);
                show.setCancelable(false);
                dialog_verifying = show.create();
                dialog_verifying.show();
                signInWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {

            }
            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {

                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
                mResendToken = token;

                // ...
            }
        };

        verifyCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String verificationCode = verifyCodeET.getText().toString();
                if(verificationCode.isEmpty()){
                    Toast.makeText( Authentication.this,"Enter verifikasi code",Toast.LENGTH_SHORT).show();
                }else {

                    LayoutInflater inflater = getLayoutInflater();
                    View alertLayout= inflater.inflate( R.layout.processing_dialog,null);
                    AlertDialog.Builder show = new AlertDialog.Builder( Authentication.this);

                    show.setView(alertLayout);
                    show.setCancelable(false);
                    dialog_verifying = show.create();
                    dialog_verifying.show();

                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, verificationCode);
                    signInWithPhoneAuthCredential(credential);

                }
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {






                if (currentStep < stepView.getStepCount() - 1) {
                    currentStep++;
                    stepView.go(currentStep, true);
                } else {
                    stepView.done(true);
                }


                LayoutInflater inflater = getLayoutInflater();
                View alertLayout= inflater.inflate( R.layout.profile_create_dialog,null);
                AlertDialog.Builder show = new AlertDialog.Builder( Authentication.this);
                show.setView(alertLayout);
                show.setCancelable(false);
                profile_dialog = show.create();
                profile_dialog.show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        profile_dialog.dismiss();
                        startActivity(new Intent( Authentication.this, Login.class));
                        finish();
                    }
                },3000);


                // simpan pelanggan


                progressDialog.setMessage("Please Wait, We are Inserting Your Data on Server");
                progressDialog.show();

                // Calling method to get value from EditText.
                GetValueFromEditText();

                // Creating string request with post method.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrl,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String ServerResponse) {

                                // Hiding the progress dialog after all task complete.
                                progressDialog.dismiss();

                                // Showing response message coming from server.
                                Toast.makeText( Authentication.this, ServerResponse, Toast.LENGTH_LONG).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {

                                // Hiding the progress dialog after all task complete.
                                progressDialog.dismiss();

                                // Showing error message if something goes wrong.
                                Toast.makeText( Authentication.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() {

                        // Creating Map String Params.
                        Map<String, String> params = new HashMap<String, String>();

                        // Adding All values to Params.
                        params.put("nama", NameHolder);
                        params.put("alamat", AlamatHolder);
                        params.put("pass", PassHolder);

                        params.put("email", EmailHolder);
                        params.put("no_hp", No_hpHolder);
                        params.put("lokasi", LokasiHolder);


                        return params;
                    }

                };

                // Creating RequestQueue.
                RequestQueue requestQueue = Volley.newRequestQueue( Authentication.this);

                // Adding the StringRequest object into requestQueue.
                requestQueue.add(stringRequest);
                //end simpan pelanggan
            }
        });

    }
    @OnClick(R.id.buka_map)
    void onBtnAClick() {
        startActivityForResult(intentMaps, RC_BTN_A);
    }

    double lat;
    double lng;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            lat = data.getDoubleExtra(KEY_LAT, 0);
            lng = data.getDoubleExtra(KEY_LNG, 0);
            String alamat = null;

            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            try {
                List<Address> list = geocoder.getFromLocation(lat,lng,1);
                alamat = list.get(0).getAddressLine(0)+", "+list.get(0).getCountryName();
            } catch (IOException e) {
                e.printStackTrace();
            }
            switch (requestCode) {
                case RC_BTN_A:
                    txt_alamat.setText(alamat);

                    break;

            }
        }
    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            dialog_verifying.dismiss();
                            if (currentStep < stepView.getStepCount() - 1) {
                                currentStep++;
                                stepView.go(currentStep, true);
                            } else {
                                stepView.done(true);
                            }
                            layout1.setVisibility(View.GONE);
                            layout2.setVisibility(View.GONE);
                            layout3.setVisibility(View.VISIBLE);
                            // ...
                        } else {

                            dialog_verifying.dismiss();
                            Toast.makeText( Authentication.this,"Something wrong",Toast.LENGTH_SHORT).show();
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {

                            }
                        }
                    }
                });
    }

    // Creating method to get value from EditText.
    public void GetValueFromEditText(){
        No_hpHolder = phonenumberText.getText().toString();
        NameHolder = Atxt_nama.getText().toString();
        EmailHolder = Atxt_email.getText().toString();
        PassHolder = Atxt_pass.getText().toString();

        LokasiHolder =  +lat+","+lng ;
        AlamatHolder = Atxt_alamat.getText().toString();

    }
}
