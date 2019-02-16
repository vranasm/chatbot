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
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * Trida udrzuje seznam uzivatelu, jejichz zpravy jsou ignorovany
 *
 * @author michal
 */
public class IgnoredUsers extends AbstractTableModel {

    public static final String[] columns = new String[]{
        "User name"
    };
    public static final int NAME = 0;
    List<String> users;

    public IgnoredUsers() {
        users = new LinkedList<>();
    }

    public void loadUsers() {
        File file = new File("./ignoredusers");
        if (file.exists()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String inp;
                while ((inp = br.readLine()) != null) {
                    users.add(inp);
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

    public void saveUsers() {
        File file = new File("./ignoredusers");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for (String user : users) {
                bw.write(user);
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
        return users.size();
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
        return users.get(rowIndex);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (aValue instanceof String) {
            users.set(rowIndex, aValue.toString());
        }
    }

    void removeUser(int row) {
        users.remove(row);
        fireTableDataChanged();
    }

    /**
     * Prida uzivatele do seznamu ignorovanych, system je non case sensitive
     *
     * @param userName
     */
    void addUser(String userName) {
        if (userName == null || userName.equals("")) {
            return;
        }
        for (String user : users) {
            if (user.toLowerCase().equals(userName.toLowerCase())) {
                return;
            }
        }
        users.add(userName);
        fireTableDataChanged();
    }

    @Override
    public void fireTableDataChanged() {
        super.fireTableDataChanged();
        saveUsers();
    }

    /**
     * Metoda zjisti zda je uzivatel zablokovany
     *
     * @param user
     * @return
     */
    boolean control(String user) {
        for (String usr : users) {
            if (usr.toLowerCase().equals(user.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

}
