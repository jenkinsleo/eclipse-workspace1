import java.awt.Rectangle;
import java.util.ArrayList;

public class JumpingSpriteUniverse implements Universe {

	private boolean complete = false;	
	private Background background = null;	
	private DisplayableSprite player1 = null;
	private ArrayList<DisplayableSprite> sprites = new ArrayList<DisplayableSprite>();
	
	public JumpingSpriteUniverse () {
	
		player1 = new JumpingSprite(0,0);
		sprites.add(player1);
		
		for (int i = 0; i < 5; i++) {
			sprites.add(new BouncingSprite(i * 100 - 200 , 0 , 200, 0));
		}
		
		//top platform
		sprites.add(new BarrierSprite(-50,34, 50, 50, true));
		//bottom platform
		sprites.add(new BarrierSprite(-150,234, 150, 250, true));
		//bottom
		sprites.add(new BarrierSprite(AnimationFrame.SCREEN_WIDTH / -2,AnimationFrame.SCREEN_HEIGHT / 2 - 16, AnimationFrame.SCREEN_WIDTH / 2, AnimationFrame.SCREEN_HEIGHT / 2, true));
		//left
		sprites.add(new BarrierSprite(AnimationFrame.SCREEN_WIDTH / -2,AnimationFrame.SCREEN_HEIGHT / -2, AnimationFrame.SCREEN_WIDTH / -2 + 16, AnimationFrame.SCREEN_HEIGHT / 2, true));
		//right
		sprites.add(new BarrierSprite(AnimationFrame.SCREEN_WIDTH / 2 - 16,AnimationFrame.SCREEN_HEIGHT / -2, AnimationFrame.SCREEN_WIDTH / 2, AnimationFrame.SCREEN_HEIGHT / 2, true));
	
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
		return "JumpingSpriteUniverse";
	}	
	
}
