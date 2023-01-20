//2020-02-10
import org.junit.jupiter.api.Assertions;

import junit.framework.TestCase;

public class GameOfLifeTest extends TestCase {

    public void testConstructors() {

        GameOfLife game = new GameOfLife(10,10);
        assertEquals(10, game.getColumns());
        assertEquals(10, game.getRows());

        GameOfLife game2 = new GameOfLife(64,32);
        assertEquals(32, game2.getColumns());
        assertEquals(64, game2.getRows());
        
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new GameOfLife(-10,10);
          });        
        
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
        	new GameOfLife(0,10);
          });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new GameOfLife(10,-10);
          });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new GameOfLife(10,0);
          });
    }
    
    public void testAccessorBounds() {

        GameOfLife game = new GameOfLife(10,10);

        //test bounds
        assertEquals(false, game.isAlive(-1,0));
        assertEquals(false, game.isAlive(0,-1));
        assertEquals(false, game.isAlive(11,0));
        assertEquals(false, game.isAlive(0,11));
        assertEquals(false, game.isAlive(20,20));        
    }
    
    public void testMutatorBounds() {

        GameOfLife game = new GameOfLife(10,10);

        //test for both legal and illegal cells
        for (int i = -2; i < game.getRows() + 1; i++) {
            for (int j = -2; j < game.getColumns() + 1; j++) {
                game.setAlive(i, j, true);
            }
        }        
    }
    
    public void testGetNeighbourCount() {

        GameOfLife game = createGlider();

		//check if the neighbour count is correct... nothing else will work if this method does return the right answers
		assertEquals(1 , game.getNeighbourCount(0,1));
		assertEquals(2 , game.getNeighbourCount(0,2));
		assertEquals(0 , game.getNeighbourCount(0,4));
		assertEquals(5 , game.getNeighbourCount(1,1));
		assertEquals(3 , game.getNeighbourCount(3,1));
		assertEquals(3 , game.getNeighbourCount(1,2));
    }
		
	public void testCalculateNextGeneration(){
				
        GameOfLife game = createGlider();

		//run ten generations, and test if the end state is correct
		for (int i = 1; i <= 10; i++) {
			game.calculateNextGeneration();
		}

		assertEquals(false, game.isAlive(3,2));
		assertEquals(false, game.isAlive(3,3));
		assertEquals(true, game.isAlive(3,4));

		assertEquals(true, game.isAlive(4,2));
		assertEquals(false, game.isAlive(4,3));
		assertEquals(true, game.isAlive(4,4));

		assertEquals(false, game.isAlive(5,2));
		assertEquals(true, game.isAlive(5,3));		
		assertEquals(true, game.isAlive(5,4));	
	}

	public void testToString() {
		
        GameOfLife game = createGlider();
        
        String expected1 = 	" *        \r\n" + 
			        		"  *       \r\n" + 
			        		"***       \r\n" + 
			        		"          \r\n" + 
			        		"          \r\n" + 
			        		"          \r\n" + 
			        		"          \r\n" + 
			        		"          \r\n" + 
			        		"          \r\n" + 
			        		"          \r\n";
        assertEquals(expected1, game.toString());
        
        String expected2 = 	"          \r\n" +
        					"* *       \r\n" + 
			        		" **       \r\n" + 
			        		" *        \r\n" + 			        		
			        		"          \r\n" + 
			        		"          \r\n" + 
			        		"          \r\n" + 
			        		"          \r\n" + 
			        		"          \r\n" + 
			        		"          \r\n";

		game.calculateNextGeneration();
        assertEquals(expected2, game.toString());
		
		
	}
	
	private GameOfLife createGlider() {
		
        GameOfLife glider = new GameOfLife(10,10);
        
        //set up a glider
        glider.setAlive(0, 1, true);
        glider.setAlive(1, 2, true);
        glider.setAlive(2, 0, true);
        glider.setAlive(2, 1, true);
        glider.setAlive(2, 2, true);
        
        return glider;
	}
	
}
