/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.praktikumpemograman2.modul06;

import javax.swing.*;

/**
 *
 * @author phikm
 */
public class contohLayout {
    public static void main (String[] args){
        JFrame frame = new JFrame ("contoh FlowLayout");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);
        
        JPanel panel = new JPanel();
        
        panel.add(new JButton("tombol 1"));
        panel.add(new JButton("tombol 2"));
        panel.add(new JButton("tombol tiga"));
        panel.add(new JButton("tombol empat panjang"));
        panel.add(new JButton("tombol 5"));
        
        frame.add(panel);
        
        frame.setVisible(true);
    }
}
