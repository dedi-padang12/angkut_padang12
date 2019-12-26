package com.angkut.pelangganp12;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class ModalService extends BottomSheetDialogFragment {
    private BottomSheetListener3 mListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.order_service, container, false);

        Button button1 = v.findViewById(R.id.btn_order);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onButtonClicked("Button 1 clicked");
                dismiss();
            }
        });

        /*filling the values in the Spinner*/
        List<String> spinnerArray = new ArrayList<String>();
        spinnerArray.add("AC");
        spinnerArray.add("KULKAS");
        spinnerArray.add("WIFI");
        spinnerArray.add("KOMPUTER");
        spinnerArray.add("CCTV");


        //Create the ArrayAdapter instance
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        //Set the Spinner with the adapter
        Spinner spinner = (Spinner) v.findViewById(R.id.txt_item_service);
        spinner.setAdapter(adapter);


        //Action to perform on functions - onItemSelected and onNothing selected

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                Object item = adapterView.getItemAtPosition(position);
                if (item != null) {




                    switch(position){
                        case 0:

                            break;
                        case 1:

                            break;
                        case 2:

                            break;

                        case 3:

                            break;
                        case 4:

                            break;


                        default:

                            break;
                    }

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // TODO Auto-generated method stub
            }
        });

        return v;
    }

    public interface BottomSheetListener3 {
        void onButtonClicked(String text);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mListener = (BottomSheetListener3) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement BottomSheetListener");
        }
    }

}