/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.praktikumpemograman2.modul06;

import javax.swing.*;
import java.awt.BorderLayout;
/**
 *
 * @author phikm
 */
public class contohBorderLayout {
     public static void main (String[] args){
        JFrame frame = new JFrame ("contoh BorderLayout");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        
        
        frame.add(new JButton("tombol 1"), BorderLayout.NORTH);
        frame.add(new JButton("tombol 2"), BorderLayout.SOUTH);
        frame.add(new JButton("tombol tiga"), BorderLayout.EAST);
        frame.add(new JButton("tombol empat panjang"), BorderLayout.CENTER);
        frame.add(new JButton("tombol 5"), BorderLayout.WEST);
        
 
        frame.setVisible(true);
    }
}
