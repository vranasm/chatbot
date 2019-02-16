/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.vrany.chatbot;

import java.awt.Color;
import javax.swing.ImageIcon;

/**
 * Trida uchovava informace o uzivateli, napr. jeho barvu
 *
 * @author michal
 */
public class User {
    
    String name;
    Color color;
    ImageIcon icon;
    
    public User(String name, Color color, ImageIcon icon) {
        this.name = name;
        this.color = color;
        this.icon = icon;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
    
}
