/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.vrany.chatbot;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * Metoda sleduje seznam hostu
 *
 * @author michal
 */
class HostsTracking implements ActionListener {

    List<String> knownHosts;
    List<HostListener> listeners;
    ChatSettings chs;

    public HostsTracking(ChatSettings chs) {
        this.chs = chs;
        knownHosts = new LinkedList<>();
        listeners = new LinkedList<>();
    }

    public void addHostListener(HostListener l) {
        listeners.add(l);
    }

    public void removeHostListener(HostListener l) {
        listeners.remove(l);
    }

    protected void fireHostStarted(String name) {
        HostEvent evt = new HostEvent(this, name);
        for (HostListener l : listeners) {
            l.hostStarted(evt);
        }
    }

    protected void fireHostEnded(String name) {
        HostEvent evt = new HostEvent(this, name);
        for (HostListener l : listeners) {
            l.hostEnded(evt);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        URL url;
        InputStream is = null;
        BufferedReader br;
        String line;
        StringBuilder sb = new StringBuilder();
        try {
            url = new URL(Config.path_1 + chs.getChannelName() + Config.path_2 + chs.getOauth());
            is = url.openStream();  // throws an IOException
            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (MalformedURLException mue) {
        } catch (IOException ioe) {
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                analyze(sb);
            } catch (IOException ioe) {
                // nothing to see here
            }
        }
    }

    public void clear() {
        knownHosts.clear();
    }

    private void analyze(StringBuilder sb) {
        //napr: {"hosts":[{"host":"vranasm"},{"host":"reto_fox"}]}
        List<String> newHosts = new LinkedList<>();
        String inner = sb.toString();
        try {
        inner = inner.substring(inner.indexOf("[") + 1, inner.indexOf("]"));
        } catch(StringIndexOutOfBoundsException ex) {
            return;
        }
        String[] split = inner.split(",");
        for (int i = 0; i < split.length; i++) {
            String[] data = split[i].substring(1, split[i].length() - 1).split(":");
            String user = data[1].substring(1, data[1].length() - 1);
            newHosts.add(user);
        }
        List<String> delete = new LinkedList<>();
        for (String user : knownHosts) {
            if (!newHosts.contains(user)) {
                delete.add(user);
            }
        }
        for (String user : delete) {
            fireHostEnded(user);
            knownHosts.remove(user);
        }
        for (String user : newHosts) {
            if (!knownHosts.contains(user)) {
                knownHosts.add(user);
                fireHostStarted(user);
            }
        }
    }

}
