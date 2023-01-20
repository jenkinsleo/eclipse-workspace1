package ex004;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import ex003.Trio;

public class ExtensionTest {

	@Test
	public void test() {
	
		Cappuccino cappuccino = new Cappuccino();
		ClubSandwich clubSandwich = new ClubSandwich();
		SpinachSalad spinachSalad = new SpinachSalad();

		Trio trio1 = new Trio(clubSandwich, spinachSalad, cappuccino);		
		assertEquals("Club Sandwich/Spinach Salad/Cappuccino Trio", trio1.getName());
		assertEquals(6.25, trio1.getPrice(), 0.001);
		
	}

}
