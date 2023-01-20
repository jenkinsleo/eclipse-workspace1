import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class JumpingSprite implements DisplayableSprite {

	
	private double ACCCELERATION_X = 5;		//PIXELS PER SECOND PER SECOND
	private double ACCCELERATION_Y = 600; 	//PIXELS PER SECOND PER SECOND
	private double MAX_VELOCITY_X = 300;	//PIXELS PER SECOND
	private double FRICTION_FACTOR_X = 0.95; 

	private boolean isJumping = false;
	private final double INITIAL_JUMP_VELOCITY = 600; //pixels / second
	
	private CollisionDetection collisionDetection;
	TwoDimensionBounce bounce;

	private static Image image;
	private double centerX = 0;
	private double centerY = 0;
	private double width = 50;
	private double height = 50;
	private boolean dispose = false;	
	private double velocityX = 000;        	//PIXELS PER SECOND
	private double velocityY = 0;          	//PIXELS PER SECOND
	
	public JumpingSprite(double centerX, double centerY) {

		super();
		this.centerX = centerX;
		this.centerY = centerY;		

		collisionDetection = new CollisionDetection();
		collisionDetection.setBounceFactorX(0.5);
		collisionDetection.setBounceFactorY(0);
		bounce = new TwoDimensionBounce();
		
		if (image == null) {
			try {
				image = ImageIO.read(new File("res/simple-sprite.png"));
			}
			catch (IOException e) {
				System.err.println(e.toString());
			}		
		}
		this.height = image.getHeight(null);
		this.width = image.getWidth(null);
		
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

		boolean onGround = isOnGround(universe);
		
		if (onGround) {

			if (keyboard.keyDown(32)) {
				isJumping = true;
				this.velocityY -= INITIAL_JUMP_VELOCITY;
				onGround = false;
			}
			// RIGHT
			if (keyboard.keyDown(39)) {
				velocityX += + ACCCELERATION_X;
				if (velocityX > MAX_VELOCITY_X) {
					velocityX = MAX_VELOCITY_X;
				}
			}
			// LEFT
			else if (keyboard.keyDown(37)) {
				velocityX -= ACCCELERATION_X;
				if (velocityX < - MAX_VELOCITY_X) {
					velocityX = - MAX_VELOCITY_X;
				}
			}
			else {
				this.velocityX = this.velocityX * FRICTION_FACTOR_X;
			}
		}
		else {
			
		}
		
		collisionDetection.calculate2DBounce(bounce, this, universe.getSprites(), velocityX, velocityY, actual_delta_time);
		this.centerX = bounce.newX + (width / 2);
		this.centerY = bounce.newY + (width / 2);
		this.velocityX = bounce.newVelocityX;
		this.velocityY = bounce.newVelocityY;

		if (onGround == true) {
			this.velocityY = 0;
		} else {
			this.velocityY = this.velocityY + ACCCELERATION_Y * 0.001 * actual_delta_time;
		}

	}
	
	private boolean isOnGround(Universe universe) {
		boolean onGround = false;
		for (DisplayableSprite sprite: universe.getSprites()) {
			boolean bottomColiding = this.getMaxY() >= (sprite.getMinY()) && this.getMaxY() <= sprite.getMinY();
			boolean withinRange = this.getMaxX() > sprite.getMinX() && this.getMinX() < sprite.getMaxX();
			if (bottomColiding && withinRange) {
				onGround = true;
				break;
			}
		}
		return onGround;
	}
		
}
