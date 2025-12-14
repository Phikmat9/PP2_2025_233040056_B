/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.praktikumpemograman2.modul09;

/**
 *
 * @author phikm
 */

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class AplikasiFileIO  extends JFrame {
    
    //komponen UI
    private JTextArea textArea;
    private JButton btnOpenText, btnSaveText;
    private JButton btnAppendText;
    private JButton btnSaveBinary, btnLoadBinary;
    private JButton btnSaveObject, btnLoadObject; //tambah button
    private JFileChooser fileChooser;

    
    public AplikasiFileIO() {
        super("Tutorial file IO & Exeptiom Handling");
        setSize (600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        //instal komponen
        textArea = new JTextArea();
        textArea.setFont(new Font ("Monospaced", Font.PLAIN, 14));
        fileChooser = new JFileChooser();
        
        // File Chooser
        fileChooser = new JFileChooser();
        
        //tombol
        JPanel buttonPanel = new JPanel();
        
        btnOpenText = new JButton("Buka Text");
        btnSaveText = new JButton("Simpan Text");
        btnAppendText = new JButton("Append Text");
        btnSaveBinary = new JButton("Simpan Config (Binary)");
        btnLoadBinary = new JButton("Muat Config (Binary)");
        btnSaveObject = new JButton("Simpan Object");
        btnLoadObject = new JButton("Muat Object");
        
        buttonPanel.add(btnOpenText);
        buttonPanel.add(btnSaveText);
        buttonPanel.add(btnAppendText);
        buttonPanel.add(btnSaveBinary);
        buttonPanel.add(btnLoadBinary);
        buttonPanel.add(btnSaveObject);
        buttonPanel.add(btnLoadObject);
        
        //layout
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        
        //handling
        btnOpenText.addActionListener(e -> bukaFileTeks());
        btnSaveText.addActionListener(e -> simpanFileTeks());
        btnAppendText.addActionListener(e -> appendFileTeks());
        btnSaveBinary.addActionListener(e -> simpanConfigBinary());
        btnLoadBinary.addActionListener(e -> muatConfigBinary());
        btnSaveObject.addActionListener(e -> simpanObject());
        btnLoadObject.addActionListener(e -> muatObject());
        
        ////latihan 2 auto load file terakhir////
        bacaFileTerakhir();

        setVisible(true);
    }
    
    
    
    private void bukaFileTeks(){
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            BufferedReader reader = null; // Deklarasi di luar try agar bisa diakses di finally
            
            try {
                // Membuka stream
                reader = new BufferedReader(new FileReader(file));
                textArea.setText(""); 
                
                String line;
                // Baca baris demi baris
                while ((line = reader.readLine()) != null){
                    textArea.append (line + "\n");
                }
                JOptionPane.showMessageDialog(this, "File berhasil dimuat!");
                
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "File tidak ditemukan: " + ex.getMessage());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Gagal membaca file: " + ex.getMessage());
            } finally {
                try {
                    if (reader !=null) {
                        reader.close(); // PENTING: Menutup stream
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    
    private void simpanFileTeks(){
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(textArea.getText());
                JOptionPane.showMessageDialog(this, "File berhassil disimpan!");  
            } catch (IOException ex){
                JOptionPane.showMessageDialog(this, "Gagal menyimpan");
            }
        }
        
        //️ Latihan 2: simpan otomatis ke last_notes.txt
        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter("last_notes.txt"))) {
            writer.write(textArea.getText());
        } catch (IOException e) {
            // abaikan
        }
    }
    
    //// latihan 4//// 
    private void appendFileTeks() {
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedWriter writer =
                new BufferedWriter(new FileWriter(file, true))) {

                writer.write("\n" + textArea.getText());
                JOptionPane.showMessageDialog(this,
                    "Teks berhasil ditambahkan (append)");
                } catch (IOException e) {
                JOptionPane.showMessageDialog(this,
                    "Gagal append file");
            }
        }
    }
        

    
    private void simpanConfigBinary(){
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("config.bin"))) {
            
            int fontSize = textArea.getFont().getSize();
            dos.writeInt(fontSize);
            
            JOptionPane.showMessageDialog(this,"Ukuran font (" + fontSize + ") disimpan ke config.bin ");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,"Gagal menyimpan binary: " + ex.getMessage());
        }
    }
        
    private void muatConfigBinary() {
        try (DataInputStream dis = new DataInputStream(new FileInputStream("config.bin"))) {
            // Membaca data Integer mentah
            int fontSize = dis.readInt();
            
            // Terapkan ke aplikasi
            textArea.setFont(new Font("Monospaced", Font. PLAIN, fontSize));
            JOptionPane.showMessageDialog(this, "Font diubah menjadi ukuran: " + fontSize);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "File config.bin belum dibuat!");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Gagal membaca binary: " + ex.getMessage());
        }   
    }
    
    ////latihan 2////
    private void bacaFileTerakhir() {
        File file = new File("last_notes.txt");
        if (!file.exists()) {
            return; // jika file tidak ada, diam saja
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            textArea.setText("");
            String line;
            while ((line = reader.readLine()) != null) {
                textArea.append(line + "\n");
            }
        } catch (IOException e) {
            // tidak perlu tampilkan error
        }
    }
    
    //// latihan 3///
     private void simpanObject() {
        UserConfig config = new UserConfig(
                "Hikmat",
                textArea.getFont().getSize()
        );

        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream("userconfig.obj"))) {

            oos.writeObject(config);
            JOptionPane.showMessageDialog(this,
                    "Object berhasil disimpan");

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this,
                    "Gagal menyimpan object");
        }
    }

    private void muatObject() {
        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream("userconfig.obj"))) {
            UserConfig config = (UserConfig) ois.readObject();

            textArea.setFont(new Font(
                    "Monospaced",
                    Font.PLAIN,
                    config.getFontSize()
            ));

            JOptionPane.showMessageDialog(this,
                    "User: " + config.getUsername());

        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this,
                    "Gagal memuat object");
        }
    }

    
    public static void main(String[] args) {
        SwingUtilities.invokeLater (() -> {
            new AplikasiFileIO().setVisible(true);
        });
    }
}