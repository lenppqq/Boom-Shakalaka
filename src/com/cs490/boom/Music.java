package com.cs490.boom;

public class Music {
	public String name;
	public String path;
	public int size;
	public int preference;
	//critical points
	public ArrayList<Clip> clips = new ArrayList<Clip>();
	
	public Music(String name, String path, int size) {
		this.name = name;
		this.path = path;
		this.size = size;
		this.preference = 0;
	}
	
    public void setName(String name) {
        this.name = name;
    }
        
    public void setPath(String path) {
        this.path = path;
    }
        
    public void setSize(int size) {
        this.size = size;
    }
        
    public void setPreference(int preference) {
        this.preference = preference;
    }
        
	public String getName() {
		return name;
	}
	
	public String getPath() {
		return path;
	}
	
	public int gerSize() {
		return size;
	}

	public int getPreference() {
		return preference;
	}
	
}
