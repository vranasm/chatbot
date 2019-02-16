/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.vrany.chatbot;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.text.DateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.swing.ImageIcon;

/**
 * uchovava informace o zprave pro zobrazeni v chatus
 *
 * @author michal
 */
public class Message {

    public static final int ICON_HEIGHT = 20;
    public static final int ICON_WIDTH = 22;

    Date timeStamp;
    String time;
    Color color;
    String userName;
    String message;
    ImageIcon icon;
    List<String> smessages;
    FontMetrics oldFM;
    int oldWidth;
    boolean oldTimestamps;
    boolean oldShorterTimestamps;
    Color messageColor;
    boolean hosted;
    boolean catchPhrase;

    public Message(boolean hosted, boolean catchPhrase, User user, String message) {
        this.hosted = hosted;
        timeStamp = new Date();
        time = DateFormat.getTimeInstance().format(timeStamp);
        if (user != null) {
            color = user.getColor();
            userName = user.getName().toUpperCase();
            icon = user.icon;
        }
        if (message.charAt(0) == 1) {
            this.message = message.substring(8, message.length() - 1);
            messageColor = color;
        } else {
            this.message = message;
        }
        smessages = new LinkedList<>();
        this.catchPhrase = catchPhrase;
    }

    /**
     * vraci velikost zpravy
     *
     * @param fm
     * @param width
     * @return
     */
    public int messageHeight(FontMetrics fm, PaintSettings ps) {
        if (oldWidth != ps.width || fm.equals(oldFM) || oldTimestamps != ps.timeStampOn || oldShorterTimestamps != ps.shorterTimeStamp) {
            parseMessage(fm, ps);
            oldWidth = ps.width;
            oldFM = fm;
        }
        int firstRowHeight = firstRowHeight(fm);
        return firstRowHeight + (smessages.size() - 1) * fm.getHeight();
    }

    private int firstRowHeight(FontMetrics fm) {
        return icon != null ? Math.max(fm.getHeight(), Math.min(ICON_HEIGHT, icon.getIconHeight())) : fm.getHeight();
    }

    public int findBreakBefore(String line, int start) {
        for (int i = start; i >= 0; --i) {
            char c = line.charAt(i);
            if (Character.isWhitespace(c) || c == '-') {
                return i;
            }
        }
        return -1;
    }

    public int findBreakAfter(String line, int start) {
        int len = line.length();
        for (int i = start; i < len; ++i) {
            char c = line.charAt(i);
            if (Character.isWhitespace(c) || c == '-') {
                return i;
            }
        }
        return -1;
    }

    private String constructTime(PaintSettings ps) {
        if (ps.timeStampOn) {
            if (ps.shorterTimeStamp) {
                return String.format("%1$tH:%1$tM", timeStamp);
            } else {
                return time;
            }
        }
        return "";
    }

    public void parseMessage(FontMetrics fm, PaintSettings ps) {
        smessages.clear();
        int rect_x = fm.stringWidth(constructTime(ps));
        if (hosted) {
            rect_x += fm.stringWidth(" ");
            rect_x += fm.stringWidth("H");
        }
        if (userName != null) {
            if (icon != null) {
                rect_x += fm.stringWidth(" ");
                rect_x += ICON_WIDTH;
            }
            rect_x += fm.stringWidth(" ");
            rect_x += fm.stringWidth(userName);
        }
        rect_x += fm.stringWidth(" ");
        //multiline
        String smessage = message;
        int len = smessage.length();
        int actWidth = ps.width - rect_x;
        int swidth;
        while (len > 0 && (swidth = fm.stringWidth(smessage)) > actWidth) {
            int guess = len * actWidth / swidth;
            String before = smessage.substring(0, guess).trim();

            swidth = fm.stringWidth(before);
            int pos;
            if (swidth > actWidth) {
                pos = findBreakBefore(smessage, guess);
            } else {
                pos = findBreakAfter(smessage, guess);
                if (pos != -1) {
                    before = smessage.substring(0, pos).trim();
                    if (fm.stringWidth(before) > actWidth) {
                        pos = findBreakBefore(smessage, guess);
                    }
                }
            }
            if (pos == -1) {
                pos = guess;
            }
            smessages.add(smessage.substring(0, pos).trim());
            smessage = smessage.substring(pos).trim();
            len = smessage.length();
            actWidth = ps.width;
        }
        if (len > 0) {
            smessages.add(smessage);
        }
    }

