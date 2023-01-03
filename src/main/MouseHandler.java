package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {
	
	public boolean mousePressed, mouseReleased;
	
	public int[] mousePressedCoor = new int[2];
	public int[] mouseReleasedCoor = new int[2];
	public int[] mouseClickedCoor = new int[2];
	
	public MouseHandler() {
		mousePressed = false;
		mouseReleased = false;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		System.out.println("(" + Integer.toString(x) + "," + Integer.toString(y) + ")");
		int[] clickedCoor = {x, y};
		mouseClickedCoor = clickedCoor;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		System.out.println("Pressed at: " + "(" + Integer.toString(x) + "," + Integer.toString(y) + ")");
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
