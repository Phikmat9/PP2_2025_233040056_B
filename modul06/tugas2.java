/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.praktikumpemograman2.modul06;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author phikm
 */
public class tugas2 {
    public static void main (String [] args){
        SwingUtilities.invokeLater(() ->{
            JFrame frame = new JFrame ("KOnversi suhu: celcius ke fahrenheit");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(300, 150);
            frame.setLayout(new GridLayout(3, 2, 5, 5));
        
            JLabel Celcius = new JLabel("Celcius");
            JTextField txtCelcius = new JTextField();
            JButton btnKonversi = new JButton("konversi");
            JLabel Fahrenheit = new JLabel("Fahrenheit: ");
            JLabel hasil = new JLabel("");
        
            frame.add(Celcius);
            frame.add(txtCelcius);
            frame.add(btnKonversi);
            frame.add(new JLabel());
            frame.add(Fahrenheit);
            frame.add(hasil);
            
            btnKonversi.addActionListener(new ActionListener(){
                @Override
                    public void actionPerformed(ActionEvent e){
                        String text = txtCelcius.getText().trim();
                        if (text.isEmpty()){
                            hasil.setText("Masukan angka");
                            return;
                        }
                        try {  
                        double c = Double.parseDouble(txtCelcius.getText());
                        double f = (c * 9/5) + 32;
                        hasil.setText(String.format("%.2f f", f));
                    }catch (NumberFormatException ex ){
                    hasil.setText("angka salah");
                    }
                }        
            });  
            frame.setVisible(true);
        }); 
    }
}
