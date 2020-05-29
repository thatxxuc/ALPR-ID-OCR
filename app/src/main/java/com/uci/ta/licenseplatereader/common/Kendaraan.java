package com.uci.ta.licenseplatereader.common;

import java.io.Serializable;

public class Kendaraan implements Serializable
{

    private String nama;
    private String no_plat;
    private String img;
    private String alamat;
    private String pel;

    public Kendaraan()
    {
    }

    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public String getNo_plat() {
        return no_plat;
    }
    public void setNo_plat(String no_plat) {
        this.no_plat = no_plat;
    }
    public void setImg(String img) {
        this.img = img;
    }
    public String getImg() {
        return img;
    }
    public String getAlamat(){return alamat;}
    public void setAlamat(String alamat){this.alamat = alamat;}
    public String getPel(){return pel;}
    public void setPel(String pel){this.pel = pel;}

    public String toString()
    {
        return

                "" +nama;
    }
    public Kendaraan(String pn, String nm, String j)
    {
        nama=nm;
    }
}
