package com.cs490.boom;

public class Music {
	private String name;
	private String path;
	private long size;
	private int preference;
	//critical points
	
	public Music(String name, String path, long size) {
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
        
        public void setSize(long size) {
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
	
	public long gerSize() {
		return size;
	}

	public int getPreference() {
		return preference;
	}
	
}
