package com.cs490.boom;

public class Music {
	public String name;
	public String path;
	public int size;
	public int preference;
	//critical points
	public ArrayList<Clip> clips;
	
	public Music(String name, String path, int size) {
		this.name = name;
		this.path = path;
		this.size = size;
		this.preference = 0;
		clips = new ArrayList<Clip>();
	}
}
