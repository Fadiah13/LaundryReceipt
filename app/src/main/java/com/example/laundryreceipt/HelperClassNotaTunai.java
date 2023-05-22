package com.example.laundryreceipt;

public class HelperClassNotaTunai {

    private String namalayanan, total;

    public HelperClassNotaTunai(){

    }

    public HelperClassNotaTunai(String namalayanan, String total) {
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
