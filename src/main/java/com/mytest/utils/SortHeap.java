package com.mytest.utils;

import java.util.List;

import com.mytest.entity.Student;

public class SortHeap implements ISort {

	private List<Student> studentList;
	int sizeList;
	int left;
	int right;
	int largest;

	public SortHeap(List<Student> studentList) {
		this.studentList = studentList;
	}

	@Override
	public List<Student> sort() {
		buildheap(studentList);
		for (int i = sizeList; i > 0; i--) {
			exchange(0, i);
			sizeList = sizeList - 1;
			maxheap(studentList, 0);
		}
		return studentList;
	}

	public void buildheap(List<Student> students) {
		sizeList = students.size() - 1;
		for (int i = sizeList / 2; i >= 0; i--) {
			maxheap(students, i);
		}
	}

	public void maxheap(List<Student> students, int i) {
		left = 2 * i;
		right = 2 * i + 1;

		if (left <= sizeList && studentList.get(left).getName().compareToIgnoreCase(students.get(i).getName()) > 0) {
			largest = left;
		} else {
			largest = i;
		}

		if (right <= sizeList
				&& students.get(right).getName().compareToIgnoreCase(students.get(largest).getName()) > 0) {
			largest = right;
		}
		if (largest != i) {
			exchange(i, largest);
			maxheap(students, largest);
		}
	}

	public void exchange(int i, int j) {
		Student temp = studentList.get(i);
		studentList.set(i, studentList.get(j));
		studentList.set(j, temp);
	}
}
