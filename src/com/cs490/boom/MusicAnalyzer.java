/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs490.boom;

import com.badlogic.audio.analysis.FFT;
import com.badlogic.audio.io.Decoder;
import com.badlogic.audio.io.WaveDecoder;
import com.badlogic.audio.visualization.Plot;
import java.awt.Color;
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
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

/**
 *
 * @author Len
 */
public class MusicAnalyzer {
    public final static int S = 256; //Sample per fft run
    public final static int R = 256; //result levels
    /**
     *
     * @param name the music file path and name
     */
    public static ArrayList<int[]> openMusic(String name) {
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
        
        //decode
        Decoder decoder;
        try {
            decoder = new WaveDecoder(new FileInputStream(name));
            
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
                    int index = Math.round(spectrum[i]*8);
                    if (index >= R) index = R-1;
                    result.get(result.size() - 1)[index]++;
                    min = spectrum[i] < min ? spectrum[i] : min;
                    max = spectrum[i] > max ? spectrum[i] : max;
                }

            }
            for (int i = 0; i < result.size(); i++) {
                for (int j = 0; j < R; j++) {
                    System.out.print(result.get(i)[j]+" ");
                }
                System.out.println();
            }
            System.out.println("min: " + min);
            System.out.println("max: " + max);
        } catch (Exception ex) {
            Logger.getLogger(MusicAnalyzer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        openMusic(name);
    }
}
