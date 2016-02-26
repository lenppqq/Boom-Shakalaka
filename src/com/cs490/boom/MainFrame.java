/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs490.boom;

import javax.swing.JFrame;
import matcher.Matcher;

import musicAnalyzer.MusicAnalyzer;

/**
 *
 * @author Len
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
    public static MusicAnalyzer analyzer;
    public static Database database;
    public static int vID;
    //public static Matcher matcher;

    public MainFrame() {
        database = new Database();
        analyzer = new MusicAnalyzer();
        initComponents();
        for (String name : Database.getlist()) {
            musicPanel1.myMusicList.add(name);
        }
        musicPanel1.musicList.updateUI();
        //matcher = new Matcher();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        musicPanel1 = new com.cs490.boom.MusicPanel();
        videoPanel1 = new com.cs490.boom.VideoPanel();
        matchButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().add(musicPanel1, java.awt.BorderLayout.CENTER);
        getContentPane().add(videoPanel1, java.awt.BorderLayout.LINE_START);

        matchButton.setText("Match!");
        matchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                matchButtonActionPerformed(evt);
            }
        });
        getContentPane().add(matchButton, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void matchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_matchButtonActionPerformed
        Matcher.matchAndPlay(videoPanel1.videos);
    }//GEN-LAST:event_matchButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton matchButton;
    private com.cs490.boom.MusicPanel musicPanel1;
    private com.cs490.boom.VideoPanel videoPanel1;
    // End of variables declaration//GEN-END:variables
}
