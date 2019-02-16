/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.vrany.chatbot;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Trida poskytuje podporu pro export/vykresleni chatu pro Image v OBS
 *
 * @author michal
 */
public class OBS extends JFrame {

    int width;
    int height;
    List<Message> messages;
    BufferedImage image;
    Color background;
    PaintSettings ps;

    public OBS(int width, int height) {
        super();
        setUndecorated(true);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(BorderLayout.CENTER, new CustomPanel());
        messages = new LinkedList<>();
        this.width = width;
        this.height = height;
        recalculateImage();
        ps = new PaintSettings();
        ps.transparent = 255;
    }

    public void addMessage(boolean hosted, boolean catchPhrase, User user, String message) {
        if (messages.size() > 99) {
            messages.remove(0);
        }
        messages.add(new Message(hosted, catchPhrase, user, message));
        paint();
        repaint();
    }

    public void setHeight(int height) {
        this.height = height;
        setSize(width, height);
        recalculateImage();
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
        setSize(width, height);
        recalculateImage();
    }

    @Override
    public int getWidth() {
        return width;
    }

    public void paint() {
        Graphics2D graphics2D = image.createGraphics();
        try {
            graphics2D.setColor(background);
            graphics2D.fillRect(0, 0, width, height);
            ps.width = width;
            Utilities.paintMessages(graphics2D, height, messages, ps);
            ImageIO.write(image, "png", new File("./chat.png"));
        } catch (Exception exception) {
            System.out.println("error");
        } finally {
            graphics2D.dispose();
        }
        repaint();
    }

    private void recalculateImage() {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        paint();
    }

    void setImageBackground(Color color) {
        this.background = color;
        paint();
    }

    public void setFontColor(Color fontColor) {
        ps.fontColor = fontColor;
        paint();
    }

    public void setSystemColor(Color systemColor) {
        ps.systemColor = systemColor;
        paint();
    }

    void setMessageFont(Font font) {
        ps.messageFont = font;
        paint();
    }

    void setCatchColor(Color color) {
        ps.catchColor = color;
        paint();
    }

    class CustomPanel extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, this);
        }

    }
}
