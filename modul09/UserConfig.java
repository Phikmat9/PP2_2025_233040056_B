/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

///latihan 3///
package id.ac.unpas.praktikumpemograman2.modul09;

/**
 *
 * @author phikm
 */

import java.io.Serializable;

public class UserConfig implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private int fontSize;

    public UserConfig(String username, int fontSize) {
        this.username = username;
        this.fontSize = fontSize;
    }

    public String getUsername() {
        return username;
    }

    public int getFontSize() {
        return fontSize;
    }
}
