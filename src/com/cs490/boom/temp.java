/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs490.boom;

import static com.cs490.boom.MusicPanel.musicList;
import static com.cs490.boom.MusicPanel.myMusicList;
import static com.cs490.boom.VideoPanel.myVideoList;
import static com.cs490.boom.VideoPanel.videoList;
import java.awt.Toolkit;

/**
 *
 * @author mtdtao
 */
public class temp extends javax.swing.JFrame {

    /**
     * Creates new form temp
     */
    public temp() {
        initComponents();
        setIcon();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        exportVideoPanel1 = new com.cs490.boom.ExportVideoPanel();
        musicPanel1 = new com.cs490.boom.MusicPanel();
        videoPanel1 = new com.cs490.boom.VideoPanel();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        Menu_AddMusic = new javax.swing.JMenuItem();
        Menu_AddVideo = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        Menu_Exit = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(650, 350));
        setPreferredSize(new java.awt.Dimension(650, 350));
        setSize(new java.awt.Dimension(650, 350));
        getContentPane().setLayout(null);
        getContentPane().add(exportVideoPanel1);
        exportVideoPanel1.setBounds(220, 90, 141, 79);

        musicPanel1.setOpaque(false);
        getContentPane().add(musicPanel1);
        musicPanel1.setBounds(0, 0, 310, 320);

        videoPanel1.setOpaque(false);
        getContentPane().add(videoPanel1);
        videoPanel1.setBounds(324, 0, 276, 320);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/x.png"))); // NOI18N
        jLabel1.setToolTipText("");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 650, 350);

        jMenuBar2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jMenuBar2.setFont(new java.awt.Font("Sitka Subheading", 3, 12)); // NOI18N

        jMenu3.setText("File");
        jMenu3.setFont(new java.awt.Font("Sitka Heading", 3, 12)); // NOI18N

        Menu_AddMusic.setFont(new java.awt.Font("Sitka Subheading", 3, 12)); // NOI18N
        Menu_AddMusic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/music_ico.png"))); // NOI18N
        Menu_AddMusic.setText("Add Music");
        Menu_AddMusic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Menu_AddMusicActionPerformed(evt);
            }
        });
        jMenu3.add(Menu_AddMusic);

        Menu_AddVideo.setFont(new java.awt.Font("Sitka Subheading", 3, 12)); // NOI18N
        Menu_AddVideo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/video_ico.png"))); // NOI18N
        Menu_AddVideo.setText("Add Video");
        Menu_AddVideo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Menu_AddVideoActionPerformed(evt);
            }
        });
        jMenu3.add(Menu_AddVideo);
        jMenu3.add(jSeparator1);

        Menu_Exit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        Menu_Exit.setFont(new java.awt.Font("Sitka Subheading", 3, 12)); // NOI18N
        Menu_Exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/exit_ico.png"))); // NOI18N
        Menu_Exit.setText("Exit");
        Menu_Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Menu_ExitActionPerformed(evt);
            }
        });
        jMenu3.add(Menu_Exit);

        jMenuBar2.add(jMenu3);

        jMenu4.setText("Help");
        jMenu4.setFont(new java.awt.Font("Sitka Heading", 3, 12)); // NOI18N
        jMenuBar2.add(jMenu4);

        setJMenuBar(jMenuBar2);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Menu_AddMusicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Menu_AddMusicActionPerformed
        AddMusicPopUp pop = new AddMusicPopUp();
        pop.setLocationRelativeTo(null);
        pop.setVisible(true);
        
        for (int i = 0; i < myMusicList.size(); i++) {
            System.out.println(myMusicList.get(i));
        }
        
        musicList.updateUI();
        
    }//GEN-LAST:event_Menu_AddMusicActionPerformed

    private void Menu_AddVideoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Menu_AddVideoActionPerformed
        AddVideoPopUp pop = new AddVideoPopUp();
        pop.setLocationRelativeTo(null);
        pop.setVisible(true);
        
        for (int i = 0; i < myVideoList.size(); i++) {
            System.out.println(myVideoList.get(i));
        }
        
        videoList.updateUI();
    }//GEN-LAST:event_Menu_AddVideoActionPerformed

    private void Menu_ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Menu_ExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_Menu_ExitActionPerformed

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
            java.util.logging.Logger.getLogger(temp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(temp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(temp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(temp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try
                {
                    Thread.sleep(2000);
                    //Thread.sleep(4500);
                }
                catch(Exception e) {
                    
                }
                
                new temp().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Menu_AddMusic;
    private javax.swing.JMenuItem Menu_AddVideo;
    private javax.swing.JMenuItem Menu_Exit;
    private com.cs490.boom.ExportVideoPanel exportVideoPanel1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private com.cs490.boom.MusicPanel musicPanel1;
    private com.cs490.boom.VideoPanel videoPanel1;
    // End of variables declaration//GEN-END:variables

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
    }

}
