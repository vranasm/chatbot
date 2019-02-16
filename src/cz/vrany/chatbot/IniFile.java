/*
 * To change this template, choose Tools | Templates
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
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * T��da implementuje manipula�n� metody se souborem .ini
 *
 * @author vranym
 */
public class IniFile {

    Hashtable<String, Section> data = new Hashtable<String, Section>();

    /**
     * Konstruktor t��dy rovnou na�te po�adovan� ini file
     *
     * @param fileName
     */
    public IniFile(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            BufferedReader input = null;
            try {
                input = new BufferedReader(new FileReader(fileName));
                String line = null;
                Section curSection = null;
                while ((line = input.readLine()) != null) {
                    line = line.trim();
                    if (line.length() == 0) {
                        continue;
                    }
                    if (line.charAt(0) == '[') {
                        String name = line.substring(1, line.length() - 1);
                        curSection = new Section(name);
                        data.put(name, curSection);
                    } else if (line.charAt(0) == ';') {

                    } else {
                        String[] dat = line.split("=");
                        if (dat != null && dat.length == 2) {
                            curSection.add(dat[0], dat[1]);
                        }
                    }
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(IniFile.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(IniFile.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    input.close();
                } catch (IOException ex) {
                    Logger.getLogger(IniFile.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void saveIniFile(String fileName) {
        BufferedWriter output = null;
        try {
            output = new BufferedWriter(new FileWriter(fileName));
            for (Section section : data.values()) {
                output.write("[" + section.name + "]");
                output.newLine();
                for (String key : section.keys()) {
                    String value = section.get(key, "");
                    output.write(key + "=" + value);
                    output.newLine();
                }
                output.flush();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(IniFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(IniFile.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                output.close();
            } catch (IOException ex) {
                Logger.getLogger(IniFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void setValue(String section, String key, String value) {
        Section sec = data.get(section);
        if (sec != null) {
            sec.setValue(key, value);
        } else {
            sec = new Section(section);
            sec.add(key, value);
            data.put(section, sec);
        }
    }

    public List<String> sections() {
        return new LinkedList<>(data.keySet());
    }

    public Section getSection(String section) {
        return data.get(section);
    }

    public String getKeyValue(String section, String key, String defaultValue) {
        if (data.get(section) != null) {
            return data.get(section).get(key, defaultValue);
        }
        return defaultValue;
    }

    public Set<String> sectionKeys(String section) {
        if (data.get(section) != null) {
            return data.get(section).keys();
        }
        return null;
    }
}
