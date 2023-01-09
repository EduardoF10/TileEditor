package tile;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.WindowPanel;

public class MapTiles {
	
	WindowPanel wp;
	Graphics2D g2;
	ArrayList<ArrayList<Tile>> map;
	
	TileImageManager tim;
	
	private final int minZoom = 3;
	
	private int x, y;
	private int pixWidth, pixHeight;
	private int zoom;
	
	public MapTiles(WindowPanel wp, int pixWidth, int pixHeight) {
		tim = new TileImageManager();
		this.wp = wp;
		this.zoom = 8;
		this.pixWidth = pixWidth;
		this.pixHeight = pixHeight;
		
		map = new ArrayList<ArrayList<Tile>>();
		setMapXY();
		setBlankMap();
		
	}
	
	public void setMapXY() {
		
		int tileSize = minZoom * zoom;
		
		// Getting panel dimensions
		int panelWidth = wp.screenWidth;
		int panelHeight = wp.screenHeight;
		
		
		// Getting max 4:3 map boundaries
		int maxMapHeight = (int) (panelHeight * 0.7);
		int maxMapWidth =  Math.min((int) (maxMapHeight * (4.0 / 3.0)), (int) (panelWidth * 0.8));
		
		// Getting map dimensions
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
		}
		else {
			this.y = middleMaxHeight - middleOrgHeight;
		}
		
		// If original map width exceeds max map width
		if (orgMapWidth > maxMapWidth) {
			this.x = middlePanelWidth - middleMaxWidth;
		}
		else {
			this.x = middlePanelWidth - middleOrgWidth;
		}
		
		
	}
	
	public void setBlankMap() {
		
		// Initializing map
		ArrayList<Tile> tiles;
		for (int i = 0; i < pixHeight; i++) {
			tiles = new ArrayList<Tile>();
			map.add(tiles);
		}
		
		BufferedImage empty0 = tim.getImage("empty", "10.png");
		BufferedImage empty1 = tim.getImage("empty", "11.png");
		BufferedImage empty2 = tim.getImage("empty", "12.png");
		BufferedImage empty3 = tim.getImage("empty", "13.png");
		
		Tile emptyTile;
		
		// Setting all tiles except the bottom and right borders
		for (int i = 0; i < pixHeight - 1; i++) {
			for (int j = 1; j < pixWidth - 1; j++) {
				emptyTile = new Tile(empty0);
				map.get(i).add(emptyTile);
			}
		}
		
		// Setting bottom border
		for (int i = 0; i < pixWidth - 1; i++) {
			emptyTile = new Tile(empty1);
			map.get(pixHeight - 1).add(emptyTile);
		}
		
		// Setting right border
		for (int i = 0; i < pixHeight - 1; i++) {
			emptyTile = new Tile(empty2);
			map.get(i).add(emptyTile);
		}
		
		// Setting lower right corner
		emptyTile = new Tile(empty3);
		map.get(pixHeight - 1).add(emptyTile);
		
	}
	
	public void draw(Graphics2D g2) {
		drawBackground(g2);
//		drawTiles(g2);
	}
	
	public void update() {
		
	}
	
//	private void drawTiles(Graphics2D g2) {
//		
//		int x = this.x;
//		int y = this.y;
//		int pixelSize = minZoom * zoom;
//		
//		
//		for (int i = 0; i < pixHeight; i++) {
//			for (int j = 0; j < pixWidth; j++) {
//				map.get(i).get(j).draw(g2, x, y, pixelSize, pixelSize);
//			}
//		}
//	}
	
	private void drawBackground(Graphics2D g2) {
		
		// Setting white color for rectangle
		g2.setColor(Color.WHITE);
		
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
