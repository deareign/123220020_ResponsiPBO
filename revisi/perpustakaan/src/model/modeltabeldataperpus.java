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
 * @author LENOVO
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
                return "ID";
            case 1:
                return "JUDUL";
            case 2: 
                return "PENULIS";
            case 3:
                return "RATING";
            case 4:
                return "HARGA";
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int row, int column) {
        switch(column){
            case 0:
                return dm.get(row).getId();
            case 1:
                return dm.get(row).getJudul();
            case 2: 
                return dm.get(row).getPenulis();
            case 3:
                return dm.get(row).getRating();
            case 4:
                return dm.get(row).getHarga();
            default:
                return null;
        }
    }
    
}
