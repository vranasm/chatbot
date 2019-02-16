/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.vrany.chatbot;

/**
 * Posluchac zmen hostovani
 *
 * @author michal
 */
public interface HostListener {

    public void hostStarted(HostEvent evt);

    public void hostEnded(HostEvent evt);

}
