package com.angkut.pelangganp12;

import android.app.Application;

public class Mykoneksi extends Application {

    private static Mykoneksi mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
    }

    public static synchronized Mykoneksi getInstance(){
        return mInstance;
    }

    public void setConnectivityListener (ConnectivityReceiver.ConnectivityReceiverListener listener){
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }
}