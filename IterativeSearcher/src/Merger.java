
public class Merger {

	public int[] doMerge(int[] arrayA, int[] arrayB) {
		if (arrayB == null && arrayA == null) {
			return new int[0];
		}
		
		if (arrayA == null) {
			return arrayB;
		} 
		if (arrayB == null) {
			return arrayA;
		} 
		
		int[] mergedArray = new int[arrayA.length + arrayB.length];
		int i=0, j=0, k=0;
		
		while (i < arrayA.length && j < arrayB.length)
		{
			if (arrayA[i] < arrayB[j]) {
				mergedArray[k] = arrayA[i];
				i++;
				k++;
			} else {
				mergedArray[k] = arrayB[j];
				j++;
				k++;
			}
		}
		
		while (i < arrayA.length)
		{
			mergedArray[k] = arrayA[i];
			i++;
			k++;
		}
		
		while (j < arrayB.length)
		{
			mergedArray[k] = arrayB[j];
			j++;
			k++;
		}
		return mergedArray;
	}

}
