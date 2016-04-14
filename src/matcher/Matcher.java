package matcher;

import java.util.ArrayList;

import org.jfree.data.xy.XYSeries;
import org.jfree.ui.RefineryUtilities;

import com.cs490.boom.Point;
import com.cs490.boom.Music;
import com.cs490.boom.Video;
import com.cs490.boom.Database;
import musicAnalyzer.MusicAnalyzer;
/**
*
* @author Dou
*/
public class Matcher {
    public final static int CONST_TIDE_DIFFERENCE = 1;
    public final static int CONST_DIVISION_LENGTH = 50;
    

    
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
    	if(scoreIndex!=-1){
	    	besttest.get(scoreIndex)[1] = besttest.get(scoreIndex+1)[1];
	    	besttest.get(scoreIndex)[2] = besttest.get(scoreIndex+1)[2];
	    	besttest.get(scoreIndex)[3] = besttest.get(scoreIndex+1)[3];
	        besttest.remove(scoreIndex+1);
    	}
		//int[] matchOption = bestMatchOption(besttest, videoLength);
		//besttest.add(matchOption);
    	
    	return besttest;
    }
    

    
    
    public static ArrayList<int[]> graph_Division(int[][] data, int[] videoLength) {
  	  	int currentTideCount = 0;
  	  	int count = videoLength.length;
  	  	int[][] org = data;
  	  	//int[][] lastData;
    	do{
    	//	lastData = data;
    		data = MusicAnalyzer.graph_TakeAverage(data, 1);
    		currentTideCount = MusicAnalyzer.music_TideHighCount(data,org);
    	}while((currentTideCount>count+CONST_TIDE_DIFFERENCE)
    			&& data.length>CONST_DIVISION_LENGTH);
    	

    	/*if(currentTideCount<count){
    		data = lastData;
    		currentTideCount = MusicAnalyzer.music_TideHighCount(data);
    		if(currentTideCount>count+CONST_TIDE_DIFFERENCE)
    			return null;
    	}*/

    	//System.out.println("currentTideCount: " + currentTideCount + "   length: " + data.length);
    	if(data.length<=CONST_DIVISION_LENGTH)
    		return null;
    	
    	ArrayList<int[]> prototype = MusicAnalyzer.music_TideHigh(data,org);

    		
    	
    	if(prototype.size()==count){
    		// int[] matchOption = bestMatchOption(prototype, videoLength);
    		// prototype.add(matchOption);
    		 prototype.get(0)[0] = 0;
    		// prototype.get(prototype.size() - 2)[1] = data[data.length - 1][1];
    		 prototype.get(prototype.size() - 1)[1] = data[data.length - 1][1];
     		 return prototype;
    	}
    	prototype = Matcher.bestMergeOption(prototype, videoLength);
		prototype.get(0)[0] = 0;
		//prototype.get(prototype.size() - 2)[1] = data[data.length - 1][1];
		prototype.get(prototype.size() - 1)[1] = data[data.length - 1][1];
    	return prototype;
    	
    }
    
 

	private static ArrayList<Video> videos;
    private static int tempScore;
    
    private final static int TYPECOUNT = 3;
    
    public static ArrayList<EditingStructure> clipMatching(ArrayList<float[]> fft, int groupID, int startTime, int endTime){
    	int scores[] = new int[TYPECOUNT];
    	ArrayList<EditingStructure> edits[] = (ArrayList<EditingStructure>[])new ArrayList[TYPECOUNT];
    	int highestI = 0;
    	int highestScore = Integer.MIN_VALUE;
    	boolean possible = false;
    	for(int i=0; i<TYPECOUNT; i++){
    		edits[i] = Type(i,fft, groupID,startTime,endTime);
    		scores[i] = tempScore;
    		if(tempScore>highestScore){
    			highestI = i;
    			highestScore = tempScore;
    			possible = true;
    		}
    	}
    	
    	if(possible == false){
    		return null;
    	}
    	tempScore = highestScore;
    	return edits[highestI];
    }


	public static void matchAndPlay(ArrayList<Video> Videos) {
		ArrayList<String> allMusics = Database.getlist();
		int bestEditScore = Integer.MIN_VALUE;
		ArrayList<EditingStructure> bestEdit = null;
		for(int k=0; k<allMusics.size(); k++){
			Music currentMusic = Database.getrow(allMusics.get(k));
			String name = currentMusic.getPath();
					//String name = "F:\\Me\\CloudMusic\\OMFG - Hello.mp3";// scanner.nextLine();
			musicID = currentMusic;
			ArrayList<float[]> fft = MusicAnalyzer.openMusic(name);
			if(fft==null)
				continue;
			int[][] data = MusicAnalyzer.fullFFTAnalyze(fft);
	
			ArrayList<Integer> videoN = new ArrayList<Integer>();
			for(int i=0; i<Videos.size(); i++){
				if(Videos.get(i).preference == MAINPRIO){
					videoN.add(Videos.get(i).duration);
				}
			}
			
			int[] videoLength = new int[videoN.size()];
			for(int i=0; i<videoN.size(); i++){
				videoLength[i] = videoN.get(i);
			}
	
			videos = Videos;
	
			
			ArrayList<int[]> divisionResult = Matcher.graph_Division(data, videoLength);
	
			if(divisionResult==null)
				continue;
	
			//int[] order = divisionResult.get(divisionResult.size() - 1);
			boolean flag = true;
			
			ArrayList<EditingStructure> editTotal = new ArrayList<EditingStructure>();
			
			for (int i = 0; i < divisionResult.size() ; i++) {
				algorithmSetup(i);
	
				
				EditingStructure edit_prefix = new EditingStructure
						(mainV, 
						 mainP.get(0).start - (divisionResult.get(i)[2] - divisionResult.get(i)[0]), 
						 divisionResult.get(i)[2] - divisionResult.get(i)[0], 
						 musicID, 
						 divisionResult.get(i)[0],
						 divisionResult.get(i)[2] - divisionResult.get(i)[0],
						 SPEEDCHANGEEFFECTID, 
						 1);
				EditingStructure edit_surfix = new EditingStructure
						(mainV, 
						 mainIntervalP.end , 
						 divisionResult.get(i)[1] - divisionResult.get(i)[3], 
						 musicID, 
						 divisionResult.get(i)[3],
						 divisionResult.get(i)[1] - divisionResult.get(i)[3],
						 SPEEDCHANGEEFFECTID, 
						 1);
				
				
				
				ArrayList<EditingStructure> edit = Matcher.clipMatching(fft, i,
						divisionResult.get(i)[2], divisionResult.get(i)[3]);
				
				
				if( edit==null){
					flag = false;
					break;
				}else{
					edit.add(0, edit_prefix);
					edit.add(edit_surfix);
					
					editTotal.addAll(edit);
				}
			}
			if(flag == false){
				continue;
			}


			
			
			if(EffectScore(editTotal) * currentMusic.preference > bestEditScore){
				bestEditScore = EffectScore(editTotal)* currentMusic.preference;
				bestEdit = editTotal;
			}
			
		}
		
		for (int j = 0; j < bestEdit.size(); j++)
			System.out.println(bestEdit.get(j).toString());
		
		//////////////////////// convert bestEdit to actual video editing
		
    }
    
    public static ArrayList<int[]> subgraph_Division2(int[][] data, int divisionLength) {

    	int total = data[data.length-1][1] - data[0][1];
    	int[][] data2 = data;
    	int diff = Integer.MAX_VALUE;
    	int diff2 = Integer.MAX_VALUE;
    	do{
    		data = data2;
    		diff = diff2;
    		
    		data2 = MusicAnalyzer.graph_TakeMax(data, 1);
    		int totalPoints = data2.length;
    		if(data2[0][1]!=data[0][1]){
    			totalPoints++;
    		}
    		if(data2[data2.length-1][1]!=data[data.length-1][1]){
    			totalPoints++;
    		}
    		diff2 = difference((int)((double)total/totalPoints), divisionLength);
    	}while(diff2 < divisionLength);
    	
    	return null;
    	
    }
    
    public static ArrayList<int[]> subgraph_Division1(int[][] data, int divisionN) {

    	int total = data[data.length-1][1] - data[0][1];
    	int[][] data2 = data;
    	int diff = Integer.MAX_VALUE;
    	int diff2 = Integer.MAX_VALUE;
    	int totalPoints = 0;
    	do{
    		data = data2;
    		diff = diff2;
    		
    		data2 = MusicAnalyzer.graph_TakeMax(data, 1);
    		totalPoints = data2.length;
    		if(data2[0][1]!=data[0][1]){
    			totalPoints++;
    		}
    		if(data2[data2.length-1][1]!=data[data.length-1][1]){
    			totalPoints++;
    		}
    		
    	}while(totalPoints < divisionN);
    	
    	return null;
    	
    }
    
    

    public final static int MAINPRIO = 9;
    public final static int OFFPRIO = 7;
    public final static int MAINCLIMAXPROI = 5;
    public final static int OFFCLIMAXPRIO = 3;
    
    public final static int MAININTERVALPRIO = 9;
    public final static int MAINPOINTPRIO = 7;
    public final static int OFFPOINTPROI = 5;
    
    
    private static Video mainV=null;
    private static Video offV=null;
    private static Video mainClimaxV=null;
    private static Video offClimaxV = null;
	
    
    
    private static Point mainIntervalP;
    private static ArrayList<Point> mainP = new ArrayList<Point>();
    private static ArrayList<Point> offP = new ArrayList<Point>();
    
    private static void algorithmSetup(int groupID){
    	topClimax = 0;
    	secondClimax = 0;
        mainV=null;
        offV=null;
        mainClimaxV=null;
        mainIntervalP=null;
        CSum = 0;
        SSum = 0;
        offClimaxV = null;
        mainP = new ArrayList<Point>();
        offP = new ArrayList<Point>();
    	for(int i=0; i<videos.size(); i++){
    		if(videos.get(i).groupId == groupID){
    			if(videos.get(i).preference == MAINPRIO){
    				mainV = videos.get(i);
    			}
    			if(videos.get(i).preference == OFFPRIO){
    				offV = videos.get(i);
    			}
    			if(videos.get(i).preference == MAINCLIMAXPROI){
    				mainClimaxV = videos.get(i);
    				topClimax++;
    			}
    			if(videos.get(i).preference == OFFCLIMAXPRIO){
    				offClimaxV = videos.get(i);
    				secondClimax++;
    			}
    		}
    	}
    	
    	
    	for(int i=0; i<mainV.points.size(); i++){
    		if( mainV.points.get(i).preference == MAININTERVALPRIO){
    			mainIntervalP = mainV.points.get(i);
    		}
    		
    		if( mainV.points.get(i).preference == MAINPOINTPRIO){
    			mainP.add(mainV.points.get(i));
    			if(topClimax!=0){
    				CSum += 2*mainV.points.get(i).duration;
    			}
    			if(secondClimax!=0){
    				CSum += 2*mainV.points.get(i).duration;
    			}
    		}
    		
    		if( mainV.points.get(i).preference == OFFPOINTPROI){
    			offP.add(mainV.points.get(i));
    			if(topClimax!=0){
    				SSum += 2*mainV.points.get(i).duration;
    			}
    			if(secondClimax!=0){
    				SSum += 2*mainV.points.get(i).duration;
    			}
    		}
    	}
    	
    	Q = mainV.duration;
    	
    	L = mainIntervalP.duration;
    	
    	D = mainIntervalP.end - mainP.get(0).start;
    
    	GF = 0;
    	
    }
    
    
    private static final int EFFECTCONSTANTINIT = 20;
    private static final int EFFECTDECRE = 5;
    
    private static int effectScore = EFFECTCONSTANTINIT;

    private static int EffectScore( ArrayList<EditingStructure> edit){
   // 	effectScore -= EFFECTDECRE;
    	return edit.size();
    }
    
    private static int Q; // all
    private static int D; // critical interval
    private static int L; // critical start
    private static int GF; // free editing
    
    private static int CSum;
    private static int SSum;
    private static int topClimax;
    private static int secondClimax;

    

    

    private static int totalDuration ( ArrayList<Point> editingP ){
    	int total = 0;
    	for(int i=0; i<editingP.size(); i++){
    		total+=editingP.get(i).duration;
    	}
    	return total;
    }
    
    public static int[][] dataCut(int[][] data, int leftBound, int rightBound) {
	    ArrayList<Integer> local = new ArrayList<Integer>();
	    ArrayList<Integer> localIndex = new ArrayList<Integer>();
    	for(int i=0; i<data.length; i++){
    		
    		//System.out.println("leftBound: "+ leftBound +"  data: "+data[i][1] + "  rightBound:" + rightBound);
    		
    		if(data[i][1]>leftBound && data[i][1] < rightBound){
	    		  local.add(data[i][0]);
	    		  localIndex.add(data[i][1]);
	    		//  System.out.println(": s");

    		}
    		
    	}
    	data = new int[local.size()][2];
	      for(int j =0;j<local.size();j++){
	    	data[j][0] = local.get(j);
	    	data[j][1] = localIndex.get(j);
	      }
	    
  	return data;
    }
