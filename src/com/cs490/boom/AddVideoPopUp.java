/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs490.boom;

import com.xuggle.xuggler.IContainer;
import java.io.File;
import java.io.IOException;
//import static java.lang.Math.toIntxEact;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.CannotRealizeException;
import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author mtdtao
 */
public class AddVideoPopUp extends javax.swing.JFrame {

    private String fileName;
    private String filePath;
    private int fileDuration;
    private int fileLength;
    private File f;

    /**
     * Creates new form AddVideoPopUp
     */
    public AddVideoPopUp() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        filePathField = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        addVideoBtn = new javax.swing.JButton();
        cancelBtn = new javax.swing.JButton();
        alertMsg = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        filePathField.setEnabled(false);
        filePathField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filePathFieldActionPerformed(evt);
            }
        });
        getContentPane().add(filePathField, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 34, 414, -1));

        jButton1.setText("Browse");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 33, -1, -1));

        addVideoBtn.setText("Add Video");
        addVideoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addVideoBtnActionPerformed(evt);
            }
        });
        getContentPane().add(addVideoBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(149, 143, -1, -1));

        cancelBtn.setText("Cancel");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });
        getContentPane().add(cancelBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(282, 143, -1, -1));

        alertMsg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        alertMsg.setText(" ");
        getContentPane().add(alertMsg, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 118, 487, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/wall-1.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 510, 290));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void filePathFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filePathFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_filePathFieldActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("MP4 file", "mp4"));
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.showOpenDialog(null);
        try {
            f = chooser.getSelectedFile();

            fileName = f.getName().substring(0, f.getName().lastIndexOf("."));
            filePath = f.getAbsolutePath();
            f.exists();

//            Player player = Manager.createRealizedPlayer(f.toURI().toURL());
//            fileDuration = (int) (player.getDuration().getNanoseconds() / 1000000);
            //XUGGLER
            IContainer container = IContainer.make();
            int result = container.open(filePath, IContainer.Type.READ, null);
            fileDuration = (int) container.getDuration()/1000;
        } catch (Exception ex) {
            Logger.getLogger(AddVideoPopUp.class.getName()).log(Level.SEVERE, null, ex);
        }

        fileLength = (int) (f.length());
        System.out.println("length is " + fileLength);
        filePathField.setText(filePath);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void addVideoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addVideoBtnActionPerformed
        // TODO add your handling code here:
        if (fileName == null) {
            alertMsg.setText("Please select a music");
        } else if (!f.exists()) {
            System.out.println("file exist: " + f.exists());
            alertMsg.setText("File doesn't exist, please select a music");
        } else {
            Video videofile = new Video(MainFrame.vID++, fileName, filePath, fileLength, fileDuration);
            System.out.println(videofile.videoId + " " + videofile.path);
            VideoPanel.videos.add(0, videofile);
            VideoPanel.myVideoList.add(0, fileName);
            VideoPanel.videoList.updateUI();
//            Music newMusic = new Music(fileName, filePath, fileLength);
            //MainFrame.analyzer.startAnalyze(newMusic);

            this.setVisible(false);

        }
    }//GEN-LAST:event_addVideoBtnActionPerformed

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_cancelBtnActionPerformed

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
            java.util.logging.Logger.getLogger(AddVideoPopUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddVideoPopUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddVideoPopUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddVideoPopUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddVideoPopUp().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addVideoBtn;
    private javax.swing.JLabel alertMsg;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JTextField filePathField;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
