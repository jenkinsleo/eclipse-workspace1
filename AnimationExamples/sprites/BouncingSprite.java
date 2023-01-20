import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BouncingSprite implements DisplayableSprite {

	private static Image image;	
	private double centerX = 0;
	private double centerY = 0;
	private double width = 50;
	private double height = 50;
	private boolean dispose = false;	

	private double ACCCELERATION_Y = 500;		//PIXELS PER SECOND PER MILLISECOND
	private static final double VELOCITY = 200; //50 pixels per second
	private static final int WIDTH = 50;
	private static final int HEIGHT = 50;

	private double velocityX = 300; //per second
	private double velocityY = 300; //per second
	private CollisionDetection collisionDetection;
	TwoDimensionBounce bounce;

	public BouncingSprite(double centerX, double centerY, double velocityX, double velocityY) {

		super();
		this.centerX = centerX;
		this.centerY = centerY;	

		collisionDetection = new CollisionDetection();
		bounce = new TwoDimensionBounce();
		collisionDetection.setBounceFactorX(1);
		collisionDetection.setBounceFactorY(1);

		this.velocityX = velocityX;
		this.velocityY = velocityY;

		if (image == null) {
			try {
				image = ImageIO.read(new File("res/star.png"));
			}
			catch (IOException e) {
				System.err.println(e.toString());
			}
		}

		this.height = HEIGHT;
		this.width = WIDTH;

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
		
		collisionDetection.calculate2DBounce(bounce, this, universe.getSprites(), velocityX, velocityY, actual_delta_time);
		this.centerX = bounce.newX + (width / 2);
		this.centerY = bounce.newY + (width / 2);
		this.velocityX = bounce.newVelocityX;
		this.velocityY = bounce.newVelocityY;			

		this.velocityY = this.velocityY + ACCCELERATION_Y * 0.001 * actual_delta_time;
	
	}

}

