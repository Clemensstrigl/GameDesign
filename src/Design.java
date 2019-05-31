

import processing.core.PApplet;

public class Design {
	private boolean gameOver; // flag to record if the game is over
	private int playerTurn; // whose turn it is
	private int winner; // who the winner is (0 if no winner)
	private int cols, rows; // # of rows and cols in game
	public int[][] grid; // the grid that stores the pieces
	int middleOfStartingZoneRow;
	int lowestCol, highestCol, lowestRow, highestRow;
	boolean firstSave = true;
	PApplet window;
	boolean findValue = true;
	int [][] origionalGrid;
	boolean load = false;
	float loadedStartingX, loadedStartingY;
	// The constructor initializes the game
	public Design(int r, int c, PApplet window) {
		this.cols = c;
		this.rows = r;
		grid = new int[r][c];
		gameOver = false;
		this.window = window;
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[0].length; col++) {
				if(row == 0 || row == 39) {
					grid[row][col] = 1;
				}
				grid[row][0] = 1;
				grid[row][grid[0].length-1] = 1;
			}
		}
		origionalGrid = grid;
	}

	/*
	 * Return true if r, c is a valid move for the game.
	 */
	public boolean isValidMove(int r, int c) {
		for(int i = 0; i < grid.length; i++) {
			if(r == 0 && c == i) {
				return false;
			}
		}
		for(int i = 0; i < grid.length; i++) {
			if(r == grid.length -1 && c == i) {
				return false;
			}
		}
		for(int i = 0; i < grid[0].length; i++) {
			if(r == i && c == 0) {
				return false;
			}
		}
		for(int i = 0; i < grid[0].length; i++) {
			if(r == i && c == grid.length - 1) {
				return false;
			}
		}
		
		
		if (isInGrid(r, c) == false) // if outside grid, not valid
			return false;

		return true; // otherwise it's valid
	}

	/*
	 * Return true if the location at row, col is in the bounds of the grid. Return
	 * false otherwise.
	 */
	public boolean isInGrid(int row, int col) {
		if (row > 39 || row < 1) {
			return false;
		}
		if (col > 39 || col < 1) {
			return false;

		}

		return true;
	}

	/*
	 * Return true if the location l is in the bounds of the grid. Note: this method
	 * calls the other isInGrid to do the work.
	 */
	public boolean isInGrid(Location l) {
		return isInGrid(l.getRow(), l.getCol());
	}

	

	// makes the move
	// returns false if no move was made, true if the move was successful.
	public boolean move(int r, int c, int color) {

		if (isValidMove(r, c) == false)
			return false;  
		if (isValidMove(r, c) == true ) {
			grid[r][c] = color;
		}
		if (color == 2 && firstSave == true) {
			lowestRow = r;
			lowestCol = c;
			highestRow = r;
			highestCol = c;
			firstSave = false;
		}
		if (color == 2 && r < lowestRow) {
			lowestRow = r + 1;
		}
		if (color == 2 && c < lowestCol) {
			lowestCol = c + 1;
		}
		if (color == 2 && r > highestRow) {
			highestRow = r + 1;

		}
		if (color == 2 && c > highestCol) {
			highestCol = c + 1;

		}

		return true; 
	}

	

	public int[][] getGrid() {
		return grid;
	}

	public void setFalse() {
		findValue = false;
	}

	public float FindMiddleY() {
		
		if (findValue == true && firstSave == false) {
			float middleY = (lowestRow + ((highestRow - lowestRow) / 2)) * (window.height / grid.length);	
			return middleY;
		}
		if(load == true) {
			return loadedStartingY;
		}
		return window.height/2;
	

	}

	public float FindMiddleX() {
		
		if (findValue == true && firstSave == false) {
			float middleX = (lowestCol + ((highestCol - lowestCol) / 2)) * (window.width / grid[0].length);
			return middleX;
		}
		if(load == true) {
			return loadedStartingX;
		}
		return window.width/2;
		
	}

	public int[][] origionalGrid() {
		
		return origionalGrid;
	}
	
	public void setStartingY(float Y) {
		
		this.loadedStartingY = Y;
	}
public void setStartingX(float X) {
		
		this.loadedStartingX = X;
	}

public void setLoadingPoints() {
		load = true;
}
}
