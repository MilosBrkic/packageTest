/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milosbrkic.testpackets.thread;

import com.milosbrkic.testpackets.model.ServerPackage;
import com.milosbrkic.testpackets.gui.Form;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author milos
 */
public class ResponseThread extends Thread {

    private final ServerPackage paket;
    private final List<ServerPackage> lista;
    private final OutputStream outputStream;
    
    public ResponseThread(ServerPackage paket, List<ServerPackage> lista, OutputStream outputStream) {
        this.paket = paket;
        this.lista = lista;
        this.outputStream = outputStream;
    }

       
    @Override
    public void run() {
        try {
            sleep(paket.getRemaningTime());
            outputStream.write(paket.getByteFormat());           
            lista.remove(paket);
            
            Form.getInstance().sent(paket);
            
            System.out.println("Poslat paket: "+paket.toString());
            System.out.println("Vreme: "+new SimpleDateFormat("hh:mm:ss").format(new Date()));
            System.out.println("\n");
            
        } catch (InterruptedException ex) {
            //System.out.println("Thread interrupted...");
        } catch (IOException ex) {
            System.out.println("Greska pri slanju paketa");
        }
    }
    
    
    
}