    /**
     * metoda vykresli zpravu do pozadovaneho graphics kontextu na pozadovane
     * souradnice
     *
     * @param g
     * @param messageFont
     */
    void paintMessage(Graphics g, int x, int y, PaintSettings ps) {
        Graphics2D g2d = (Graphics2D) g;
        RenderingHints oldRH = g2d.getRenderingHints();
        if (ChatSettings.AA) {
            g2d.setRenderingHints(Utilities.rh);
        }
        Color clr = new Color(ps.systemColor.getRed(), ps.systemColor.getGreen(), ps.systemColor.getBlue(), ps.transparent);
        FontMetrics fm = g.getFontMetrics();
        Rectangle rect = new Rectangle(x, y, 0, firstRowHeight(fm));
        g.setColor(clr);
        rect.width = fm.stringWidth(constructTime(ps));
        Utilities.TextRect(g, constructTime(ps), rect, Utilities.AlignVCenter + Utilities.AlignLeft);
        rect.x += fm.stringWidth(constructTime(ps));
        if (hosted) {
            rect.x += fm.stringWidth(" ");
            rect.width = fm.stringWidth("H");
            Utilities.TextRect(g, "H", rect, Utilities.AlignVCenter + Utilities.AlignLeft);
            rect.x += fm.stringWidth("H");
        }
        if (userName != null) {
            if (icon != null) {
                rect.x += fm.stringWidth(" ");
                Rectangle oldrect = g.getClipBounds();
                g.setClip(rect.x + 1, rect.y + 1, ICON_WIDTH - 2, ICON_HEIGHT - 2);
                x = rect.x + (ICON_WIDTH - 2) / 2 - icon.getIconWidth() / 2;
                y = rect.y + (ICON_HEIGHT - 2) / 2 - icon.getIconHeight() / 2;
                icon.paintIcon(null, g, x, y);
                g.setClip(oldrect);
                rect.x += ICON_WIDTH;
            }
            rect.x += fm.stringWidth(" ");
            if (color != null) {
                clr = new Color(color.getRed(), color.getGreen(), color.getBlue(), ps.transparent);
            }
            rect.width = fm.stringWidth(userName);
            g.setColor(clr);
            Utilities.TextRect(g, userName, rect, Utilities.AlignVCenter + Utilities.AlignLeft);
            rect.x += fm.stringWidth(userName);

        }
        rect.x += fm.stringWidth(" ");
        if (userName != null) {
            if (messageColor != null) {
                clr = messageColor;
            } else {
                if (!catchPhrase) {
                    clr = new Color(ps.fontColor.getRed(), ps.fontColor.getGreen(), ps.fontColor.getBlue(), ps.transparent);
                } else {
                    clr = new Color(ps.catchColor.getRed(), ps.catchColor.getGreen(), ps.catchColor.getBlue(), ps.transparent);
                }
            }
        } else {
            clr = new Color(ps.systemColor.getRed(), ps.systemColor.getGreen(), ps.systemColor.getBlue(), ps.transparent);
        }
        g.setColor(clr);
        rect.width = fm.stringWidth(message);
        boolean first = true;
        rect.width = ps.width;
        for (int i = 0; i < smessages.size(); i++) {
            Utilities.TextRect(g, smessages.get(i), rect, Utilities.AlignVCenter + Utilities.AlignLeft);
            if (first) {
                first = false;
                rect.x = 0;
                rect.height = fm.getHeight();
                rect.y += firstRowHeight(fm);
            } else {
                rect.y += fm.getHeight();
            }
        }
        if (ChatSettings.AA) {
            g2d.setRenderingHints(oldRH);
        }
    }

}
