/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.vrany.chatbot;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 * Trida obsahuje informace o ulozenych barvach v systemu
 *
 * @author michal
 */
public class Colors extends AbstractTableModel {

    public static final String[] columns = new String[]{
        "Red", "Green", "Blue", "Color"
    };
    public static final int RED = 0;
    public static final int GREEN = 1;
    public static final int BLUE = 2;
    public static final int COLOR = 3;
    List<InnerColor> colors;
    int colorsCounter;

    public Colors() {
        colors = new LinkedList<>();
    }

    public void loadColors() {
        File file = new File("./colors");
        if (file.exists()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String inp;
                while ((inp = br.readLine()) != null) {
                    String split[] = inp.split(",");
                    int red = Integer.parseInt(split[0]);
                    int green = Integer.parseInt(split[1]);
                    int blue = Integer.parseInt(split[2]);
                    colors.add(new InnerColor(new Color(red, green, blue)));
                }
                br.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Icons.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Icons.class.getName()).log(Level.SEVERE, null, ex);
            }
            fireTableDataChanged();
        }
    }

    public void saveColors() {
        File file = new File("./colors");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for (InnerColor color : colors) {
                bw.write(String.valueOf(color.color.getRed()));
                bw.write(",");
                bw.write(String.valueOf(color.color.getGreen()));
                bw.write(",");
                bw.write(String.valueOf(color.color.getBlue()));
                bw.newLine();
                bw.flush();
            }
            bw.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Icons.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Icons.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getRowCount() {
        return colors.size();
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case RED:
                return colors.get(rowIndex).color.getRed();
            case GREEN:
                return colors.get(rowIndex).color.getGreen();
            case BLUE:
                return colors.get(rowIndex).color.getBlue();
            case COLOR:
                return colors.get(rowIndex).color;
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == RED || columnIndex == GREEN || columnIndex == BLUE;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (aValue instanceof String) {
            switch (columnIndex) {
                case RED:
                    colors.get(rowIndex).setRed(Integer.parseInt(aValue.toString()));
                    break;
                case GREEN:
                    colors.get(rowIndex).setGreen(Integer.parseInt(aValue.toString()));
                    break;
                case BLUE:
                    colors.get(rowIndex).setBlue(Integer.parseInt(aValue.toString()));
                    break;
            }
        }
    }

    void removeIcon(int row) {
        colors.remove(row);
        fireTableDataChanged();
    }

    boolean moveUp(int row) {
        if (row > 0) {
            Collections.swap(colors, row - 1, row);
            fireTableDataChanged();
            return true;
        }
        return false;
    }

    boolean moveDn(int row) {
        if (row < colors.size() - 1) {
            Collections.swap(colors, row, row + 1);
            fireTableDataChanged();
            return true;
        }
        return false;
    }

    void addColor(Color color) {
        InnerColor ic = new InnerColor(color);
        colors.add(ic);
        fireTableDataChanged();
    }

    @Override
    public void fireTableDataChanged() {
        super.fireTableDataChanged();
        saveColors();
    }

    /**
     * metoda vraci dalsi barvu v poradi podle rotace. Pokud nejsou definovany
     * zadne barvy pak vraci null;
     *
     * @return
     */
    Color getNewColor() {
        if (colors.size() == 0) {
            return null;
        }
        Color clr = colors.get(colorsCounter).color;
        colorsCounter = (colorsCounter + 1) % colors.size();
        return clr;
    }

    private class InnerColor {

        Color color;

        public InnerColor(Color color) {
            this.color = color;
        }

        private void setRed(int val) {
            color = new Color(val, color.getGreen(), color.getBlue());
        }

        private void setGreen(int val) {
            color = new Color(color.getRed(), val, color.getBlue());
        }

        private void setBlue(int val) {
            color = new Color(color.getRed(), color.getGreen(), val);
        }
    }
}
