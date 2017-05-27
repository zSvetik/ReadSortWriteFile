package com.mytest.entity;

public class Student {
	
	private String name;
    private String average;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAverage() {
        return average;
    }
    public void setAverage(String average) {
        this.average = average;
    }
	@Override
	public String toString() {
		return name + "," + average;
	}
    
}
