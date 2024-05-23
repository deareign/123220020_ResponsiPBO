/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.swing.JOptionPane;

/**
 *
 * @author LENOVO
 */
public class dataperpus {
    private Integer id;
    private String judul;
    private String penulis;
    private double rating;
    private Integer harga;
    private Integer hargaawal; 

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
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

    public Integer getHarga() {
        return harga;
    }

    public void setHarga(Integer harga) {
        this.harga = harga;
    }

    public Integer getHargaawal() {
        return hargaawal;
    }

    public void setHargaawal(Integer hargaawal) {
        this.hargaawal = hargaawal;
    }

  

    
    public int hitungHarga(int hargaawal) {
        return (int) (hargaawal + 500 + (rating * 100));
    }

   
}
