package id.ac.unpas.praktikumpemograman2.modul10.tugas.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MahasiswaView extends JFrame {

    private JTextField txtNama, txtNIM, txtJurusan, txtCari;
    private JButton btnSimpan, btnEdit, btnHapus, btnClear, btnCari;
    private JTable tableMahasiswa;
    private DefaultTableModel tableModel;

    public MahasiswaView() {
        setTitle("Aplikasi CRUD Mahasiswa (MVC)");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ===== FORM =====
        JPanel panelForm = new JPanel(new GridLayout(3, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelForm.add(new JLabel("Nama"));
        txtNama = new JTextField();
        panelForm.add(txtNama);

        panelForm.add(new JLabel("NIM"));
        txtNIM = new JTextField();
        panelForm.add(txtNIM);

        panelForm.add(new JLabel("Jurusan"));
        txtJurusan = new JTextField();
        panelForm.add(txtJurusan);

        // ===== TOMBOL =====
        JPanel panelTombol = new JPanel(new FlowLayout());
        btnSimpan = new JButton("Simpan");
        btnEdit = new JButton("Edit");
        btnHapus = new JButton("Hapus");
        btnClear = new JButton("Clear");

        panelTombol.add(btnSimpan);
        panelTombol.add(btnEdit);
        panelTombol.add(btnHapus);
        panelTombol.add(btnClear);

        // ===== CARI =====
        JPanel panelCari = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelCari.add(new JLabel("Cari Nama"));
        txtCari = new JTextField(20);
        btnCari = new JButton("Cari");
        panelCari.add(txtCari);
        panelCari.add(btnCari);

        JPanel panelAtas = new JPanel(new BorderLayout());
        panelAtas.add(panelForm, BorderLayout.NORTH);
        panelAtas.add(panelTombol, BorderLayout.CENTER);
        panelAtas.add(panelCari, BorderLayout.SOUTH);

        add(panelAtas, BorderLayout.NORTH);

        // ===== TABLE =====
        tableModel = new DefaultTableModel(
            new Object[]{"No", "Nama", "NIM", "Jurusan"}, 0
        );
        tableMahasiswa = new JTable(tableModel);
        add(new JScrollPane(tableMahasiswa), BorderLayout.CENTER);
    }

    // ===== GETTER (INI KUNCI MVC) =====
    public JTextField getTxtNama() {
        return txtNama;
    }

    public JTextField getTxtNIM() {
        return txtNIM;
    }

    public JTextField getTxtJurusan() {
        return txtJurusan;
    }

    public JTextField getTxtCari() {
        return txtCari;
    }

    public JButton getBtnSimpan() {
        return btnSimpan;
    }

    public JButton getBtnEdit() {
        return btnEdit;
    }

    public JButton getBtnHapus() {
        return btnHapus;
    }

    public JButton getBtnClear() {
        return btnClear;
    }

    public JButton getBtnCari() {
        return btnCari;
    }

    public JTable getTableMahasiswa() {
        return tableMahasiswa;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }
}
