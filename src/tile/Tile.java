package tile;

// Each tile will represent 1 pixel

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Tile {
	
	public BufferedImage image;
	
	public Tile() {
		image = null;
	}
	
	public Tile(BufferedImage image) {
		this.image = image;
	}
	
	public void draw(Graphics2D g2, int x, int y, int width, int height) {
		g2.drawImage(this.image, x, y, width, height, null);
	}

}
