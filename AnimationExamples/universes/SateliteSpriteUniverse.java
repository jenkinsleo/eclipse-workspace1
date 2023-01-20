import java.awt.Rectangle;
import java.util.ArrayList;

public class SateliteSpriteUniverse implements Universe {

	private boolean complete = false;	
	private Background background = null;	
	private DisplayableSprite player1 = null;
	private ArrayList<DisplayableSprite> sprites = new ArrayList<DisplayableSprite>();
	ArrayList<DisplayableSprite> disposedSprites = new ArrayList<DisplayableSprite>();
	
	public SateliteSpriteUniverse () {
			
		SateliteSprite jupiter = new SateliteSprite(0, 0, 0, 0, 1000000, 100,false, "res/satelites/jupiter.png");
		this.player1 = jupiter;		
		SateliteSprite io = new SateliteSprite(-100,0, 0, 350 ,  1000, 36, false, "res/satelites/io.png");
		SateliteSprite europa = new SateliteSprite(-150,0, 0, 325,  1000, 31, false, "res/satelites/europa.png");
		SateliteSprite ganymede  = new SateliteSprite(-250,0, 0, 300,  1000, 52,false, "res/satelites/ganymede.png");
		SateliteSprite callisto = new SateliteSprite(-350,0, 0, 275,  1000, 48,false, "res/satelites/callisto.png");

		sprites.add(io);
		sprites.add(europa);
		sprites.add(callisto);
		sprites.add(ganymede);
		sprites.add(jupiter);
	
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
		return "SateliteSpriteUniverse";
	}	
		
}
