/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.vrany.chatbot;

/**
 * 
 * Konfigurace bota pro pripojeni na twitch IRC
 *
 * @author michal
 */
public class Config {
    
    public static final String HOST = "irc.twitch.tv";
    public static final int PORT = 6667;
    //public static final String NICK = "vranasm";
    //public static final String PASS = "oauth:q33u7aiz7zhwel9bfrasbz7bcwgt90";
    //public static final String CHAN = "#wonkywobblar";
    
    public static final double RATE = (20.0/30);
    
    public static final String path_1 = "http://chatdepot.twitch.tv/rooms/";
    public static final String path_2 = "/hosts?oauth_token=";
    
}
