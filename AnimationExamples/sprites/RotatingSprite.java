import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class RotatingSprite implements DisplayableSprite {
	
	private double currentAngle = 90;
	private double ROTATION_SPEED = 72;	//degrees per second
	private final static int FRAMES = 360;
	private static Image[] rotatedImages = new Image[FRAMES];
	private static boolean framesLoaded = false;

	private double centerX = 0;
	private double centerY = 0;
	private double width = 50;
	private double height = 50;
	private boolean dispose = false;	
	private double velocityX = 0;
	private double velocityY = 0;
	
	public RotatingSprite(double centerX, double centerY) {

		super();
		this.centerX = centerX;
		this.centerY = centerY;				

		Image defaultImage = null;

		if (framesLoaded == false) {
			try {
				defaultImage = ImageIO.read(new File("res/earth-polar-view.png"));
				
				width = defaultImage.getWidth(null);
				height = defaultImage.getHeight(null);
				
				for (int i = 0; i < FRAMES; i++) {
					rotatedImages[i] = ImageRotator.rotate(defaultImage, i);
				}

			}
			catch (IOException e) {
			}
			framesLoaded = true;
		}		
	}

	public Image getImage() {
		return rotatedImages[(int)currentAngle];
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
		
		currentAngle -= (ROTATION_SPEED * (actual_delta_time * 0.001));
	    if (currentAngle >= 360) {
	    	currentAngle -= 360;
	    }
	    if (currentAngle < 0) {
	    	currentAngle += 360;
	    }
	    
	    int frame = (int)currentAngle;
	    if (rotatedImages[frame] != null) {
		    this.height = rotatedImages[frame].getHeight(null);
		    this.width = rotatedImages[frame].getWidth(null);
	    }
	
	}
}
