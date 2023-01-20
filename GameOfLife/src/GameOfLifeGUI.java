//2019-09-09
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

final public class GameOfLifeGUI extends JFrame {

	final int GAME_ROWS = 50;
	final int GAME_COLS = 50;
	
	final int FRAME_HEIGHT = 600;
	final int FRAME_WIDTH = 600;	
	final int WIDTH_OFFSET = 0;
	final int HEIGHT_OFFSET = 24;

	final int PANEL_HEIGHT = FRAME_HEIGHT - HEIGHT_OFFSET;
	final int PANEL_WIDTH = FRAME_WIDTH - WIDTH_OFFSET;

	final int FRAMES_PER_SECOND = 60;
	final int DRAW_DELAY_VALUE = 500;
	
	long current_time = 0;								//MILLISECONDS
	long next_refresh_time = 0;							//MILLISECONDS
	long minimum_delta_time = 1000 / FRAMES_PER_SECOND;	//MILLISECONDS
	long actual_delta_time = 0;							//MILLISECONDS

    private boolean paused = true;
    private boolean leftButtonDown = false;
    private int drawDelay = 0;
    private int stepSpeed = 0; 							//MILLISECONDS

    private DrawPanel panel;
    private JButton btnPauseRun;
    private JButton btnRandomize;
    private JSlider sldSpeed;
    
    GameOfLife gameOfLife = null;
    Random random = null;
    private long last_refresh_time;
    private long timeRemainingToStep;
    private boolean doStep;
    	
