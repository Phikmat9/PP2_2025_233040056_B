/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.praktikumpemograman2.modul06;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.*;

/**
 *
 * @author phikm
 */
public class tugas1 {
    public static void main (String[]args) {
        JFrame frame = new JFrame ("kalkulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        
        JTextField layer = new JTextField();
        frame.add (layer, BorderLayout.NORTH);
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 5,5));
        
        panel.add(new JButton("7"));
        panel.add(new JButton("8"));
        panel.add(new JButton("9"));
        
        panel.add(new JButton("/"));
        panel.add(new JButton("4"));
        panel.add(new JButton("5"));
        
        panel.add(new JButton("6"));
        panel.add(new JButton("*"));
        panel.add(new JButton("1"));
        
        panel.add(new JButton("2"));
        panel.add(new JButton("3"));
        panel.add(new JButton("_"));
        
        panel.add(new JButton("0"));
        panel.add(new JButton(","));
        panel.add(new JButton("="));
        panel.add(new JButton("+"));
        
        frame.add(panel, BorderLayout.CENTER);
 
        frame.setVisible(true);
    }
}
