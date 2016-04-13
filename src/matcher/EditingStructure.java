
package matcher;

public class EditingStructure {
	public int VideoID;
	public int VstartPosition;
	public int Vduration;
	public int MusicID;
	public int MstartPosition;
	public int Mduration;
	public int effect;
	public double effectArgument;

	public static final int FASTANDSTATION = 1;
	public static final int SLOW = 2;
	
	public EditingStructure(){}
	public EditingStructure(int videoID, int vstart, int vdur,int mid, int mstart,int mdur, int eff, double effectArg){
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
		return "videoID: "+VideoID+" \tvideo start position: "+ VstartPosition+
				"\tvideo duration: " + Vduration+ "    musicID: "+ MusicID+
				"\tmusic start position: " + MstartPosition+
				"\tMduration: "+ Mduration+
				"\tmusic end position: " + (MstartPosition+Mduration)+
				"\teffect: " + effect + "\teffectArgument: " + effectArgument;
	}
}

