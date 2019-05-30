import java.util.ArrayList;

import processing.core.PApplet;

public class Play {
	float x, y;
	PApplet window;
	Location t, b, r, l,m;
	int[][] grid;
	final float StartingX, StartingY;
	ArrayList<Objects> objects;

	public Play(float x, float y, PApplet window) {
		this.x = x;
		this.y = y;
		this.StartingX = x;
		this.StartingY = y;
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
// the eight directions that the ball can go and then the #8 for when it is stationary
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
	
	// return true when ball hits an barrier
	public boolean isHittingBarrier() {
		if (isHittingTop() == true || isHittingBottom() == true || isHittingRight() == true
				|| isHittingLeft() == true) {
			return true;
		}
		return false;
	}
	
	//is hitting for all the four spots it can

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
		if (grid[gridYPositionMiddle()][gridXPositionMiddle()] == 3) {
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
	
public void restart() {
	x = StartingX;
	y = StartingY;
}

public void setArray(ArrayList<Objects> objects) {
	this.objects = objects;
}

public boolean isXIntervalOverlapping(float x1, float wD, float xO, float wO) {
	if ((x1+wD)  <= (xO + wO) && (x1+wD) >= (xO - wO) || (x1-wD)  <= (xO + wO) && (x1-wD) >= (xO - wO)) {
		return true;
	}

	return false;
}

public boolean isYIntervalOverlapping(float y1, float hD, float yO, float hO) {
	if ((y1+hD)  <= (yO + hO) && (y1+hD) >= (yO - hO) || (y1-hD)  <= (yO + hO) && (y1-hD) >= (yO - hO)) {
		return true;

	}
	return false;
}


public boolean isHitting(Objects o) {
	
	if (isXIntervalOverlapping(this.x, 9, o.getX(), 10) == true
			&& isYIntervalOverlapping(this.y, 9, o.getY(), 10) == true) {
		return true;

	}
	
	return false;
}
}




