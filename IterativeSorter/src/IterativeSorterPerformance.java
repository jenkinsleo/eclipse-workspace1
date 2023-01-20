import java.util.Random;

public class IterativeSorterPerformance {

	final static int N = 1000;
	final static String FORMAT = "%5.3f";

	public static void main(String[] args) {	

        IterativeSorter sorter = new IterativeSorter();
        SorterUtilities utility = new SorterUtilities();
		IntegerArray clone = null;
		Random generator = new Random();
        generator.setSeed(12345);  //do not change this statement
        
        long bubbleSortReads = 0;
        long bubbleSortWrites = 0;
        long bubbleSortSpaces = 0;
        long insertionSortReads = 0;
        long insertionSortWrites = 0;
        long insertionSortSpaces = 0;
        long selectionSortReads = 0;
        long selectionSortWrites = 0;
        long selectionSortSpaces = 0;
        		
		///////////////////////////////////RANDOM
		IntegerArray random = utility.createRandomArray(N);
		
		clone = random.clone();
		IntegerArray.reset();
		sorter.doBubbleSort(clone);
		printPerformance("Bubble Sort", "RANDOM", clone.getReads(), clone.getWrites(), clone.getSpaces(), 1);
		bubbleSortReads += clone.getReads();
		bubbleSortWrites += clone.getWrites();
		bubbleSortSpaces += clone.getSpaces();

		clone = random.clone();
		IntegerArray.reset();
		sorter.doInsertionSort(clone);
		printPerformance("Insertion Sort", "RANDOM", clone.getReads(), clone.getWrites(), clone.getSpaces(), 1);
		insertionSortReads += clone.getReads();
		insertionSortWrites += clone.getWrites();
		insertionSortSpaces += clone.getSpaces();

		clone = random.clone();
		IntegerArray.reset();
		sorter.doSelectionSort(clone);
		printPerformance("Selection Sort", "RANDOM", clone.getReads(), clone.getWrites(), clone.getSpaces(), 1);
		selectionSortReads += clone.getReads();
		selectionSortWrites += clone.getWrites();
		selectionSortSpaces += clone.getSpaces();
		
		///////////////////////////////////NEARLY SORTED
		IntegerArray nearlySorted = utility.createNearlySortedArray(N);
		
		clone = nearlySorted.clone();
		IntegerArray.reset();
		sorter.doBubbleSort(clone);
		printPerformance("Bubble Sort", "NEARLY SORTED", clone.getReads(), clone.getWrites(), clone.getSpaces(), 1);
		bubbleSortReads += clone.getReads();
		bubbleSortWrites += clone.getWrites();
		bubbleSortSpaces += clone.getSpaces();

		clone = nearlySorted.clone();
		IntegerArray.reset();
		sorter.doInsertionSort(clone);
		printPerformance("Insertion Sort", "NEARLY SORTED", clone.getReads(), clone.getWrites(), clone.getSpaces(), 1);
		insertionSortReads += clone.getReads();
		insertionSortWrites += clone.getWrites();
		insertionSortSpaces += clone.getSpaces();

		clone = nearlySorted.clone();
		IntegerArray.reset();
		sorter.doSelectionSort(clone);
		printPerformance("Selection Sort", "NEARLY SORTED", clone.getReads(), clone.getWrites(), clone.getSpaces(), 1);
		selectionSortReads += clone.getReads();
		selectionSortWrites += clone.getWrites();
		selectionSortSpaces += clone.getSpaces();
		
		///////////////////////////////////REVERSE ORDER
		IntegerArray reversed = utility.createReverseOrderArray(N);			
		
		clone = reversed.clone();
		IntegerArray.reset();
		sorter.doBubbleSort(clone);
		printPerformance("Bubble Sort", "REVERSE ORDER", clone.getReads(), clone.getWrites(), clone.getSpaces(), 1);
		bubbleSortReads += clone.getReads();
		bubbleSortWrites += clone.getWrites();
		bubbleSortSpaces += clone.getSpaces();

		clone = reversed.clone();
		IntegerArray.reset();
		sorter.doInsertionSort(clone);
		printPerformance("Insertion Sort", "REVERSE ORDER", clone.getReads(), clone.getWrites(), clone.getSpaces(), 1);
		insertionSortReads += clone.getReads();
		insertionSortWrites += clone.getWrites();
		insertionSortSpaces += clone.getSpaces();

		clone = reversed.clone();
		IntegerArray.reset();
		sorter.doSelectionSort(clone);
		printPerformance("Selection Sort", "RANDOM", clone.getReads(), clone.getWrites(), clone.getSpaces(), 1);
		selectionSortReads += clone.getReads();
		selectionSortWrites += clone.getWrites();
		selectionSortSpaces += clone.getSpaces();
		
		///////////////////////////////////FEW UNIQUE
		IntegerArray fewUnique = utility.createFewUniqueArray(N);			
		
		clone = fewUnique.clone();
		IntegerArray.reset();
		sorter.doBubbleSort(clone);
		printPerformance("Bubble Sort", "FEW UNIQUE", clone.getReads(), clone.getWrites(), clone.getSpaces(), 1);
		bubbleSortReads += clone.getReads();
		bubbleSortWrites += clone.getWrites();
		bubbleSortSpaces += clone.getSpaces();

		clone = fewUnique.clone();
		IntegerArray.reset();
		sorter.doInsertionSort(clone);
		printPerformance("Insertion Sort", "FEW UNIQUE", clone.getReads(), clone.getWrites(), clone.getSpaces(), 1);
		insertionSortReads += clone.getReads();
		insertionSortWrites += clone.getWrites();
		insertionSortSpaces += clone.getSpaces();

		clone = fewUnique.clone();
		IntegerArray.reset();
		sorter.doSelectionSort(clone);
		printPerformance("Selection Sort", "FEW UNIQUE", clone.getReads(), clone.getWrites(), clone.getSpaces(), 1);
		selectionSortReads += clone.getReads();
		selectionSortWrites += clone.getWrites();
		selectionSortSpaces += clone.getSpaces();
		
		
		printPerformance("Bubble Sort", "Overall", bubbleSortReads, bubbleSortWrites, bubbleSortSpaces, 4);
		printPerformance("Insertion Sort", "Overall", insertionSortReads, insertionSortWrites, insertionSortSpaces, 4);
		printPerformance("Selection Sort", "Overall", selectionSortReads, selectionSortWrites, selectionSortSpaces, 4);
		
	}
	
	private static void printPerformance(String sort, String inputType, long reads, long writes, long spaces, int scale) {
		System.out.println(String.format("%s (%s)", sort, inputType));
		System.out.println(String.format("\t Reads / n^2 = " + FORMAT, reads / (float)(N * N * scale)));
		System.out.println(String.format("\tWrites / n^2 = " + FORMAT, writes / (float)(N * N * scale)));
		System.out.println(String.format("\t   R+W / n^2 = " + FORMAT, (reads + writes) / (float)(N * N * scale)));
		System.out.println(String.format("\tSpaces / n   = " + FORMAT, spaces / (float)(N * scale)));
		System.out.println();
	}
	
}
