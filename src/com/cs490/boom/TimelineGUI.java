package com.cs490.boom;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.Player;
import javax.media.Time;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Len
 */
public class TimelineGUI extends javax.swing.JPanel {

    private int beginning;
    private int ending;
    private int beginningInWindow;
    private int endingInWindow;
    private int time;
    public Set<Point> criticalPoints;
    private Player player;
    private Playback playback;
    private static Comparator<Point> comparator = new Comparator<Point>() {
        @Override
        public int compare(Point a, Point b) {
            return a.getStart() - b.getStart();
        }
    };

    /**
     * Creates new form Timeline
     */
    public TimelineGUI() {
        initComponents();
        criticalPoints = new TreeSet<>(comparator);

        updateTimeLabel();

        this.player = null;
    }

    public TimelineGUI(int begin, int end, ArrayList<Point> points, Player player) {
        initComponents();
        setData(begin, end, points, player);
    }

    public TimelineGUI(int begin, int end, ArrayList<Point> points, Playback playback) {
        initComponents();
        setData(begin, end, points, playback);
    }

    public TimelineGUI(int begin, int end, ArrayList<Point> points) {
        initComponents();
        setData(begin, end, points);
    }

    public void setData(int begin, int end, ArrayList<Point> points, Player player) {
        beginning = begin;
        criticalPoints.clear();
        criticalPoints.addAll(points);
        ending = end;
        beginningInWindow = begin;
        endingInWindow = end;
        time = begin;
        this.player = player;
        updateTimeLabel();
        drawOnPanel();
    }

    public void setData(int begin, int end, ArrayList<Point> points, Playback playback) {
        beginning = begin;
        criticalPoints.clear();
        criticalPoints.addAll(points);
        ending = end;
        beginningInWindow = begin;
        endingInWindow = end;
        time = begin;
        this.playback = playback;
        updateTimeLabel();
        drawOnPanel();
    }

