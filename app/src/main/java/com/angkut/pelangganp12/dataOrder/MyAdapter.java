package com.angkut.pelangganp12.dataOrder;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.angkut.pelangganp12.R;
import com.angkut.pelangganp12.chat.chatroom;
import com.angkut.pelangganp12.chat.chatting;
import com.angkut.pelangganp12.chat.history_chatroom;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private List<Model> listItems;
    private Context context;
    private ProgressDialog dialog;
  String  status;
    public static final String TAG_STT = "status";
    private String status_cht;
    public MyAdapter(List<Model> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView id;
        public TextView keterangan;
        public TextView berat;
        public TextView tanggal;
        public TextView status;
       // public TextView lokasi;
        public CardView card_view;

        String nama,room_chat;
        public ViewHolder(View itemView ) {
            super(itemView);
           // id = (TextView) itemView.findViewById(R.id.id);
            keterangan = (TextView) itemView.findViewById( R.id.keterangan);
            berat = (TextView) itemView.findViewById(R.id.berat);
            tanggal = (TextView) itemView.findViewById(R.id.tanggal);
            status = (TextView) itemView.findViewById(R.id.status);
        //    lokasi = (TextView) itemView.findViewById(R.id.lokasi);
            card_view = (CardView) itemView.findViewById(R.id.card_view);
        }


    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Model listItem = listItems.get(position);
//        holder.id.setText(listItem.getId());
        holder.keterangan.setText(listItem.getKeterangan());
        holder.berat.setText(listItem.getBerat());
        holder.tanggal.setText(listItem.getTanggal());
        holder.status.setText(listItem.getStatus());
      //  holder.lokasi.setText(listItem.getLokasi());
        status = listItem.getStatus();

        holder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Intent intent;
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                final ProgressDialog dialog = new ProgressDialog(view.getContext());
                dialog.setMessage("Loading Cancel Data");
                final CharSequence[] dialogitem = {"Lihat Data","Chat Driver","Cancel"};
                builder.setTitle(listItem.getKeterangan());
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    ///untuk chat
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0 :
                                Intent intent = new Intent(view.getContext(), DetailData.class);
                                intent.putExtra("id", listItem.getId());
                                intent.putExtra("keterangan",listItem.getKeterangan());
                                intent.putExtra("berat",listItem.getBerat());
                                intent.putExtra("tanggal",listItem.getTanggal());
                                intent.putExtra("status", listItem.getStatus());
                                intent.putExtra("total_harga", listItem.getTotal_harga());
                                intent.putExtra("driver", listItem.getDriver());
                                intent.putExtra("lokasi", listItem.getLokasi());
                                view.getContext().startActivity(intent);
                                break;
                                //end chat
                            case 1 :

                                if (listItem.getStatus().equals("diterima")) {
                                    Intent I = new Intent( view.getContext(), chatting.class );
                                    I.putExtra("room_name",listItem.getRoom_chat());
                                    I.putExtra("user_name",listItem.getNama());
                                    view.getContext().startActivity( I );
                                } else   if (listItem.getStatus().equals("selesai")){
                                    Intent I = new Intent( view.getContext(), history_chatroom.class );
                                    I.putExtra("room_name",listItem.getRoom_chat());
                                    I.putExtra("user_name",listItem.getNama());
                                    view.getContext().startActivity( I );
                                } else   if (listItem.getStatus().equals("proses")) {
                                    Toast.makeText(view.getContext(), "Sedang mencari driver...",
                                            Toast.LENGTH_LONG).show();
                                }else   if (listItem.getStatus().equals("cancel")) {
                                    Toast.makeText(view.getContext(), "Pesanan diCancel!",
                                            Toast.LENGTH_LONG).show();
                                }


                                break;
                            case 2 :
                             String   URL_CANCEL ="http://192.168.1.4/bang_angkut/cancel_order.php";
                                AlertDialog.Builder builderDel = new AlertDialog.Builder(view.getContext());
                                builderDel.setTitle(listItem.getKeterangan());
                                builderDel.setMessage("Yakin mau di cancel?");
                                builderDel.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialog.show();

                                        StringRequest stringRequest = new StringRequest( Request.Method.POST, URL_CANCEL, new Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String response) {
                                                dialog.hide();
                                                dialog.dismiss();
                                                Toast.makeText(view.getContext(),"Successfully cancel Data "+ listItem.getKeterangan(),Toast.LENGTH_LONG).show();
                                                ListActivity.ma.refresh_list();

                                            }
                                        }, new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError error) {
                                                dialog.hide();
                                                dialog.dismiss();
                                            }
                                        }){
                                            protected HashMap<String, String> getParams() throws AuthFailureError {
                                                Map<String, String> params= new HashMap<>();
                                                params.put("id",listItem.getId());
                                                return (HashMap<String, String>) params;

                                            }
                                        };
                                        RequestHandler.getInstance(view.getContext()).addToRequestQueue(stringRequest);
                                        dialogInterface.dismiss();
                                    }
                                });

                                builderDel.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override

                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                });


                                builderDel.create().show();
                                break;
                        }
                    }
                });

                builder.create().show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }
}