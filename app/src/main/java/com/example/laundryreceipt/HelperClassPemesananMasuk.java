package com.example.laundryreceipt;

public class HelperClassPemesananMasuk {
    String nopesanan, nama, nohp, estimasi;

    public HelperClassPemesananMasuk(){

    }

    public String getNopesanan() {
        return nopesanan;
    }

    public void setNopesanan(String nopesanan) {
        this.nopesanan = nopesanan;
    }

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
    public String getEstimasi() {
        return estimasi;
    }

    public void setEstimasi(String estimasi) {
        this.estimasi = estimasi;
    }

    public HelperClassPemesananMasuk(String nopesanan, String nama, String nohp, String estimasi) {
        this.nopesanan = nopesanan;
        this.nama = nama;
        this.nohp = nohp;
        this.estimasi = estimasi;
    }
}
