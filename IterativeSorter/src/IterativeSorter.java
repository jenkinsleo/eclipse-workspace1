public class IterativeSorter {
	
	public void doBubbleSort(IntegerArray arrayToSortayToSort) {
		int n = arrayToSortayToSort.length();
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (arrayToSortayToSort.read(j) > arrayToSortayToSort.read(j + 1)) {

                	int temp = arrayToSortayToSort.read(j);
                    arrayToSortayToSort.write(j, arrayToSortayToSort.read(j+1));
                    arrayToSortayToSort.write(j+1, temp);
                }
		
	}

	public void doSelectionSort(IntegerArray arrayToSort) {
		int n = arrayToSort.length();
		 
        for (int i = 0; i < n; i++)
        {
            int min_idx = i;
            for (int j = i+1; j < n; j++)
                if (arrayToSort.read(j) < arrayToSort.read(min_idx))
                    min_idx = j;
 
            
            int temp = arrayToSort.read(min_idx);
            arrayToSort.write(min_idx, arrayToSort.read(i));
            arrayToSort.write(i, temp);
        }
	}

	public void doInsertionSort(IntegerArray array) {
		int size = array.length();

	    for (int step = 0; step < size; step++) {
	      int key = array.read(step);
	      int j = step - 1;

	      
	      while (j >= 0 && key < array.read(j)) {
	        array.write(j+1, array.read(j));;
	        j--;
	      }

	      array.write(j+1, key);;
	    }
		
	}

}
