package main;

import javax.swing.JFrame;

public class Main {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);
		window.setTitle("Tile Editor");
		
		WindowPanel windowPanel = new WindowPanel();
		window.add(windowPanel);
		
		// Causes the window to be sized to fit the preferred size and 
		// layouts of its subcomponents
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		windowPanel.startThread();
	}	

}
