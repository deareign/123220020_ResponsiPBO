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
    final String insert = "INSERT INTO buku (judul, penulis, rating, harga ) VALUES ( ?, ?, ?, ?);";
    final String update = "UPDATE buku SET judul=?, penulis=?, rating=?, harga=? WHERE id=?";
    final String delete = "DELETE FROM buku WHERE id=?";
    
    public dataperpusDAO() {
        connection = connector.connection();
    }

    @Override
    public void insert(dataperpus p) {
        if (p.getJudul().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Judul harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return; 
        }
        
        if (p.getPenulis().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Penulis harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return; 
        }
       
       
        

        
        double rating = p.getRating();
        if (rating <= 0) {
            JOptionPane.showMessageDialog(null, "Nilai rating harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return; 
        }

        
       
    
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(insert);
            
            statement.setString(1, p.getJudul());
            statement.setString(2, p.getPenulis());
            statement.setDouble(3, p.getRating());
            statement.setInt(4, p.getHarga());
           
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
       if (p.getJudul().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Judul harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return; 
        }
        
        if (p.getPenulis().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Penulis harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return; 
        }
       
       
       

        
        double rating = p.getRating();
        if (rating <= 0) {
            JOptionPane.showMessageDialog(null, "Nilai rating harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return; 
        }
        
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(update);
            statement.setString(1, p.getJudul());
            statement.setString(2, p.getPenulis());
            statement.setDouble(3, p.getRating());
            statement.setInt(4, p.getHarga());
            
            statement.setInt(5, p.getId());
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
    public void delete(Integer id) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(delete);
            statement.setInt(1, id);
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
        ResultSet rs = st.executeQuery("SELECT COUNT(*) AS count FROM buku");
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
                dataperpus mv = new dataperpus();
                mv.setId(rs.getInt("id"));
                mv.setJudul(rs.getString("judul"));
                mv.setPenulis(rs.getString("penulis"));
                mv.setRating(rs.getDouble("rating"));
                mv.setHarga(rs.getInt("harga"));
                dm.add(mv);
            }
        } catch (SQLException ex) {
            Logger.getLogger(dataperpusDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dm;
    }
}
