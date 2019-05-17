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

	// The constructor initializes the game
	public Design(int r, int c, PApplet window) {
		this.cols = c;
		this.rows = r;
		grid = new int[r][c];
		gameOver = false;
		this.window = window;
	}

	/*
	 * Return true if r, c is a valid move for the game.
	 */
	public boolean isValidMove(int r, int c) {
		if (isInGrid(r, c) == false) // if outside grid, not valid
			return false;

		return true; // otherwise it's valid
	}

	/*
	 * Return true if the location at row, col is in the bounds of the grid. Return
	 * false otherwise.
	 */
	public boolean isInGrid(int row, int col) {
		if (row > 40 || row < 0) {
			return false;
		}
		if (col > 40 || col < 0) {
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
			return false; // if not valid, exit
		if (gameOver == true)
			return false; // if game is over, exit
		if (isValidMove(r, c) == true) {
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
			lowestRow = r +1;
		}
		if (color == 2 && c < lowestCol) {
			lowestCol = c +1;
		}
		if (color == 2 && r > highestRow) {
			highestRow = r +1;

		}
		if (color == 2 && c > highestCol) {
			highestCol = c +1;

		}

		return true; // this means the move was successfully made
	}

	/*
	 * Return true if the game is over. False otherwise.
	 */

	public int[][] getGrid() {
		return grid;
	}

	public void Save() {

	}

	public void setFalse() {
		findValue = false;
	}

	public float FindMiddleY() {
		float middleY = window.height/2;
		if (findValue == true) {
			middleY = (lowestRow + ((highestRow - lowestRow) / 2)) * (window.height / grid.length);
		}
		return middleY;

	}

	public float FindMiddleX() {
		float middleX = window.width/2;
		if (findValue == true) {
			middleX = (lowestCol + ((highestCol - lowestCol) / 2)) * (window.width / grid[0].length);
		}
		return middleX;
	}
}
