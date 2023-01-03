package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.JPanel;

//import tile.TileManager;

public class WindowPanel extends JPanel implements Runnable {
	
	// DEVICE SCREEN DIMENSIONS
	final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	final int maxScreenWidth = (int) screenSize.getWidth();
	final int maxScreenHeight = (int) screenSize.getHeight();
	
	// SCREEN SETTINGS
	
	public int screenWidth = (int) (0.8 * maxScreenWidth);
	public int screenHeight = (int) (0.8 * maxScreenHeight);
	
//	final int originalTileSize = 1; // 1 x 1 tile
//	public int scale = 1;
//	
//	public int tileSize = originalTileSize * scale;		// 1 x 1 tile
//	public int maxWindowCol = 64;
//	public int maxMapRow = 64;
//	public int mapScreenWidth = tileSize * maxMapCol;
//	public int mapScreenHeight = tileSize * maxMapRow;
//	public final int maxScreenCol = 24;
//	public final int maxScreenRow = 18;
//	
//	public final int screenWidth = tileSize * maxScreenCol;	// 768 pixels	
//	public final int screenHeight = tileSize * maxScreenRow;	// 576 pixels
//	
	// FPS
	int FPS = 60;
	
	// Key handler
	KeyHandler keyH = new KeyHandler();
	
	// Mouse handler
	MouseHandler mouseH = new MouseHandler();
	
	// Mouse motion handler
	MouseMotionHandler motionH = new MouseMotionHandler();
	
	// program clock
	Thread programThread;
		
	// Tile manager
//	TileManager tileM = new TileManager(this);
		
		
	public WindowPanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
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
//					repaint();
					
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
			
		}
		
		public void paintComponent(Graphics g) {
			
			super.paintComponent(g);
			
			Graphics2D g2 = (Graphics2D) g;
			
//			tileM.draw(g2);
//			tileM.loadMap("/maps/fenceMap1.txt");
//			tileM.draw(g2);
//			tileM.draw2(g2,  2);
//			tileM.draw2(g2, 1);
			
			
			g2.dispose();
		}

}
