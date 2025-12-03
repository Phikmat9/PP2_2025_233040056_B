/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.praktikumpemograman2.modul08.controller;

/**
 *
 * @author phikm
 */

import id.ac.unpas.praktikumpemograman2.modul08.model.PersegiPanjangModel;
import id.ac.unpas.praktikumpemograman2.modul08.view.PersegiPanjangView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PersegiPanjangController {
    private PersegiPanjangModel model;
    private PersegiPanjangView view;
    
    public PersegiPanjangController(PersegiPanjangModel model, PersegiPanjangView view) {
        this.model = model;
        this.view = view;
        
        this.view.addHitungListener(new HitungListener());
        //tambah reset listener
        this.view.addResetListener(new ResetListener());
    }
    
    class HitungListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double p = view.getPanjang();
                double l = view.getLebar();
                    
                model.setPanjang(p);
                model.setLebar(l);
                    
                model.hitungLuas();
                //menambahkan model hitung keliling
                model.hitungKeliling();
                    
   
                view.setHasilLuas(model.getLuas());
                //menampilkan hasil keliling
                view.setHasilKeliling(model.getKeliling());
                
            } catch (NumberFormatException ex) {
                view.tampilkanPesanError("Masukkan angka yang valid");
            }
        }
    }
    
    class ResetListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.resetForm();
        }
    }
   
}
