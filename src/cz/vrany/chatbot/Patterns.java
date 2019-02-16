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
import java.util.regex.Pattern;
import javax.swing.table.AbstractTableModel;

/**
 * Trida obsahuje nastaveni regularnich vyrazu, ktere budou blokovany
 *
 * @author michal
 */
public class Patterns extends AbstractTableModel {

    public static final String[] columns = new String[]{
        "Pattern"
    };
    List<RPattern> patterns;

    public Patterns() {
        patterns = new LinkedList<>();
    }

    public void loadPatterns() {
        File file = new File("./patterns");
        if (file.exists()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String inp;
                while ((inp = br.readLine()) != null) {
                    RPattern pattern = new RPattern(inp);
                    patterns.add(pattern);
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

    public void savePatterns() {
        File file = new File("./patterns");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for (RPattern pattern : patterns) {
                bw.write(pattern.expr);
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
        return patterns.size();
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
        return patterns.get(rowIndex).expr;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (aValue instanceof String) {
            patterns.get(rowIndex).setExpr(aValue.toString());
        }
    }

    void addPatern(String pattern) {
        if (pattern == null || pattern.equals("")) {
            return;
        }
        for (RPattern pat : patterns) {
            if (pat.expr.equals(pattern)) {
                return;
            }
        }
        patterns.add(new RPattern(pattern));
        fireTableDataChanged();
    }

    void removePattern(int row) {
        patterns.remove(row);
        fireTableDataChanged();
    }

    @Override
    public void fireTableDataChanged() {
        super.fireTableDataChanged();
        savePatterns();
    }

    /**
     * Metoda zjisti zda jde o zakazany vyraz
     *
     * @param message
     * @return
     */
    boolean control(String message) {
        for (RPattern pattern : patterns) {
            if (pattern.pattern.matcher(message).matches()) {
                return true;
            }
        }
        return false;
    }

    private class RPattern {

        Pattern pattern;
        String expr;

        public RPattern(String expr) {
            this.expr = expr;
            pattern = Pattern.compile(expr);
        }

        private void setExpr(String str) {
            expr = str;
            pattern = Pattern.compile(expr);
        }
    }
}
