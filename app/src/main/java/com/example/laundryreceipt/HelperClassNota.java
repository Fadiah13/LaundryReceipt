package com.example.laundryreceipt;

public class HelperClassNota {

    private String namalayanan, total;

    public HelperClassNota(){

    }

    public HelperClassNota(String namalayanan, String total) {
        this.namalayanan = namalayanan;
        this.total = total;
    }

    public String getNamalayanan() {
        return namalayanan;
    }

    public void setNamalayanan(String namalayanan) {
        this.namalayanan = namalayanan;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
