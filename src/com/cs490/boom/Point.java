package com.cs490.boom;

public class Point implements Comparable {

    public int start;
    public int end;
    public int duration;
    public int tag;
    public int preference;

    public Point(int start, int duration, int tag, int preference) {
        this.start = start;
        this.duration = duration;
        this.tag = tag;
        this.preference = preference;
        this.end = start + duration;
    }

    public Point(int start) {
        this.start = start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getStart() {
        return start;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public int getTag() {
        return tag;
    }

    public void setPreference(int preference) {
        this.preference = preference;
    }

    public int getPreference() {
        return preference;
    }

    @Override
    public int compareTo(Object o) {
        return this.getStart() - ((Point) o).getStart();
    }
}
