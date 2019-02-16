/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.vrany.chatbot;

import java.util.EventObject;

/**
 *
 * @author michal
 */
public class TwitchChatEvent extends EventObject {
    
    String user;
    String message;
    
    public TwitchChatEvent(Object source, String user, String message) {
        super(source);
        this.user = user;
        this.message = message;
    }
    
}
