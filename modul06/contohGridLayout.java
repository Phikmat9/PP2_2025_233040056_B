/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.praktikumpemograman2.modul06;

import javax.swing.*;
import java.awt.GridLayout;

/**
 *
 * @author phikm
 */
public class contohGridLayout {
     public static void main (String[] args){
        JFrame frame = new JFrame ("contoh GridLayout");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        
        frame.setLayout (new GridLayout (3, 2, 5, 5)); // 3baris, 2kolom, 5px hgap, 5px vgap
        
        frame.add(new JLabel("label 1:"));
        frame.add(new JTextField());
        frame.add(new JLabel("label 2:"));
        frame.add(new JPasswordField());
        frame.add(new JButton("login"));
        frame.add(new JButton("batal"));
       
        
        frame.setVisible(true);
    }
}
