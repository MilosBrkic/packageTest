/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milosbrkic.testpackets.gui;

import com.milosbrkic.testpackets.model.ServerPackage;
import com.milosbrkic.testpackets.thread.SocketThread;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author milos
 */
public class Form extends javax.swing.JFrame {

    private static Form instance;
    
    private int recivedCount = 0;
    private int sentCount = 0;
            
    private Form() {
        initComponents();
        setLocationRelativeTo(null);
    }
    
    public static Form getInstance(){
        if(instance == null)
            instance = new Form();
        
        return instance;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaPrimljeni = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaPoslati = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabelPrimljeni = new javax.swing.JLabel();
        jLabelCekanje = new javax.swing.JLabel();
        jLabelPoslati = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Paketi");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jTextAreaPrimljeni.setEditable(false);
        jTextAreaPrimljeni.setColumns(20);
        jTextAreaPrimljeni.setRows(5);
        jScrollPane3.setViewportView(jTextAreaPrimljeni);

        jTextAreaPoslati.setEditable(false);
        jTextAreaPoslati.setColumns(20);
        jTextAreaPoslati.setRows(5);
        jScrollPane1.setViewportView(jTextAreaPoslati);

        jLabel1.setText("Primljeni paketi:");

        jLabel2.setText("Posalti paketi:");

        jLabel3.setText("Paketi na cekanju:");

        jLabelPrimljeni.setText("0");

        jLabelCekanje.setText("0");

        jLabelPoslati.setText("0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelPoslati, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelPrimljeni, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelCekanje, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabelPrimljeni))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabelCekanje))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabelPoslati))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        SocketThread.stopThread();
    }//GEN-LAST:event_formWindowClosing

    
    
    public synchronized void recived(ServerPackage paket){       
        String vreme = new SimpleDateFormat("hh:mm:ss").format(new Date());
        jTextAreaPrimljeni.append(vreme+"   "+paket+"\n");
        jTextAreaPrimljeni.setCaretPosition(jTextAreaPrimljeni.getDocument().getLength());
        
        recivedCount++;
        jLabelPrimljeni.setText(""+recivedCount);
    }
    
    public synchronized void memory(List<ServerPackage> lista){
        jLabelCekanje.setText(""+lista.size());
    }
       
    public synchronized void sent(ServerPackage paket){
        String vreme = new SimpleDateFormat("hh:mm:ss").format(new Date());
        jTextAreaPoslati.append(vreme+"   "+paket+"\n");
        jTextAreaPoslati.setCaretPosition(jTextAreaPoslati.getDocument().getLength());
        
        sentCount++;
        jLabelPoslati.setText(""+sentCount);
    }
    
    public synchronized void expired(ServerPackage paket){
        jTextAreaPoslati.append("Istekao je "+paket+"\n");
    }
    
    public void showError(String message){
        JOptionPane.showMessageDialog(this, message, "Greška", JOptionPane.ERROR_MESSAGE);
    }    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelCekanje;
    private javax.swing.JLabel jLabelPoslati;
    private javax.swing.JLabel jLabelPrimljeni;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextAreaPoslati;
    private javax.swing.JTextArea jTextAreaPrimljeni;
    // End of variables declaration//GEN-END:variables
}
