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
 * Slova, ktera kdyz jsou nalezena tak se pak zvyrazni jinou barvou text
 *
 * @author michal
 */
public class CatchPhrases extends AbstractTableModel {

    public static final int COL_PHRASE = 0;
    public static final String[] columns = new String[]{
        "Phrase"
    };

    List<String> phrases;

    public CatchPhrases() {
        phrases = new LinkedList<>();
    }

    public void loadPhrases() {
        File file = new File("./phrases");
        if (file.exists()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String inp;
                while ((inp = br.readLine()) != null) {
                    phrases.add(inp);
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

    public void savePhrases() {
        File file = new File("./phrases");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for (String phrase : phrases) {
                bw.write(phrase);
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
        return phrases.size();
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
        return phrases.get(rowIndex);
    }

    void addPhrase(String phrase) {
        phrases.add(phrase);
        fireTableDataChanged();
    }

    void removePhrase(int row) {
        phrases.remove(row);
        fireTableDataChanged();
    }

    @Override
    public void fireTableDataChanged() {
        super.fireTableDataChanged();
        savePhrases();
    }

    boolean hasCatchPhrase(String message) {
        String lmessage = message.toLowerCase();
        for(String phrase : phrases) {
            if(lmessage.contains(phrase.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

}
