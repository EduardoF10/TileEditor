package tile;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;

public class TileImageManager {
	
	HashMap<String, HashMap<String, BufferedImage>> tileSets;
	
	public TileImageManager() {
		tileSets = new HashMap<String, HashMap<String, BufferedImage>>();
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
				
				// Adding image
				addImage(tileFile);
			}
		}
		
	}
	
	public BufferedImage getImage(String tilePackage, String imageName) {
		return tileSets.get(tilePackage).get(imageName);
	}
	
	
	public void addPackage(File tilePackage) {
		String[] packagePath = tilePackage.getAbsolutePath().split("\\\\");
		String packageName = packagePath[packagePath.length - 1];
		HashMap<String, BufferedImage> packageTiles = new HashMap<String, BufferedImage>();
		tileSets.put(packageName, packageTiles);
	}
	
	public void addImage(File imageFile) {
		
		String[] imagePath = imageFile.getAbsolutePath().split("\\\\");
		String imageName = imagePath[imagePath.length - 1];
		String[] parentPath = imageFile.getParent().split("\\\\");
		String parentName = parentPath[parentPath.length - 1];
		
		// Capturing tile image
		BufferedImage tileImage = null;
		try {
			tileImage = ImageIO.read(imageFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		tileSets.get(parentName).put(imageName, tileImage);
	}
	
	
	
	

}
