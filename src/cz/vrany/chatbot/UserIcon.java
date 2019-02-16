/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.vrany.chatbot;

/**
 * uchovava informace o propojeni uzivatel ikona
 *
 * @author michal
 */
public class UserIcon {
    
    String user;
    int icon;
    
    public UserIcon(String user, int icon) {
        this.user = user;
        this.icon = icon;
    }

    void setIcon(int num) {
        icon = num;
    }
}
