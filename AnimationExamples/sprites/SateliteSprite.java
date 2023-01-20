import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class SateliteSprite implements DisplayableSprite {

	
	public static double GRAVITATIONAL_CONSTANT = 1E-1;
	private Image image;	
	private double centerX = 0;
	private double centerY = 0;
	private double width = 50;
	private double height = 50;
	private boolean dispose = false;	
	private double velocityX = 0;
	private double velocityY = 0;
	private double mass = 0;
	private double diameter = 0;					//100 KM PER PIXEL
	private boolean anchored = false;
	
	public SateliteSprite(double centerX, double centerY, double velocityX, double velocityY, double mass, double diameter, boolean anchored, String imageFile) {

		super();
		this.centerX = centerX;
		this.centerY = centerY;

		this.velocityX = velocityX;
		this.velocityY = velocityY;
		this.mass = mass;
		this.diameter = diameter;
		this.anchored = anchored;

		if (image == null) {
			try {
				image = ImageIO.read(new File(imageFile));
			}
			catch (IOException e) {
				System.err.println(e.toString());
			}		
		}
		this.height = ((int) diameter);
		this.width = ((int) diameter);
		
	}
	
	public boolean isAnchored() {
		return anchored;
	}


	public Image getImage() {
		return image;
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

		//calculate new position assuming there are no changes in direction
	    double movement_x = (this.velocityX * actual_delta_time * 0.001);
	    double movement_y = (this.velocityY * actual_delta_time * 0.001);
	    this.centerX += movement_x;
	    this.centerY += movement_y;
		
		for (DisplayableSprite other : universe.getSprites()) {
			if (other instanceof SateliteSprite && other != this) {
				SateliteSprite satelite = (SateliteSprite)other;
				if (satelite.isAnchored() == false) {
					//calculate the attraction vector
					//first, calculate distance.... leave it in squared form
					double deltaX = satelite.getCenterX() - this.getCenterX();
					double deltaY = satelite.getCenterY() - this.getCenterY();
					double distanceSquared = deltaX * deltaX + deltaY * deltaY;
					double force = GRAVITATIONAL_CONSTANT * ((this.mass * satelite.mass) / distanceSquared);
					//force == mass * acceleration; thus, acceleration = force / mass
					double acceleration = force / satelite.mass;
					double vector = acceleration * actual_delta_time * 0.001;
					double tangent = deltaY / deltaX;
					//too lazy to do the math here... vector should equal sqrt(attractionX ^ 2 + attractionY ^ 2);
					double attractionX = deltaX * vector * -1;
					double attractionY = deltaY * vector * -1;
					satelite.velocityX += attractionX;
					satelite.velocityY += attractionY;
					//System.out.println(String.format("satelite.velocityX %.10f;  satelite.velocityY %.10f; delta %d", satelite.velocityX, satelite.velocityY, actual_delta_time));
				}
			}
		}				
	}				
}
