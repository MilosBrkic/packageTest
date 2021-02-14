/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milosbrkic.testpackets.thread;

import com.milosbrkic.testpackets.model.ServerPackage;
import com.milosbrkic.testpackets.storage.Storage;
import com.milosbrkic.testpackets.gui.Form;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author milos
 */
public class SocketThread extends Thread{

    private List<ServerPackage> paketi;
    private List<ResponseThread> niti;
    private static boolean stop = false;
    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;

        
    @Override
    public void run() {
        try {
            paketi = Storage.readPackages();
            niti = new LinkedList<>();
            
            socket = new Socket("hermes.plusplus.rs", 4000);
            System.out.println("Konekcija je uspostavljena \n");
            
            inputStream = socket.getInputStream();                      
            outputStream = socket.getOutputStream();            
                                               
            checkPackages();
            Form.getInstance().memory(paketi);
            System.out.println("\nPreostao broj paketa: "+paketi.size()+"\n");
            
            while(!stop){
                                              
                byte[] bytePackage = recivePackage();
                                   
                ServerPackage paket = new ServerPackage(bytePackage);
                paketi.add(paket);
                Form.getInstance().recived(paket);
                Form.getInstance().memory(paketi);
              
                System.out.println("Primljen paket: "+paket);
                System.out.println("Vreme: "+new SimpleDateFormat("hh:mm:ss").format(new Date()));
                System.out.println("\n");
                      
                //dummy
                if(paket.getType() == 1){
                    ResponseThread thread = new ResponseThread(paket, paketi, outputStream);
                    niti.add(thread);
                    thread.start();
                }
                //cancel
                else{
                    stop = true;
                }       
            }
            
            System.out.println("\nPrimanje paketa je prekinuto\n");
            
            niti.forEach((thread) -> {
                thread.interrupt();
            });
            
            Storage.savePackages(paketi);
            System.out.println("Broj paketa sacuvano: "+paketi.size());
            
            inputStream.close();
            outputStream.close();
            socket.close();
            
        } catch (IOException ex) {
            System.out.println("Greska pri uspostavljanju konekcije!");
            Form.getInstance().showError("Greska pri uspostavljanju konekcije!");
        }
    }
    
    public static void stopThread(){
        stop = true;
    }
    
    
    private byte [] recivePackage() throws IOException{
        byte[] header = new byte[4];
        byte[] body;

        inputStream.read(header);

        int type = ByteBuffer.wrap(header).order(ByteOrder.LITTLE_ENDIAN).getInt();

        if(type == 1)
            body = new byte[12];
        else
            body = new byte[8];

        inputStream.read(body);

        //spajanje headera i bodija
        byte[] bytePackage = new byte[header.length + body.length];
        System.arraycopy(header, 0, bytePackage, 0, header.length);
        System.arraycopy(body, 0, bytePackage, header.length, body.length);
        
        return bytePackage;
    }
    
    
    // proverava da li su sacuvani paketi istekli
    private void checkPackages(){
        if(paketi != null && niti != null)
        for (int i = 0; i < paketi.size(); i++) {

            ServerPackage paket = paketi.get(i);

            if(paket.getRemaningTime() <= 0){
                System.out.println("Istekao je paket: "+paket);
                Form.getInstance().expired(paket);
                paketi.remove(paket);
                i--;
            }
            else{
                ResponseThread thread = new ResponseThread(paket, paketi, outputStream);
                niti.add(thread);
                thread.start();
            }
        }       
    }
    
    
    
    
    
}
