import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BlinkySprite implements DisplayableSprite {

	private static final double VELOCITY = 200;
	private static final int WIDTH = 50;
	private static final int HEIGHT = 50;

	private static Image[] images;
	boolean isGhost = false;
	private long elapsedTime = 0;
		
	private double centerX = 0;
	private double centerY = 0;
	private double width = 50;
	private double height = 50;
	private boolean dispose = false;
	private int direction = 0;		//0 = down; 1 = left; 2 = up; 3 = right
	
	public BlinkySprite(double centerX, double centerY) {

		this.centerX =centerX;
		this.centerY = centerY;	
		this.width = WIDTH;
		this.height = HEIGHT;
		
		if (images == null) {
			try {
				images = new Image[8];
				for (int i = 0; i < 8; i++) {
					String path = String.format("res/blinky/blinky-%d.gif", i);
					images[i] = ImageIO.read(new File(path));
				}
			}
			catch (IOException e) {
				System.err.println(e.toString());
			}		
		}
	}

	public Image getImage() {
		
		long frame = elapsedTime / 200;
		int phase = (int) (frame % 2);
		int index = direction * 2 + phase;
		
		return images[index];
				
	}
	
	//DISPLAYABLE
	
	public boolean getVisible() {
		return true;
	}
	
	public double getMinX() {
		return centerX - (width / 2);
	}

	public double getMaxX() {
		return centerX + (width / 2);
	}

	public double getMinY() {
		return centerY - (height / 2);
	}

	public double getMaxY() {
		return centerY + (height / 2);
	}

	public double getHeight() {
		return height;
	}

	public double getWidth() {
		return width;
	}

	public double getCenterX() {
		return centerX;
	};

	public double getCenterY() {
		return centerY;
	};
	
	
	public boolean getDispose() {
		return dispose;
	}

	public void setDispose(boolean dispose) {
		this.dispose = dispose;
	}


	public void update(Universe universe, KeyboardInput keyboard, long actual_delta_time) {
		
		elapsedTime += actual_delta_time;
		
		//how many 2-second periods have elapsed?
		long periods = (elapsedTime / 2000);
		this.direction = (int) (periods % 4);
		
		double newX = getCenterX();
		double newY = getCenterY();

		if (direction == 0) {
			newY += actual_delta_time * 0.001 * VELOCITY;
		} else if (direction == 1) {		
			newX -= actual_delta_time * 0.001 * VELOCITY;
		} else if (direction == 2) {
			newY -= actual_delta_time * 0.001 * VELOCITY;
		} else {
			newX += actual_delta_time * 0.001 * VELOCITY;
		}

		this.centerX =newX;
		this.centerY = newY;			
	
	}
}
