package com.angkut.pelangganp12.dataOrder;

public class Model {
    String id,keterangan,berat,tanggal,status,room_chat,nama,total_harga,driver,lokasi;

    public Model(String id ,String keterangan, String berat, String tanggal, String status,String room_chat,String nama ,String total_harga ,String driver ,String lokasi  ) {
        this.id = id;
        this.keterangan = keterangan;
        this.berat = berat;
        this.tanggal = tanggal;
        this.status = status;
        this.room_chat = room_chat;
        this.nama = nama;
        this.total_harga = total_harga;
        this.driver = driver;
        this.lokasi = lokasi;
    }

    public String getId(){
        return id;
    }
    public String getKeterangan(){
        return keterangan;
    }
    public String getBerat(){
        return berat;
    }
    public String getTanggal(){
        return tanggal;
    }
    public String getStatus(){
        return status;
    }
    public String getRoom_chat(){
        return room_chat;
    }
    public String getNama(){
        return nama;
    }
    public String getTotal_harga(){
        return total_harga;
    }
    public String getDriver(){
        return driver;
    }
    public String getLokasi(){
        return lokasi;
    }
}
