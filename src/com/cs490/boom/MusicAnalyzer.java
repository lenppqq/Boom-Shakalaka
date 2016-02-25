/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs490.boom;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import com.badlogic.audio.analysis.FFT;
import com.badlogic.audio.io.Decoder;
import com.badlogic.audio.io.MP3Decoder;

/**
 *
 * @author Len
 */
public class MusicAnalyzer {
    public final static int S = 1024; //Sample per fft run
    public final static int R = 256; //result levels
    
    public MusicAnalyzer() {
    
    }
    /**
     *
     * @param name the music file path and name
     */
    public static int[] openMusic(String name) {
        //from the file name get the input file stream
        ArrayList<int[]> result;
        result = new ArrayList<int[]>();
        AudioInputStream audioInputStream = null;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(name);
            audioInputStream = AudioSystem.getAudioInputStream(
                    new BufferedInputStream(
                            fileInputStream));
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
        int[] testing = null;
        //decode
        Decoder decoder;
        try {
            decoder = new MP3Decoder(new FileInputStream(name));
            
            //create a fft obj according to the file
            FFT fft;
            fft = new FFT(S, format.getSampleRate());
            
            
            float[] samples = new float[S];
            float[] spectrum = new float[S / 2 + 1];
            float[] lastSpectrum = new float[S / 2 + 1];
            ArrayList<Float> spectralFlux = new ArrayList<Float>();
            double min = Double.MAX_VALUE;
            double max = Double.MIN_VALUE;
            while (decoder.readSamples(samples) > 0) {
                fft.forward(samples);
                System.arraycopy(spectrum, 0, lastSpectrum, 0, spectrum.length);
                System.arraycopy(fft.getSpectrum(), 0, spectrum, 0, spectrum.length);
                result.add(new int[R]);

                for (int i = 0; i < spectrum.length; i++) {
                    int index = (int) Math.round(spectrum[i]*100 );//* (1+i/((double)spectrum.length)));
                   // if (index >= R) index = R-1;
                    result.get(result.size() - 1)[0]+=index;
                }

            }
            testing = new int[result.size()];
            for (int i = 0; i < result.size(); i++) {
          	  testing[i] = result.get(i)[0];
              //for (int j = 0; j < 256; j++) {
            	//  testing[i]+=result.get(i)[j];
            	//}
              //System.out.print(testing[i]+"|");
              
            }
        } catch (Exception ex) {
            Logger.getLogger(MusicAnalyzer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return testing;
    }

    /**
     *
     * @author Ris
     * This function is the starting point of analyzation
     * 
     * @param music music object that contains basic music
     *        information
     */
    public void startAnalyze(Music music) {
        //analyze part

        //storing part
        Database.add(music);
    }

    public static void main(String[] args) {
    	System.out.print("asdasdasd");
       // Scanner scanner = new Scanner(System.in);
    	 String name = "F:\\Me\\CloudMusic\\Alan Walker - Fade.mp3";//scanner.nextLine();
        openMusic(name);
    }
}
