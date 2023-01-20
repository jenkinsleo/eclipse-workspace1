//2019-10-02
import junit.framework.TestCase;

public class TriangleClassifierTest extends TestCase {
    
    public void testValidTriangle() {
    	assertEquals(true, TriangleClassifier.isValidTriangle(60,60,60));
    	assertEquals(false, TriangleClassifier.isValidTriangle(60,60,90));    	
    	assertEquals(false, TriangleClassifier.isValidTriangle(0,90,90));    	
    	assertEquals(false, TriangleClassifier.isValidTriangle(90,0,90));    	
    	assertEquals(false, TriangleClassifier.isValidTriangle(0,90,90));    	
    }
    
    public void testClassifyByAngle() {
       	assertEquals("acute", TriangleClassifier.classifyByAngle(60,60,60));    	
       	assertEquals("right", TriangleClassifier.classifyByAngle(90,60,30));    	
       	assertEquals("right", TriangleClassifier.classifyByAngle(30,90,60));    	
       	assertEquals("right", TriangleClassifier.classifyByAngle(60,30,90));    	
       	assertEquals("obtuse", TriangleClassifier.classifyByAngle(100,60,20));    	
       	assertEquals("obtuse", TriangleClassifier.classifyByAngle(60,20,100));    	
       	assertEquals("obtuse", TriangleClassifier.classifyByAngle(20,100,60));    	
    }
    
    public void testClassifyBySide() {
    	assertEquals("equilateral", TriangleClassifier.classifyBySide(60,60,60));	
    	assertEquals("isosceles", TriangleClassifier.classifyBySide(30,30,120));	
    	assertEquals("isosceles", TriangleClassifier.classifyBySide(30,120,30));	
    	assertEquals("isosceles", TriangleClassifier.classifyBySide(120,30,30));	
    	assertEquals("scalene", TriangleClassifier.classifyBySide(50,60,70));
    	assertEquals("scalene", TriangleClassifier.classifyBySide(60,70,50));
    	assertEquals("scalene", TriangleClassifier.classifyBySide(70,50,60));
    }
        
    public void testRightScalene() {
        assertEquals("right scalene", TriangleClassifier.getClassification(30, 60, 90));
        assertEquals("right scalene", TriangleClassifier.getClassification(30, 90, 60));
        assertEquals("right scalene", TriangleClassifier.getClassification(60, 30, 90));
        assertEquals("right scalene", TriangleClassifier.getClassification(60, 90, 30));
        assertEquals("right scalene", TriangleClassifier.getClassification(90, 30, 60));
        assertEquals("right scalene", TriangleClassifier.getClassification(90, 60, 30));
    }
    
    public void testRightIsosceles() {
        assertEquals("right isosceles", TriangleClassifier.getClassification(45, 45, 90));
        assertEquals("right isosceles", TriangleClassifier.getClassification(45, 90, 45));
        assertEquals("right isosceles", TriangleClassifier.getClassification(90, 45, 45));
    }

    public void testObtuseIsosceles() {
        assertEquals("obtuse isosceles", TriangleClassifier.getClassification(100, 40, 40));
        assertEquals("obtuse isosceles", TriangleClassifier.getClassification(40, 100, 40));
        assertEquals("obtuse isosceles", TriangleClassifier.getClassification(40, 40, 100));
        assertEquals("obtuse isosceles", TriangleClassifier.getClassification(1, 1, 178));
    }
    
    public void testObtuseScalene() {
        assertEquals("obtuse scalene", TriangleClassifier.getClassification(100, 60, 20));
        assertEquals("obtuse scalene", TriangleClassifier.getClassification(100, 20, 60));
        assertEquals("obtuse scalene", TriangleClassifier.getClassification(60, 20, 100));
        assertEquals("obtuse scalene", TriangleClassifier.getClassification(60, 100, 20));
        assertEquals("obtuse scalene", TriangleClassifier.getClassification(20, 100, 60));
        assertEquals("obtuse scalene", TriangleClassifier.getClassification(20, 60, 100));
    }

    public void testEquilateral() {
        assertEquals("equilateral", TriangleClassifier.getClassification(60, 60, 60));
    }

    public void testAcuteIsosceles() {
        assertEquals("acute isosceles", TriangleClassifier.getClassification(80, 50, 50));
        assertEquals("acute isosceles", TriangleClassifier.getClassification(50, 50, 80));
        assertEquals("acute isosceles", TriangleClassifier.getClassification(50, 80, 50));
    }
    
    public void testAcuteScalene() {
        assertEquals("acute scalene", TriangleClassifier.getClassification(50, 60, 70));
        assertEquals("acute scalene", TriangleClassifier.getClassification(50, 70, 60));
        assertEquals("acute scalene", TriangleClassifier.getClassification(60, 70, 50));
        assertEquals("acute scalene", TriangleClassifier.getClassification(60, 50, 70));
        assertEquals("acute scalene", TriangleClassifier.getClassification(70, 50, 60));
        assertEquals("acute scalene", TriangleClassifier.getClassification(70, 60, 50));
    }

    public void testInvalid() {
        assertEquals("INVALID", TriangleClassifier.getClassification(30, 90, 90));
        assertEquals("INVALID", TriangleClassifier.getClassification(90, 30, 90));
        assertEquals("INVALID", TriangleClassifier.getClassification(90, 90, 30));
        assertEquals("INVALID", TriangleClassifier.getClassification(120, 60, 0));
        assertEquals("INVALID", TriangleClassifier.getClassification(60, 120, 0));
        assertEquals("INVALID", TriangleClassifier.getClassification(0, 60, 10));        
    }
    
}