/*    public static int[] subgraph_Division3(int[][] data, int startPoint, int duration) {

    	int diff = Integer.MAX_VALUE;
    	int diff2 = Integer.MAX_VALUE;
    	
    	data = dataCut(data, startPoint-duration, startPoint+2*duration);
    	int[][] data2 = data;
    	
    	int total = data[data.length-1][1] - data[0][1];
    	
    	do{
    		data = data2;
    		diff = diff2;
    		
    		data2 = MusicAnalyzer.graph_TakeMax(data, 1);
    		int totalPoints = data2.length;
    		if(data2[0][1]!=data[0][1]){
    			totalPoints++;
    		}
    		if(data2[data2.length-1][1]!=data[data.length-1][1]){
    			totalPoints++;
    		}
    		
    	//	System.out.println("totalPoints: "+totalPoints);
    		diff2 = difference((int)((double)total/totalPoints), duration);
    	}while(diff2 < diff && data2.length>=2);
    	
    	
    	int closest = Integer.MAX_VALUE;
    	int finalStartPoint = 0;
    	int finalDuration = 0;
    	for(int i=0; i<data.length-1; i++){
    		if( difference(data[i][1], startPoint) < closest){
    			finalStartPoint = data[i][1];
    			closest = difference(data[i][1], startPoint);
    			finalDuration = data[i+1][1] - data[i][1];
    		}
    	}
    	
    	
    	return new int[]{finalStartPoint, finalDuration} ;
    }*/
    private static int maxInData(int[][] data){
    	int maxI = -1;
    	int maxScore = -100;
    	for(int i=0; i< data.length; i++){
    		if(maxScore<data[i][0]){
    			maxI = i;
    			maxScore = data[i][0];
    		}
    	}
    	return maxI;
    }
   
    public static int[] subgraph_Division3(int[][] data, int startPoint, int endPoint, int startBall, int endBall) {

    	int[][] dataStart = dataCut(data, startPoint-startBall, startPoint+startBall);
    	int[][] dataEnd = dataCut(data, endPoint-endBall, endPoint+endBall);
    	
    	int startMax = maxInData(dataStart);
    	int endMax = maxInData(dataEnd);
    	
    	if(startMax == -1)
    		startMax = startPoint;
    	else
    		startMax = dataStart[startMax][1];
    	
    	if(endMax == -1)
    		endMax = endPoint;
    	else
    		endMax = dataEnd[endMax][1];
    	
    	
    	return new int[]{startMax, endMax-startMax} ;
    	
    }
    

    private static Music musicID = null;
    
    
    public static final int SPEEDCHANGEEFFECTID = 1;
    
    private static ArrayList<EditingStructure> detailCriticalEditing(int vstart, int vdur,int mstart,int mdur, Video offClimaxVID){
    	ArrayList<EditingStructure> totalResult = new ArrayList<EditingStructure>();
    	if(((double)mdur/vdur)<=2 || mdur<2000){
		//if(1==1){
			EditingStructure ResultI1 = new EditingStructure
					(mainClimaxV, 
			 		 vstart, 
					 vdur, 
					 musicID, 
					 mstart,
					 mdur,
					 SPEEDCHANGEEFFECTID, 
					 (double)mdur/vdur);
			totalResult.add(ResultI1);
    	}else{
    		if(offClimaxVID==null){
    			EditingStructure ResultI1 = new EditingStructure
    					(mainClimaxV, 
    			 		 vstart, 
    					 vdur, 
    					 musicID, 
    					 mstart,
    					 2*vdur,
    					 SPEEDCHANGEEFFECTID, 
    					 (double)2);
    			totalResult.add(ResultI1);
    			
    			EditingStructure ResultI2 = new EditingStructure
    					(mainClimaxV, 
    			 		 vstart+vdur-(mdur-2*vdur), 
    					 (mdur-2*vdur), 
    					 musicID, 
    					 mstart+2*vdur,
    					 (mdur-2*vdur),
    					 SPEEDCHANGEEFFECTID, 
    					 (double)1);
    			totalResult.add(ResultI2);
    		}else{
    			EditingStructure ResultI1 = new EditingStructure
    					(mainClimaxV, 
    			 		 vstart, 
    					 vdur, 
    					 musicID, 
    					 mstart,
    					 (int)(1.5*vdur),
    					 SPEEDCHANGEEFFECTID, 
    					 (double)1.5);
    			totalResult.add(ResultI1);
    			
    			EditingStructure ResultI2 = new EditingStructure
    					(mainV, 
    			 		 vstart+vdur-(mdur-(int)(1.5*vdur))/2, 
    					 (mdur-(int)(1.5*vdur))/2, 
    					 musicID, 
    					 mstart+(int)(1.5*vdur),
    					 (mdur-(int)(1.5*vdur))/2,
    					 SPEEDCHANGEEFFECTID, 
    					 (double)1);
    			totalResult.add(ResultI2);
    			
    			EditingStructure ResultI3 = new EditingStructure
    					(offClimaxVID, 
    			 		 vstart+vdur-(mdur-(int)(1.5*vdur))/2, 
    					 (mdur-(int)(1.5*vdur)), 
    					 musicID, 
    					 mstart+(int)(1.5*vdur)+(mdur-(int)(1.5*vdur))/2,
    					 (mdur-(int)(1.5*vdur))/2,
    					 SPEEDCHANGEEFFECTID, 
    					 (double)1);
    			totalResult.add(ResultI3);
    		}
    	}
		return totalResult;
			
    }
    private static ArrayList<EditingStructure> detailNonCriticalEditing(int vstart, int vdur,int mstart,int mdur){
    	ArrayList<EditingStructure> totalResult = new ArrayList<EditingStructure>();
    	if(vdur>mdur){
			EditingStructure ResultI1 = new EditingStructure
					(mainV, 
					vstart, 
					vdur, 
					 musicID, 
					 mstart,
					 mdur,
					 SPEEDCHANGEEFFECTID, 
					 (double)mdur/vdur);
			totalResult.add(ResultI1);
    	}else{
    		EditingStructure ResultI1 = new EditingStructure
    				(mainV, 
    				 vstart+vdur-mdur, 
    				 mdur, 
    				 musicID, 
    				 mstart,
    				 mdur,
    				 SPEEDCHANGEEFFECTID, 
    				 1);
    		totalResult.add(ResultI1);
    	}
		return totalResult;
    }
    
    private static Video findOffClimax(int tagID){
    	if(offClimaxV!=null)
    		return offClimaxV;
    	return null;
    }
    
    private static ArrayList<EditingStructure> detailEditing(ArrayList<Point> editingP, ArrayList<float[]> fft,  int climaxStart, int climaxEnd){
    	int climaxDuration = climaxEnd - climaxStart;
    	int criticalMultiplier = 0;
    	int totalCrit = totalDuration(editingP);
    	double slowFactor = 1;
    	
    	if(topClimax!=0)
    		criticalMultiplier += 1;
    	if(secondClimax!=0)
    		criticalMultiplier += 1; 
    	
    	int[][] data = MusicAnalyzer.bassExtraction(fft);
    	
    	if(climaxDuration<totalCrit+D || climaxDuration > totalCrit*2*criticalMultiplier+D || editingP.size()<=1){
    	//	System.out.println("NOOO");
    		return null;
    	}
    	//System.out.println("YESSS!!! " + editingP.size());
    	
    	
    	
    	
    	ArrayList<EditingStructure> editingResult = new  ArrayList<EditingStructure>();
    	int currentMusic = climaxStart;
    	
		int totalFree = climaxDuration - D;
		slowFactor = (double)(totalFree) / totalCrit;
		
		//System.out.println("slow: " + slowFactor);
		int[][] criticalFinal = new int [editingP.size()][6];
		
		for(int i=0; i<editingP.size(); i++){
    		int PointIDuration = (int) (editingP.get(i).duration * slowFactor);
    		
    		
    		
    		int startBall = 0;
    		if(i!=0){
    			if( PointIDuration/5 < (editingP.get(i).start - editingP.get(i-1).end)/10)
    				startBall = PointIDuration/5;
    			else
    				startBall = (editingP.get(i).start - editingP.get(i-1).end)/10;
    		}
    		if(startBall<0)
    			startBall = 0;
    		
    		int endBall = 0;
    		
    		if(i!=editingP.size()-1){
    			if( PointIDuration/5 < (editingP.get(i+1).start - editingP.get(i).end)/10)
    				endBall = PointIDuration/5;
    			else
    				endBall = (editingP.get(i+1).start - editingP.get(i).end)/10;
    		}
    		if(endBall<0)
    			endBall = 0;
    		
    		int[] PointIFinal = subgraph_Division3(data, currentMusic, currentMusic+PointIDuration, startBall, endBall);
/*    		System.out.print("currentMusic: "+currentMusic);
    		System.out.print("  endPoint: "+(currentMusic+PointIDuration));
    		System.out.print("  startBall: "+startBall);
    		System.out.print("  endBall: "+endBall);
    		System.out.print("  PointIDuration: " + PointIDuration);
    		System.out.println("  PointIFinal[1]: " + PointIFinal[1]);
    		*/
    		criticalFinal[i][0] = PointIFinal[0];
    		criticalFinal[i][1] = PointIFinal[1];
    		criticalFinal[i][2] = PointIFinal[0]+PointIFinal[1];

    	/*	System.out.println("PointIFinal[0]: "+PointIFinal[0]);
    		System.out.println("PointIFinal[2]: "+criticalFinal[i][2]);
    		System.out.println("startBall: "+startBall);
    		System.out.println("endBall: "+endBall);
    		System.out.println("1currentPosision: "+currentMusic);
    		*/
    		
    	//	System.out.println("slowFactor: "+slowFactor);
    		criticalFinal[i][3] = editingP.get(i).start;
    		criticalFinal[i][4] = editingP.get(i).duration;
    		criticalFinal[i][5] = editingP.get(i).duration + editingP.get(i).start;
    		
    		
    		currentMusic = criticalFinal[i][2];
    		if(i+1 < editingP.size())
        		if(i%2==0){
        			currentMusic += editingP.get(i+1).end - editingP.get(i).start;
        	//		System.out.println("456: "+(editingP.get(i+1).end - editingP.get(i).start));
        		}
        		else if(editingP.get(i+1).start - editingP.get(i).end >=0){
        			currentMusic +=	editingP.get(i+1).start - editingP.get(i).end;	
        		//	System.out.println("123: "+(editingP.get(i+1).start - editingP.get(i).end));
        		}
    		//System.out.println("1currentPosision: "+currentMusic);
    		
    		totalFree -= PointIFinal[1];
    		totalCrit -= editingP.get(i).duration;
    		slowFactor = (double)(totalFree) / totalCrit;
    		
    	}
		
		int orgDuration = 0;
		int newDuration = 0;
		
		for(int i=0; i<editingP.size()-1; i++){
			if(i%2 == 0){
				orgDuration =  editingP.get(i+1).end - editingP.get(i).start;
				newDuration =  criticalFinal[i+1][0] - criticalFinal[i][2];
				
				
				Video offVideoClimax = findOffClimax(editingP.get(i).tag);
				
				ArrayList<EditingStructure> ResultI1 = detailCriticalEditing(
						 criticalFinal[i][3], 
						 criticalFinal[i][4],
						 criticalFinal[i][0],
						 criticalFinal[i][1],
						 offVideoClimax);
				
				ArrayList<EditingStructure> ResultI2 = detailNonCriticalEditing(
						editingP.get(i).start, 
						orgDuration, 
						criticalFinal[i][2],
						newDuration);
				
				offVideoClimax = findOffClimax(editingP.get(i+1).tag);
				
				ArrayList<EditingStructure> ResultI3 = detailCriticalEditing(
						criticalFinal[i+1][3], 
						 criticalFinal[i+1][4], 
						 criticalFinal[i+1][0],
						 criticalFinal[i+1][1],
						 offVideoClimax);
						
				editingResult.addAll(ResultI1);
				editingResult.addAll(ResultI2);
				editingResult.addAll(ResultI3);    				
			}else{
				orgDuration =  editingP.get(i+1).start - editingP.get(i).end;
				newDuration =  criticalFinal[i+1][0] - criticalFinal[i][2];
				

				ArrayList<EditingStructure> ResultI1 = detailNonCriticalEditing(
						editingP.get(i).end, 
						orgDuration, 
						criticalFinal[i][2],
						newDuration);
				
				editingResult.addAll(ResultI1);
			}
		}
    	return editingResult;
    }
    
    private static ArrayList<EditingStructure> Type(int type, ArrayList<float[]> fft, int groupID, int climaxStart, int climaxEnd){
    	int climaxDuration = climaxEnd - climaxStart;
    	algorithmSetup(groupID);
    	ArrayList<Point> editingP = new ArrayList<Point>();
    	int bestScore = 0;
    	ArrayList<EditingStructure> bestEditing = null;
    	int[][] data = MusicAnalyzer.fullFFTAnalyze(fft);
    	switch(type){
    	case 0:
    	//	if((int)((double)D/2+(double)CSum/2)-GF<climaxDuration  &&  D<climaxDuration){
    			for(int i=0; i<mainV.points.size(); i++){
	        		if( mainV.points.get(i).preference == MAININTERVALPRIO){
	        			mainV.points.remove(i);
	        		}
	    		}
    			ArrayList<EditingStructure> curEdit1 = basicClipMatching(mainV, mainV.points.get(0).start , D , mainV.points , climaxStart, climaxEnd);
    			mainV.points.add(mainIntervalP);
    			if(curEdit1!=null){
    				tempScore = -50000;
    				return curEdit1;
    			}
    		//}
	    	tempScore = Integer.MIN_VALUE;
	    	return null;
    	case 1:
    		System.out.println("TYPE 2: start: " + (D) + " end: " + (D+CSum+SSum) + " free:" + (D+CSum+SSum-D) + " climaxDuration:" + climaxDuration);
    		if(D-GF<climaxDuration  &&  climaxDuration<D+CSum+SSum){
    		//	System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        		
        		for(int i=0; i<mainP.size(); i++){
        			editingP.add(mainP.get(i));
        			ArrayList<EditingStructure> curEdit = detailEditing(editingP, fft, climaxStart,climaxEnd);
        			if(curEdit!=null){
        				if(EffectScore(curEdit)>=bestScore){
        					bestEditing = curEdit;
        					bestScore = EffectScore(curEdit);
        				}
        			}
        		}
        		
        		for(int i=0; i<offP.size(); i++){
        			editingP.add(offP.get(i));
        			ArrayList<EditingStructure> curEdit = detailEditing(editingP, fft, climaxStart,climaxEnd);
        			if(curEdit!=null){
        				if(EffectScore(curEdit)>=bestScore){
        					bestEditing = curEdit;
        					bestScore = EffectScore(curEdit);
        				}
        			}
        		}
        		
        		tempScore = bestScore;
        		return bestEditing;
        		
        	}
        	tempScore = Integer.MIN_VALUE;
        	return null;
    	case 2:
    		System.out.println("TYPE 3: start: " + (D+L-GF) + " end: " + (D+CSum+SSum+Q) + " free:" + (D+CSum+SSum+Q-D-L) + " climaxDuration:"+climaxDuration);
		
        	if(D+L-GF<climaxDuration  &&  climaxDuration<D+CSum+SSum+Q){
        		
        		int division = (Q-L)/DIVISIONCONSTANT;
        		
        		if(Q>climaxEnd)
        			division = (climaxDuration-5000)/DIVISIONCONSTANT;
        		
        		for(int i=0; i<DIVISIONCONSTANT; i++){
        			ArrayList<EditingStructure> curEdit = Type(1, fft, groupID, climaxStart, climaxEnd-L-i*division);
        			if(curEdit != null){
        				EditingStructure ResultI1 = new EditingStructure
    							(mainV, 
    							 mainIntervalP.start-i*division, 
    							 mainIntervalP.duration + i*division, 
    							 musicID, 
    							 climaxEnd-L-i*division,
    							 L+i*division,
    							 SPEEDCHANGEEFFECTID, 
    							 1);
        				curEdit.add(ResultI1);
        				
        				return curEdit;
        			}
        		}
        	}
        	tempScore = Integer.MIN_VALUE;
        	return null;
    	}
    	tempScore = Integer.MIN_VALUE;
    	return null;
    }
    private static final int DIVISIONCONSTANT = 16;

    public static ArrayList<EditingStructure> basicClipMatching(Video videoID, int videoStart,int videoDuration, ArrayList<Point> points, int startTime, int endTime){
    	int currentDuration = endTime-startTime;
    	int nonCritDuration = videoDuration;
    	double multiplierFactor = 0;
    	ArrayList<EditingStructure> edit = new ArrayList<EditingStructure>();

    	for(int i=0; points!=null&&i<points.size(); i++){
    		Point point = points.get(i);
    		if(point.tag==1){
    			currentDuration -= point.duration*1;
    		}else{
    			currentDuration -= point.duration*1.5;
    		}
    		nonCritDuration -= point.duration;
    	}
    	//if(currentDuration <= video.duration){
    		multiplierFactor = (double)currentDuration/nonCritDuration;
    		//System.out.println("sad: " + (nonCritDuration));
    	    		int currentPosition = videoStart;
    		int currentMusic = startTime;
    		for(int i=0; i<points.size(); i++){
        		Point point = points.get(i);
        		EditingStructure es = new EditingStructure(
        			  videoID,
      				  currentPosition,
      				  point.start-currentPosition,
      				  musicID,
      				  currentMusic,
      				  (int)((point.start-currentPosition)*multiplierFactor),
      				  EditingStructure.FASTANDSTATION,
      				  multiplierFactor
      				);
        		if(point.start!=currentPosition)
        			edit.add(es);
        		currentPosition = point.start; 
        		currentMusic += (int)((point.start-currentPosition)*multiplierFactor);
        		double tagMultiplier = 0;
        		if(point.tag == 1){
        			tagMultiplier = 1;
        		}else{
        			tagMultiplier = 1.5;
        		}
        		EditingStructure es2 = new EditingStructure(
        				videoID,
      				  currentPosition,
      				  point.duration,
      				musicID,
      				  currentMusic,
      				  (int)((point.duration)*tagMultiplier),
      				  EditingStructure.SLOW,
      				  tagMultiplier
      				);
        		currentPosition += point.duration;
        		currentMusic += (int)((point.duration)*tagMultiplier);
        		edit.add(es2);
        	 }
    		EditingStructure es = new EditingStructure(
    				videoID,
    				  currentPosition,
    				  videoStart+videoDuration-currentPosition,
    				  musicID,
    				  currentMusic,
    				  (int)((videoStart+videoDuration-currentPosition)*multiplierFactor),
    				  EditingStructure.FASTANDSTATION,
    				  multiplierFactor
    				);
    		if(videoDuration!=currentPosition)
    			edit.add(es);
    		
    	//}else{
    		
    	return edit;
    }
    
  /*  public final static int MAININTERVALPRIO = 9;
    public final static int MAINPOINTPRIO = 7;
    public final static int OFFPOINTPROI = 5;*/
    
    public static void main(String args[]){
    	//new Database();
	   	//String name = "F:\\Me\\CloudMusic\\OMFG - Hello.mp3";// scanner.nextLine();
	   	//Music testing = new Music("Hello", name, 30);
	   	//testing.setPreference(10);
	   	//Database.add(testing);
		/*int[][] data = MusicAnalyzer.fullFFTAnalyze(MusicAnalyzer.openMusic(name));
		int[] videoLength = new int[]{180000,180000};
    	ArrayList<int[]> divisionResult = Matcher.graph_Division(data, videoLength);
    	
    	for(int i=0; i<divisionResult.size(); i++){
    		int[] temp = divisionResult.get(i);
    		for(int j=0; j<temp.length; j++)
    			System.out.print(temp[j]+ " ");
    		System.out.println("");
    	}*/
    

     	Video v1 = new Video(1,"test1", "/test1", 30, 180000);
    	Video v11 = new Video(11,"test2", "/test2", 30, 180000);
    	Video v12 = new Video(12,"test2", "/test2", 30, 180000);
    	v1.setPreference(MAINPRIO);
    	v1.setGroupId(0);
    	v11.setGroupId(0);
    	v11.setPreference(MAINCLIMAXPROI);
    	v12.setGroupId(0);
    	v12.setPreference(OFFCLIMAXPRIO);
    	
    	Video v2 = new Video(2,"test2", "/test2", 30, 180000);
     	Video v21 = new Video(21,"test1", "/test1", 30, 180000);
     	Video v22 = new Video(22,"test1", "/test1", 30, 180000);
     	v2.setPreference(MAINPRIO);
     	v2.setGroupId(1);
    	v21.setGroupId(1);
    	v21.setPreference(MAINCLIMAXPROI);
    	v22.setGroupId(1);
    	v22.setPreference(OFFCLIMAXPRIO);
    	//	Video v3 = new Video(3,"test3", "/test3", 30, 68000);
   // 	Video v4 = new Video(4,"test4", "/test4", 30, 75000);

    	Point p1 = new Point(100000, 60000, 0, MAININTERVALPRIO);
    	Point p2 = new Point(100000, 50000, 0, MAININTERVALPRIO);
    	Point p5 = new Point(110000, 2000, 0, MAINPOINTPRIO);
    	Point p6 = new Point(130000, 4000, 0, MAINPOINTPRIO);
    	Point p7 = new Point(135000, 200, 0, MAINPOINTPRIO);
    	Point p8 = new Point(139400, 500, 0, MAINPOINTPRIO);
    	Point p9 = new Point(140000, 2000, 0, MAINPOINTPRIO);
    	Point p10 =new Point(145000, 1000, 0, MAINPOINTPRIO);
        v1.addPoint(p1);
        v1.addPoint(p5);
        v1.addPoint(p6);
        v1.addPoint(p7);
    	v1.addPoint(p8);
    	v1.addPoint(p9);
    	v1.addPoint(p10);
    	
    	v2.addPoint(p2);
    	v2.addPoint(p6);
    	v2.addPoint(p7);
    	
    	
    	ArrayList<Video> vs = new ArrayList<Video>();
    	vs.add(v1);
    	vs.add(v2);
    	vs.add(v11);
    	vs.add(v12);
    	vs.add(v21);
    	vs.add(v22);
    	Matcher.matchAndPlay(vs);
    }
}
