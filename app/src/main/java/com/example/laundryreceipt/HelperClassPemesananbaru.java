package com.example.laundryreceipt;

public class HelperClassPemesananbaru {

    private String nama, nohp, alamat;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNohp() {
        return nohp;
    }

    public void setNohp(String nohp) {
        this.nohp = nohp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }


    public HelperClassPemesananbaru(String nama, String nohp, String alamat) {
        this.nama = nama;
        this.nohp = nohp;
        this.alamat = alamat;
    }

    public HelperClassPemesananbaru(){

    }
}
