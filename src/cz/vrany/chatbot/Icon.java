/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.vrany.chatbot;

import javax.swing.ImageIcon;

/**
 * Popis definice jedne ikony (cesta a nactena ikona ze zdroje)
 *
 * @author michal
 */
public class Icon {
    
    String path;
    ImageIcon icon;
    
    public Icon(String path) {
        this.path = path;
        icon = new ImageIcon(path);
    }

    void setPath(String toString) {
        icon = new ImageIcon(path);
    }

}
