/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.vrany.chatbot;

/**
 * Posluchac udalosti v twitchchatu (onjoin, onmessage apod.)
 *
 * @author michal
 */
public interface TwitchChatListener {
    
    public void onJoin(TwitchChatEvent evt);
    
    public void onModerator(TwitchChatEvent evt);
    
    public void onMessage(TwitchChatEvent evt);
    
    public void onClose(TwitchChatEvent evt);
    
    public void onCommand(TwitchChatEvent evt);
    
    public void onColor(TwitchChatEvent evt);
    
}
