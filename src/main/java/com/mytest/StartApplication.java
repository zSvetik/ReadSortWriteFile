package com.mytest;

import javax.swing.SwingUtilities;

import com.mytest.view.MainView;

public class StartApplication {

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainView();
			}
		});
	}

}
