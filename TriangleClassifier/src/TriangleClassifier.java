
public class TriangleClassifier {
	public static String getClassification(int angleA, int angleB, int angleC) {
		return String.format();
	}
	
	public static boolean isValidTriangle(int side1, int side2, int side3) {
		if (side1 + side2 + side3 == 180 && side1 > 0 && side2 > 0 && side3 > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public static String classifyByAngle(int angle1, int angle2, int angle3) {
		if (isValidTriangle(angle1, angle2, angle3)) {
			if (angle1 == 90 || angle2 == 90 || angle3 == 90) {
				return "right";
			} else if () {
				
			}
		}
	}
	
	
}
