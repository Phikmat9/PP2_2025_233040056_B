/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.praktikumpemograman2.modul10.tugas;

/**
 *
 * @author phikm
 */

import id.ac.unpas.praktikumpemograman2.modul10.tugas.controller.MahasiswaController;
import id.ac.unpas.praktikumpemograman2.modul10.tugas.model.MahasiswaModel;
import id.ac.unpas.praktikumpemograman2.modul10.tugas.view.MahasiswaView;

public class main {
    public static void main(String[] args) {
        MahasiswaView view = new MahasiswaView();
        MahasiswaModel model = new MahasiswaModel();
        new MahasiswaController(view, model);
        view.setVisible(true);
    }
}
