/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.praktikumpemograman2.modul05;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 *
 * @author phikm
 */
public class latihan2 {
  public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame ("jendela dengan lable");
                frame.setSize (400, 300);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                // 1. buat komponen jlabel
                JLabel label = new JLabel ("ini adalah JLable");
                
                //2. tambahkan jlable ke jframe
                // secara default, jframe menggunkan brderlayout,
                //dan .add() akan menammbahkannya ke bagian tengah (senter)
                frame.add(label);
                
                frame.setVisible(true);
            }
        });
    }
}

