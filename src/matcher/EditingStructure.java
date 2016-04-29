package matcher;

import com.cs490.boom.Music;
import com.cs490.boom.Video;

public class EditingStructure {
	public Video VideoID;
	public int VstartPosition;
	public int Vduration;
	public Music MusicID;
	public int MstartPosition;
	public int Mduration;
	public int effect;
	public double effectArgument;

	public static final int FASTANDSTATION = 1;
	public static final int SLOW = 2;
	
	public EditingStructure(){}
	public EditingStructure(Video videoID, int vstart, int vdur,Music mid, int mstart,int mdur, int eff, double effectArg){
		  VideoID=videoID;
		  VstartPosition=vstart;
		  Vduration=vdur;
		  MusicID=mid;
		  MstartPosition=mstart;
		  Mduration=mdur;
		  effect=eff;
		  effectArgument=effectArg;
		  if(effectArgument == 1 && effect == 1){
			  effect = 0;
		  }
	}
	
	public String toString(){
		return "videoID: "+VideoID.videoId+" \tvideo start position: "+ VstartPosition+
				"\tvideo duration: " + Vduration+ "    musicID: "+ MusicID.path+
				"\tmusic start position: " + MstartPosition+
				"\tMduration: "+ Mduration+
				"\tmusic end position: " + (MstartPosition+Mduration)+
				"\teffect: " + effect + "\teffectArgument: " + effectArgument;
	}
}

