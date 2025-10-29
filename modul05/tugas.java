/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.praktikumpemograman2.modul05;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 *
 * @author phikm
 */
public class tugas {
 public static void main (String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            public void run(){
                JFrame frame = new JFrame ("TUGAS");
                frame.setSize(400, 300);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                frame.setLayout(new BorderLayout());
                JLabel label = new JLabel ("AREA (NORTH)", JLabel.CENTER);
                JButton Sbutton = new JButton ("AREA (SOUTH)");
               
                frame.add(label, BorderLayout.NORTH);
                frame.add(Sbutton, BorderLayout.SOUTH);
                 
                //tambahkan komponen lain
                JButton westbutton = new JButton("Tombol WEST");
                westbutton.addActionListener(e -> label.setText("Klik Tombol: " + e.getActionCommand()));
                frame.add(westbutton , BorderLayout.WEST);
                
                JButton eastbutton = new JButton("Tombol EAST");
                eastbutton.addActionListener(e -> label.setText("Klik Tombol: " + e.getActionCommand()));
                frame.add(eastbutton , BorderLayout.EAST);
                
                
                JButton centerbutton = new JButton("Tombol CENTER");
                centerbutton.addActionListener(e -> label.setText("Klik Tombol: " + e.getActionCommand()));
                frame.add(centerbutton , BorderLayout.CENTER);
                
                frame.setVisible(true);
            }
        });
    }
    
}