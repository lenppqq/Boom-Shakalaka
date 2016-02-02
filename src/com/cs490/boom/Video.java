package com.cs490.boom;

public class Video {
	private final String name;
	private final String path;
	private final long size;
	//critical points
	
	public Video(String name, String path, long size) {
		this.name = name;
		this.path = path;
		this.size = size;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPath() {
		return path;
	}
	
	public long getSize() {
		return size;
	}
	
}
