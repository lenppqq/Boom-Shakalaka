package com.cs490.boom;


public class Point {
    private int start;
	private int duration;
	private int tag;
	private int preference;

	public Point(int start, int duration, int tag, int preference) {
		this.start = start;
		this.duration = duration;
		this.tag = tag;
		this.preference = preference;
	}

	public void setStart(start) {
		this.start = start;
	}

	public int getStart() {
		return start;
	}

	public void setDuration(duration) {
		this.duration = duration;
	}

	public int getDuration() {
		return duration;
	}

	public void setTag(tag) {
		this.tag = tag;
	}

	public int getTag() {
		return tag;
	}

	public void setPreference(preference) {
		this.preference = preference;
	}

	public int getPreference() {
		return preference;
	}
}
