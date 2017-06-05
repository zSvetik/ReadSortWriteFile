package com.mytest.repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.mytest.entity.Student;

public class StudentDAOImpl implements StudentDAO {

	public StudentDAOImpl() {
	}

	@Override
	public List<Student> getStudentList(File file) {
		String readLine = null;
		List<Student> studentList = new ArrayList<>();
		try {

			FileReader reader = new FileReader(file);
			BufferedReader bufReader = new BufferedReader(reader);

			while ((readLine = bufReader.readLine()) != null) {
				String[] splitData = readLine.split(",");

				Student student = new Student();
				student.setName(splitData[0]);
				student.setAverage(splitData[1]);

				studentList.add(student);
			}
		} catch (IOException ex) {
		}

		return studentList;
	}

	@Override
	public void saveStudentList(File file, List<Student> studentList) {
		if (studentList == null) {
			studentList = new ArrayList<>();
		}
		try {
			FileWriter writer = new FileWriter(file);
			for (Student student : studentList) {
				writer.write(student.toString());
				writer.write(System.getProperty("line.separator"));
			}
			writer.close();

		} catch (IOException ex) {
		}
	}
}
