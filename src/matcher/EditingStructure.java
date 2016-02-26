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
	}
	
	public String toString(){
		return "videoID: "+VideoID+"    video start position: "+ VstartPosition+
				"    video duration: " + Vduration+ "    musicID: "+ MusicID+
				"    music start position: " + MstartPosition+
				"    Mduration: "+ Mduration+
				"    music end position: " + (MstartPosition+Mduration)+
				"    effect: " + effect + "    effectArgument: " + effectArgument;
	}
}
