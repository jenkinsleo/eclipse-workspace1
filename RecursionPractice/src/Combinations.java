import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Combinations {

	public static void main(String[] args) {

		ArrayList<String> cities = new ArrayList<String>();
		cities.add("Amsterdam");
		cities.add("Brussels");
		cities.add("Ulaan bataar");
		cities.add("Dublin");	
		cities.add("Edinburgh");	
		printPermutations(cities);
		System.out.println();

		System.out.println(cities.toString());

	}

	private static void printPermutations(ArrayList<String> elements) {
		printPermutations(new ArrayList<String>(), elements);
		
		
	}
		
	
	private static void printPermutations(ArrayList<String> finished, ArrayList<String> remaining) {

		if (remaining.size() == 0) {
			//BASE CASE
			
			//note: you can print out a result by using the toString() method on the finished arrayList
			System.out.println(finished.toString());
		}
		else {
			//RECURSIVE CASE
			//The basic idea is to move items (in turn) from the remaining list to the finished
			//list...
			//note: you will need to make clones of the two parameters passed
			ArrayList<String> remainingClone = (ArrayList<String>) remaining.clone();
			ArrayList<String> finishedClone = (ArrayList<String>) finished.clone();
			
			finishedClone.add(remaining.get(0));
			remainingClone.remove(remaining.get(0));

			printPermutations(finishedClone, remainingClone);
			//Why? Because ArrayLists are objects, they can have many object references pointing to them. If the different levels of recursion all
			//operated on the same list, there would be all sorts of problems if two
			//methods tried to modify the same lists...
		}
	}
}