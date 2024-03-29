
public class IterativeSearcher {

	public int doBinarySearch(IntegerArray arr, int x) {
		int start = 0;
		int end = arr.length() - 1;
		
		while (start <= end) {
			int middle = (start + end) / 2;
			
			int mid_pos = arr.read(middle);
			if (x < mid_pos) {
				end = middle - 1;
			}
			
			if (x > mid_pos) {
				start = middle + 1;
			}
			
			
			
			
			if (x == mid_pos) {
				
				if (middle > 1 && arr.read(middle-1) == mid_pos) {
					end = middle - 1;
				}
				else {
					return middle;
				}
				
			}
		}
		
		return -1;
		}
	
	

}
