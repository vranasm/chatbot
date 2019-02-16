/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.vrany.chatbot;

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
import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

/**
 * Trida obsahuje informace o nactenych (png) ikonach v systemu
 *
 * @author michal
 */
public class Icons extends AbstractTableModel {

    public static final String[] columns = new String[]{
        "Number", "Icon", "Path"
    };
    public static final int NUMBER = 0;
    public static final int ICON = 1;
    public static final int PATH = 2;
    List<Icon> icons;

    public Icons() {
        icons = new LinkedList<>();
    }

    public void loadIcons() {
        File file = new File("./icons");
        if (file.exists()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String inp;
                while ((inp = br.readLine()) != null) {
                    Icon icon = new Icon(inp);
                    icons.add(icon);
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

    public void saveIcons() {
        File file = new File("./icons");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for (Icon icon : icons) {
                bw.write(icon.path);
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
        return icons.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case NUMBER:
                return rowIndex;
            case ICON:
                return icons.get(rowIndex).icon;
            case PATH:
                return icons.get(rowIndex).path;
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == PATH;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (columnIndex == PATH && aValue instanceof String) {
            Icon icon = icons.get(rowIndex);
            icon.setPath(aValue.toString());
        }
    }

    void addIcon(Icon icon) {
        icons.add(icon);
        fireTableDataChanged();
    }

    void removeIcon(int row) {
        icons.remove(row);
        fireTableDataChanged();
    }

    boolean moveUp(int row) {
        if (row > 0) {
            Collections.swap(icons, row - 1, row);
            fireTableDataChanged();
            return true;
        }
        return false;
    }

    boolean moveDn(int row) {
        if (row < icons.size() - 1) {
            Collections.swap(icons, row, row + 1);
            fireTableDataChanged();
            return true;
        }
        return false;
    }

    /**
     * vraci ikonu s pozadovanym indexem
     *
     * @param icon
     * @return
     */
    ImageIcon getIcon(int icon) {
        return icon < icons.size() ? icons.get(icon).icon : null;
    }

}
