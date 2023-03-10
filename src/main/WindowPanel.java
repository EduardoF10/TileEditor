package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JPanel;

import tile.MapTiles;
import tile.TileImageManager;

public class WindowPanel extends JPanel implements Runnable {
	
	// DEVICE SCREEN DIMENSIONS
	final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	final public int maxScreenWidth = (int) screenSize.getWidth();
	final public int maxScreenHeight = (int) screenSize.getHeight();
	final float maxScreenRatio = 0.9f;
	
	// SCREEN SETTINGS
	public int screenWidth = (int) (maxScreenRatio * maxScreenWidth);
	public int screenHeight = (int) (maxScreenRatio * maxScreenHeight);
	
	
	// FPS
	int FPS = 240;
	
	// Key handler
	KeyHandler keyH = new KeyHandler();
	
	// Mouse handler
	MouseHandler mouseH = new MouseHandler();
	
	// Mouse motion handler
	MouseMotionHandler motionH = new MouseMotionHandler();
	
	// program clock
	Thread programThread;
	
	// Tile image manager
	TileImageManager tim = new TileImageManager();
	
	// Map of tiles
	MapTiles mapT;
		
	public WindowPanel() {
		mapT = new MapTiles(this, 32, 32);
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.white);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.addMouseListener(mouseH);
		this.addMouseMotionListener(motionH);
		this.setFocusable(true);
	}
		
		public void startThread() {
			programThread = new Thread(this);
			programThread.start();
		}

		
		// PROGRAM LOOP
		@Override
		public void run() {
			
			double drawInterval = 1000000000 / FPS;		// 0.01666 seconds
			double delta = 0;
			long lastTime = System.nanoTime();
			long currentTime;
//			long timer = 0;
//			int drawCount = 0;
			
			while (programThread != null) {
				
				currentTime = System.nanoTime();
				
				delta += (currentTime - lastTime) / drawInterval;
//				timer += (currentTime - lastTime);
				lastTime = currentTime;
				
				// If enough time has passed (drawInterval) to be able to update and draw
				if (delta >= 1) {
					
					// 1 UPDATE: update information
					update();
					
					// 2 DRAW: draw the screen with the updated information
					repaint();
					
					// Reseting time needed
					delta --;
					
					// Drawing counter
//					drawCount++;
					
				}
				
				
				// If a second has passed
//				if (timer >= 1000000000) {
//					System.out.println("FPS: " + drawCount);
//					drawCount = 0;
//					timer = 0;
//				}
				
			}
			
		}
		
		public void update() {
			
			updateScreenSize();
			
		}
		
		public void paintComponent(Graphics g) {
			
			super.paintComponent(g);
			
			Graphics2D g2 = (Graphics2D) g;
			
			mapT.draw(g2);		
			
			g2.dispose();
		}
		
		public void updateScreenSize() {
			int currentWidth = this.getWidth();
			int currentHeight = this.getHeight();
			if (currentWidth != screenWidth || currentHeight != screenHeight) {
				screenWidth = this.getWidth();
				screenHeight = this.getHeight();
				mapT.setMapXY();
			}
		}

}
