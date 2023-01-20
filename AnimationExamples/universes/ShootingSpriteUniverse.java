import java.awt.Rectangle;
import java.util.ArrayList;

public class ShootingSpriteUniverse implements Universe {

	private boolean complete = false;	
	private Background background = null;	
	private DisplayableSprite player1 = null;
	private ArrayList<DisplayableSprite> sprites = new ArrayList<DisplayableSprite>();
	ArrayList<DisplayableSprite> disposedSprites = new ArrayList<DisplayableSprite>();
	
	public ShootingSpriteUniverse () {
	
		player1 = new SpaceShipSprite(0,0);
		background = new StarfieldBackground();
		
		this.sprites.add(player1);
	
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
		return true;
	}		
	
	public void update(KeyboardInput keyboard, long actual_delta_time) {

		if (keyboard.keyDownOnce(27)) {
			complete = true;
		}
		
		for (int i = 0; i < sprites.size(); i++) {
			DisplayableSprite sprite = sprites.get(i);
			sprite.update(this, keyboard, actual_delta_time);
    	}
		
		disposeSprites();

	}
	
    protected void disposeSprites() {
        
    	
		for (int i = 0; i < sprites.size(); i++) {
			DisplayableSprite sprite = sprites.get(i);
    		if (sprite.getDispose() == true) {
    			disposedSprites.add(sprite);
    			System.out.println(String.format("Dispose: %s", sprite.toString()));
    		}
    	}
		for (int i = 0; i < disposedSprites.size(); i++) {
			DisplayableSprite sprite = disposedSprites.get(i);
			sprites.remove(sprite);
    	}
    	if (disposedSprites.size() > 0) {
    		disposedSprites.clear();
    	}
    }	
	
	
	public String toString() {
		return "ShootingSpriteUniverse";
	}	
	
}
