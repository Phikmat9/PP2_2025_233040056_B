/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.praktikumpemograman2.modul06;

import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author phikm
 */
public class ContohActionListener {
     public static void main (String[] args){
        JFrame frame = new JFrame ("contoh ActionListener");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);
        frame.setLayout(new FlowLayout());
        
        JLabel label = new JLabel("Hallo, Klik tombol di bawah!!");
        JButton button = new JButton ("kilk saya");
        
        ActionListener listener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText("Tombol telah diklik");
            }
        };  
        
        button.addActionListener(listener);
        
        frame.add(label);
        frame.add(button);
        frame.setVisible(true);
    }
}
