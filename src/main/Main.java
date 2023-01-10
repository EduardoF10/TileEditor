package main;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Main {
	
	public static void main(String[] args) {
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension minDimension = new Dimension((int) (screenSize.width * 0.5), (int) (screenSize.height * 0.5));
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);
		window.setMinimumSize(minDimension);	// The minimum size of the window is 50% of the original screen's size
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
