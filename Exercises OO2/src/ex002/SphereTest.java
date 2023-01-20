package ex002;

import junit.framework.TestCase;

public class SphereTest extends TestCase
{
   public void test()
   {
       //Sphere class constructor takes in one paramter: double radius
       GeometricSolid solid = new Sphere(10.0);
      
       //volume should be (4/3)(pi)(r ^ 3)
       System.out.printf("Volume: %.2f\n",solid.getVolume());
       System.out.println("Expected: 4188.79");
       assertEquals(4188.79, solid.getVolume(), 0.01);

       //surface area should be (4)(pi)(r ^ 2)
       System.out.printf("SurfaceArea: %.2f\n",solid.getSurfaceArea());
       System.out.println("Expected: 1256.64");
       assertEquals(1256.64, solid.getSurfaceArea(), 0.01);
       
       solid = new Sphere(5.1);
       System.out.printf("Volume: %.2f\n",solid.getVolume());
       System.out.println("Expected:  555.65");
       assertEquals(555.65, solid.getVolume(), 0.01);

       System.out.printf("SurfaceArea: %.2f\n",solid.getSurfaceArea());
       System.out.println("Expected: 326.85"); 
       assertEquals(326.85, solid.getSurfaceArea(), 0.01);
       
   }
}
