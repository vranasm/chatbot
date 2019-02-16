/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.vrany.chatbot;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * Trida obsluhuje textovy soubor "chat.txt", ktery obsahuje informace pro
 * zobrazeni v OBS
 *
 * @author michal
 */
public class ChatTextfile {

    File file;
    List<String> messages;

    public ChatTextfile() {
        file = new File("chat.txt");
        messages = new LinkedList<String>();
    }
    
    public void addMessage(String user, String message) {
        
    }

}
