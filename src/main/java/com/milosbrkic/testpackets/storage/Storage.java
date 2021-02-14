/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milosbrkic.testpackets.storage;

import com.milosbrkic.testpackets.model.ServerPackage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author milos
 */
public class Storage {
    
    private static final String filename = "data";
    
    public static void savePackages(List<ServerPackage> lista){
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(filename));
            os.writeObject(lista);
            
        } catch (FileNotFoundException ex) {
            
        } catch (IOException ex) {
            
        }
    }
    
    
    public static List<ServerPackage> readPackages(){
         try {
             List<Integer> syncList = Collections.synchronizedList(new LinkedList<>());
            ObjectInputStream os = new ObjectInputStream(new FileInputStream(filename));
            return (List<ServerPackage>) os.readObject(); 
            
        } catch (IOException | ClassNotFoundException ex) {
            List<ServerPackage> syncList = Collections.synchronizedList(new LinkedList<>());
            return syncList;
        } 
        
    }
}
