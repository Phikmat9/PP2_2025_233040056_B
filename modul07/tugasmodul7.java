/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.praktikumpemograman2.modul07;

/**
 *
 * @author phikm
 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class tugasmodul7 extends JFrame  {
    private JTextField txtNama;
    private JTextField txtNilai;
    private JComboBox<String> cmbMatkul;
    private JTable tableData;
    private DefaultTableModel tableModel;
    private JTabbedPane tabbedPane;
    
    //method untuk desain tab input
    private JPanel createInputPanel(){
        JPanel panel = new JPanel (new GridLayout (4, 2, 10, 10));
        panel.setBorder (BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        panel.add (new JLabel("Nama Siswa:"));
        txtNama = new JTextField();
        panel.add(txtNama);
        
        panel.add (new JLabel ("Mata Pelajaran:"));
        String[] matkul = {"Matematika Dasar", 
            "Bahasa Indonesia", "Algoritma dan Pemograman I",
            "Praktikum Pemograman II"};
        cmbMatkul = new JComboBox<>(matkul);
        panel.add(cmbMatkul);
        
        panel.add(new JLabel ("Nilai (0-100):"));
        txtNilai = new JTextField();
        panel.add(txtNilai);
        
        JButton btnSimpan = new JButton ("Simpan Data");
        panel.add(btnSimpan);
        btnSimpan.addActionListener (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prosesSimpan();
            }
        });
        
    //tugas no 4 menambahkan tombol reset
        JButton btnReset = new JButton("Reset Data");
        panel.add(btnReset);
        btnReset.addActionListener (new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
                txtNama.setText("");
                txtNilai.setText("");
                cmbMatkul.setSelectedIndex(0);
            }
        });
        return panel;
    }
    
    //method untuk membuat desain tab tabel
    private JPanel CreateTablePanel() {
        JPanel panel = new JPanel (new BorderLayout());
        
        String[] kolom  = {"Nama Siswa", "Mata Pelajaran", "Nilai", "Grade"};
        tableModel = new DefaultTableModel (kolom, 0);
        tableData = new JTable (tableModel);
        
        JScrollPane scrollPane = new JScrollPane (tableData);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        
    //tugas no 2 tambah tombol hapus
        JButton btnHapus = new JButton("Hapus Data Terpilih");
        panel.add(btnHapus, BorderLayout.SOUTH);
        btnHapus.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tableData.getSelectedRow();
                if (selectedRow > -1) {
                    tableModel.removeRow(selectedRow);
                    JOptionPane.showMessageDialog(null, 
                            "Data berhasil dihapus.");
                } else {
                    JOptionPane.showMessageDialog(null, 
                            "Pilih baris yang akan dihapus.", 
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        return panel;
    }
    
    //logika validasi dan penyimpanan data
    private void prosesSimpan(){
        String nama = txtNama.getText();
        String matkul =  (String) cmbMatkul.getSelectedItem();
        String strNilai = txtNilai.getText();
        
        if (nama.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama tidak boleh kosong!",
                  "Error Validasi", JOptionPane.ERROR_MESSAGE);
            return;
            
    //tugas no 3 validasi nama lebih dari 3 karakter
        } else if (nama.trim().length()<3){
            JOptionPane.showMessageDialog(null, 
                    "Nama siswa harus lebih dari 3 karakter.",
                    "Error Validasi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int nilai;
        try {
            nilai = Integer.parseInt(strNilai);
            if (nilai < 0 || nilai > 100) {
                JOptionPane.showMessageDialog(this,
                        "Nilai harus antara 0 - 100!",
                        "Error Validasi", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "Nilai harus berupa angka!",
                "Error Validasi", JOptionPane.ERROR_MESSAGE);
            return;
        }
      
    String grade;
    
    //tugas no 1 
    
    int nilaiMatkul = nilai/10;
    switch (nilaiMatkul){
        case 10:
        case 9:
        case 8:
            grade = "A";
            break;
        case 7:
            grade = "AB";
            break;
        case 6:
            grade = "B";
            break;
        case 5:
            grade = "BC";
            break;
        case 4:
            grade = "C";
            break;
        case 3:
            grade = "D";
            break;
        default :
            grade = "E";
    }
    Object[] dataBaris = {nama, matkul, nilai, grade};
    tableModel.addRow(dataBaris);
    
    txtNama.setText("");
    txtNilai.setText("");
    cmbMatkul.setSelectedIndex(0);
    
    JOptionPane.showMessageDialog(this, "Data Berhasil Disimpan!");
    tabbedPane.setSelectedIndex(1);
    }
    
    
   public tugasmodul7() {
       setTitle("Aplikasi Manajemen Nilai Siswa");
       setSize (500, 400);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setLocationRelativeTo(null);
       
       tabbedPane = new JTabbedPane();
       
       JPanel panelInput = createInputPanel();
       tabbedPane.addTab ("Input Data", panelInput);
       
       JPanel panelTabel = CreateTablePanel();
       tabbedPane.addTab("Daftar Nilai", panelTabel);
       
       add(tabbedPane);
   }
   
   public static void main (String[] args) {
       SwingUtilities.invokeLater(()-> {
       new tugasmodul7().setVisible(true);
       });
   }
    
}