	public static void main(String[] args) {
	    GameOfLifeGUI gui = new GameOfLifeGUI();
	    gui.setVisible(true);
	    gui.run();
	}

	
	public GameOfLifeGUI()
    {
	    gameOfLife = new GameOfLife(GAME_COLS,GAME_ROWS);
	    random = new Random();
	    random.setSeed(System.currentTimeMillis());

		//set up a glider
	    gameOfLife.setAlive(0, 1, true);
	    gameOfLife.setAlive(1, 2, true);
	    gameOfLife.setAlive(2, 0, true);
	    gameOfLife.setAlive(2, 1, true);
	    gameOfLife.setAlive(2, 2, true);
	    
//	    //set up a 'C'
//        gameOfLife.setAlive(50, 49, true);
//        gameOfLife.setAlive(49, 49, true);
//        gameOfLife.setAlive(48, 49, true);
//        gameOfLife.setAlive(48, 50, true);
//        gameOfLife.setAlive(48, 51, true);
//        gameOfLife.setAlive(49, 51, true);
//        gameOfLife.setAlive(50, 51, true);
//
        Container cp = getContentPane();
        
        panel = new DrawPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().setLayout(null);
        panel.setLayout(null);
        panel.setSize(PANEL_WIDTH, PANEL_HEIGHT);
        panel.setBounds(WIDTH_OFFSET, HEIGHT_OFFSET, PANEL_WIDTH, PANEL_HEIGHT);
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent arg0) {
                panel_mousePressed(arg0);
            }
            public void mouseClicked(MouseEvent e) {
                panel_mouseClicked(e);
            }
            public void mouseReleased(MouseEvent e) {
            	panel_mouseReleased(e);
            }
        });
        
        btnPauseRun = new JButton(">");
        btnPauseRun.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		btnRun_mouseClicked(e);
        	}
        });
        btnPauseRun.setBounds(0, 0, 41, 23);
        getContentPane().add(btnPauseRun);
        getContentPane().setComponentZOrder(btnPauseRun, 0);
        
        JButton btnStep = new JButton("+");
        btnStep.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		btnStep_mouseClicked(e);
        	}
        });
        btnStep.setBounds(51, 0, 41, 23);
        getContentPane().add(btnStep);
        getContentPane().setComponentZOrder(btnStep, 0);
        
        sldSpeed = new JSlider();
        sldSpeed.addChangeListener(new ChangeListener() {
        	public void stateChanged(ChangeEvent arg0) {
        		sldSpeed_stateChanged(arg0);
        	}
        });
        sldSpeed.setBounds(102, 0, 250, 23);
        sldSpeed.setMaximum(100);
        sldSpeed.setMinimum(0);
        sldSpeed.setValue(10);
        getContentPane().add(sldSpeed);
        getContentPane().setComponentZOrder(sldSpeed, 0);
        
        btnRandomize = new JButton("Randomize");
        btnRandomize.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		btnRandomize_mouseClicked(e);
        	}
        });
        btnRandomize.setBounds(358, 0, 89, 23);
        getContentPane().add(btnRandomize);
        sldSpeed_stateChanged(null);
        
        panel.setLayout(null);
        panel.setSize(PANEL_WIDTH, PANEL_HEIGHT);
        this.setSize(panel.getWidth() + WIDTH_OFFSET, panel.getHeight() + HEIGHT_OFFSET);
        
        sldSpeed_stateChanged(null);
    
    }
	
	class DrawPanel extends JPanel 
	{
		public void paintComponent(Graphics g)
		{
			if (g == null) {
				return;
			}

			
			this.resize(this.getParent().getWidth() - 8, this.getParent().getHeight() - 8);
			double cell_width = getCellWidth();
			double cell_height = getCellHeight();
			
			//BS way of clearing out the old rectangle to save CPU.
			g.setColor(getBackground());
						
			for (int y = 0; y < gameOfLife.getRows(); y++){
				for (int x = 0; x < gameOfLife.getColumns(); x++){
					if (gameOfLife.isAlive(y,x) == true) {
						g.setColor(Color.YELLOW);
					}
					else {
						g.setColor(Color.BLACK);
					}
					g.fillRect((int)(x * cell_width),(int)(y * cell_height + HEIGHT_OFFSET), (int)(cell_width) + 1, (int)(cell_height) + 1);
				}
			}

		}

	}
	
	private double getCellWidth() {
	    return (double)(this.getWidth()) / (gameOfLife.getColumns());
	}

    private double getCellHeight() {
        return (double)(this.getHeight() - HEIGHT_OFFSET) / (gameOfLife.getRows());
    }
	
    public void run()
    {
        Thread loop = new Thread()
        {
           public void run()
           {
              gameLoop();
           }
        };
        loop.start();
    	
    }
    
    private void gameLoop() {

		while (true) { // main game loop
		    			
		    //UPDATE STATE
		    updateTime();
		    if (doStep) {
		        doStep = false;
		        gameOfLife.calculateNextGeneration();
		    }

		    //HANDLE EVENTS
			if (inDrawMode()) {
				panel_mouseClicked(new MouseEvent(panel, 0, 0, 0, (int)panel.getMousePosition().getX(), (int)panel.getMousePosition().getY(), 0, false, 0));
			}

			//REFRESH
		    this.repaint();

		    //adapted from http://www.java-gaming.org/index.php?topic=24220.0
		    next_refresh_time = current_time + minimum_delta_time;
            while (current_time < next_refresh_time)
            {
               Thread.yield();
            
               //This stops the app from consuming all your CPU. It makes this slightly less accurate, but is worth it.
               //You can remove this line and it will still work (better), your CPU just climbs on certain OSes.
               //FYI on some OS's this can cause pretty bad stuttering. Scroll down and have a look at different peoples' solutions to this.
               try {Thread.sleep(1);} catch(Exception e) {} 
            
               current_time = System.currentTimeMillis();
            }
		}
    }
    
    private void updateTime() {

        current_time = System.currentTimeMillis();
        actual_delta_time = current_time - last_refresh_time;
        last_refresh_time = current_time;
        if (paused == false) {
        	timeRemainingToStep -= actual_delta_time;
        }

        if (timeRemainingToStep < 0) {
            timeRemainingToStep += stepSpeed;
            doStep = true;
        }
        
		if (drawDelay > 0) {
			drawDelay -= actual_delta_time;
		}
                        
    }
	protected void panel_mouseClicked(MouseEvent e) {
        double cell_width = getCellWidth();
        double cell_height = getCellHeight();
        int x = (int) (e.getX() / cell_width);
        int y = (int) ((e .getY() - HEIGHT_OFFSET) / cell_height);
        gameOfLife.setAlive(y,x, inDrawMode() ? true : ! gameOfLife.isAlive(y,x));	    
	}
	
	protected void panel_mouseReleased(MouseEvent e) {
	    if(e.getButton() == MouseEvent.BUTTON1) {	    	
	    	leftButtonDown = false;
	    }
	}
	
    protected void panel_mousePressed(MouseEvent e) {
	    if(e.getButton() == MouseEvent.BUTTON1) {	    	
	    	leftButtonDown = true;
	        drawDelay = DRAW_DELAY_VALUE;
	    }
    }
    
    protected void btnRun_mouseClicked(MouseEvent e) {
		if (paused) {
			paused = false;
			this.btnPauseRun.setText("||");
		}
		else {			
			paused = true;
			this.btnPauseRun.setText(">");
		}
	}
	protected void btnStep_mouseClicked(MouseEvent e) {
	    doStep = true;
	}

	protected void btnRandomize_mouseClicked(MouseEvent e) {
		
		for (int row = 0; row < GAME_ROWS; row++) {
			for (int col = 0; col < GAME_COLS; col++) {
				 gameOfLife.setAlive(row, col, random.nextBoolean());
			}
		}
	}
	
	protected void sldSpeed_stateChanged(ChangeEvent arg0) {
		stepSpeed = (int) Math.pow(sldSpeed.getValue(), 1.5);
	}
	
	private boolean inDrawMode( ) {
		return (drawDelay <= 0 && leftButtonDown);
	}
}
