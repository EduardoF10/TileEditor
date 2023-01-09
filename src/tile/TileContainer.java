package tile;

import java.util.ArrayList;

public class TileContainer {
	
	private final int containerSize = 8;	// 8 x 8
	
	public ArrayList<ArrayList<Tile>> tileContainer;
	
	public TileContainer() {
		
		
		for (int i = 0; i < containerSize; i++) {
			for (int j = 0; j < containerSize; j++) {
				Tile emptyTile = new Tile();
			}
		}
		
	}

}
