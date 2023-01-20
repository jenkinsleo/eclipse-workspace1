import java.util.Arrays;

//2020/10/09
public class IntegerArray {

	public static long spaces = 0;
	public static long reads = 0;
	public static long writes = 0;
	
	private int[] array = null;

	public IntegerArray(int[] array) {
		this(array, false);
	}
	
	protected IntegerArray(int[] array, boolean reset) {		
		this.array = array.clone();

		if (reset) {
			reset();
		}
		
		spaces += array.length;
	}
		
	public int length() {
		return array.length;
	}
	
    public int read(int index) {	
    	reads++;    	
        return array[index];
    }
    
    public void write(int index, int element) {	
    	writes++;    	
        array[index] = element;
    }
    
    public long getReads() {
    	return reads;
    }
    
    public long getWrites() {
    	return writes;
    }
    
    public long getSpaces() {
    	return spaces + this.array.length;
    }
    
    public static void reset() {
    	reads = 0;
    	writes = 0;
    	spaces = 0;
    }

    public IntegerArray clone() {
    	IntegerArray clone = new IntegerArray(this.array);
    	return clone;
    }
    
    public String toString() {
//    	String output = "";
//    	
//    	for (int i = 0; i < array.length; i++) {
//    		output += String.format("[%8d]: %8d", i, array[i]);
//    	}    	
    	return String.format("%s\nreads: %d; writes: %d; spaces: %d", Arrays.toString(array), reads, writes, spaces);
    }
		
}
