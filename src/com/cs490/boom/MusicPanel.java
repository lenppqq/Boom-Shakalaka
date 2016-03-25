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
        deleteButton = new javax.swing.JButton();
        removeButton = new javax.swing.JButton();
        musicNameLabel = new javax.swing.JLabel();
        musicGrade = new javax.swing.JLabel();
        editButton = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(367, 350));

        musicList.setMaximumSize(new java.awt.Dimension(33, 80));
        musicList.setMinimumSize(new java.awt.Dimension(33, 80));
        musicList.setPreferredSize(new java.awt.Dimension(33, 80));
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

        addMusicButton.setFont(new java.awt.Font("Sitka Subheading", 3, 12)); // NOI18N
        addMusicButton.setText("Add Music");
        addMusicButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addMusicButtonActionPerformed(evt);
            }
        });

        deleteButton.setFont(new java.awt.Font("Sitka Subheading", 3, 12)); // NOI18N
        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteMusicButtonActionPerformed(evt);
            }
        });

        removeButton.setFont(new java.awt.Font("Sitka Subheading", 3, 12)); // NOI18N
        removeButton.setText("Remove");
        removeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeButtonActionPerformed(evt);
            }
        });

        musicNameLabel.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        musicNameLabel.setText("MusicName");

        musicGrade.setText("Rate: -");

        editButton.setFont(new java.awt.Font("Lucida Grande", 0, 8)); // NOI18N
        editButton.setText("Edit");
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(deleteButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addMusicButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(removeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(musicNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(musicGrade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(70, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(musicNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(musicGrade)
                            .addComponent(editButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addMusicButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(removeButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteButton))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
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
            updateNameGradeLabel(value);
            System.out.println("you click a music " + value + " and index is " + musicIndex);
        }

    }//GEN-LAST:event_musicListValueChanged

    private void updateNameGradeLabel(String name) {
        if (name == null) {
            musicNameLabel.setText("MusicName");
            musicGrade.setText("Rate: -");
        } else {
            musicNameLabel.setText(name);
            Music selectedMusic = Database.getrow(name);
            musicGrade.setText("Rate: " + Integer.toString(selectedMusic.getPreference()));
        }
    }
    
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
//                Database.delete_name(name);
            }
        }

        for (int i = 0; i < myMusicList.size(); i++) {
            System.out.println(myMusicList.get(i));
        }
        
        //update music name and grade label
        String value = (String) musicList.getSelectedValue();
        musicIndex = musicList.getSelectedIndex();
        System.out.println("===value is " + value);
        updateNameGradeLabel(value);
    }//GEN-LAST:event_DeleteMusicButtonActionPerformed

    private void musicListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_musicListMouseClicked
        int count = evt.getClickCount();
        if (count == 2) {
            String name = musicList.getSelectedValue();
            Music music = Database.getrow(name);
            new MusicMainFrame(music).setVisible(true);
        }
     }//GEN-LAST:event_musicListMouseClicked

    private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeButtonActionPerformed
        if (!musicList.isSelectionEmpty()) {
            Database.delete_name(musicList.getSelectedVamusicGrade
            myMusicList.remove(musicList.getSelectedValue());
        }     
       
        musicList.updateUI();
    }//GEN-LAST:event_removeButtonActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        // TODO add your handling code here:
        if (musicList.isSelectionEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Select a music!", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            EditGradePopUp pop = new EditGradePopUp(musicList.getSelectedValue());
//          pop.musicName = "hallo";
            pop.setLocationRelativeTo(null);
            pop.setVisible(true);
        }
        
        
    }//GEN-LAST:event_editButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addMusicButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton editButton;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JLabel musicGrade;
    public static javax.swing.JList<String> musicList;
    public static javax.swing.JLabel musicNameLabel;
    private javax.swing.JButton removeButton;
    // End of variables declaration//GEN-END:variables
}
