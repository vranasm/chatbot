/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.vrany.chatbot;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * Trida implementuje cell renderer pro sloupec barev
 *
 * @author michal
 */
public class ColorCellRenderer extends JLabel implements TableCellRenderer {

    public static final int BORDER = 2;
    Color color;

    public ColorCellRenderer() {
        super();
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
            setBackground(table.getSelectionBackground());
            setForeground(table.getSelectionForeground());
        } else {
            setBackground(table.getBackground());
            setForeground(table.getForeground());
        }
        if (value instanceof Color) {
            color = (Color) value;
        }
        return this;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Rectangle r = g.getClipBounds();
        if (color != null) {
            g.setColor(color);
            g.fillRect(r.x + BORDER, r.y + BORDER, r.width - 2 * BORDER, r.height - 2 * BORDER);
        }
    }

}
