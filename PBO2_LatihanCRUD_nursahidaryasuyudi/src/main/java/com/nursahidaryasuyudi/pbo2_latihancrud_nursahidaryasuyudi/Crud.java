package com.nursahidaryasuyudi.pbo2_latihancrud_nursahidaryasuyudi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Crud {
    private final String URL = "jdbc:mysql://localhost/cash?serverTimezone=Asia/Makassar";
    private final String USER = "root";
    private final String PASSWORD = "";
    
    public Connection getConnection(){
        Connection conn;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // untuk mengecek driver
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Koneksi berhasil");
            return conn;
        } catch (ClassNotFoundException ex) {
            System.err.println("ERROR ClassNotFoundException : " + ex.toString());
//            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            System.err.println("ERROR SQLException : " + ex.toString());
//            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    public void read(){
        
        Connection conn = new Crud().getConnection();
        Statement stmt;
        
        try {
            stmt = conn.createStatement();
            ResultSet resultset = stmt.executeQuery("SELECT * FROM cashlanggar");
            
            while(resultset.next()){    
                System.out.println(resultset.getString("kode_barang"));
                System.out.println(resultset.getString("nama_barang"));
                System.out.println(resultset.getString("jumlah_barang"));
                System.out.println(resultset.getString("harga"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Crud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    public void create(String kode_barang, String nama_barang, int jumlah, int harga){
        Connection conn = new Crud().getConnection();
        String SQL = "INSERT INTO cashlanggar VALUES (?,?,?,?)";
            
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(SQL);
            ps.setString(1,kode_barang);
            ps.setString(2,nama_barang);
            ps.setInt(3,jumlah);
            ps.setInt(4,harga);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Crud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void delete(String kode_barang){
        Connection conn = new Crud().getConnection();
        String SQL = "DELETE FROM cashlanggar WHERE kode_barang = ?";
            
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(SQL);
            ps.setString(1,kode_barang);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Crud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void update(String kode_barang, String nama_barang, int jumlah, int harga){
        Connection conn = new Crud().getConnection();
        String SQL = "UPDATE cashlanggar SET Nama_barang= ?, Jumlah_barang = ?, Harga = ? WHERE Kode_barang = ?";
            
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(SQL);
            ps.setString(1,nama_barang);
            ps.setInt(2,jumlah);
            ps.setInt(3,harga);
            ps.setString(4,kode_barang);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Crud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {
        Crud x = new Crud();
        x.delete("2");
        x.read();
        x.update("1","peci",5,50000);
        x.read();
    }

}
