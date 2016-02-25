/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs490.boom;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author mtdtao
 */
public class VideoPanel extends javax.swing.JPanel {
    private int videoIndex = -1;
    //public static String[] myMusicList;
    public static ArrayList<String> myVideoList = new ArrayList<String>();
    public static ArrayList<Video> Videoes = new ArrayList<Video>();
    /**
     * Creates new form VideoPanel
     */
    public VideoPanel() {
        initComponents();
        myVideoList.add("first");
        myVideoList.add("helo");
        myVideoList.add("third");
        
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
        addVideoBtn = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();

        videoList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        videoList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                videoListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(videoList);

        addVideoBtn.setText("Add Video");
        addVideoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addVideoBtnActionPerformed(evt);
            }
        });

        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(addVideoBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(deleteButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(129, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addVideoBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(deleteButton))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addVideoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addVideoBtnActionPerformed
        // TODO add your handling code here:
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
    }//GEN-LAST:event_addVideoBtnActionPerformed

    private void videoListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_videoListValueChanged
        // TODO add your handling code here:
        if (evt.getValueIsAdjusting()) {
            String value = (String) videoList.getSelectedValue();
            videoIndex = videoList.getSelectedIndex();
            System.out.println("you click a video "+value+" and index is "+videoIndex);
        }
    }//GEN-LAST:event_videoListValueChanged

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        // TODO add your handling code here:
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
                myVideoList.remove(videoList.getSelectedValue());
                videoList.updateUI();
            }
        }
    }//GEN-LAST:event_deleteButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addVideoBtn;
    private javax.swing.JButton deleteButton;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JList<String> videoList;
    // End of variables declaration//GEN-END:variables
}
