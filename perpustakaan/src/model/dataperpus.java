/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.swing.JOptionPane;

/**
 *
 * @author Lab Informatika
 */
public class dataperpus {
    private String nama;
    private String penulis;
    private double rating;
    private double harga; 
    private double hasil; 
    private double k = 500; 

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
      
        this.penulis = penulis;
    }

    public double getRating() {
        return rating;

    }

    public void setRating(double rating) {
       
        this.rating = rating;
    }

    public double getHarga() {
       
        return harga;
    }

    public void setHarga(double harga) {
       
        this.harga = harga;
    }

    public double getHasil() {
        return hasil;
    }

    public void setHasil(double hasil) {
        this.hasil = hasil;
    }
    
    public double hitunghasilHarga() {
        return harga+k+(rating*100);
    }

   
}
