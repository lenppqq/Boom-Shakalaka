package matcher;

import java.util.ArrayList;

import com.cs490.boom.Video;

import musicAnalyzer.MusicAnalyzer;

public class Matcher {
    public final static int CONST_TIDE_DIFFERENCE = 1;
    public final static int CONST_DIVISION_LENGTH = 100;
    
    public static void matchAndPlay(ArrayList<Video> videos){
    	
    }
    
    public static int[] bestMatchOption( ArrayList<int[]> prototype, int[] videoLength){
    	int[] order = new int[videoLength.length];
    	int[] duration = new int[videoLength.length];
    	for(int i=0; i<videoLength.length; i++){
    		duration[i] = prototype.get(i)[1] - prototype.get(i)[0];
    	}
    	for(int i=0; i<videoLength.length; i++){
    		int currentOrder = 0;
    		for(int j=0; j<videoLength.length; j++){
    			if(i!=j && duration[i] > duration[j])
    				currentOrder++;
    		}
    		order[i] = currentOrder;
    	}
    	return order;
    }
    
    public static int difference(int n1, int n2){
    	if(n1>n2)
    		return n1-n2;
    	return n2-n1;
    }
    
    public static int bestMatchOptionScore( ArrayList<int[]> prototype, int[] videoLength){
    	int[] order = bestMatchOption(prototype, videoLength);
    	return 0;
    }
    
    
    
    public static ArrayList<int[]> bestMergeOption( ArrayList<int[]> prototype, int[] videoLength){
    	int highestScore = -999999;
    	int scoreIndex = -1;
    	for(int i=0; i<prototype.size()-1; i++){
    		ArrayList<int[]> copytest = new ArrayList<int[]>(prototype);
    		copytest.get(i)[1] = copytest.get(i+1)[1];
    		copytest.remove(i+1);
    		if(bestMatchOptionScore(copytest, videoLength)>highestScore){
    			highestScore = bestMatchOptionScore(copytest, videoLength);
    			scoreIndex = i;
    		}
    	}
    	ArrayList<int[]> besttest = new ArrayList<int[]>(prototype);
    	besttest.get(scoreIndex)[1] = besttest.get(scoreIndex+1)[1];
    	besttest.remove(scoreIndex+1);
	
		int[] matchOption = bestMatchOption(besttest, videoLength);
		besttest.add(matchOption);
    	
    	return besttest;
    }
    
    public static ArrayList<int[]> graph_Division(int[][] data, int[] videoLength) {
  	  	int currentTideCount = 0;
  	  	int count = videoLength.length;
  	  	//int[][] lastData;
    	do{
    	//	lastData = data;
    		data = MusicAnalyzer.graph_TakeAverage(data, 1);
    		currentTideCount = MusicAnalyzer.music_TideHighCount(data);
    	}while((currentTideCount>count+CONST_TIDE_DIFFERENCE)
    			&& data.length>CONST_DIVISION_LENGTH);
    	
    	/*if(currentTideCount<count){
    		data = lastData;
    		currentTideCount = MusicAnalyzer.music_TideHighCount(data);
    		if(currentTideCount>count+CONST_TIDE_DIFFERENCE)
    			return null;
    	}*/
    	
    	
    	if(data.length<=CONST_DIVISION_LENGTH)
    		return null;
    	
    	ArrayList<int[]> prototype = MusicAnalyzer.music_TideHigh(data);
    	
    	
    	if(prototype.size()==count){
    		 int[] matchOption = bestMatchOption(prototype, videoLength);
    		 prototype.add(matchOption);
    		 return prototype;
    	}
    	
    	return Matcher.bestMergeOption(prototype, videoLength);
    	
    }
}
