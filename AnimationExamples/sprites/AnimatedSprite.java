import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class AnimatedSprite implements DisplayableSprite {
	
	private int currentFrame = 0;
	private long elapsedTime = 0;
	private final static int FRAMES = 150;
	private double framesPerSecond = 30;
	private double milliSecondsPerFrame = 1000 / framesPerSecond;
	private static Image[] frames = new Image[FRAMES];
	private static boolean framesLoaded = false;	
	
	private static Image image;	
	private double centerX = 0;
	private double centerY = 0;
	private double width = 50;
	private double height = 50;
	private boolean dispose = false;	
	private double velocityX = 0;
	private double velocityY = 0;
	
	public AnimatedSprite(double centerX, double centerY, double framesPerSecond) {

		this.centerX = centerX;
		this.centerY = centerY;		

		this.framesPerSecond = framesPerSecond;
		this.milliSecondsPerFrame = 1000 / framesPerSecond;
		long startTime = System.currentTimeMillis();
		
		if (framesLoaded == false) {
			for (int frame = 0; frame < FRAMES; frame++) {
				String filename = "res/animated-earth/frame_" + String.format("%03d", frame) + "_delay-0.04s.gif";
				try {
					frames[frame] = ImageIO.read(new File(filename));
				}
				catch (IOException e) {
					System.err.println(e.toString());
				}		
			}
			
			if (frames[0] != null) {
				width = frames[0].getWidth(null);
				height = frames[0].getHeight(null);
				framesLoaded = true;
			}
		}		
	}

	public Image getImage() {
		return frames[currentFrame];
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
		long elapsedFrames = (long) (elapsedTime / milliSecondsPerFrame);
		currentFrame = (int) (elapsedFrames % FRAMES);
	
	}
}
