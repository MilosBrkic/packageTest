/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milosbrkic.testpackets;

import com.milosbrkic.testpackets.thread.SocketThread;
import com.milosbrkic.testpackets.gui.Form;

/**
 *
 * @author milos
 */
public class Main {
    
    
    
    public static void main(String[] args) throws InterruptedException {
                     
        Form.getInstance().setVisible(true);
        
        SocketThread socketThread = new SocketThread();
        socketThread.start();

        socketThread.join(); 
        
        Form.getInstance().dispose();
        System.out.println("Applikacija je zaustavljena");                   
    }
    
    
    
}
