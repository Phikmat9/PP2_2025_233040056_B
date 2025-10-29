/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.praktikumpemograman2.modul05;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author phikm
 */
public class latihan1 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // 1. buat objek jframe
                JFrame frame = new JFrame ("Jendela Pertamaku");
                
                // 2. atur ukuran jendela (lebar 400, tinggi 300)
                frame.setSize(400, 300);
                
                //3. atur aksi saat tombol close (x) ditekan
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                //4. Buat Jendela Terlihat
                frame.setVisible(true);
            }
        });
    }
}
