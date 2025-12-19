/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java
 */
package id.ac.unpas.praktikumpemograman2.modul10.tugas.controller;

/**
 *
 * @author phikm
 */

import id.ac.unpas.praktikumpemograman2.modul10.tugas.model.MahasiswaModel;
import id.ac.unpas.praktikumpemograman2.modul10.tugas.view.MahasiswaView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class MahasiswaController  {

    private MahasiswaView view;
    private MahasiswaModel model;

    public MahasiswaController(MahasiswaView view, MahasiswaModel model) {
        this.view = view;
        this.model = model;

        // pasang event listener
        view.getBtnSimpan().addActionListener(e -> simpan());
        view.getBtnEdit().addActionListener(e -> edit());
        view.getBtnHapus().addActionListener(e -> hapus());
        view.getBtnClear().addActionListener(e -> clear());
        view.getBtnCari().addActionListener(e -> cari());

        // load data awal
        loadData();

        // klik tabel → isi form
        view.getTableMahasiswa().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                isiFormDariTabel();
            }
        });
    }

    // ================= LOAD DATA =================
    private void loadData() {
        try {
            DefaultTableModel table = view.getTableModel();
            table.setRowCount(0);

            ResultSet rs = model.getAll();
            int no = 1;

            while (rs.next()) {
                table.addRow(new Object[]{
                    no++,
                    rs.getString("nama"),
                    rs.getString("nim"),
                    rs.getString("jurusan")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view,
                "Gagal load data\n" + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ================= SIMPAN =================
    private void simpan() {
        try {
            if (view.getTxtNama().getText().trim().isEmpty()
                || view.getTxtNIM().getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(view,
                    "Data tidak boleh kosong!",
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            if (model.nimExists(view.getTxtNIM().getText())) {
                JOptionPane.showMessageDialog(view,
                    "NIM sudah terdaftar!",
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            model.insert(
                view.getTxtNama().getText(),
                view.getTxtNIM().getText(),
                view.getTxtJurusan().getText()
            );

            JOptionPane.showMessageDialog(view, "Data berhasil disimpan");
            loadData();
            clear();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(view,
                "Gagal simpan\n" + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ================= EDIT =================
    private void edit() {
        try {
            model.update(
                view.getTxtNama().getText(),
                view.getTxtJurusan().getText(),
                view.getTxtNIM().getText()
            );

            JOptionPane.showMessageDialog(view, "Data berhasil diubah");
            loadData();
            clear();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(view,
                "Gagal edit\n" + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ================= HAPUS =================
    private void hapus() {
        try {
            model.delete(view.getTxtNIM().getText());

            JOptionPane.showMessageDialog(view, "Data berhasil dihapus");
            loadData();
            clear();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(view,
                "Gagal hapus\n" + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ================= CARI =================
    private void cari() {
        try {
            DefaultTableModel table = view.getTableModel();
            table.setRowCount(0);

            ResultSet rs = model.search(view.getTxtCari().getText());
            int no = 1;

            while (rs.next()) {
                table.addRow(new Object[]{
                    no++,
                    rs.getString("nama"),
                    rs.getString("nim"),
                    rs.getString("jurusan")
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(view,
                "Gagal cari data\n" + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ================= CLEAR =================
    private void clear() {
        view.getTxtNama().setText("");
        view.getTxtNIM().setText("");
        view.getTxtJurusan().setText("");
        view.getTxtCari().setText("");
        view.getTxtNIM().setEditable(true);
        view.getTableMahasiswa().clearSelection();
    }

    // ================= ISI FORM DARI TABEL =================
    private void isiFormDariTabel() {
        int row = view.getTableMahasiswa().getSelectedRow();
        if (row >= 0) {
            view.getTxtNama().setText(
                view.getTableMahasiswa().getValueAt(row, 1).toString()
            );
            view.getTxtNIM().setText(
                view.getTableMahasiswa().getValueAt(row, 2).toString()
            );
            view.getTxtJurusan().setText(
                view.getTableMahasiswa().getValueAt(row, 3).toString()
            );
            view.getTxtNIM().setEditable(false);
        }
    }
}
