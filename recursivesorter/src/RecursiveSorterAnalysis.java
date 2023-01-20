import java.util.Random;

public class RecursiveSorterAnalysis {

	public static void main(String[] args) {
		createTable();
//		createSummary();
	}
	
	static void createTable() {

        RecursiveSorter sorter = new RecursiveSorter();
        SorterUtilities utility = new SorterUtilities();
		Random generator = new Random();
        generator.setSeed(12345);  //do not change this statement
        
        			
		System.out.print(String.format("%6s", ""));
		for (int i = 0; i < 6; i++) {
			System.out.print(String.format("%12s","random"));			
		}
		for (int i = 0; i < 6; i++) {
			System.out.print(String.format("%12s","nearly"));			
		}
		for (int i = 0; i < 6; i++) {
			System.out.print(String.format("%12s","reverse"));			
		}
		for (int i = 0; i < 6; i++) {
			System.out.print(String.format("%12s","few unique"));			
		}
		
		System.out.println();
		
		
		System.out.print(String.format("%6s", "n"));
		for (int i = 0; i < 4; i++) {
			System.out.print(String.format("%12s%12s%12s", "QS-reads", "QS-writes", "QS-space"));
			System.out.print(String.format("%12s%12s%12s", "MS-reads", "MS-writes", "MS-space"));
		}
		System.out.println();
		
		for (int n = 100; n <= 10000; n+=100){
			///////////////////////////////////RANDOM
			IntegerArray arrayToSort = utility.createRandomArray(n);
			IntegerArray clone = null;
						
			System.out.print(String.format("%6d",n));

			clone = arrayToSort.clone();
			IntegerArray.reset();
			sorter.doQuickSort(clone);
			System.out.print(String.format("%12d%12d%12d", clone.getReads(), clone.getWrites(), clone.getSpaces()));

			clone = arrayToSort.clone();			
			IntegerArray.reset();
			sorter.doMergeSort(clone);
			System.out.print(String.format("%12d%12d%12d", clone.getReads(), clone.getWrites(), clone.getSpaces()));

			///////////////////////////////////NEARLY SORTED
			arrayToSort = utility.createNearlySortedArray(n);
			
			clone = arrayToSort.clone();			
			IntegerArray.reset();
			sorter.doQuickSort(clone);
			System.out.print(String.format("%12d%12d%12d", clone.getReads(), clone.getWrites(), clone.getSpaces()));

			clone = arrayToSort.clone();			
			IntegerArray.reset();
			sorter.doMergeSort(clone);
			System.out.print(String.format("%12d%12d%12d", clone.getReads(), clone.getWrites(), clone.getSpaces()));

			///////////////////////////////////REVERSE ORDER
			arrayToSort = utility.createReverseOrderArray(n);			
			
			clone = arrayToSort.clone();			
			IntegerArray.reset();
			sorter.doQuickSort(clone);
			System.out.print(String.format("%12d%12d%12d", clone.getReads(), clone.getWrites(), clone.getSpaces()));

			clone = arrayToSort.clone();			
			IntegerArray.reset();
			sorter.doMergeSort(clone);
			System.out.print(String.format("%12d%12d%12d", clone.getReads(), clone.getWrites(), clone.getSpaces()));

			///////////////////////////////////FEW UNIQUE
			arrayToSort = utility.createFewUniqueArray(n);			
			
			clone = arrayToSort.clone();			
			IntegerArray.reset();
			sorter.doQuickSort(clone);
			System.out.print(String.format("%12d%12d%12d", clone.getReads(), clone.getWrites(), clone.getSpaces()));

			clone = arrayToSort.clone();			
			IntegerArray.reset();
			sorter.doMergeSort(clone);
			System.out.print(String.format("%12d%12d%12d", clone.getReads(), clone.getWrites(), clone.getSpaces()));

			System.out.println();
		}

		System.out.println();
		
	}
	
//	public static void createSummary() {
//
//		final int N = 10000;
//		final int TESTS = 4;
//		final String FORMAT = "%8.3f";
//		final double OPERATION_DIVISOR = N * N *TESTS;
//		final double SPACE_DIVISOR = N * TESTS;
//		
//        RecursiveSorter sorter = new RecursiveSorter();
//        SorterUtilities utility = new SorterUtilities();
//		IntegerArray clone = null;
//		Random generator = new Random();
//        generator.setSeed(12345);  //do not change this statement
//        
//        long quickSortReads = 0;
//        long quickSortWrites = 0;
//        long quickSortSpaces = 0;
//        long mergeSortReads = 0;
//        long mergeSortWrites = 0;
//        long mergeSortSpaces = 0;
//        		
//		///////////////////////////////////RANDOM
//		IntegerArray random = utility.createRandomArray(N);
//		
//		IntegerArray.reset();
//		clone = random.clone();
//		sorter.doQuickSort(clone);
//		quickSortReads += clone.getReads();
//		quickSortWrites += clone.getWrites();
//		quickSortSpaces += IntegerArray.getSpaces();
//
//		IntegerArray.reset();
//		clone = random.clone();
//		sorter.doMergeSort(clone);
//		mergeSortReads += clone.getReads();
//		mergeSortWrites += clone.getWrites();
//		mergeSortSpaces += IntegerArray.getSpaces();
//
//		///////////////////////////////////NEARLY SORTED
//		IntegerArray nearlySorted = utility.createNearlySortedArray(N);
//		
//		IntegerArray.reset();
//		clone = nearlySorted.clone();
//		sorter.doQuickSort(clone);
//		quickSortReads += clone.getReads();
//		quickSortWrites += clone.getWrites();
//		quickSortSpaces += IntegerArray.getSpaces();
//
//		IntegerArray.reset();
//		clone = nearlySorted.clone();
//		sorter.doMergeSort(clone);
//		mergeSortReads += clone.getReads();
//		mergeSortWrites += clone.getWrites();
//		mergeSortSpaces += IntegerArray.getSpaces();
//
//		///////////////////////////////////REVERSE ORDER
//		IntegerArray reversed = utility.createReverseOrderArray(N);			
//		
//		IntegerArray.reset();
//		clone = reversed.clone();
//		sorter.doQuickSort(clone);
//		quickSortReads += clone.getReads();
//		quickSortWrites += clone.getWrites();
//		quickSortSpaces += IntegerArray.getSpaces();
//
//		IntegerArray.reset();
//		clone = reversed.clone();
//		sorter.doMergeSort(clone);
//		mergeSortReads += clone.getReads();
//		mergeSortWrites += clone.getWrites();
//		mergeSortSpaces += IntegerArray.getSpaces();
//
//		///////////////////////////////////FEW UNIQUE
//		IntegerArray fewUnique = utility.createFewUniqueArray(N);			
//		
//		IntegerArray.reset();
//		clone = fewUnique.clone();
//		sorter.doQuickSort(clone);
//		quickSortReads += clone.getReads();
//		quickSortWrites += clone.getWrites();
//		quickSortSpaces += IntegerArray.getSpaces();
//
//		IntegerArray.reset();
//		clone = fewUnique.clone();
//		sorter.doMergeSort(clone);
//		mergeSortReads += clone.getReads();
//		mergeSortWrites += clone.getWrites();
//		mergeSortSpaces += IntegerArray.getSpaces();
//		
//		
//		System.out.println(String.format("Quick Sort (n = %d)", N));
//		System.out.println(String.format("\t Reads (/n^2): " + FORMAT, quickSortReads / OPERATION_DIVISOR));
//		System.out.println(String.format("\tWrites (/n^2): " + FORMAT, quickSortWrites / OPERATION_DIVISOR));
//		System.out.println(String.format("\tSpaces (/n)  : " + FORMAT, quickSortSpaces / SPACE_DIVISOR));
//		System.out.println();
//		
//		System.out.println(String.format("Merge Sort (n = %d)", N));
//		System.out.println(String.format("\t Reads (/n^2): " + FORMAT, mergeSortReads / OPERATION_DIVISOR));
//		System.out.println(String.format("\tWrites (/n^2): " + FORMAT, mergeSortWrites / OPERATION_DIVISOR));
//		System.out.println(String.format("\tSpaces (/n)  : " + FORMAT, mergeSortSpaces / SPACE_DIVISOR));
//		System.out.println();
//	}
		
}
