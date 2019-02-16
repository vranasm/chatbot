/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.vrany.chatbot;

import static cz.vrany.chatbot.ChatBot.CHAT_MSG;
import static cz.vrany.chatbot.ChatBot.COLOR_MSG;
import static cz.vrany.chatbot.ChatBot.JOIN_MSG;
import static cz.vrany.chatbot.ChatBot.MOD_MSG;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;

/**
 * Obsluhuje informace pro twitch chat
 *
 * @author michal
 */
public class TwitchChat implements Runnable {

    String nick;
    String oauth;
    String channel;
    List<TwitchChatListener> listeners;
    boolean konec = false;
    Socket sock;
    BufferedReader br;

    public TwitchChat(String nick, String oauth, String channel) {
        this.nick = nick;
        this.oauth = oauth;
        this.channel = channel;
        listeners = new LinkedList<>();
    }

    public void addTwitchChatListener(TwitchChatListener l) {
        listeners.add(l);
    }

    public void removeTwitchChatListener(TwitchChatListener l) {
        listeners.remove(l);
    }

    protected void fireJoinMessage(String user) {
        TwitchChatEvent evt = new TwitchChatEvent(this, user, null);
        for (TwitchChatListener l : listeners) {
            l.onJoin(evt);
        }
    }

    protected void fireMessage(String user, String message) {
        TwitchChatEvent evt = new TwitchChatEvent(this, user, message);
        for (TwitchChatListener l : listeners) {
            l.onMessage(evt);
        }
    }

    protected void fireClose() {
        TwitchChatEvent evt = new TwitchChatEvent(this, null, null);
        for (TwitchChatListener l : listeners) {
            l.onClose(evt);
        }
    }

    protected void fireCommand(String user, String message) {
        TwitchChatEvent evt = new TwitchChatEvent(this, user, message);
        for (TwitchChatListener l : listeners) {
            l.onCommand(evt);
        }
    }
    
    protected void fireModerator(String user) {
        TwitchChatEvent evt = new TwitchChatEvent(this, user, null);
        for (TwitchChatListener l : listeners) {
            l.onModerator(evt);
        }
    }
    
    protected void fireColor(String user, String color) {
        TwitchChatEvent evt = new TwitchChatEvent(this, user, color);
        for (TwitchChatListener l : listeners) {
            l.onColor(evt);
        }
    }

    public void free() {
    }

    @Override
    public void run() {
        while (!konec) {
            try {
                sock = new Socket(Config.HOST, Config.PORT);
                br = new BufferedReader(new InputStreamReader(sock.getInputStream(), "utf-8"));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream(), "utf-8"));
                bw.write("PASS " + oauth + "\r\n");
                bw.write("NICK " + nick + "\r\n");
                bw.write("JOIN #" + channel + "\r\n");
                bw.flush();
                bw.write("TWITCHCLIENT\r\n");
                bw.flush();
                long startTime = System.currentTimeMillis();
                String response;
                while ((response = br.readLine()) != null && !konec) {
                    if (response.equals("PING :tmi.twitch.tv\r\n")) {
                        bw.write("PONG :tmi.twitch.tv\r\n");
                        bw.flush();
                    } else {
                        Matcher match = CHAT_MSG.matcher(response);
                        //System.out.println("orig " + response);
                        if (match.matches()) {
                            String user = match.group(1);
                            String message = match.group(5);
                            if (message.startsWith("!")) {
                                fireCommand(user, message);
                            } else {
                                fireMessage(user, message);
                            }
                            System.out.println(user + " : " + message);
                        } else {
                            if ((match = JOIN_MSG.matcher(response)).matches()) {
                                fireJoinMessage(match.group(1));
                                System.out.println(response);
                            } else if ((match = MOD_MSG.matcher(response)).matches()) {
                                System.out.println("Adding moderator " + match.group(2));
                                fireModerator(match.group(2));
                            } else if ((match = COLOR_MSG.matcher(response)).matches()) {
                                String suser = match.group(2);
                                String scolor = match.group(3);
                                fireColor(suser, scolor);
                            } else {
                                System.out.println(response);
                            }
                        }
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        konec = true;
                        break;
                    }
                }
                long endTime = System.currentTimeMillis();
                System.out.println("Last runtime " + String.valueOf((endTime - startTime) / 1000) + " seconds");
                System.out.println("Ending connection");
            } catch (SocketException ex) {
            } catch (IOException ex) {
                Logger.getLogger(ChatBot.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        fireClose();
    }

    void end() {
        try {
            sock.close();
        } catch (IOException ex) {
            Logger.getLogger(TwitchChat.class.getName()).log(Level.SEVERE, null, ex);
        }
        konec = true;
    }

}
