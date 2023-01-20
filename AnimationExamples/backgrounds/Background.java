import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public interface Background {

	public Tile getTile(int col, int row);
	
	public int getCol(int x);
	
	public int getRow(int y);
	
}
