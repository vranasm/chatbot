/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.vrany.chatbot;

import java.util.HashMap;
import java.util.Set;

/**
 * Sekce ini fileu
 *
 * @author michal
 */
public class Section {
    
    String name;
    HashMap<String, String> values = new HashMap<>();
    
    public Section(String name) {
        this.name = name;
    }

    void add(String key, String value) {
        values.put(key, value);
    }

    String get(String key, String defaultValue) {
        String value = values.get(key);
        return value != null ? value : defaultValue;
    }

    Set<String> keys() {
        return values.keySet();
    }

    void setValue(String key, String value) {
        values.put(key, value);
    }
    
}
