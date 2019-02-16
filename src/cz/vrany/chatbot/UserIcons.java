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
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author michal
 */
public class UserIcons extends AbstractTableModel {

    public static final String[] columns = new String[]{
        "User", "Icon number"
    };

    public static final int USER = 0;
    public static final int NUMBER = 1;

    List<UserIcon> userIcons;

    public UserIcons() {
        userIcons = new LinkedList<>();
    }

    public void loadIcons() {
        File file = new File("./usericons");
        if (file.exists()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String inp;
                while ((inp = br.readLine()) != null) {
                    String[] split = inp.split(",");
                    UserIcon icon = new UserIcon(split[0], Integer.parseInt(split[1]));
                    userIcons.add(icon);
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
        File file = new File("./usericons");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for (UserIcon icon : userIcons) {
                bw.write(icon.user);
                bw.write(",");
                bw.write(String.valueOf(icon.icon));
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
        return userIcons.size();
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
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == NUMBER;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (columnIndex == NUMBER && aValue instanceof String) {
            try {
                userIcons.get(rowIndex).setIcon(Integer.parseInt(aValue.toString()));
            } catch (NumberFormatException ex) {

            }
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case USER:
                return userIcons.get(rowIndex).user;
            case NUMBER:
                return userIcons.get(rowIndex).icon;
        }
        return null;
    }

    @Override
    public void fireTableDataChanged() {
        super.fireTableDataChanged();
        //uloz zaznamy na disk
        saveIcons();
    }

    void newData(String userName, String iconNumber) {
        try {
            int ic = Integer.parseInt(iconNumber);
            for (UserIcon ui : userIcons) {
                if (ui.user.equals(userName)) {
                    ui.setIcon(ic);
                    fireTableDataChanged();
                    return;
                }
            }
            UserIcon ui = new UserIcon(userName, ic);
            userIcons.add(ui);
            fireTableDataChanged();
        } catch (NumberFormatException ex) {

        }
    }

    void removeUserIcon(int row) {
        userIcons.remove(row);
        fireTableDataChanged();
    }

    boolean moveUp(int row) {
        if (row > 0) {
            Collections.swap(userIcons, row - 1, row);
            fireTableDataChanged();
            return true;
        }
        return false;
    }

    boolean moveDn(int row) {
        if (row < userIcons.size() - 1) {
            Collections.swap(userIcons, row, row + 1);
            fireTableDataChanged();
            return true;
        }
        return false;
    }

}
