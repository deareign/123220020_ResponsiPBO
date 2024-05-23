/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import java.util.List;
import DAOdataperpus.dataperpusDAO;
import DAOImplement.dataperpusimplement;
import javax.swing.JOptionPane;
import model.dataperpus;
import model.modeltabeldataperpus;
import view.MainView;
/**
 *
 * @author LENOVO
 */
public class dataperpuscontroller {
    MainView frame;
    dataperpusimplement impldataperpus;
    List<dataperpus> dm;
    
    public dataperpuscontroller(MainView frame) {
        this.frame = frame;
        impldataperpus = new dataperpusDAO();
        dm = impldataperpus.getAll();
    }
    
    public void isitabel(){
        dm = impldataperpus.getAll();
        modeltabeldataperpus mm = new modeltabeldataperpus(dm);
        frame.getTabelDataperpus().setModel(mm);
        int count = impldataperpus.count();
    frame.getJTxtjumlah().setText(Integer.toString(count));

    }
    
    public void insert(){
    try {
        dataperpus dm = new dataperpus();
        
        dm.setJudul(frame.getJTxtjudul().getText());
        dm.setPenulis(frame.getJTxtpenulis().getText());
        dm.setRating(Double.parseDouble(frame.getJTxtrating().getText()));
        // Mengambil hargaawal dari input pengguna
    int hargaawal = Integer.parseInt(frame.getJTxthargaawal().getText());

    // Menghitung harga akhir
    dm.setHarga(dm.hitungHarga(hargaawal));
    frame.getJTxtharga().setText(String.valueOf(dm.getHarga()));
        
        impldataperpus.insert(dm);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Input tidak valid. Harap masukkan nilai yang sesuai, jangan dikosongkan!", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

public void update(){
    try {
        dataperpus dm = new dataperpus();
        dm.setId(Integer.parseInt(frame.getJTxtid().getText()));
        dm.setJudul(frame.getJTxtjudul().getText());
        dm.setPenulis(frame.getJTxtpenulis().getText());
        dm.setRating(Double.parseDouble(frame.getJTxtrating().getText()));
        
        // Mengambil harga awal dari input pengguna
        int hargaawal = Integer.parseInt(frame.getJTxthargaawal().getText());

        // Menghitung harga akhir
        dm.setHarga(dm.hitungHarga(hargaawal));
        
        impldataperpus.update(dm);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Input tidak valid. Harap masukkan nilai yang sesuai, jangan dikosongkan!", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    
    public void delete(){
        try {
        Integer id = Integer.parseInt(frame.getJTxtid().getText());
            if(id <=0) {
                JOptionPane.showMessageDialog(null, "Silakan pilih data yang ingin dihapus!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            } else {
                impldataperpus.delete(id);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat menghapus data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void reset() {
        frame.getJTxtid().setText(""); 
        frame.getJTxtjudul().setText(""); 
        frame.getJTxtpenulis().setText(""); 
        frame.getJTxtrating().setText("");
        frame.getJTxtharga().setText("");
    }
    
   

    
}
