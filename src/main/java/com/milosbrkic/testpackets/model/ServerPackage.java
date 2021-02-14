/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milosbrkic.testpackets.model;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Calendar;

/**
 *
 * @author milos
 */
public class ServerPackage implements Serializable{
    
    private int type, lenght, id, delay;
    private byte[] byteFormat;
    private Calendar recivedTime;

    public ServerPackage(int type, int lenght, int id, int delay) {
        this.type = type;
        this.lenght = lenght;
        this.id = id;
        this.delay = delay;
        this.recivedTime = Calendar.getInstance();
    }

    public ServerPackage(byte[] byteFormat) {
        this.byteFormat = byteFormat;
        this.recivedTime = Calendar.getInstance();
        
        this.type =   ByteBuffer.wrap(Arrays.copyOfRange(byteFormat, 0, 4)).order(ByteOrder.LITTLE_ENDIAN).getInt();
        this.lenght = ByteBuffer.wrap(Arrays.copyOfRange(byteFormat, 4, 8)).order(ByteOrder.LITTLE_ENDIAN).getInt();
        this.id =     ByteBuffer.wrap(Arrays.copyOfRange(byteFormat, 8, 12)).order(ByteOrder.LITTLE_ENDIAN).getInt();
        if(type == 1)
            this.delay =  ByteBuffer.wrap(Arrays.copyOfRange(byteFormat, 12, 16)).order(ByteOrder.LITTLE_ENDIAN).getInt();
             
    }
    
    public long getRemaningTime(){
        Calendar now = Calendar.getInstance();
        Calendar expiringTime = (Calendar) recivedTime.clone();
        expiringTime.add(Calendar.SECOND, delay);
       
        return expiringTime.getTimeInMillis() - now.getTimeInMillis();
    }

    public byte[] getByteFormat() {
        return byteFormat;
    }

    public void setByteFormat(byte[] byteFormat) {
        this.byteFormat = byteFormat;
    }
    
        
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getLenght() {
        return lenght;
    }

    public void setLenght(int lenght) {
        this.lenght = lenght;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public Calendar getRecivedTime() {
        return recivedTime;
    }

    public void setRecivedTime(Calendar recivedTime) {
        this.recivedTime = recivedTime;
    }
    
    

    @Override
    public String toString() {       
        return "Paket: " + "type=" + type + ", lenght=" + lenght + ", id=" + id + ", delay=" + delay;
    }
    
    
    
}
