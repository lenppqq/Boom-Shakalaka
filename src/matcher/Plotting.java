package matcher;

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

import musicAnalyzer.MusicAnalyzer;

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
		String name = "F:\\Me\\CloudMusic\\Maybe In Japan.mp3";// scanner.nextLine();
		final XYSeries music = new XYSeries(name);
		int[][] data = MusicAnalyzer.fullFFTAnalyze(MusicAnalyzer.openMusic(name));
		
		System.out.println("average1: "+ MusicAnalyzer.averageValue(data));
		
		data = MusicAnalyzer.graph_TakeAverage(data, 7);
		System.out.println("average2: "+ MusicAnalyzer.averageValue(data));
		System.out.println("dataSize: "+ data.length);
//		data = MusicAnalyzer.graph_TakeMax(data, 5);

		System.out.println("average3: "+ MusicAnalyzer.averageValue(data));

      
		for(int i=0; i<data.length; i++){
			music.add(data[i][1], data[i][0]);
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