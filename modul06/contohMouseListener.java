/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.praktikumpemograman2.modul06;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/**
 *
 * @author phikm
 */
public class contohMouseListener {
    public static void main (String [] args){
        JFrame frame = new JFrame ("contoh muselistener");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize (300, 300);
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setPreferredSize (new Dimension (200,300));
        
        MouseAdapter adapter = new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                panel.setBackground(Color.CYAN);
            }
            
            @Override
            public void mouseExited(MouseEvent e){
                panel.setBackground(Color.LIGHT_GRAY);
            }
            
            @Override
            public void mouseClicked(MouseEvent e){
              System.out.print("mouse diklik di: x-" + e.getX() + ", y-" + e.getY());
            }
        };
        
        panel.addMouseListener (adapter);
        
        frame.add(panel);
        frame.setVisible(true);
    }
}
