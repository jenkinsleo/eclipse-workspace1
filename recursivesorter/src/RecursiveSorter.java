public class RecursiveSorter {
	
	public void doQuickSort(IntegerArray arrayToSort) {
        quickSort(arrayToSort, 0, arrayToSort.length() - 1);
    }

    private void quickSort(IntegerArray arrayToSort, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arrayToSort, low, high);
            quickSort(arrayToSort, low, pivotIndex);
            quickSort(arrayToSort, pivotIndex + 1, high);
        }
    }

    private int partition(IntegerArray arrayToSort, int low, int high) {
        int pivot = arrayToSort.read(low);
        int i = low - 1;
        int j = high + 1;

        while (true) {
            do {
                i++;
            } while (arrayToSort.read(i) < pivot);
            do {
                j--;
            } while (arrayToSort.read(j) > pivot);

            if (i >= j) {
                return j;
            }

            swap(arrayToSort, i, j);
        }
    }

    private void swap(IntegerArray arrayToSort, int i, int j) {
        int temp = arrayToSort.read(i);
        arrayToSort.write(i, arrayToSort.read(j));
        arrayToSort.write(j, temp);
    }

    public void doMergeSort(IntegerArray arrayToSort) {
        mergeSort(arrayToSort, 0, arrayToSort.length() - 1);
    }

    private void mergeSort(IntegerArray arrayToSort, int start, int end) {
        if (start < end) {
            int middle = (start + end) / 2;
            mergeSort(arrayToSort, start, middle);
            mergeSort(arrayToSort, middle + 1, end);
            merge(arrayToSort, start, middle, end);
        }
    }

    private void merge(IntegerArray arrayToSort, int start, int middle, int end) {
        int leftSize = middle - start + 1;
        int rightSize = end - middle;
        int[] leftArray = new int[leftSize];
        int[] rightArray = new int[rightSize];

        for (int i = 0; i < leftSize; i++) {
            leftArray[i] = arrayToSort.read(start + i);
        }
        for (int j = 0; j < rightSize; j++) {
            rightArray[j] = arrayToSort.read(middle + 1 + j);
        }

        int i = 0, j = 0, k = start;

        while (i < leftSize && j < rightSize) {
            if (leftArray[i] <= rightArray[j]) {
                arrayToSort.write(k, leftArray[i]);
                i++;
            } else {
                arrayToSort.write(k, rightArray[j]);
                j++;
            }
            k++;
        }

        while (i < leftSize) {
            arrayToSort.write(k, leftArray[i]);
            i++;
            k++;
        }

        while (j < rightSize) {
            arrayToSort.write(k, rightArray[j]);
            j++;
            k++;
        }
    }
}



