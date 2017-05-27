package com.mytest.utils;

public enum SortNames {
	BUBBLE("Bubble"), HEAP("Heap"), MERGE("Merge");

	private String name;

	SortNames(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
