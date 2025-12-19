/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java
 */
package id.ac.unpas.praktikumpemograman2.modul10.tugas.model;

/**
 *
 * @author phikm
 */

import java.sql.*;
import id.ac.unpas.praktikumpemograman2.modul10.tugas.KoneksiDB;

public class MahasiswaModel {

    // ================= AMBIL SEMUA DATA =================
    public ResultSet getAll() throws Exception {
        Connection conn = KoneksiDB.configDB();
        Statement stm = conn.createStatement();
        return stm.executeQuery("SELECT * FROM mahasiswa");
    }

    // ================= INSERT =================
    public void insert(String nama, String nim, String jurusan) throws Exception {
        String sql = "INSERT INTO mahasiswa (nama, nim, jurusan) VALUES (?, ?, ?)";
        PreparedStatement pst = KoneksiDB.configDB().prepareStatement(sql);
        pst.setString(1, nama);
        pst.setString(2, nim);
        pst.setString(3, jurusan);
        pst.executeUpdate();
    }

    // ================= UPDATE =================
    public void update(String nama, String jurusan, String nim) throws Exception {
        String sql = "UPDATE mahasiswa SET nama=?, jurusan=? WHERE nim=?";
        PreparedStatement pst = KoneksiDB.configDB().prepareStatement(sql);
        pst.setString(1, nama);
        pst.setString(2, jurusan);
        pst.setString(3, nim);
        pst.executeUpdate();
    }

    // ================= DELETE =================
    public void delete(String nim) throws Exception {
        String sql = "DELETE FROM mahasiswa WHERE nim=?";
        PreparedStatement pst = KoneksiDB.configDB().prepareStatement(sql);
        pst.setString(1, nim);
        pst.executeUpdate();
    }

    // ================= CEK NIM =================
    public boolean nimExists(String nim) throws Exception {
        String sql = "SELECT nim FROM mahasiswa WHERE nim=?";
        PreparedStatement pst = KoneksiDB.configDB().prepareStatement(sql);
        pst.setString(1, nim);
        ResultSet rs = pst.executeQuery();
        return rs.next();
    }

    // ================= SEARCH =================
    public ResultSet search(String keyword) throws Exception {
        String sql = "SELECT * FROM mahasiswa WHERE nama LIKE ?";
        PreparedStatement pst = KoneksiDB.configDB().prepareStatement(sql);
        pst.setString(1, "%" + keyword + "%");
        return pst.executeQuery();
    }
}
