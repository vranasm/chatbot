/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.vrany.chatbot;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Pomocne funkce pro praci s twitch chatem
 *
 * @author michal
 */
public class Utilities {

    /**
     *
     * Univerzalni AA konstanta
     *
     */

    public static RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    public static void chat(BufferedWriter bw, String msg, String chan) {
        try {
            bw.write("PRIVMSG #" + chan + " :" + msg);
            bw.flush();
        } catch (IOException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void ban(BufferedWriter bw, String user, String chan) {
        chat(bw, ".ban " + user, chan);
    }

    public static void timeout(BufferedWriter bw, String user, int secs, String chan) {
        chat(bw, ".timeout " + user + " " + secs, chan);
    }

    public static final int AlignLeft = 1;
    public static final int AlignRight = 2;
    public static final int AlignHCenter = 4;
    public static final int AlignTop = 8;
    public static final int AlignBottom = 16;
    public static final int AlignVCenter = 32;

    /**
     *
     * Metoda implementuje TextRect metodu z Canvasu v Delphi v prostredi
     * Java<p>
     * @param g kreslící plocha
     * @param txt vypisovaný text
     * @param rct cílový obdélník
     * @param flags příznaky modifikace kreslení (zarovnání apod.)
     */
    public static void TextRect(Graphics g, String txt, Rectangle rct, int flags) {
        Rectangle rect = g.getClipBounds();
        g.setClip(rct.x, rct.y, rct.width, rct.height);
        FontMetrics fm = g.getFontMetrics();
        int width = fm.stringWidth(txt);
        int x = 0, y = 0;
        if (flags == 0) {
            //defaultni rezim
        } else {
            if ((flags & AlignLeft) != 0) {
                x = 0;
            } else if ((flags & AlignRight) != 0) {
                x = rct.width - width;
            } else {
                //defaultne zarovnavam na stred (tzn. zde je i sekce obsluhy kdyz flags obsahuje AlignHCenter)
                x = (rct.width - width) / 2;
            }
            //pozor! v jave se drawString kresli od BASELINE, tedy levy dolni roh
            if ((flags & AlignTop) != 0) {
                y = fm.getAscent();
            } else if ((flags & AlignBottom) != 0) {
                y = rct.height - fm.getDescent();
            } else {
                //defaultne zarovnavam na stred (tzn. zde je i sekce obsluhy kdyz flags obsahuje AlignVCenter)
                if (rct.height < fm.getHeight()) {
                    y = rct.height;
                } else {
                    y = rct.height - (rct.height - fm.getHeight()) / 2 - fm.getDescent();
                }
            }
        }
        g.drawString(txt, rct.x + x, rct.y + y);
        g.setClip(rect);
    }

    public static void paintMessages(Graphics graphics2D, int height, List<Message> messages, PaintSettings ps) {
        if (messages.size() > 0) {
            graphics2D.setFont(ps.messageFont);
            int accHeight = 0;
            int i;
            for (i = messages.size() - 1; i >= 0; i--) {
                int futWidth = messages.get(i).messageHeight(graphics2D.getFontMetrics(), ps);
                if (accHeight + futWidth > height) {
                    break;
                }
                accHeight += futWidth;
            }
            int koef = i >= 0 ? i : 0;
            int y = height;
            for (i = messages.size() - 1; i >= Math.max(koef, 0); i--) {
                y -= messages.get(i).messageHeight(graphics2D.getFontMetrics(ps.messageFont), ps);
                messages.get(i).paintMessage(graphics2D, 0, y, ps);
            }
        }
    }
}
