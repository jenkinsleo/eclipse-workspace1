import java.util.ArrayList;

public class AnimatedSpritesUniverse implements Universe {

	private boolean complete = false;	
	private Background background = null;	
	private DisplayableSprite player1 = null;
	private ArrayList<DisplayableSprite> sprites = new ArrayList<DisplayableSprite>();
	
	public AnimatedSpritesUniverse () {
	
		sprites.add(new AnimatedSprite(-300,0, 30));
		sprites.add(new RotatingSprite(150,-30));
	
	}
	
	public double getScale() {
		return 1;
	}

	public double getXCenter() {
		return 0;
	}

	public double getYCenter() {
		return 0;
	}
	
	public void setXCenter(double xCenter) {
		this.setXCenter(xCenter);
	}

	public void setYCenter(double yCenter) {
		this.setYCenter(yCenter);
	}
	
	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		complete = true;
	}

	public Background getBackground() {
		return background;
	}

	public DisplayableSprite getPlayer1() {
		return player1;
	}

	public ArrayList<DisplayableSprite> getSprites() {
		return sprites;
	}
		
	public boolean centerOnPlayer() {
		return false;
	}		
	
	public void update(KeyboardInput keyboard, long actual_delta_time) {
		
		if (keyboard.keyDownOnce(27)) {
			complete = true;
		}
		
		for (int i = 0; i < sprites.size(); i++) {
			DisplayableSprite sprite = sprites.get(i);
			sprite.update(this, keyboard, actual_delta_time);
    	} 
	}
	
	public String toString() {
		return "AnimatedSpritesUniverse";
	}	

}