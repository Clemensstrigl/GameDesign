import processing.core.PApplet;

public class Play {
	float x, y;
	PApplet window;
	Location t, b, r, l,m;
	int[][] grid;

	public Play(float x, float y, PApplet window) {
		this.x = x;
		this.y = y;
		this.window = window;
	}

	public void draw(Display display) {
		window.fill(window.color(255, 255, 0));
		window.ellipse(x, y, 18, 18);
		FindLocationTop(display);
		FindLocationBottom(display);
		FindLocationRight(display);
		FindLocationLeft(display);
		FindLocationMiddle(display);
	}

	public void move(int direction) {
		if (direction == 0) {
			x = x - 4;
		}
		if (direction == 1) {
			x = (float) (x - 2.5);
			y = (float) (y - 2.5);
		}
		if (direction == 2) {
			y = y - 4;

		}
		if (direction == 3) {
			x = (float) (x + 2.5);
			y = (float) (y - 2.5);
		}
		if (direction == 4) {
			x = x + 4;
		}
		if (direction == 5) {
			x = (float) (x + 2.5);
			y = (float) (y + 2.5);
		}
		if (direction == 6) {
			y = y + 4;

		}
		if (direction == 7) {
			x = (float) (x - 2.5);
			y = (float) (y + 2.5);
		}
		if (x < 0) {
			x = 0;
		}
		if (x > window.width) {
			x = window.width;
		}
		if (y < 0) {
			y = 0;
		}
		if (y > window.height) {
			y = window.height;
		}

	}
	
	public boolean isHittingBarrier() {
		if (isHittingTop() == true || isHittingBottom() == true || isHittingRight() == true
				|| isHittingLeft() == true) {
			return true;
		}
		return false;
	}
	
	

	private boolean isHittingLeft() {
		if (grid[gridYPositionLeft()][gridXPositionLeft()] == 1) {
			return true;
		}
		return false;
	}

	private boolean isHittingRight() {
		if (grid[gridYPositionRight()][gridXPositionRight()] == 1) {
			return true;
		}
		return false;
	}

	private boolean isHittingBottom() {
		if (grid[gridYPositionBottom()][gridXPositionBottom()] == 1) {
			return true;
		}
		return false;
	}

	private boolean isHittingTop() {
		if (grid[gridYPositionTop()][gridXPositionTop()] == 1) {
			return true;
		}
		return false;
	}
	
	public boolean isInEndingZone() {
		if (grid[gridXPositionMiddle()][gridXPositionMiddle()] == 3) {
			return true;
		}
		return false;
	}
	//find all x and y positions of the top, bottom, and both sides of the player's ball
	
	
	public int gridXPositionRight() {
		return r.getCol();
	}

	public int gridYPositionRight() {
		return r.getRow();
	}
	public int gridXPositionMiddle() {
		return m.getCol();
	}

	public int gridYPositionMiddle() {
		return m.getRow();
	}

	public int gridXPositionLeft() {
		return l.getCol();
	}

	public int gridYPositionLeft() {
		return l.getRow();
	}

	public int gridXPositionTop() {
		return t.getCol();
	}

	public int gridYPositionTop() {
		return t.getRow();
	}

	public int gridXPositionBottom() {
		return b.getCol();
	}

	public int gridYPositionBottom() {
		return b.getRow();
	}

	public Location FindLocationTop(Display d) {
		t = d.gridLocationAt(this.x - 9, this.y);
		return t;
	}

	public Location FindLocationBottom(Display d) {
		b = d.gridLocationAt(this.x + 9, this.y);
		return b;
	}

	public Location FindLocationRight(Display d) {
		r = d.gridLocationAt(this.x, this.y - 9);
		return r;
	}

	public Location FindLocationLeft(Display d) {
		l = d.gridLocationAt(this.x, this.y + 9);
		return l;
	}
	public Location FindLocationMiddle(Display d) {
		m = d.gridLocationAt(this.x, this.y);
		return m;
	}

	public void setGrid(int[][] grid) {
		this.grid = grid;
	}

}




