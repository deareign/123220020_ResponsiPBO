package DAOdataperpus;

import java.sql.*;
import java.util.*;
import koneksi.connector;
import model.*;
import DAOImplement.dataperpusimplement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class dataperpusDAO implements dataperpusimplement {
    Connection connection;
     
    final String select = "SELECT * FROM buku";
    final String insert = "INSERT INTO buku (nama, penulis, rating, hasil, id) VALUES (?, ?, ?, ?, ?);";
    final String update = "UPDATE buku SET nama=?, penulis=?, rating=?, hasil=? WHERE nama=?";
    final String delete = "DELETE FROM buku WHERE nama=?";
    
    public dataperpusDAO() {
        connection = connector.connection();
    }

    @Override
    public void insert(dataperpus p) {
        if (p.getNama().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nama harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return; 
        }
       
        String Penulis = p.getPenulis();
        if (p.getPenulis().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Penulis harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return; 
        }

        
        double Rating = p.getRating();
        if (Rating < 0 || Rating > 1000000000) {
            JOptionPane.showMessageDialog(null, "Masukan Angka Yang Sesuai!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return; 
        }

        
        double Harga = p.getHarga();
        if (Harga < 0 || Harga > 1000000000) {
            JOptionPane.showMessageDialog(null, "Masukan Angka Yang Sesuai!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return; 
        }
    
    
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(insert);
            statement.setString(1, p.getNama());
            statement.setString(2, p.getPenulis());
            statement.setDouble(3, p.getRating());
            statement.setDouble(4, p.getHarga());
            statement.setDouble(5, p.hitunghasilHarga());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Gagal menambahkan data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void update(dataperpus p) {
        if (p.getNama().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nama harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return; 
        }
       
        String Penulis = p.getPenulis();
        if (p.getPenulis().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Penulis harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return; 
        }

        
        double Rating = p.getRating();
        if (Rating < 0 || Rating > 1000000000) {
            JOptionPane.showMessageDialog(null, "Masukan Angka Yang Sesuai!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return; 
        }

        
        double Harga = p.getHarga();
        if (Harga < 0 || Harga > 1000000000) {
            JOptionPane.showMessageDialog(null, "Masukan Angka Yang Sesuai!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return; 
        }
        
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(update);
            statement.setString(1, p.getPenulis());
            statement.setDouble(2, p.getRating());
            statement.setDouble(3, p.getHarga());
            statement.setDouble(4, p.hitunghasilHarga());
            statement.setString(5, p.getNama());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Data berhasil diubah!");
            } else {
                JOptionPane.showMessageDialog(null, "Gagal mengubah data!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            
            JOptionPane.showMessageDialog(null, "Gagal mengubah data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void delete(String Nama) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(delete);
            statement.setString(1, Nama);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");
            } else {
                JOptionPane.showMessageDialog(null, "Gagal menghapus data!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Gagal menghapus data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    @Override
    public int count() {
    int count = 0;
    try {
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("SELECT COUNT(*) AS count FROM perpus");
        if (rs.next()) {
            count = rs.getInt("count");
        }
    } catch (SQLException ex) {
        Logger.getLogger(dataperpusDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return count;
}

    
    @Override
    public List<dataperpus> getAll() {
        List<dataperpus> dm = null;
        try {
            dm = new ArrayList<dataperpus>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while (rs.next()) {
                dataperpus dp = new dataperpus();
                dp.setNama(rs.getString("Nama"));
                dp.setPenulis(rs.getString("Penulis"));
                dp.setRating(rs.getDouble("Rating"));
                dp.setHarga(rs.getDouble("Harga"));
                dp.setHasil(rs.getDouble("Hasil"));
                dm.add(dp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(dataperpusDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dm;
    }
}
