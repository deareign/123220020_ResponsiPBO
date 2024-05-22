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
import model.*;
import view.MainView;
/**
 *
 * @author Lab Informatika
 */
public class dataperpuscontroller {
    MainView frame;
    dataperpusimplement impldataperpus;
    List<dataperpus> dp;
    
    public dataperpuscontroller(MainView frame) {
        this.frame = frame;
        impldataperpus = new dataperpusDAO();
        dp = impldataperpus.getAll();
    }
    
    public void isitabel(){
        dp = impldataperpus.getAll();
        modeltabeldataperpus mm = new modeltabeldataperpus(dp);
        frame.getTabelDataperpus().setModel(mm);
        int count = impldataperpus.count();
        

    }
    
    public void insert(){
    try {
        dataperpus dp = new dataperpus();
        dp.setnama(frame.getJTxtnama().getText());
        dp.setpenulis(Double.parseDouble(frame.getJTxtpenulis().getText()));
        dp.setrating(Double.parseDouble(frame.getJTxtrating().getText()));
        dp.setharga(Double.parseDouble(frame.getJTxtharga().getText()));
        impldataperpus.insert(dp);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Input tidak valid. Harap masukkan hasil yang sesuai, jangan dikosongkan!", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

public void update(){
    try {
        dataperpus dp = new dataperpus();
        dp.setnama(frame.getJTxtnama().getText());
        dp.setpenulis(Double.parseDouble(frame.getJTxtpenulis().getText()));
        dp.setrating(Double.parseDouble(frame.getJTxtrating().getText()));
        dp.setharga(Double.parseDouble(frame.getJTxtharga().getText()));
        impldataperpus.update(dp);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Input tidak valid. Harap masukkan hasil yang sesuai, jangan dikosongkan!", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    
    public void delete(){
        try {
        String nama = frame.getJTxtnama().getText();
            if(nama.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Silakan pilih data yang ingin dihapus!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            } else {
                impldataperpus.delete(nama);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat menghapus data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void reset() {
        frame.getJTxtnama().setText(""); 
        frame.getJTxtpenulis().setText(""); 
        frame.getJTxtrating().setText(""); 
        frame.getJTxtharga().setText("");
        frame.getJTxthasil().setText("");
    }
    
   

    
}
