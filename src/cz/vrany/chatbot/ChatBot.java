/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.vrany.chatbot;

import java.util.regex.Pattern;

/**
 *
 * @author michal
 */
public class ChatBot {

    public static final Pattern CHAT_MSG = Pattern.compile(":(\\w+)!(\\w+)@(\\w+)\\.tmi\\.twitch\\.tv PRIVMSG #(\\w+) :(.+)");
    public static final Pattern JOIN_MSG = Pattern.compile(":(\\w+)!(\\w+)@(\\w+)\\.tmi\\.twitch\\.tv JOIN #(\\w+)");
    public static final Pattern MOD_MSG = Pattern.compile(":jtv MODE #(\\w+) \\+o (\\w+)");
    public static final Pattern COLOR_MSG = Pattern.compile(":jtv PRIVMSG (\\w+) :USERCOLOR (\\w+) #(\\w+)");

    ChatSettings chatSettings;

    public ChatBot() {
        chatSettings = new ChatSettings();
        chatSettings.setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ChatBot bot = new ChatBot();
    }

}
