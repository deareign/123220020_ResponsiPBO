/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Lab Informatika
 */
public class modeltabeldataperpus extends AbstractTableModel{
    
    List<dataperpus> dm;
    public modeltabeldataperpus(List<dataperpus>dm) {
        this.dm = dm;
        
    }
    
    @Override
    public int getRowCount() {
        return dm.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }
    
    @Override
    public String getColumnName(int column){
        switch(column){
            case 0:
                return "NAMA BUKU";
            case 1:
                return "PENULIS";
            case 2: 
                return "RATING";
            case 3:
                return "HARGA";
            case 4:
                return "HASIL";
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int row, int column) {
        switch(column){
            case 0:
                return dm.get(row).getNama();
            case 1:
                return dm.get(row).getPenulis();
            case 2: 
                return dm.get(row).getRating();
            case 3:
                return dm.get(row).getHarga();
            case 4:
                return dm.get(row).getHasil();
            default:
                return null;
        }
    }
    
}