    public void setData(int begin, int end, ArrayList<Point> points) {
        beginning = begin;
        criticalPoints.clear();
        criticalPoints.addAll(points);
        ending = end;
        beginningInWindow = begin;
        endingInWindow = end;
        time = begin;
        this.player = null;
        updateTimeLabel();
        drawOnPanel();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSlider1 = new javax.swing.JSlider();
        jLabel1 = new javax.swing.JLabel();
        pointButton = new javax.swing.JToggleButton();
        rightButton = new javax.swing.JButton();
        leftButton = new javax.swing.JButton();
        timeLinePanel = new javax.swing.JPanel();
        tagField = new javax.swing.JTextField();

        setMinimumSize(new java.awt.Dimension(30, 30));

        jSlider1.setValue(0);
        jSlider1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jSlider1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider1StateChanged(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("DialogInput", 1, 18)); // NOI18N
        jLabel1.setText("0.0");
        jLabel1.setMaximumSize(new java.awt.Dimension(0, 27));

        pointButton.setText("·");
        pointButton.setToolTipText("Press to set a critical point.");
        pointButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pointButtonActionPerformed(evt);
            }
        });

        rightButton.setText(">");
        rightButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rightButtonActionPerformed(evt);
            }
        });

        leftButton.setText("<");
        leftButton.setToolTipText("");
        leftButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leftButtonActionPerformed(evt);
            }
        });

        timeLinePanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout timeLinePanelLayout = new javax.swing.GroupLayout(timeLinePanel);
        timeLinePanel.setLayout(timeLinePanelLayout);
        timeLinePanelLayout.setHorizontalGroup(
            timeLinePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        timeLinePanelLayout.setVerticalGroup(
            timeLinePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 360, Short.MAX_VALUE)
        );

        tagField.setText("Tag");
        tagField.setEnabled(false);
        tagField.setMinimumSize(new java.awt.Dimension(6, 42));
        tagField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tagFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(timeLinePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSlider1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 802, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(leftButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pointButton, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rightButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tagField, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(timeLinePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tagField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(leftButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pointButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rightButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private int updateTimeLabel() {
        int value = jSlider1.getValue();
        time = (int) (beginningInWindow + ((double) value / 100) * (endingInWindow - beginningInWindow));
        jLabel1.setText(time / 1000 + "." + time % 1000);
        return time;
    }

    private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider1StateChanged
        time = updateTimeLabel();
        boolean pointMarked = criticalPoints.contains(new Point(time, 500, 0, 0));
        pointButton.setSelected(pointMarked);
        tagField.setEnabled(pointMarked);
        if (pointMarked) {
            for (Point i : criticalPoints) {
                if (i.start == time) {
                    tagField.setText(i.tag + "");
                    break;
                }
            }
        } else {
            tagField.setText("Tag");
        }
        if (player != null) {
            player.setMediaTime(new Time((long) time * 1000000));
            player.stop();
        }
        if (playback != null) {
            playback.pause();
            playback.jumpToTime((long) time);
            playback.unpause();
        }
        drawOnPanel();

    }//GEN-LAST:event_jSlider1StateChanged

    private void moveToPoint(Point point) {
        int newTime = point.getStart();
        double percentage = 100 * (double) (newTime - beginningInWindow) / (double) (endingInWindow - beginningInWindow);
        int intPercentage = (int) Math.round(percentage);
        jSlider1.setValue(intPercentage);
        jSlider1StateChanged(null);
    }

    private void pointButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pointButtonActionPerformed
        if (pointButton.isSelected()) {
            criticalPoints.add(new Point(time, 500, 0, 0));
            tagField.setEnabled(true);
            tagField.setText("0");
        } else {
            criticalPoints.remove(new Point(time, 500, 0, 0));
            tagField.setEnabled(false);
        }
        for (Object i : criticalPoints.toArray()) {
            System.out.print(((Point) i).start + "(" + ((Point) i).tag + ")" + " ");
        }
        System.out.println();
        drawOnPanel();
    }//GEN-LAST:event_pointButtonActionPerformed

    private void rightButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rightButtonActionPerformed
        Object[] criticalArray;
        criticalArray = criticalPoints.toArray();
        if (criticalArray.length <= 1) {
            return;
        }
        Object point = criticalArray[0];
        for (int i = criticalArray.length - 1; i >= 0; i--) {
            if (((Point)criticalArray[i]).getStart() <= time) {
                break;
            }
            point = criticalArray[i];
        }
        moveToPoint((Point)point);
    }//GEN-LAST:event_rightButtonActionPerformed

    private void leftButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leftButtonActionPerformed
        Object[] criticalArray;
        criticalArray = criticalPoints.toArray();
        if (criticalArray.length <= 1) {
            return;
        }
        Object point = criticalArray[criticalArray.length - 1];
        for (int i = 0; i < criticalArray.length; i++) {
            if (((Point)criticalArray[i]).getStart() >= time) {
                break;
            }
            point = criticalArray[i];
        }

        moveToPoint((Point)point);
    }//GEN-LAST:event_leftButtonActionPerformed

    private void tagFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tagFieldActionPerformed
        // TODO add your handling code here:
        try {
            int tag = Integer.parseInt(tagField.getText());
            criticalPoints.remove(new Point(time, 500, 0, 0));
            criticalPoints.add(new Point(time, 500, tag, 0));
            for (Object i : criticalPoints.toArray()) {
                System.out.print(((Point) i).start + "(" + ((Point) i).tag + ")" + " ");
            }
            System.out.println();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_tagFieldActionPerformed

    private void drawOnPanel() {
        Graphics g = timeLinePanel.getGraphics();
        g.clearRect(0, 0, timeLinePanel.getWidth(), timeLinePanel.getHeight());
        //draw vertical line
        int linePos = (int) Math.round((double) timeLinePanel.getWidth() * (time - beginningInWindow) / (endingInWindow - beginningInWindow));
        g.drawLine(linePos, 0, linePos, timeLinePanel.getHeight());

        //drawOval accroding to the criticalPoints
        for (Point i : criticalPoints) {
            if (i.getStart() >= beginningInWindow && i.getStart() <= endingInWindow) {
                int ovalPos = (int) Math.round((double) timeLinePanel.getWidth() * (i.getStart() - beginningInWindow) / (endingInWindow - beginningInWindow));
                g.drawOval(ovalPos - 4, timeLinePanel.getHeight() / 2, 8, 8);
            }
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JButton leftButton;
    private javax.swing.JToggleButton pointButton;
    private javax.swing.JButton rightButton;
    private javax.swing.JTextField tagField;
    private javax.swing.JPanel timeLinePanel;
    // End of variables declaration//GEN-END:variables
}
