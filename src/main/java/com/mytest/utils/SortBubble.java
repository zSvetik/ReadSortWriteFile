package com.mytest.utils;

import java.util.List;

import com.mytest.entity.Student;

public class SortBubble implements ISort {

	private List<Student> studentList;

	public SortBubble(List<Student> studentList) {
		this.studentList = studentList;
	}

	@Override
	public List<Student> sort() {
		int j;
		boolean flag = true; // will determine when the sort is finished
		Student temp;

		while (flag) {
			flag = false;
			for (j = 0; j < studentList.size() - 1; j++) {
				if (studentList.get(j).getName().compareToIgnoreCase(studentList.get(j + 1).getName()) > 0) { // ascending
					// sort
					temp = studentList.get(j);
					studentList.set(j, studentList.get(j + 1)); // swapping
					studentList.set(j + 1, temp);
					flag = true;
				}
			}
		}
		return studentList;
	}
}
