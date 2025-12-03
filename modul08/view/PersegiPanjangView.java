/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package id.ac.unpas.praktikumpemograman2.modul08.view;
/**
 *
 * @author phikm
 */
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class PersegiPanjangView extends JFrame {
    private JTextField txtPanjang = new JTextField(10);
    private JTextField txtLebar = new JTextField(10);

    private JLabel lblHasilLuas = new JLabel("_");
    
    //menambah label keliling
    private JLabel lblHasilKeliling = new JLabel("_");

    private JButton btnHitung = new JButton("Hitung");
    
    //latihan 3 menembahkan tombol reset
    private JButton btnReset = new JButton ("Reset");
    
    

    public PersegiPanjangView() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(350, 250);
        this.setLayout(new GridLayout(5, 2, 10, 10));
        this.setTitle("MVC Kalkulator Persegi Panjang");

        this.add(new JLabel("Panjang:"));
        this.add(txtPanjang);

        this.add(new JLabel("Lebar:"));
        this.add(txtLebar);

        this.add(new JLabel("Luas:"));
        this.add(lblHasilLuas);
        
        //menambahkan JLabael keliling
        this.add(new JLabel("Keliling:"));
        this.add(lblHasilKeliling);

        this.add(btnHitung);
        
        this.add(btnReset);
    }

    public double getPanjang() {
        return Double.parseDouble(txtPanjang.getText());
    }

    public double getLebar() {
        return Double.parseDouble(txtLebar.getText());
    }

    public void setHasilLuas(double hasil) {
        lblHasilLuas.setText(String.valueOf(hasil));
    }
    
    //menambahkan set lebel keliling
    public void setHasilKeliling(double hasil) {
        lblHasilKeliling.setText(String.valueOf(hasil));
    }

    public void tampilkanPesanError(String pesan) {
        JOptionPane.showMessageDialog(this, pesan);
    }

    public void addHitungListener(ActionListener listener) {
        btnHitung.addActionListener(listener);
    }
    
    // ,ethod event reset
    public void addResetListener(ActionListener listener) {
        btnReset.addActionListener(listener);
    }
    
    public void resetForm(){
        txtPanjang.setText("");
        txtLebar.setText("");
        
        lblHasilLuas.setText("_");
        lblHasilKeliling.setText("_");
    }
}

