package com.cs490.boom;

import java.util.ArrayList;

public class Video {
	//basic information
	private final int videoId;
	private final String name;
	private final String path;
	private final long size;

	//preference information
	private int groupId;
	private int preference;
	private int tag;
	private int start;		//in ms
	private int duration;	//in ms

	//critical points
	private ArrayList<Point> points;
	
	public Video(int videoId, String name, String path, long size, int duration) {
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
	
	public String getName() {
		return name;
	}
	
	public String getPath() {
		return path;
	}
	
	public long getSize() {
		return size;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setPreference(int preference) {
		this.preference = preference;
	}

	public int getPreference() {
		return preference;
	}
	
	public void setTag(int tag) {
		this.tag = tag;
	}

	public int getTag() {
		return tag;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getStart() {
		return start;
	}

	public void setDuration(int duration) {
		this.groupId = groupId;
	}

	public int getDuration() {
		return duration;
	}

	public ArrayList<Point> getPoints() {
		return points;
	}

	public void addPoint(Point point) {
		points.add(point);
	}

	public void deletePoint(Point point) {
		points.remove(points.indexOf(point));
	}
}
