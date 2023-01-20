import java.util.ArrayList;
import java.util.Scanner;

public class PathFinder {

	class CityNode {

		protected String name = "";
		protected int population = 0;
		protected ArrayList<CityNode> neighbourNodes;
		protected ArrayList<Double> neighbourDistances;

		public String toString() {
			//return String.format("CityNode:%s (pop: %d)", this.name, this.population);			
			return this.name;			
		}

	}

	final boolean VERBOSE = false;	
	final int MAX_DISTANCE_BETWEEN_CITIES = 100;
	double MAX_DISTANCE_OF_PATH = 400;

	CityNode origin;
	CityNode destination;
	ArrayList<CityNode> cities = new ArrayList<CityNode>();
	double[][] distanceData;

	long calls = 0;

	public static void main(String[] args) {
		PathFinder pathfinder = new PathFinder();
		pathfinder.run();
	}

	public void run() {

		buildGraph();
		ArrayList<CityNode> path;

		for (int i = 0; i < cities.size(); i++) {
			System.out.println(String.format("%3d: %s", i,  cities.get(i).toString()));
		}

		Scanner scanner = new Scanner(System.in);
		System.out.print("Starting city #?:");
		int startIndex = scanner.nextInt();
		System.out.print("Destination city #?:");
		int destinationIndex = scanner.nextInt();
		System.out.print("Maximum Path Length #?:");
		MAX_DISTANCE_OF_PATH = scanner.nextInt();


		CityNode startingCity = cities.get(startIndex);
		CityNode destinationCity = cities.get(destinationIndex);

		System.out.println("\nAlgorithm 0");
		path = findAPath(startingCity, destinationCity);

		System.out.println("\nAlgorithm 1");
		path = findAPath1(startingCity, destinationCity);

		System.out.println("\nAlgorithm 2");
		path = findAPath2(startingCity, destinationCity);

	}


	public ArrayList<CityNode> findAPath(CityNode start, CityNode goal) {
		calls = 0;
		ArrayList<CityNode> path = new ArrayList<CityNode>();
		path.add(start);
		boolean result = findAPath(start, goal, path);
		if (result == false) {
			path.remove(start);
		}
		return path;
	}

	//BRUTE FORCE ALGORITHM
	//
	//Will find a solution, regardless of length
	private boolean findAPath(CityNode current, CityNode goal, ArrayList<CityNode> currentPath) {

		calls++;

		if (current.neighbourNodes.contains(goal)) {
			//base case
			currentPath.add(goal);
			System.out.println(String.format("Solution: calls: %8d; length = %5.1f; path = %s" , calls, pathLength(currentPath), currentPath.toString()));
			return true;
		}
		else {
			//recursive case
			for (CityNode neighbour : current.neighbourNodes) {
				boolean haveNotVisited =currentPath.contains(neighbour) == false;
				if (haveNotVisited) {
					currentPath.add(neighbour);
					if (VERBOSE) {
						System.out.println(String.format("Step: calls: %8d; length = %5.1f; path = %s" , calls, pathLength(currentPath), currentPath.toString()));
					}
					boolean solved = findAPath(neighbour, goal, currentPath);
					if (solved) {
						return solved;
					}
					else {
						currentPath.remove(neighbour);
					}								
				}
			}
			return false;
		}		
	}


	//REFINED ALGORITHM #1
	//
	//Track the distance used, and compare it to a maximum; don't pursue paths that will be longer
	//This assumes that we know approximately how long the path should be!

	public ArrayList<CityNode> findAPath1(CityNode start, CityNode goal) {
		return null;
	}
	//REFINED ALGORITHM #2 - Track the distance left
	//
	//Track the distance used, and compare it to a maximum; don't pursue paths that will be longer
	//This assumes that we know approximately how long the path should be, and how far from each node to the destination

	public ArrayList<CityNode> findAPath2(CityNode start, CityNode goal) {
		return null;
	}
	private void buildGraph() {

		Object[][] cityData;
		Object[][] distanceData;

		cityData = CSVReader.importFromCSV("res/ab-cities.csv");
		distanceData = CSVReader.importFromCSV("res/ab-driving-distances.csv");

		//		cityData = CSVReader.importFromCSV("res/europe-cities.csv");
		//		distanceData = CSVReader.importFromCSV("res/europe-driving-distances.csv");

		this.distanceData = new double[distanceData.length][distanceData[0].length];

		for (int row = 0; row < cityData.length; row++) {
			CityNode city = new CityNode();
			city.name = (String) cityData[row][0];
			city.population = Integer.parseInt((String)cityData[row][1]);
			city.neighbourNodes = new ArrayList<CityNode>();
			city.neighbourDistances = new ArrayList<Double>();
			//			System.out.println(String.format("%s: %d", city.name, city.population));
			cities.add(city);
		}


		for (int row = 0; row < distanceData.length; row++) {
			for (int col = 0; col < distanceData[0].length; col++) {
				try {
					CityNode from = cities.get(row);
					CityNode to = cities.get(col);
					double distance = Double.parseDouble((String)distanceData[row][col]);
					this.distanceData[row][col] = distance;					
					if (distance > 0 && distance <= MAX_DISTANCE_BETWEEN_CITIES) {
						from.neighbourNodes.add(to);
						from.neighbourDistances.add(distance);
						//						System.out.println(String.format("%s - %s: %f", from.name, to.name, distance));				
					}
				} catch (NumberFormatException e) {
				}
			}			
		}		
	}

	private double pathLength(ArrayList<CityNode> path) {
		double length = 0;
		for (int i = 0; i < path.size() - 1; i++) {
			CityNode city1 = path.get(i);
			CityNode city2 = path.get(i+1);
			length+= distanceData[cities.indexOf(city1)][cities.indexOf(city2)];
		}
		return length;
	}

}
