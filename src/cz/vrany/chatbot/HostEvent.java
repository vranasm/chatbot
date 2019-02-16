/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.vrany.chatbot;

import java.util.EventObject;

/**
 * Trida obsahuje informace pro otevreni/ukonceni host IRC kanalu
 *
 * @author michal
 */
public class HostEvent extends EventObject {

    String name;

    public HostEvent(Object source, String name) {
        super(source);
        this.name = name;
    }

}
