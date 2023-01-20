/**
 * Leo Jenkins
 * CS20
 * GameOfLife
 * Jan-3-2022
 * 
 */
public class GameOfLife {
	private Boolean[][] gameBoard;
	private Boolean[][] nextGameBoard;
	private boolean gameStatic;
	
	private int totalRows;
	private int totalColumns;
	
	public GameOfLife(int rows, int columns) throws IllegalArgumentException {
		if (rows <= 0 || columns <= 0) {
			throw new IllegalArgumentException();
		} else {
		this.gameBoard = new Boolean[rows][columns];
		this.nextGameBoard = new Boolean[rows][columns];
		
		this.totalRows = rows;
		this.totalColumns = columns;
		}
	}
	
	public int getRows() {
		return this.totalRows;
	}
	
	public int getColumns() {
		return this.totalColumns;
	}
	
	public boolean isAlive(int row, int column) {
		try {
			return this.gameBoard[row][column];
		} catch (Exception e) {
			return false;
		}
	}
	
	public void setAlive(int row, int column, boolean status) {
		try {
			this.gameBoard[row][column] = status;
		} catch (Exception e) {
			//do nothing if errors
		}
	}
	
	public int getNeighbourCount(int row, int column) {
		//2d array of all possible neighbour relative position combinations
		int[][] neighbours = {{-1,0},{0,-1},{-1,-1},{0,1},{1,0},{1,1},{-1,1},{1,-1}};
		int neighbourCount = 0;
		
		for (int[] neighbour : neighbours) {
			//get neighbour cell
			int desiredRow = row + neighbour[0];
			int desiredColumn = column + neighbour[1];
			
			boolean cellStatus = isAlive(desiredRow,desiredColumn);
			if (cellStatus == true) {
				neighbourCount += 1;
			}
		}
		
		return neighbourCount;
	}
	
	public void calculateNextGeneration() {
		//loops through every cell checking neighbours then deciding wether to be alive
		
		for (int rows = 0; rows < this.totalRows; rows ++) {
			for (int columns = 0; columns < this.totalColumns; columns++) {
				if (isAlive(rows,columns)) {
					if (getNeighbourCount(rows,columns) == 2 | getNeighbourCount(rows,columns) == 3) {
						this.nextGameBoard[rows][columns] = true;
					}
					else {
						this.nextGameBoard[rows][columns] = false;
					}
				}
				else {
					if (getNeighbourCount(rows,columns) == 3) {
						this.nextGameBoard[rows][columns] = true;
					}
					else {
						this.nextGameBoard[rows][columns] = false;
					}
				}
			}
		}
		
		if (this.gameBoard == this.nextGameBoard) {
			this.gameStatic = true;
		} else {
			this.gameStatic = false;
		
		for (int rows = 0; rows < this.totalRows; rows++) {
			for (int columns = 0; columns < this.totalColumns; columns++) {
				this.gameBoard[rows][columns] = this.nextGameBoard[rows][columns];
			}
		}
		}
		
	}
	public String toString() {
		String returnString = "";
		
		for (int rows = 0; rows < this.totalRows; rows ++) {
			for (int columns = 0; columns < this.totalColumns; columns ++) {
				if(isAlive(rows, columns)) {
					returnString += "*";
				} else {
					returnString += " ";
				}
			} 
			returnString += "\r\n";
		}
		
		return returnString;
	}
	
	public Boolean isStatic() {
		return this.gameStatic;
	}
}
