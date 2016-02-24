package com.cs490.boom;

import java.util.ArrayList;

public class Video {
	//basic information
	public final int videoId;
	public final String name;
	public final String path;
	public final int size;

	//preference information
	public int groupId;
	public int preference;
	public int tag;
	public int start;		//in ms
	public int duration;	//in ms

	//critical points
	public ArrayList<Point> points;
	
	public Video(int videoId, String name, String path, int size, int duration) {
		this.videoId = videoId;
		this.name = name;
		this.path = path;
		this.size = size;
		groupId = videoId;
		preference = 0;
		tag = 0;
		start = 0;
		this.duration = duration;
		points = new ArrayList<Point>();
	}
}
