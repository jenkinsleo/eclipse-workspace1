package ex002;

import junit.framework.TestCase;

public class CubeTest extends TestCase
{
   public void test()
   {

       //Cube class constructor takes in one paramter: double sideLength
       GeometricSolid solid = new Cube(5.0);
       //volume should be sideLength ^ 3
       System.out.printf("Volume: %.2f\n",solid.getVolume());
       System.out.println("Expected: 125.00");
       assertEquals(125.00, solid.getVolume(), 0.01);

       //surface area should be 6 * (sideLength ^ 2)
       System.out.printf("SurfaceArea: %.2f\n",solid.getSurfaceArea());
       System.out.println("Expected: 150.00");    
       assertEquals(150.00, solid.getSurfaceArea(), 0.01);

       solid  = new Cube(3.0);
       System.out.printf("Volume: %.2f\n",solid.getVolume());
       System.out.println("Expected: 27.00");
       assertEquals(27.00, solid.getVolume(), 0.01);

       System.out.printf("SurfaceArea: %.2f\n",solid.getSurfaceArea());
       System.out.println("Expected: 54.00");   
       assertEquals(54.00, solid.getSurfaceArea(), 0.01);

   }
}
