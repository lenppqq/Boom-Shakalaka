package com.cs490.boom;

public class Music {
	private final String name;
	private final String path;
	private final long size;
	private int preference;
	//critical points
	
	public Music(String name, String path, long size) {
		this.name = name;
		this.path = path;
		this.size = size;
		this.preference = 0;
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
	
	public void setPreference(int preference) {
		this.preference = preference;
	}
	
	public int getPreference() {
		return preference;
	}
	
}
