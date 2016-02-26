/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs490.boom;

import java.io.File;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author mtdtao
 */
public class MusicPanel extends javax.swing.JPanel {

    private int musicIndex = -1;
    //public static String[] myMusicList;
    public static ArrayList<String> myMusicList = new ArrayList<String>();

    /**
     * Creates new form MusicPanel
     */
    public MusicPanel() {
        initComponents();
        musicList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = myMusicList.toArray(new String[myMusicList.size()]);

            public int getSize() {
                return myMusicList.size();
            }

            public String getElementAt(int i) {
                return myMusicList.get(i);
            }
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
        musicList = new javax.swing.JList<>();
        addMusicButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        musicList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                musicListMouseClicked(evt);
            }
        });
        musicList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                musicListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(musicList);

        addMusicButton.setText("add music");
        addMusicButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addMusicButtonActionPerformed(evt);
            }
        });

        jButton1.setText("Delete");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteMusicButtonActionPerformed(evt);
            }
        });

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(addMusicButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(103, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addMusicButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addMusicButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addMusicButtonActionPerformed
        // TODO add your handling code here:
        //DefaultListModel model = new DefaultListModel();
        System.out.println("you click a button");
        AddMusicPopUp pop = new AddMusicPopUp();
        pop.setLocationRelativeTo(null);
        pop.setVisible(true);

        for (int i = 0; i < myMusicList.size(); i++) {
            System.out.println(myMusicList.get(i));
        }

        if (musicIndex < 0) {
            System.out.println("select index");

        } else {
            //JOptionPane.showMessageDialog(jButton1, "click");
            //myMusicList[musicIndex] = "this has changed";
            musicList.updateUI();
        }

    }//GEN-LAST:event_addMusicButtonActionPerformed

    private void musicListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_musicListValueChanged
        // TODO add your handling code here:
        if (evt.getValueIsAdjusting()) {
            String value = (String) musicList.getSelectedValue();
            musicIndex = musicList.getSelectedIndex();
            System.out.println("you click a music " + value + " and index is " + musicIndex);
        }

    }//GEN-LAST:event_musicListValueChanged

    private void DeleteMusicButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteMusicButtonActionPerformed
        System.out.println("you click the delete button");
        //DeleteMusicPopUp pop = new DeleteMusicPopUp();
        //pop.setLocationRelativeTo(null);
        //pop.setVisible(true);
        String musicName = (String) musicList.getSelectedValue();
//        String musicPath = Database.getrow(musicName).getPath();
//        File f = new File(musicPath);       
        System.out.println(getName());
        if (musicName == null) {
            JOptionPane.showMessageDialog(this, "Please Select the music!!! :D", "Warning", JOptionPane.WARNING_MESSAGE);

        } else {
            String val = "Delete \"" + musicName + "\" ?";
            int confirm = JOptionPane.showConfirmDialog(null, val, "Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirm == 0) {
                String name = musicList.getSelectedValue();
                myMusicList.remove(name);
                musicList.updateUI();
                Database.delete_name(name);
            }
        }

        for (int i = 0; i < myMusicList.size(); i++) {
            System.out.println(myMusicList.get(i));
        }
    }//GEN-LAST:event_DeleteMusicButtonActionPerformed

    private void musicListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_musicListMouseClicked
        int count = evt.getClickCount();
        if (count == 2) {
            String name = musicList.getSelectedValue();
            Music music = Database.getrow(name);
            new MusicMainFrame(music).setVisible(true);
        }
     }//GEN-LAST:event_musicListMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (!musicList.isSelectionEmpty()) {
            Database.delete_name(musicList.getSelectedValue());
            musicList.remove(musicList.getSelectedIndex());
        }     
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addMusicButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JList<String> musicList;
    // End of variables declaration//GEN-END:variables
}
