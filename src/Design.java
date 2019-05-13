public class Design {
	private boolean gameOver; // flag to record if the game is over
	private int playerTurn; // whose turn it is
	private int winner; // who the winner is (0 if no winner)
	private int cols, rows; // # of rows and cols in game
	private int[][] grid; // the grid that stores the pieces

	// The constructor initializes the game
	public Design(int r, int c) {
		// Create the board
		this.cols = c;
		this.rows = r;
		grid = new int[r][c];
		gameOver = false;
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
	public boolean move(int r, int c, int o) {
		if (isValidMove(r, c) == false)
			return false; // if not valid, exit
		if (gameOver == true)
			return false; // if game is over, exit
		if (isValidMove(r, c) == true) {
			if (grid[r][c] == 0) {
				grid[r][c] = o;
			}
		
		}
		/* Make the move by changing what you need to change in grid */

		// check for the winner

		return true; // this means the move was successfully made
	}

	/*
	 * Return true if the game is over. False otherwise.
	 */


	public int[][] getGrid() {
		return grid;
	}
}
