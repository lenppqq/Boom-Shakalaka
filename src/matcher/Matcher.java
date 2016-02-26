package matcher;

import java.util.ArrayList;

import org.jfree.data.xy.XYSeries;
import org.jfree.ui.RefineryUtilities;

import com.cs490.boom.Point;
import com.cs490.boom.Video;

import musicAnalyzer.MusicAnalyzer;
/**
*
* @author Dou
*/
public class Matcher {
    public final static int CONST_TIDE_DIFFERENCE = 1;
    public final static int CONST_DIVISION_LENGTH = 100;
    
    public static void matchAndPlay(ArrayList<Video> videos){
    	String name = "/Users/Len/Dropbox/mij.mp3";// scanner.nextLine();
		int[][] data = MusicAnalyzer.fullFFTAnalyze(MusicAnalyzer.openMusic(name));
		int[] videoLength = new int[videos.size()];
    	
		for(int i=0; i<videos.size(); i++){
    		videoLength[i] = videos.get(i).duration;
    	}
		ArrayList<int[]> divisionResult = Matcher.graph_Division(data, videoLength);
    	
		if(divisionResult == null){
			System.out.println("Current Music requier 4 or 5 videos");
			return;
		}
		
		int[] order = divisionResult.get(divisionResult.size()-1);
		
    	for(int i=0; i<divisionResult.size()-1; i++){
    		//System.out.println("CheckPoint 1");
    		ArrayList<EditingStructure> edit = 
    		Matcher.clipMatching(data, videos.get(order[i]), divisionResult.get(i)[0], divisionResult.get(i)[1]);
    		//System.out.println("CheckPoint 2");
    		for(int j=0; j<edit.size(); j++)
				System.out.println(edit.get(j).toString());
    	}
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
    	int score = 0;
    	
    	int[] duration = new int[videoLength.length];
    	for(int i=0; i<videoLength.length; i++){
    		duration[i] = prototype.get(i)[1] - prototype.get(i)[0];
    	}
    	
    	for(int i=0; i<videoLength.length; i++){
    		score -= difference( duration[order[i]], videoLength[i]);
    	}
    	return score;
    }
    
    
    
    public static ArrayList<int[]> bestMergeOption( ArrayList<int[]> prototype, int[] videoLength){
    	int highestScore = -999999;
    	int scoreIndex = -1;
    	for(int i=0; i<prototype.size()-1; i++){
    		ArrayList<int[]> copytest = new ArrayList<int[]>();
    		
    		for(int j=0; j<prototype.size(); j++){
    			int[] temp = new int[]{prototype.get(j)[0], prototype.get(j)[1]};
    			copytest.add(temp);
    		}
    		
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
    
    public static ArrayList<EditingStructure> clipMatching(int[][] data, Video video, int startTime, int endTime){
    	int currentDuration = endTime-startTime;
    	int nonCritDuration = video.duration;
    	double multiplierFactor = 0;
    	ArrayList<EditingStructure> edit = new ArrayList<EditingStructure>();
    	ArrayList<Point> points = video.getPoints();
    	for(int i=0; points!=null&&i<points.size(); i++){
    		Point point = points.get(i);
    		if(point.tag==1){
    			currentDuration -= point.duration*1.5;
    		}else{
    			currentDuration -= point.duration*2;
    		}
    		nonCritDuration -= point.duration;
    	}
    	//if(currentDuration <= video.duration){
    		multiplierFactor = (double)currentDuration/video.duration;
    		int currentPosition = 0;
    		int currentMusic = startTime;
    		for(int i=0; i<points.size(); i++){
        		Point point = points.get(i);
        		EditingStructure es = new EditingStructure(
      				  video.videoId,
      				  currentPosition,
      				  point.start-currentPosition,
      				  1,
      				  currentMusic,
      				  (int)((point.start-currentPosition)*multiplierFactor),
      				  EditingStructure.FASTANDSTATION,
      				  multiplierFactor
      				);
        		
        		currentPosition = point.start; 
        		currentMusic += (int)((point.start-currentPosition)*multiplierFactor);
        		double tagMultiplier = 0;
        		if(point.tag == 1){
        			tagMultiplier = 1.5;
        		}else{
        			tagMultiplier = 2;
        		}
        		EditingStructure es2 = new EditingStructure(
      				  video.videoId,
      				  currentPosition,
      				  point.duration,
      				  1,
      				  currentMusic,
      				  (int)((point.duration)*tagMultiplier),
      				  EditingStructure.SLOW,
      				  tagMultiplier
      				);
        		currentPosition += point.duration;
        		currentMusic += (int)((point.duration)*tagMultiplier);
        		edit.add(es);
        		edit.add(es2);
        	 }
    		EditingStructure es = new EditingStructure(
    				  video.videoId,
    				  currentPosition,
    				  video.duration-currentPosition,
    				  1,
    				  currentMusic,
    				  (int)((video.duration-currentPosition)*multiplierFactor),
    				  EditingStructure.FASTANDSTATION,
    				  multiplierFactor
    				);
    		edit.add(es);
    		
    	//}else{
    		
    	return edit;
    }
    
    
    
    public static void main(String args[]){
    	
/*    	String name = "F:\\Me\\CloudMusic\\Maybe In Japan.mp3";// scanner.nextLine();
		int[][] data = MusicAnalyzer.fullFFTAnalyze(MusicAnalyzer.openMusic(name));
		int[] videoLength = new int[]{51000,60000,68000,75000};
    	ArrayList<int[]> divisionResult = Matcher.graph_Division(data, videoLength);
    	
    	for(int i=0; i<divisionResult.size(); i++){
    		int[] temp = divisionResult.get(i);
    		for(int j=0; j<temp.length; j++)
    			System.out.print(temp[j]+ " ");
    		System.out.println("");
    	}*/
    

    	Video v1 = new Video(1,"test1", "/test1", 30, 51000);
    	Video v2 = new Video(2,"test2", "/test2", 30, 60000);
    	Video v3 = new Video(3,"test3", "/test3", 30, 68000);
    	Video v4 = new Video(4,"test4", "/test4", 30, 75000);

    	Point p1 = new Point(10000, 500, 2, 0);
    	Point p2 = new Point(30000, 500, 2, 0);
    	Point p3 = new Point(35000, 500, 1, 0);
    	Point p4 = new Point(40000, 500, 1, 0);
    	v1.addPoint(p1);
    	v1.addPoint(p2);
    	v1.addPoint(p3);
    	
    	v2.addPoint(p2);
    	v2.addPoint(p4);
    	
    	
    	ArrayList<Video> vs = new ArrayList<Video>();
    	vs.add(v1);
    	vs.add(v2);
    	vs.add(v3);
    	vs.add(v4);
    	Matcher.matchAndPlay(vs);
    }
}
