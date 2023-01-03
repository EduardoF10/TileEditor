package tile;

import java.awt.image.BufferedImage;

public class Tile {
	public BufferedImage image;
	public int x, y, width, height;
	
	public Tile() {
		x = 0;
		y = 0;
		width = 0;
		height = 0;
		image = null;
	}
	
	public Tile(BufferedImage image) {
		this.image = image;
	}

}
