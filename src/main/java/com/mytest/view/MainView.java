package com.mytest.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import com.mytest.entity.*;
import com.mytest.repository.StudentDAO;
import com.mytest.utils.*;

public class MainView implements ActionListener {

	private File selectedFile;
	private JButton btnOpenFile, btnSaveToFile;
	private StudentTableModel tableModel;
	private List<Student> studentList;
	private JFileChooser fileChooser;
	private JRadioButton rbSortBubble, rbSortHeap, rbSortMerge;
	private String nameFrame;
	private JFrame frame;
	private JTable table;
	private StudentDAO studentDAO;

	public MainView() {
		nameFrame = "TestApplication";
		studentDAO = new StudentDAO();
		createAndShowGUI();
	}

	private void createAndShowGUI() {
		// Create and set up the window.
		frame = new JFrame(nameFrame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		createFileChooser();

		JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

		btnOpenFile = new JButton("Open a File...");
		btnOpenFile.addActionListener(this);
		btnPanel.add(btnOpenFile);

		btnSaveToFile = new JButton("Save a File...");
		btnSaveToFile.addActionListener(this);
		btnPanel.add(btnSaveToFile);

		// ==========================================================
		rbSortBubble = new JRadioButton(SortNames.BUBBLE.getName());
		rbSortBubble.setSelected(true);
		rbSortBubble.addActionListener(this);
		rbSortHeap = new JRadioButton(SortNames.HEAP.getName());
		rbSortHeap.addActionListener(this);
		rbSortMerge = new JRadioButton(SortNames.MERGE.getName());
		rbSortMerge.addActionListener(this);

		ButtonGroup group = new ButtonGroup();
		group.add(rbSortBubble);
		group.add(rbSortHeap);
		group.add(rbSortMerge);

		btnPanel.add(rbSortBubble);
		btnPanel.add(rbSortHeap);
		btnPanel.add(rbSortMerge);
		// ==========================================================

		table = new JTable();

		updateTable(studentList);

		frame.add(btnPanel, BorderLayout.NORTH);
		frame.add(new JScrollPane(table));
		frame.setTitle("File to JTable");
		// Display the window.
		frame.pack();
		frame.setVisible(true);

	}

	private void createFileChooser() {
		fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		fileChooser.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("TXT file", "txt");
		fileChooser.addChoosableFileFilter(filter);
	}

	public void actionPerformed(ActionEvent e) {
		// Handle open button action.
		if (e.getSource() == btnOpenFile) {
			int returnVal = fileChooser.showOpenDialog(null);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				selectedFile = fileChooser.getSelectedFile();
				studentList = studentDAO.getStudentList(selectedFile);
				updateTable(studentList);
			}
			// Handle save button action.
		} else if (e.getSource() == btnSaveToFile) {
			int returnVal = fileChooser.showSaveDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				selectedFile = fileChooser.getSelectedFile();
				studentDAO.saveStudentList(selectedFile, studentList);
			}
		} else if (e.getSource() == rbSortBubble) {
			if (studentList != null) {
				setTitleAfterSort(new SortBubble(studentList));
				updateTable(studentList);
			}
		} else if (e.getSource() == rbSortHeap) {
			if (studentList != null) {
				setTitleAfterSort(new SortHeap(studentList));
				updateTable(studentList);
			}
		} else if (e.getSource() == rbSortMerge) {
			if (studentList != null) {
				setTitleAfterSort(new SortMerge(studentList));
				updateTable(studentList);
			}
		}
	}

	private void updateTable(List<Student> listStudent) {
		if (listStudent != null) {
			tableModel = new StudentTableModel();
			table.setModel(tableModel);
			tableModel.setList(listStudent);
		}
	}

	private void setTitleAfterSort(ISort sort) {
		final long startTime = System.currentTimeMillis();
		// sorted by Name
		sort.sort();
		frame.setTitle(nameFrame + " - Sorted - " + ((System.currentTimeMillis() - startTime) + " ms."));
	}
}
