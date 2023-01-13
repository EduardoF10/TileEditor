package tile;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;

public class TileImageManager {
	
	
	class TileImage {
		
		private BufferedImage image;
		private int width;
		private int height;
		
		private ArrayList<ArrayList<BufferedImage>> pixelImages;
		
		public TileImage(BufferedImage image) {
			this.image = image;
			this.width = image.getWidth();
			this.height = image.getHeight();
			
			// Getting 2D array of images 1 x 1 pixels from the original image
			this.pixelImages = new ArrayList<ArrayList<BufferedImage>>();
			for (int i = 0; i < height; i++) {
				ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
				for (int j = 0; j < width; j++) {
					BufferedImage croppedImage = image.getSubimage(j, i, 1, 1);
					images.add(croppedImage);
				}
				this.pixelImages.add(images);
			}
		}
		
		public BufferedImage image() { return image; }
		
		public int width() { return width; }
		
		public int height() { return height; }
		
		public ArrayList<ArrayList<BufferedImage>> pixelImages() { return pixelImages; }
		
	}
	
	HashMap<String, HashMap<String, TileImage>> tileSets;
	
	public TileImageManager() {
		tileSets = new HashMap<String, HashMap<String, TileImage>>();
		setTileSprites();
	}

	// Storing all tile images inside the tile package
	private void setTileSprites() {
		
		// File that contains all tile packages and files
		File tilesDir = new File("C:\\Users\\eduar\\Github\\TileEditor\\res\\tiles");
		
		// Tile packages
		File[] tilePackages = tilesDir.listFiles();
		
		// Iterating each sub package in the tiles package
		for (File tilePackage : tilePackages) {
			
			// Adding package
			addPackage(tilePackage);
						
			File[] tileFiles = tilePackage.listFiles();
			// Iterating each file in the sub package
			for (File tileFile : tileFiles) {
				
				// Adding tile image
				addTileImage(tileFile);
			}
		}
		
	}
	
	public TileImage getTileImage(String tilePackage, String imageName) {
		return tileSets.get(tilePackage).get(imageName);
	}
	
	
	public void addPackage(File tilePackage) {
		String[] packagePath = tilePackage.getAbsolutePath().split("\\\\");
		String packageName = packagePath[packagePath.length - 1];
		HashMap<String, TileImage> packageTiles = new HashMap<String, TileImage>();
		tileSets.put(packageName, packageTiles);
	}
	
	public void addTileImage(File imageFile) {
		
		String[] imagePath = imageFile.getAbsolutePath().split("\\\\");
		String imageName = imagePath[imagePath.length - 1];
		String[] parentPath = imageFile.getParent().split("\\\\");
		String parentName = parentPath[parentPath.length - 1];
		
		// Capturing tile image
		BufferedImage image = null;
		try {
			image = ImageIO.read(imageFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		TileImage tileImage = new TileImage(image);
		tileSets.get(parentName).put(imageName, tileImage);
	}
	
	
	
	

}
