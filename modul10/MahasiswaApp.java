/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.praktikumpemograman2.modul10;

/**
 *
 * @author phikm
 */
import javax.swing.*;
import javax.swing.table. DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event. MouseAdapter;
import java.awt.event. MouseEvent;
import java.sql.Connection;
import java.sql. PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class MahasiswaApp extends JFrame{
    // Komponen GUI
    JTextField txtNama, txtNIM, txtJurusan, txtCari;
    JButton btnSimpan, btnEdit, btnHapus, btnClear, btnCari;
    JTable tableMahasiswa;
    DefaultTableModel model;
    
    public MahasiswaApp() {
        // Setup Frame
        setTitle("Aplikasi CRUD Mahasiswa JDBC");
        setSize (600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        
        // 1. Panel Form (Input Data)
        JPanel panelForm = new JPanel (new GridLayout (3, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        panelForm.add(new JLabel("Nama:"));
        txtNama = new JTextField();
        panelForm.add(txtNama);
        
        panelForm.add(new JLabel("NIM:"));
        txtNIM = new JTextField();
        panelForm.add(txtNIM);
        
        panelForm.add(new JLabel("Jurusan:"));
        txtJurusan = new JTextField();
        panelForm.add(txtJurusan);
        //////  panel input//////
       
  
        ///// Panel Tombol////
        JPanel panelTombol = new JPanel (new FlowLayout(FlowLayout.CENTER, 10, 5));
        btnSimpan = new JButton("Simpan");
        btnEdit = new JButton("Edit");
        btnHapus = new JButton("Hapus");
        btnClear = new JButton("Clear"); 

        panelTombol.add(btnSimpan);
        panelTombol.add(btnEdit);
        panelTombol.add(btnHapus);
        panelTombol.add(btnClear);
        ////// panel tombol /////
        
        
        ////// LATIHAN 3 tambah filed cari//////
        ///panel cari/////
        JPanel panelCari = new JPanel(new GridLayout(2, 1, 5, 5));
        panelCari.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
        
        panelCari.add(new JLabel("Cari Nama:"));
        
        JPanel panelCariInput = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
        txtCari = new JTextField(20);
        btnCari = new JButton("Cari"); 
        
        panelCariInput.add(txtCari);
        panelCariInput.add(btnCari); 
        
        panelCari.add(panelCariInput);
        //////panel cari////
        
        // Gabungkan Panel Form dan Tombol di bagian Atas from + tombol + cari (NORTH)
        JPanel panelAtas = new JPanel (new BorderLayout()); 
        panelAtas.add(panelForm, BorderLayout.NORTH);
        
        JPanel panelBawahForm = new JPanel (new BorderLayout());
        panelBawahForm.add(panelTombol, BorderLayout.NORTH);
        panelBawahForm.add(panelCari, BorderLayout.SOUTH);
        
        panelAtas.add(panelBawahForm, BorderLayout.CENTER);
        add (panelAtas, BorderLayout. NORTH);
        
        // 2. Tabel Data (Menampilkan Data)
        model = new DefaultTableModel();
        model.addColumn ("No");
        model.addColumn("Nama");
        model.addColumn ("NIM");
        model.addColumn("Jurusan");
        
        tableMahasiswa = new JTable(model);
        JScrollPane scrollPane = new JScrollPane (tableMahasiswa);
        add (scrollPane, BorderLayout.CENTER);
        
        //--- Event Listeners ---
        
        // Listener Klik Tabel (Untuk mengambil data saat baris diklik)
        tableMahasiswa.addMouseListener(new MouseAdapter() {
            @Override
                public void mouseClicked (MouseEvent e) {
                int row = tableMahasiswa.getSelectedRow();
                txtNama.setText (model.getValueAt(row, 1).toString());
                txtNIM.setText(model.getValueAt(row, 2).toString());
                txtJurusan.setText(model.getValueAt(row, 3).toString());
                
                txtNIM.setEditable(false);
                }
            });
        
        // Aksi Tombol Simpan (CREATE)
        btnSimpan.addActionListener(e -> tambahData());
        
        // Aksi Tombol Edit (UPDATE)
        btnEdit.addActionListener(e -> ubahData());
        
        //Aksi tombol (delet)
        btnHapus.addActionListener(e -> hapusData());
        
        // Aksi tombol clear
        btnClear.addActionListener(e -> kosongkanForm());
        
        //// event tbl cari
        btnCari.addActionListener(e -> cariData());

        // Load data saat aplikasi pertama Jalan
        loadData();
    }    
    
    // LOGIKA CRUD
    
    // 1. READ (Menampilkan Data)
    private void loadData() {
        model.setRowCount(0); // Reset tabel
        try {
            Connection conn = KoneksiDB.configDB();
            Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM mahasiswa");
            int no = 1;
            while (res.next()) {
                model.addRow (new Object[] {
                    no++,
                    res.getString("nama"),
                    res.getString("nim"),
                    res.getString("jurusan")
                }); 
            }
        } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Gagal Load Data: " + e.getMessage());
        }
    }
    
    // 2. CREATE (Menambah Data)
    private void tambahData() {
        
        ////latihan 2  validasi inputan////
        // VALIDASI INPUT
        if (txtNama.getText().trim().isEmpty() ||
            txtNIM.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                this,
                "Data tidak boleh kosong!",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
            return; // hentikan proses simpan
        }
        
        // cek nim duplikat
        if (nimSudahAda(txtNIM.getText().trim())) {
            JOptionPane.showMessageDialog(
                this,
                "NIM sudah terdaftar!",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
            return; // hentikan INSERT
        }
        
        try {
            String sql = "INSERT INTO mahasiswa (nama, nim, jurusan) VALUES (?, ?, ?)";
            Connection conn = KoneksiDB.configDB();
            PreparedStatement pst = conn.prepareStatement(sql);
            
            pst.setString(1, txtNama.getText());
            pst.setString(2, txtNIM.getText());
            pst.setString (3, txtJurusan.getText());
            
            pst.execute();
            JOptionPane.showMessageDialog(this, "Data Berhasil Disimpan");
            loadData();
            kosongkanForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal Simpan: " + e.getMessage());
        }
    }
    
    //3. UPDATE (Mengubah Data berdasarkan NIM)
    private void ubahData() {
        try{
            String sql= "UPDATE mahasiswa SET nama = ?, jurusan = ? WHERE nim = ?"; 
            Connection conn = KoneksiDB.configDB();
            PreparedStatement pst = conn.prepareStatement(sql);
            
            pst.setString(1, txtNama.getText());
            pst.setString(2, txtJurusan.getText());
            pst.setString(3, txtNIM.getText()); // Kunci update
            
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data Berhasil Diubah");
            loadData();
            kosongkanForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal Edit: " + e.getMessage());
        }
    }
    
    // 4. DELETE (Menghapus Data).
    private void hapusData() {
        try {
            String sql = "DELETE FROM mahasiswa WHERE nim = ?";
            Connection conn = KoneksiDB.configDB();
            PreparedStatement pst = conn.prepareStatement (sql);
            pst.setString(1, txtNIM.getText());
            pst.execute();
            JOptionPane.showMessageDialog(this, "Data Berhasil Dihapus");
            loadData();
            kosongkanForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal Hapus: " + e.getMessage());
        }
    }
    
    ////method tombol cari////
    private void cariData() {
        model.setRowCount(0); // reset tabel

        try {
            String keyword = txtCari.getText().trim();

            // Jika kosong, tampilkan semua data
            if (keyword.isEmpty()) {
                loadData();
                return;
            }

            Connection conn = KoneksiDB.configDB();
            String sql = "SELECT * FROM mahasiswa WHERE nama LIKE ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, "%" + keyword + "%");

            ResultSet res = pst.executeQuery();
            int no = 1;

            while (res.next()) {
                model.addRow(new Object[]{
                    no++,
                    res.getString("nama"),
                    res.getString("nim"),
                    res.getString("jurusan")
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Gagal Cari Data: " + e.getMessage());
        }
    }

    
    private void kosongkanForm() {
    txtNama.setText(null);
    txtNIM.setText(null);
    txtJurusan.setText(null);
    txtCari.setText(null);
    
    txtNIM.setEditable(true);
    loadData();
    }
    
    ////latihan 4 method penecek NIM////
    private boolean nimSudahAda(String nim) {
        try {
            Connection conn = KoneksiDB.configDB();
            String sql = "SELECT nim FROM mahasiswa WHERE nim = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, nim);
            ResultSet rs = pst.executeQuery();

            return rs.next(); // true jika NIM ditemukan
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Gagal cek NIM: " + e.getMessage());
            return true; // anggap sudah ada agar insert dibatalkan
        }
    }


    
    public static void main(String[] args) {
        // Menjalankan Aplikasi
        SwingUtilities.invokeLater(() -> new MahasiswaApp().setVisible(true));
    }
    
    
    
}
