import java.util.Random;

public class IterativeSorterAnalysis {

	private Random generator;
	private IterativeSorter sorter;

	public static void main(String[] args) {
		IterativeSorterAnalysis s = new IterativeSorterAnalysis();
		s.analyse();
	}
	
	private void analyse() {
        generator = new Random();
        generator.setSeed(12345);  //do not change this statement
        
        sorter = new IterativeSorter();
        
        analyseRandom();
	}
	
	private void analyseRandom() {
			
		System.out.print(String.format("%6s", ""));
		for (int i = 0; i < 6; i++) {
			System.out.print(String.format("%12s\t","random"));			
		}
		for (int i = 0; i < 6; i++) {
			System.out.print(String.format("%12s\t","nearly"));			
		}
		for (int i = 0; i < 6; i++) {
			System.out.print(String.format("%12s\t","reverse"));			
		}
		for (int i = 0; i < 6; i++) {
			System.out.print(String.format("%12s\t","few unique"));			
		}
		
		System.out.println();
		
		
		System.out.print(String.format("%6s\t", "n"));
		for (int i = 0; i < 4; i++) {
			System.out.print(String.format("%12s\t%12s\t", "BS-reads", "BS-writes"));
			System.out.print(String.format("%12s\t%12s\t", "IS-reads", "IS-writes"));
			System.out.print(String.format("%12s\t%12s\t", "SS-reads", "SS-writes"));
		}
		System.out.println();
				
		for (int n = 100; n <= 10000; n+=100){
			///////////////////////////////////RANDOM
			int[] random = createRandomArray(n);
			IntegerArray copy;
						
			System.out.print(String.format("%6d\t",n));
			
			copy = new IntegerArray(random, true);
			sorter.doBubbleSort(copy);
			System.out.print(String.format("%10d\t%10d\t", copy.getReads(), copy.getWrites()));

			copy = new IntegerArray(random, true);
			sorter.doInsertionSort(copy);
			System.out.print(String.format("%10d\t%10d\t", copy.getReads(), copy.getWrites()));

			copy = new IntegerArray(random, true);
			sorter.doSelectionSort(copy);
			System.out.print(String.format("%10d\t%10d\t", copy.getReads(), copy.getWrites()));

			///////////////////////////////////NEARLY SORTED
			int[] nearlySorted = createNearlySortedArray(n);						
			copy = new IntegerArray(nearlySorted, true);
			sorter.doBubbleSort(copy);
			System.out.print(String.format("%10d\t%10d\t", copy.getReads(), copy.getWrites()));

			copy = new IntegerArray(nearlySorted, true);
			sorter.doInsertionSort(copy);
			System.out.print(String.format("%10d\t%10d\t", copy.getReads(), copy.getWrites()));

			copy = new IntegerArray(nearlySorted, true);
			sorter.doSelectionSort(copy);
			System.out.print(String.format("%10d\t%10d\t", copy.getReads(), copy.getWrites()));
			
			///////////////////////////////////REVERSE ORDER
			int[] reversed = createReverseOrderArray(n);			
			
			copy = new IntegerArray(reversed, true);
			sorter.doBubbleSort(copy);
			System.out.print(String.format("%10d\t%10d\t", copy.getReads(), copy.getWrites()));

			copy = new IntegerArray(reversed, true);
			sorter.doInsertionSort(copy);
			System.out.print(String.format("%10d\t%10d\t", copy.getReads(), copy.getWrites()));

			copy = new IntegerArray(reversed, true);
			sorter.doSelectionSort(copy);
			System.out.print(String.format("%10d\t%10d\t", copy.getReads(), copy.getWrites()));

			///////////////////////////////////NEARLY SORTED
			int[] fewUnique = createFewUniqueArray(n);			
			
			copy = new IntegerArray(fewUnique, true);
			sorter.doBubbleSort(copy);
			System.out.print(String.format("%10d\t%10d\t", copy.getReads(), copy.getWrites()));

			copy = new IntegerArray(fewUnique, true);
			sorter.doInsertionSort(copy);
			System.out.print(String.format("%10d\t%10d\t", copy.getReads(), copy.getWrites()));

			copy = new IntegerArray(fewUnique, true);
			sorter.doSelectionSort(copy);
			System.out.print(String.format("%10d\t%10d\t", copy.getReads(), copy.getWrites()));

			
			System.out.println();

		}
		
	}
	
	private int[] createRandomArray(int size) {
		int[] array = new int[size];		
        
        for (int i = 0; i < size; i++) {
        	array[i] = generator.nextInt(size);
        }
		
		return array;		
	}	

	private int[] createNearlySortedArray(int size) {
		int changes = size / 50;
		int temp = 0;
		int indexA = 0;
		int indexB = 0;
		int[] array = new int[size];
		
		for (int i = 0; i < size; i++) {
			array[i] = i;
		}
		
        for (int i = 0; i < changes; i++) {
        	indexA = generator.nextInt(size);
        	indexB = generator.nextInt(size);
        	
        	temp = array[indexA];
        	array[indexA] = array[indexB];
        	array[indexB] = temp;
        }
		
		return array;		
	}	

	private int[] createReverseOrderArray(int size) {
		int[] array = new int[size];
		
		for (int i = 0; i < size; i++) {
			array[i] = size - i - 1;
		}
				
		return array;		
	}	
	
	private int[] createFewUniqueArray(int size) {
		int[] array = new int[size];
        
        for (int i = 0; i < size; i++) {
        	array[i] = generator.nextInt(10);
        }
		
		return array;		
	}	

	
	
}
