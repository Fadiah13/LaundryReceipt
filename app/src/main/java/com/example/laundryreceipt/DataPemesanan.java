package com.example.laundryreceipt;

public class DataPemesanan {
    private String nama;
    private String noTelp;
    private String kodeResi;


    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getKodeResi() {
        return kodeResi;
    }

    public void setKodeResi(String kodeResi) {
        this.kodeResi = kodeResi;
    }

    public DataPemesanan(String nama, String noTelp, String kodeResi) {
        this.nama = nama;
        this.noTelp = noTelp;
        this.kodeResi = kodeResi;
    }

    public DataPemesanan() {
    }
}
