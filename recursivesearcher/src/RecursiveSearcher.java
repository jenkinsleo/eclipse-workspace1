
public class RecursiveSearcher {
	
	public RecursiveSearcher() {
	}

	public int doBinarySearch(IntegerArray arrayOfInts, int target) {
		int length = arrayOfInts.length();
		if (target == arrayOfInts.read(0)) {
			return 0;
		}
		
		if (target == arrayOfInts.read(length - 1)) {
			return length - 1;
		}
		int res = binarySearch(arrayOfInts, 0, arrayOfInts.length() - 1, target);
		return res;
	}
		
	

	
	public int binarySearch(IntegerArray arrayOfInts, int start, int end, int target) {
		int middle = (start + end) / 2;
	     if (end < start) {
	       return -1;
	     }
	     int mid_pos = arrayOfInts.read(middle);
	     if (target == mid_pos) {
	    	 if (middle > 1 && arrayOfInts.read(middle-1) == mid_pos) {
					return binarySearch(arrayOfInts, start, middle - 1, target);
				}
	    	 return middle;
	        
	     } else if (target < mid_pos) {
	        return binarySearch(arrayOfInts, start, middle - 1, target);
	     } else {
	        return binarySearch(arrayOfInts, middle + 1, end, target);
	     }
		
		
	}
}
