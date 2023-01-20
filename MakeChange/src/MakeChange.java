import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;


public class MakeChange {
	private static final int TOONIES_CENT = 200;
	private static final int LOONIES_CENT = 100;
	private static final int QUARTERS_CENT = 25;
	private static final int DIMES_CENT = 10;
	private static final int NICKELS_CENT = 5;
	
	
	public static void main(String[] args) throws IOException {
		double change;
		
		change = getInput();
		
		if (change == -1) {
			System.out.println("INVALID");
			
		} else {
			System.out.println(makeChange(change));
		}
		
	}
	
	public static double getInput() throws IOException {
			System.out.print("Enter change?:");
			
			BufferedReader reader = new BufferedReader(
	            new InputStreamReader(System.in));
	 
	        // Reading data using readLine
			double change;
			try {
				change = Double.parseDouble(reader.readLine());
				
				return change;
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				return -1.00;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				return 0;
			}
	        
	 
	        
	}
	
	public static String makeChange(double change) {
		change =  change * 100;
		change = NICKELS_CENT*(Math.round(change/NICKELS_CENT));
		
		
		int toonies = (int) (change / TOONIES_CENT);
		change = change % TOONIES_CENT;
		
		int loonies = (int) (change / LOONIES_CENT);
		change = change % LOONIES_CENT;
		
		int quarters = (int) (change / QUARTERS_CENT);
		change = change % QUARTERS_CENT;
		
		int dimes = (int) (change / DIMES_CENT);
		change = change % DIMES_CENT;
		
		int nickels = (int) (change / NICKELS_CENT);
		change = change % NICKELS_CENT;
		
		return String.format("toonies:%d; loonies:%d; quarters:%d; dimes:%d; nickels:%d", toonies, loonies, quarters, dimes, nickels);
		
	}
	
	
}
