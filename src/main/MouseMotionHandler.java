package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseMotionHandler implements MouseMotionListener {
	
	public int[] mouseCoor;
	public int[] mouseDragCoor;

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX();
		int y = e.getY();
		System.out.println("(" + Integer.toString(x) + "," + Integer.toString(y) + ")");
		int[] dragCoor = {x, y};
		mouseDragCoor = dragCoor;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
//		int x = e.getX();
//		int y = e.getY();
//		System.out.println("Mouse at: " + "(" + Integer.toString(x) + "," + Integer.toString(y) + ")");
//		int[] coor = {x, y};
//		mouseCoor = coor;
//		
	}

}
