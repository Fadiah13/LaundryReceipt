package com.example.laundryreceipt;

public class HelperClass {
    String nama, norek, nohp, username, password;
    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public String getNorek() {
        return norek;
    }
    public void setNorek(String norek) {
        this.norek = norek;
    }
    public String getNohp() {
        return nohp;
    }
    public void setNohp(String nohp) {
        this.nohp = nohp;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public HelperClass(String nama, String norek, String nohp, String username, String password) {
        this.nama = nama;
        this.norek = norek;
        this.nohp = nohp;
        this.username = username;
        this.password = password;
    }
    public HelperClass() {
    }
}