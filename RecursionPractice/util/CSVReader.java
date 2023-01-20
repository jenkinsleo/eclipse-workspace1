import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVReader {
			
	public static Object[][] importFromCSV(String fileName) {

		File file = new File(fileName);
		ArrayList<String> rows = new ArrayList<String>();

		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e1) {
			return null;
		} 

		String input;
		try {
			while ((input = br.readLine()) != null) {
				rows.add(input);
			}
		} catch (IOException e) {
		}			
		
		int rowCount = rows.size();
		if (rowCount == 0) {
			return null;
		}
		else {
			String[] fields = rows.get(0).split("\\s*,\\s*");
			int columnCount = fields.length;
			
			String[][] csv = new String[rowCount][columnCount];
			
			for (int row = 0; row < rowCount; row++) {
				fields = rows.get(row).split("\\s*,\\s*");
				for (int column = 0; column < columnCount; column++) {
					csv[row][column] = fields[column];
				}
			}
			return csv;
		}		
	}
	
}
