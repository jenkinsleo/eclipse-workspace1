import java.util.Random;

public class RecursiveSorterPerformance {

	final static int N = 1000;
	final static double NLOGN = N * ((Math.log(N) / Math.log(2))); 

	final static String FORMAT = "%5.3f";

	public static void main(String[] args) {	

        RecursiveSorter sorter = new RecursiveSorter();
        SorterUtilities utility = new SorterUtilities();
		IntegerArray clone = null;
		Random generator = new Random();
        generator.setSeed(12345);  //do not change this statement
        
        long quickSortReads = 0;
        long quickSortWrites = 0;
        long quickSortSpaces = 0;
        long mergeSortReads = 0;
        long mergeSortWrites = 0;
        long mergeSortSpaces = 0;
        		
		///////////////////////////////////RANDOM
		IntegerArray random = utility.createRandomArray(N);
		
		clone = random.clone();
		IntegerArray.reset();
		sorter.doQuickSort(clone);
		printPerformance("Quick Sort", "RANDOM", clone.getReads(), clone.getWrites(), clone.getSpaces(), 1);
		quickSortReads += clone.getReads();
		quickSortWrites += clone.getWrites();
		quickSortSpaces += clone.getSpaces();

		clone = random.clone();
		IntegerArray.reset();
		sorter.doMergeSort(clone);
		printPerformance("Merge Sort", "RANDOM", clone.getReads(), clone.getWrites(), clone.getSpaces(), 1);
		mergeSortReads += clone.getReads();
		mergeSortWrites += clone.getWrites();
		mergeSortSpaces += clone.getSpaces();

		///////////////////////////////////NEARLY SORTED
		IntegerArray nearlySorted = utility.createNearlySortedArray(N);
		
		clone = nearlySorted.clone();
		IntegerArray.reset();
		sorter.doQuickSort(clone);
		printPerformance("Quick Sort", "NEARLY SORTED", clone.getReads(), clone.getWrites(), clone.getSpaces(), 1);
		quickSortReads += clone.getReads();
		quickSortWrites += clone.getWrites();
		quickSortSpaces += clone.getSpaces();

		clone = nearlySorted.clone();
		IntegerArray.reset();
		sorter.doMergeSort(clone);
		printPerformance("Merge Sort", "NEARLY SORTED", clone.getReads(), clone.getWrites(), clone.getSpaces(), 1);
		mergeSortReads += clone.getReads();
		mergeSortWrites += clone.getWrites();
		mergeSortSpaces += clone.getSpaces();

		///////////////////////////////////REVERSE ORDER
		IntegerArray reversed = utility.createReverseOrderArray(N);			
		
		clone = reversed.clone();
		IntegerArray.reset();
		sorter.doQuickSort(clone);
		printPerformance("Quick Sort", "REVERSE ORDER", clone.getReads(), clone.getWrites(), clone.getSpaces(), 1);
		quickSortReads += clone.getReads();
		quickSortWrites += clone.getWrites();
		quickSortSpaces += clone.getSpaces();

		clone = reversed.clone();
		IntegerArray.reset();
		sorter.doMergeSort(clone);
		printPerformance("Merge Sort", "REVERSE ORDER", clone.getReads(), clone.getWrites(), clone.getSpaces(), 1);
		mergeSortReads += clone.getReads();
		mergeSortWrites += clone.getWrites();
		mergeSortSpaces += clone.getSpaces();

		///////////////////////////////////FEW UNIQUE
		IntegerArray fewUnique = utility.createFewUniqueArray(N);			
		
		clone = fewUnique.clone();
		IntegerArray.reset();
		sorter.doQuickSort(clone);
		printPerformance("Quick Sort", "FEW UNIQUE", clone.getReads(), clone.getWrites(), clone.getSpaces(), 1);
		quickSortReads += clone.getReads();
		quickSortWrites += clone.getWrites();
		quickSortSpaces += clone.getSpaces();

		clone = fewUnique.clone();
		IntegerArray.reset();
		sorter.doMergeSort(clone);
		printPerformance("Merge Sort", "FEW UNIQUE", clone.getReads(), clone.getWrites(), clone.getSpaces(), 1);
		mergeSortReads += clone.getReads();
		mergeSortWrites += clone.getWrites();
		mergeSortSpaces += clone.getSpaces();
		
		printPerformance("Quick Sort", "Overall", quickSortReads, quickSortWrites, quickSortSpaces, 4);
		printPerformance("Merge Sort", "Overall", mergeSortReads, mergeSortWrites, mergeSortSpaces, 4);
		
	}
	
	private static void printPerformance(String sort, String inputType, long reads, long writes, long spaces, int scale) {
		
		
		System.out.println(String.format("%s (%s)", sort, inputType));
		System.out.println(String.format("\t Reads / nlogn = " + FORMAT, reads / (float)(NLOGN * scale)));
		System.out.println(String.format("\tWrites / nlogn = " + FORMAT, writes / (float)(NLOGN * scale)));
		System.out.println(String.format("\tSpaces / n = " + FORMAT, spaces / (float)(N * scale)));
		System.out.println();
	}
	
}
