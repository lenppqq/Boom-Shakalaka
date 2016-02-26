/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicAnalyzer;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import com.badlogic.audio.analysis.FFT;
import com.badlogic.audio.io.Decoder;
import com.badlogic.audio.io.MP3Decoder;
import com.cs490.boom.Music;

import javazoom.spi.mpeg.sampled.file.MpegAudioFileReader;

/**
 *
 * @author Dou
 */
public class MusicAnalyzer {
    public final static int S = 1024; //Sample per fft run
    public final static int R = 256; //result levels
    
    public MusicAnalyzer() {
    
    }

    public static void musicFullAnalyze(Music music){
    	
    }
    
    public static ArrayList<float[]> openMusic(String name) {
        //from the file name get the input file stream
        ArrayList<float[]> result;
        result = new ArrayList<float[]>();
        AudioInputStream audioInputStream = null;
        FileInputStream fileInputStream = null;
        float durationInSeconds = 0;
        try {
            fileInputStream = new FileInputStream(name);
            audioInputStream = AudioSystem.getAudioInputStream(
                    new BufferedInputStream(
                            fileInputStream));
            
            File file = new File(name);
            AudioFileFormat baseFileFormat = new MpegAudioFileReader().getAudioFileFormat(file);
            Map properties = baseFileFormat.properties();
            durationInSeconds =  (float)(long)properties.get("duration")/1000000;
            byte[] bytes = new byte[(int) (audioInputStream.getFrameLength()) * (audioInputStream.getFormat().getFrameSize())];
            audioInputStream.read(bytes);
            
            
        } catch (Exception ex) {
            Logger.getLogger(MusicAnalyzer.class.getName()).log(Level.SEVERE, null, ex);
        }
        byte[] bytes = new byte[(int) (audioInputStream.getFrameLength()) * (audioInputStream.getFormat().getFrameSize())];
        try {
            audioInputStream.read(bytes);
        } catch (IOException ex) {
            Logger.getLogger(MusicAnalyzer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //get the format of input audio file
        AudioFormat format = audioInputStream.getFormat();
        
        
        //decode
        Decoder decoder;
        try {
            decoder = new MP3Decoder(new FileInputStream(name));
            
            //create a fft obj according to the file
            FFT fft;
            fft = new FFT(S, format.getSampleRate());

            float[] samples = new float[S];
            float[] lastSpectrum = new float[S / 2 + 1];
            while (decoder.readSamples(samples) > 0) {
            	float[] spectrum = new float[S / 2 + 1];
                fft.forward(samples);
                System.arraycopy(spectrum, 0, lastSpectrum, 0, spectrum.length);
                System.arraycopy(fft.getSpectrum(), 0, spectrum, 0, spectrum.length);
                result.add(spectrum);
            }
        } catch (Exception ex) {
            Logger.getLogger(MusicAnalyzer.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        
        result.add(new float[]{durationInSeconds});
        
        return result;
    }

    public static int[][] fullFFTAnalyze(ArrayList<float[]> fft) {
        //analyze part
    	float timeInS = fft.get(fft.size()-1)[0];
    	fft.remove(fft.size()-1);
    	int timeInMS = (int)timeInS*1000;
    	int[][] result = new int[fft.size()][2];
    	for(int j=0; j<fft.size(); j++){
    		float[] spectrum = fft.get(j);
    		result[j][0] = 0;
    		result[j][1] = MusicAnalyzer.realTimeCalculater(j, fft.size(), timeInMS);
	        for (int i = 0; i < spectrum.length; i++) {
		            result[j][0] += (int) Math.round(spectrum[i]*100 );//* (1+i/((double)spectrum.length)));
		        }
	 	}	
    	return result;
    }

    public static int[][] graph_TakeAverage(int[][] data, int time) {
    	for(int k=0; k<time; k++)  {
  	      ArrayList<Integer> localMax = new ArrayList<Integer>();
  	      ArrayList<Integer> localMaxIndex = new ArrayList<Integer>();
  	      for (int i = 0; i < data.length-1; i+=2) {
  	    		  localMax.add((data[i][0]+data[i+1][0])/2);
  	    		  localMaxIndex.add((data[i][1]+data[i+1][1])/2);
  	      }
  	    data = new int[localMax.size()][2];
  	      for(int j =0;j<localMax.size();j++){
  	    	data[j][0] = localMax.get(j);
  	    	data[j][1] = localMaxIndex.get(j);
  	      }
  	   	}
    	return data;
    }

    public static int[][] graph_TakeMax(int[][] data, int time) {
	  
    	for(int k=0; k<time; k++)  {
  	      ArrayList<Integer> localMax = new ArrayList<Integer>();
  	      ArrayList<Integer> localMaxIndex = new ArrayList<Integer>();
  	      for (int i = 1; i < data.length-1; i++) {
  	    	  if(data[i-1][0]<data[i][0] && data[i][0]>data[i+1][0]){
  	    		  localMax.add(data[i][0]);
  	    		  localMaxIndex.add(data[i][1]);
  	    	  }
  	      }
  	      data = new int[localMax.size()][2];
  	      for(int j =0;j<localMax.size();j++){
  	    	data[j][0] = localMax.get(j);
  	    	data[j][1] = localMaxIndex.get(j);
  	      }
  	    }
    	return data;
    }

    public final static int CONST_BREAKING_HORIZON = 10;

    public static int realTimeCalculater(int vrTime, int vrBound, int realBound){
    	return (int)((double)vrTime*(double)realBound/(double)vrBound);
    }
    
    public static ArrayList<int[]>  music_TideHigh(int[][] data) {
    	int average = MusicAnalyzer.averageValue(data);
    	int breakingHorizon = average/CONST_BREAKING_HORIZON;
    	ArrayList<int[]> tideHigh = new ArrayList<int[]>();
    	boolean inHigh = false;
    	int lastI = 0;
    	if(data[0][0]>average)
    		inHigh = true;
    	for(int i=0; i<data.length; i++){
    		if(inHigh){
    			for(;i<data.length && data[i][0]>(average-breakingHorizon);i++);
    			int[] high = new int[]{data[lastI][1], data[i][1]};
    			tideHigh.add(high);
    			if(i==data.length)
    				return tideHigh;
    			inHigh = false;
    			lastI = i;
    		}else{
    			for(;i<data.length && data[i][0]<(average+breakingHorizon);i++);
    			if(i==data.length)
    				return tideHigh;
    			inHigh = true;
    		}
    	}
    	return tideHigh;
    }
    
    public static int music_TideHighCount(int[][] data){
    	return MusicAnalyzer.music_TideHigh(data).size();
    }
    

    
    
    public static int averageValue(int[][] data){
  	  long average = 0;
      for(int i=0; i<data.length; i++){
    	  average +=data[i][0];
      }
      average = average/data.length;
      return (int) average;
    }
    
    public static void main(String[] args) {
       // Scanner scanner = new Scanner(System.in);
    	 String name = "F:\\Me\\CloudMusic\\Maybe In Japan.mp3";//scanner.nextLine();
        openMusic(name);
    }
}
