//2020-12-15

import junit.framework.TestCase;

public class RecursiveSorterTest extends TestCase {

	private SorterUtilities utility = new SorterUtilities();
	private RecursiveSorter sorter = new RecursiveSorter();
	
	public void testQuickSortRandom() {

		IntegerArray arrayToSort = utility.createRandomArray(10);
		long originalCheckSum = utility.checkSum(arrayToSort);

		sorter.doQuickSort(arrayToSort);
		assertEquals(true, utility.verifySort(arrayToSort) );		
		long sortedCheckSum = utility.checkSum(arrayToSort);
		assertEquals(originalCheckSum, sortedCheckSum);
		
		arrayToSort = utility.createRandomArray(1000);
		originalCheckSum = utility.checkSum(arrayToSort);
		sorter.doQuickSort(arrayToSort);
		assertEquals(true, utility.verifySort(arrayToSort) );		
		sortedCheckSum = utility.checkSum(arrayToSort);
		assertEquals(originalCheckSum, sortedCheckSum);
	}

	public void testQuickSortNearlySorted() {

		IntegerArray arrayToSort = utility.createNearlySortedArray(10);
		sorter.doQuickSort(arrayToSort);
		assertEquals(true, utility.verifySort(arrayToSort) );
		
		arrayToSort = utility.createNearlySortedArray(1000);
		sorter.doQuickSort(arrayToSort);
		assertEquals(true, utility.verifySort(arrayToSort) );		
	}

	public void testQuickSortFewUniqueArray() {

		IntegerArray arrayToSort = utility.createFewUniqueArray(10);
		sorter.doQuickSort(arrayToSort);
		assertEquals(true, utility.verifySort(arrayToSort) );
		
		arrayToSort = utility.createFewUniqueArray(1000);
		sorter.doQuickSort(arrayToSort);
		assertEquals(true, utility.verifySort(arrayToSort) );		
	}
	
	public void testQuickSortReverseOrder() {

		IntegerArray arrayToSort = utility.createReverseOrderArray(10);
		long originalCheckSum = utility.checkSum(arrayToSort);
		sorter.doQuickSort(arrayToSort);
		assertEquals(true, utility.verifySort(arrayToSort) );
		long sortedCheckSum = utility.checkSum(arrayToSort);
		assertEquals(originalCheckSum, sortedCheckSum);
		
		arrayToSort = utility.createReverseOrderArray(1000);
		sorter.doQuickSort(arrayToSort);
		assertEquals(true, utility.verifySort(arrayToSort) );		
	}

	public void testMergeSortRandom() {

		IntegerArray arrayToSort = utility.createRandomArray(10);		
		long originalCheckSum = utility.checkSum(arrayToSort);
		sorter.doMergeSort(arrayToSort);
		assertEquals(true, utility.verifySort(arrayToSort) );
		long sortedCheckSum = utility.checkSum(arrayToSort);
		assertEquals(originalCheckSum, sortedCheckSum);
		
		arrayToSort = utility.createRandomArray(1000);
		originalCheckSum = utility.checkSum(arrayToSort);
		sorter.doMergeSort(arrayToSort);
		assertEquals(true, utility.verifySort(arrayToSort) );		
		sortedCheckSum = utility.checkSum(arrayToSort);
		assertEquals(originalCheckSum, sortedCheckSum);
	}

	public void testMergeSortNearlySorted() {

		IntegerArray arrayToSort = utility.createNearlySortedArray(10);
		sorter.doMergeSort(arrayToSort);
		assertEquals(true, utility.verifySort(arrayToSort) );
		
		arrayToSort = utility.createNearlySortedArray(1000);
		sorter.doMergeSort(arrayToSort);
		assertEquals(true, utility.verifySort(arrayToSort) );		
	}

	public void testMergeSortFewUniqueArray() {

		IntegerArray arrayToSort = utility.createFewUniqueArray(10);
		sorter.doMergeSort(arrayToSort);
		assertEquals(true, utility.verifySort(arrayToSort) );
		
		arrayToSort = utility.createFewUniqueArray(1000);
		sorter.doMergeSort(arrayToSort);
		assertEquals(true, utility.verifySort(arrayToSort) );		
	}
	
	public void testMergeSortReverseOrder() {

		IntegerArray arrayToSort = utility.createReverseOrderArray(10);
		sorter.doMergeSort(arrayToSort);
		assertEquals(true, utility.verifySort(arrayToSort) );
		
		arrayToSort = utility.createReverseOrderArray(1000);
		sorter.doMergeSort(arrayToSort);
		assertEquals(true, utility.verifySort(arrayToSort) );		
	}
			
}
