package com.example.laundryreceipt;


public class ModelLayanan {

    private String namalayanan;

    private int logolayanan;

    public ModelLayanan(String namalayanan, int logolayanan){
        this.namalayanan = namalayanan;
        this.logolayanan = logolayanan;
    }
    public String getNamalayanan(){

        return  namalayanan;
    }

    public void setNamalayanan(String namalayanan){

        this.namalayanan = namalayanan;
    }
    public  int getLogolayanan(){

        return logolayanan;
    }

    public  void  setLogolayanan(int logolayanan){

        this.logolayanan = logolayanan;
    }

    public ModelLayanan(){

    }

}
