package com.cs490.boom;

import java.awt.BasicStroke;

/* --------------------
* Function2DDemo1.java
* --------------------
* (C) Copyright 2007, by Object Refinery Limited.
*
*/

import java.awt.Color;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class Plotting extends ApplicationFrame 
{
   public Plotting( String applicationTitle, String chartTitle )
   {
      super(applicationTitle);
      JFreeChart xylineChart = ChartFactory.createXYLineChart(
         chartTitle ,
         "Timeline" ,
         "Score" ,
         createDataset() ,
         PlotOrientation.VERTICAL ,
         true , true , false);
         
      ChartPanel chartPanel = new ChartPanel( xylineChart );
      chartPanel.setPreferredSize( new java.awt.Dimension( 1920 , 1000 ) );
      final XYPlot plot = xylineChart.getXYPlot( );
      XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
      renderer.setSeriesPaint( 0 , Color.RED );
      renderer.setSeriesStroke( 0 , new BasicStroke( 4.0f ) );
      plot.setRenderer( renderer ); 
      setContentPane( chartPanel ); 
   }
   
   private XYDataset createDataset( )
   {
	  String name = "F:\\Me\\CloudMusic\\Drop Tower - If Only You.mp3";//scanner.nextLine();
	  final XYSeries music = new XYSeries( name );          
      int[] testing = MusicAnalyzer.openMusic(name);
      
      int[] indexing = new int[testing.length];
      for(int i=0; i<testing.length; i++)
    	  indexing[i] = i;
      
	  for(int k=0; k<5; k++)  {
	      ArrayList<Integer> localMax = new ArrayList<Integer>();
	      for (int i = 0; i < testing.length-1; i+=2) {
	    		  localMax.add((testing[i]+testing[i+1])/2);
	      }
	      testing = new int[localMax.size()];
	      for(int j =0;j<localMax.size();j++){
	    	  testing[j] = localMax.get(j);
	      }
	   }

	  for(int k=0; k<2; k++)  {
	      ArrayList<Integer> localMax = new ArrayList<Integer>();
	      ArrayList<Integer> localMaxIndex = new ArrayList<Integer>();
	      for (int i = 1; i < testing.length-1; i++) {
	    	  if(testing[i-1]<testing[i] && testing[i]>testing[i+1]){
	    		  localMax.add(testing[i]);
	    		  localMaxIndex.add(indexing[i]);
	    	  }
	          //System.out.println(testing[i]);
	      }
	    //  testing = localMax.toArray(Integer);
	      testing = new int[localMax.size()];
	      indexing = new int[localMax.size()];
	      for(int j =0;j<localMax.size();j++){
	    	  testing[j] = localMax.get(j);
	    	  indexing[j] = localMaxIndex.get(j);
	      }
	   }

	  long average = 0;
      for(int i=0; i<testing.length; i++){
    	  average +=testing[i];
      }
      average = average/testing.length;
      System.out.println("average: "+ average);

      
      for(int i=0; i<testing.length; i++){
    	  music.add(i, testing[i]);
      }
      
      

      
      final XYSeriesCollection dataset = new XYSeriesCollection( );          
      dataset.addSeries( music );    
      return dataset;
   }

   public static void main( String[ ] args ) 
   {
	  Plotting chart = new Plotting("Analysised data", "Music analyzer");
      chart.pack( );          
      RefineryUtilities.centerFrameOnScreen( chart );          
      chart.setVisible( true ); 
   }
}