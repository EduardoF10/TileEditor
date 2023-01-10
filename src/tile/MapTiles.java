package tile;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.WindowPanel;

public class MapTiles {
	
	WindowPanel wp;
	ArrayList<ArrayList<Tile>> map;
	
	TileImageManager tim;
	
	private final int minZoom = 3;
	
	private int upperBound, lowerBound, leftBound, rightBound;
	
	private int x, y;
	private int pixWidth, pixHeight;
	private int zoom;
	private int mapStartX, mapStartY;
	
	public MapTiles(WindowPanel wp, int pixWidth, int pixHeight) {
		tim = new TileImageManager();
		this.wp = wp;
		this.zoom = 8;
		this.pixWidth = pixWidth;
		this.pixHeight = pixHeight;
		
		mapStartX = 0;
		mapStartY = 0;
		
		map = new ArrayList<ArrayList<Tile>>();
		setMapXY();
		setBlankMap();
		
	}
	
//	public void setBounds() {
//		
//		// Pixel size of each square
//		int tileSize = minZoom * zoom;
//		
//		// Getting panel dimensions
//		int panelWidth = wp.screenWidth;
//		int panelHeight = wp.screenHeight;
//		
//		// Getting original map dimensions
//		int orgMapHeight = tileSize * pixHeight;
//		int orgMapWidth = tileSize * pixWidth;
//		
//		
//		// Getting max 4:3 map boundaries
//		int maxMapHeight = Math.min((int) (panelHeight * 0.7), orgMapHeight) ;
//		int maxMapWidth =  Math.min((int) (maxMapHeight * (4.0 / 3.0)), (int) (panelWidth * 0.8));
//		
//	}
	
	public void setMapXY() {
		
		int tileSize = minZoom * zoom;
		
		// Getting panel dimensions
		int panelWidth = wp.screenWidth;
		int panelHeight = wp.screenHeight;
		
		
		// Getting max 4:3 map boundaries
		int maxMapHeight = (int) (panelHeight * 0.7);
		int maxMapWidth =  Math.min((int) (maxMapHeight * (4.0 / 3.0)), (int) (panelWidth * 0.8));
		
		// Getting original map dimensions
		int orgMapHeight = tileSize * pixHeight;
		int orgMapWidth = tileSize * pixWidth;
		
		int middlePanelWidth = panelWidth / 2;    
		int middleMaxHeight = (maxMapHeight / 2) + (panelHeight / 10);  
		int middleMaxWidth = maxMapWidth / 2;  
		int middleOrgHeight = orgMapHeight / 2;   
		int middleOrgWidth = orgMapWidth / 2;
		
		// If original map height exceeds max map height
		if (orgMapHeight > maxMapHeight) {
			this.y = (panelHeight / 10);
			this.upperBound = this.y;
			this.lowerBound = upperBound + ((maxMapHeight / tileSize) * tileSize);
		}
		else {
			this.y = middleMaxHeight - middleOrgHeight;
			this.upperBound = this.y;
			this.lowerBound = upperBound + orgMapHeight;
		}
		
		// If original map width exceeds max map width
		if (orgMapWidth > maxMapWidth) {
			this.x = middlePanelWidth - middleMaxWidth;
			this.leftBound = this.x;
			this.rightBound = leftBound + ((maxMapWidth / tileSize) * tileSize);
		}
		else {
			this.x = middlePanelWidth - middleOrgWidth;
			this.leftBound = this.x;
			this.rightBound = leftBound + orgMapWidth;
		}
		
		
	}
	
	public void setBlankMap() {

		// Initializing map
		ArrayList<Tile> tiles;
		for (int i = 0; i < pixHeight; i++) {
			tiles = new ArrayList<Tile>();
			map.add(tiles);
		}
		
		BufferedImage empty = tim.getImage("empty", "00.png");
		
		Tile emptyTile;
		
		// Setting all tiles
		for (int i = 0; i < pixHeight; i++) {
			for (int j = 0; j < pixWidth; j++) {
				emptyTile = new Tile(empty);
				map.get(i).add(emptyTile);
			}
		}
		
	}
	
	public void draw(Graphics2D g2) {
		fillMapBoundaries(g2);
		drawTiles(g2);
		drawGrid(g2);
//		drawBackground(g2);
	}
	
	public void update() {
		
	}
	
	public void fillMapBoundaries(Graphics2D g2) {
		
		// Setting boundary color
		g2.setColor(Color.black);
		
		// Upper boundary
		g2.fillRect(0, 0, wp.screenWidth, upperBound);
		
		// Lower boundary
		g2.fillRect(0, lowerBound, wp.screenWidth, wp.screenHeight - lowerBound);
		
		// Left boundary
		g2.fillRect(0, 0, leftBound, wp.screenHeight);
		
		// Right boundary
		g2.fillRect(rightBound, 0, wp.screenWidth - rightBound, wp.screenHeight);
	}
	
	public void drawGrid(Graphics2D g2) {
		
		// Setting line color
		g2.setColor(Color.black);
		
		int tileSize = minZoom * zoom;
		
		// Drawing vertical lines
		for (int i = this.x; i < rightBound; i += tileSize) {
			g2.drawLine(i, upperBound, i, lowerBound);
		}
		// Drawing horizontal lines
		for (int i = this.y; i < lowerBound; i += tileSize) {
			g2.drawLine(leftBound, i, rightBound, i);
		}
	}
	
	private void drawTiles(Graphics2D g2) {
		
		int x = this.x;
		int y = this.y;
		int pixelSize = minZoom * zoom;
		
		// Getting max number of squares we can display inside the map's boundaries
		int heightLimit = (lowerBound - upperBound) / pixelSize;
		int widthLimit = (rightBound - leftBound) / pixelSize;
		
		
		for (int i = mapStartY; i < mapStartY + heightLimit; i++) {
			for (int j = mapStartX; j < mapStartX + widthLimit; j++) {
				map.get(i).get(j).draw(g2, x, y, pixelSize, pixelSize);
				x += pixelSize;
			}
			x = this.x;
			y += pixelSize;
		}
	}
	
	private void drawBackground(Graphics2D g2) {
		
		// Setting white color for rectangle
		g2.setColor(Color.white);
		
		int pixelSize = minZoom * zoom;
		int height = pixHeight * pixelSize;
		int width = pixWidth * pixelSize;
		
		int panelHeight = wp.screenHeight;
		int panelWidth = wp.screenWidth;
		// Getting max 4:3 map boundaries
		int maxMapHeight = (int) (panelHeight * 0.7);
		int maxMapWidth = (int) (maxMapHeight * (4.0 / 3.0));
		
		if (maxMapWidth > (int) (panelWidth * 0.8)) {
			maxMapWidth = (int) (panelWidth * 0.8);
			maxMapHeight = (int) (maxMapWidth * 0.75);
		}
		
		
		
		if (height > maxMapHeight) {
			height = maxMapHeight;
		}
		if (width > maxMapWidth) {
			width = maxMapWidth;
		}
		
		g2.fillRect(x, y, width, height);
		
		
	}
	
	
	

}
