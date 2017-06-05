package com.mytest.repository;

import java.io.File;
import java.util.List;

import com.mytest.entity.Student;

public interface StudentDAO {

	public List<Student> getStudentList(File file);

	public void saveStudentList(File file, List<Student> studentList);
}
