package com.example.laundryreceipt;

public class HelperClassDetailLayanan {

    private String namalayanan, kuantitas, hari, harga;

    public HelperClassDetailLayanan(String namalayanan, String kuantitas, String hari, String harga) {
        this.namalayanan = namalayanan;
        this.kuantitas = kuantitas;
        this.hari = hari;
        this.harga = harga;
    }

    public HelperClassDetailLayanan(){

    }
    public String getNamalayanan() {
        return namalayanan;
    }

    public void setNamalayanan(String namalayanan) {
        this.namalayanan = namalayanan;
    }

    public String getKuantitas() {
        return kuantitas;
    }

    public void setKuantitas(String kuantitas) {
        this.kuantitas = kuantitas;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }
}
