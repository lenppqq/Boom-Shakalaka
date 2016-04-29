/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs490.boom;

import java.io.File;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author mtdtao
 */
public class VideoPanel extends javax.swing.JPanel {
    private int videoIndex = -1;
    //public static String[] myMusicList;
    public static ArrayList<String> myVideoList = new ArrayList<String>();
    public static ArrayList<Video> videos = new ArrayList<Video>();
    /**
     * Creates new form VideoPanel
     */
    public VideoPanel() {
        initComponents();
        
        videoList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = myVideoList.toArray(new String[myVideoList.size()]);
            public int getSize() { return myVideoList.size(); }
            public String getElementAt(int i) { return myVideoList.get(i); }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        videoList = new javax.swing.JList<>();
        videoNameLabel = new javax.swing.JLabel();
        vedioLength = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(266, 350));
        setLayout(null);

        videoList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        videoList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                videoListMouseClicked(evt);
            }
        });
        videoList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                videoListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(videoList);

        add(jScrollPane1);
        jScrollPane1.setBounds(10, 11, 151, 270);

        videoNameLabel.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        videoNameLabel.setText("VideoName");
        videoNameLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        add(videoNameLabel);
        videoNameLabel.setBounds(173, 11, 141, 28);

        vedioLength.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        vedioLength.setText("Length");
        add(vedioLength);
        vedioLength.setBounds(180, 40, 141, 30);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Normal2.png"))); // NOI18N
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel1MouseReleased(evt);
            }
        });
        add(jLabel1);
        jLabel1.setBounds(180, 190, 100, 40);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Normal1.png"))); // NOI18N
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel2MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel2MouseReleased(evt);
            }
        });
        add(jLabel2);
        jLabel2.setBounds(180, 230, 100, 40);
    }// </editor-fold>//GEN-END:initComponents

    private void videoListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_videoListValueChanged
        // TODO add your handling code here:
        if (evt.getValueIsAdjusting()) {
            String value = (String) videoList.getSelectedValue();
            videoIndex = videoList.getSelectedIndex();
            videoNameLabel.setText(value);
            System.out.println("you click a video "+value+" and index is "+videoIndex);
            File file = new File(videos.get(videoIndex).path);
            if (!file.exists()) {
                videos.remove(videoList.getSelectedIndex());
                myVideoList.remove(videoList.getSelectedValue());
                videoList.updateUI();
            }
        }
    }//GEN-LAST:event_videoListValueChanged

    private void videoListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_videoListMouseClicked
        int count = evt.getClickCount();
        System.out.println( count);
        if (count == 2 && myVideoList.size() != 0) {
            String name = videoList.getSelectedValue();
            Video video = videos.get(videoList.getSelectedIndex());
            new VideoMainFrame(video).setVisible(true);
        }
        videoList.updateUI();
    }//GEN-LAST:event_videoListMouseClicked

    private void jLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseEntered
        // TODO add your handling code here:
        ImageIcon II = new ImageIcon(getClass().getResource("/Images/Hover2.png"));
        jLabel1.setIcon(II);
    }//GEN-LAST:event_jLabel1MouseEntered

    private void jLabel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseExited
        // TODO add your handling code here:
        ImageIcon II = new ImageIcon(getClass().getResource("/Images/Normal2.png"));
        jLabel1.setIcon(II);
    }//GEN-LAST:event_jLabel1MouseExited

    private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MousePressed
        // TODO add your handling code here:
        ImageIcon II = new ImageIcon(getClass().getResource("/Images/Active2.png"));
        jLabel1.setIcon(II);
    }//GEN-LAST:event_jLabel1MousePressed

    private void jLabel1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseReleased
        // TODO add your handling code here:
        ImageIcon II = new ImageIcon(getClass().getResource("/Images/Normal2.png"));
        jLabel1.setIcon(II);
        System.out.println("you click a button");
        AddVideoPopUp pop = new AddVideoPopUp();
        pop.setLocationRelativeTo(null);
        pop.setVisible(true);

        
        for (int i = 0; i < myVideoList.size(); i++) {
            System.out.println(myVideoList.get(i));
        }
        
        if (videoIndex < 0) {
            System.out.println("select index");
            
        } else {
            //JOptionPane.showMessageDialog(jButton1, "click");
            //myMusicList[musicIndex] = "this has changed";
            videoList.updateUI();
        }
    }//GEN-LAST:event_jLabel1MouseReleased

    private void jLabel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseEntered
        // TODO add your handling code here:
        ImageIcon II = new ImageIcon(getClass().getResource("/Images/Hover1.png"));
        jLabel2.setIcon(II);
    }//GEN-LAST:event_jLabel2MouseEntered

    private void jLabel2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseExited
        // TODO add your handling code here:
         ImageIcon II = new ImageIcon(getClass().getResource("/Images/Normal1.png"));
        jLabel2.setIcon(II);
    }//GEN-LAST:event_jLabel2MouseExited

    private void jLabel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MousePressed
        // TODO add your handling code here:
        ImageIcon II = new ImageIcon(getClass().getResource("/Images/Active1.png"));
        jLabel2.setIcon(II);
    }//GEN-LAST:event_jLabel2MousePressed

    private void jLabel2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseReleased
        // TODO add your handling code here:
        ImageIcon II = new ImageIcon(getClass().getResource("/Images/Normal1.png"));
        jLabel2.setIcon(II);
        System.out.println("you click the delete button");
        //DeleteMusicPopUp pop = new DeleteMusicPopUp();
        //pop.setLocationRelativeTo(null);
        //pop.setVisible(true);
        String value = (String) videoList.getSelectedValue();
        String val = "Delete \"" + value + "\" ?";
        if (value == null) {
            JOptionPane.showMessageDialog(this, "Please Select the video!!! :D", "Warning",JOptionPane.WARNING_MESSAGE);  
        } else {
            int confirm = JOptionPane.showConfirmDialog(null, val, "Confirmation",JOptionPane.YES_NO_OPTION);
            if (confirm == 0) {
                videos.remove(videoList.getSelectedIndex());
                myVideoList.remove(videoList.getSelectedValue());
                videoList.updateUI();
            }
        }
    }//GEN-LAST:event_jLabel2MouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel vedioLength;
    public static javax.swing.JList<String> videoList;
    private javax.swing.JLabel videoNameLabel;
    // End of variables declaration//GEN-END:variables
}
