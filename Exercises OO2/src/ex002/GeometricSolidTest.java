package ex002;

import junit.framework.TestCase;

public class GeometricSolidTest extends TestCase implements GeometricSolid 
{
	@Override
    public double getVolume() {
        return 0;
    }

	@Override
	public double getSurfaceArea() {
		return 0;
	}
	
   public void test()
   {
	   //dummy test... as long as this test compiles, the interface is written correctly
       assertEquals(true,true);
   }
	

}