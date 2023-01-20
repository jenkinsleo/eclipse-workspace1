import junit.framework.TestCase;

public class IterativeSorterTest extends TestCase {

	private SorterUtilities utility = new SorterUtilities();
	private IterativeSorter sorter = new IterativeSorter();
	
	public void testBubbleSortRandom() {

		IntegerArray arrayToSort = utility.createRandomArray(10);
		long originalCheckSum = utility.checkSum(arrayToSort);
		sorter.doBubbleSort(arrayToSort);
		assertEquals(true, utility.verifySort(arrayToSort) );
		long sortedCheckSum = utility.checkSum(arrayToSort);
		assertEquals(originalCheckSum, sortedCheckSum);
		
		arrayToSort = utility.createRandomArray(1000);
		sorter.doBubbleSort(arrayToSort);
		assertEquals(true, utility.verifySort(arrayToSort) );
		

	}

	public void testBubbleSortNearlySorted() {

		IntegerArray arrayToSort = utility.createNearlySortedArray(10);
		sorter.doBubbleSort(arrayToSort);
		assertEquals(true, utility.verifySort(arrayToSort) );
		
		arrayToSort = utility.createNearlySortedArray(1000);
		sorter.doBubbleSort(arrayToSort);
		assertEquals(true, utility.verifySort(arrayToSort) );		
	}

	public void testBubbleSortFewUniqueArray() {

		IntegerArray arrayToSort = utility.createFewUniqueArray(10);
		sorter.doBubbleSort(arrayToSort);
		assertEquals(true, utility.verifySort(arrayToSort) );
		
		arrayToSort = utility.createFewUniqueArray(1000);
		sorter.doBubbleSort(arrayToSort);
		assertEquals(true, utility.verifySort(arrayToSort) );		
	}
	
	public void testBubbleSortReverseOrder() {

		IntegerArray arrayToSort = utility.createReverseOrderArray(10);
		sorter.doBubbleSort(arrayToSort);
		assertEquals(true, utility.verifySort(arrayToSort) );
		
		arrayToSort = utility.createReverseOrderArray(1000);
		sorter.doBubbleSort(arrayToSort);
		assertEquals(true, utility.verifySort(arrayToSort) );		
	}

	public void testSelectionSortRandom() {

		IntegerArray arrayToSort = utility.createRandomArray(10);
		long originalCheckSum = utility.checkSum(arrayToSort);
		sorter.doSelectionSort(arrayToSort);
		assertEquals(true, utility.verifySort(arrayToSort) );
		long sortedCheckSum = utility.checkSum(arrayToSort);
		
		assertEquals(originalCheckSum, sortedCheckSum);
		
		arrayToSort = utility.createRandomArray(1000);
		sorter.doSelectionSort(arrayToSort);
		assertEquals(true, utility.verifySort(arrayToSort) );		
	}

	public void testSelectionSortNearlySorted() {

		IntegerArray arrayToSort = utility.createNearlySortedArray(10);
		sorter.doSelectionSort(arrayToSort);
		assertEquals(true, utility.verifySort(arrayToSort) );
		
		arrayToSort = utility.createNearlySortedArray(1000);
		sorter.doSelectionSort(arrayToSort);
		assertEquals(true, utility.verifySort(arrayToSort) );		
	}

	public void testSelectionSortFewUniqueArray() {

		IntegerArray arrayToSort = utility.createFewUniqueArray(10);
		sorter.doSelectionSort(arrayToSort);
		assertEquals(true, utility.verifySort(arrayToSort) );
		
		arrayToSort = utility.createFewUniqueArray(1000);
		sorter.doSelectionSort(arrayToSort);
		assertEquals(true, utility.verifySort(arrayToSort) );		
	}
	
	public void testSelectionSortReverseOrder() {

		IntegerArray arrayToSort = utility.createReverseOrderArray(10);
		sorter.doSelectionSort(arrayToSort);
		assertEquals(true, utility.verifySort(arrayToSort) );
		
		arrayToSort = utility.createReverseOrderArray(1000);
		sorter.doSelectionSort(arrayToSort);
		assertEquals(true, utility.verifySort(arrayToSort) );		
	}
	
	public void testInsertionSortRandom() {

		IntegerArray arrayToSort = utility.createRandomArray(10);
		long originalCheckSum = utility.checkSum(arrayToSort);
		sorter.doInsertionSort(arrayToSort);
		assertEquals(true, utility.verifySort(arrayToSort) );
		long sortedCheckSum = utility.checkSum(arrayToSort);
		assertEquals(originalCheckSum, sortedCheckSum);
		
		arrayToSort = utility.createRandomArray(1000);
		originalCheckSum = utility.checkSum(arrayToSort);
		sorter.doInsertionSort(arrayToSort);
		System.out.println(arrayToSort);
		assertEquals(true, utility.verifySort(arrayToSort) );		
		sortedCheckSum = utility.checkSum(arrayToSort);
		assertEquals(originalCheckSum, sortedCheckSum);
	}

	public void testInsertionSortNearlySorted() {

		IntegerArray arrayToSort = utility.createNearlySortedArray(10);
		sorter.doInsertionSort(arrayToSort);
		assertEquals(true, utility.verifySort(arrayToSort) );
		
		arrayToSort = utility.createNearlySortedArray(1000);
		sorter.doInsertionSort(arrayToSort);
		
		assertEquals(true, utility.verifySort(arrayToSort) );		
	}

	public void testInsertionSortFewUniqueArray() {

		IntegerArray arrayToSort = utility.createFewUniqueArray(10);
		sorter.doInsertionSort(arrayToSort);
		assertEquals(true, utility.verifySort(arrayToSort) );
		
		arrayToSort = utility.createFewUniqueArray(1000);
		sorter.doInsertionSort(arrayToSort);
		assertEquals(true, utility.verifySort(arrayToSort) );		
	}
	
	public void testInsertionSortReverseOrder() {

		IntegerArray arrayToSort = utility.createReverseOrderArray(10);
		sorter.doInsertionSort(arrayToSort);
		assertEquals(true, utility.verifySort(arrayToSort) );
		
		arrayToSort = utility.createReverseOrderArray(1000);
		sorter.doInsertionSort(arrayToSort);
		assertEquals(true, utility.verifySort(arrayToSort) );		
	}
	
}
