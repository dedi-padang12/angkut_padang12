package com.angkut.pelangganp12;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class ModalOrderMobil extends BottomSheetDialogFragment {
    private BottomSheetListener2 mListener;
String jarakX;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.order_mobil, container, false);
        EditText total2= v.findViewById(R.id.txt_keterangan);
        Button button1 = v.findViewById(R.id.button1);
        jarakX = getActivity().getIntent().getExtras().getString("jarak");
        total2.setText( jarakX );
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onButtonClicked("Button 1 clicked");
                dismiss();
            }
        });


        return v;
    }

    public interface BottomSheetListener2 {
        void onButtonClicked(String text);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mListener = (BottomSheetListener2) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement BottomSheetListener");
        }
    }

}