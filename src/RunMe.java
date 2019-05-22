import java.util.ArrayList;

import processing.core.*;

public class RunMe extends PApplet {
	int c;
	Design Design;
	Display display;
	Play play;
	int object = 0;
	int direction = 8;
	int count = 0;
	boolean aPressed, sPressed, dPressed, wPressed;
	boolean YObjects = false, XObjects = false, CircleObjects = false;
	boolean designMode = true;
	int[][] grid;
	ArrayList<Object> objects;
	boolean firstClickAddingObjects = true;
	boolean addObjects = false;

	public void setup() {
		size(1200, 800); // set the size of the screen.

		// Create a game object
		Design = new Design(40, 40, this);

		// Create the display
		// parameters: (10,10) is upper left of display
		// (300, 300) is the width and height
		display = new Display(this, 0, 0, width, height);

		display.setColor(1, 0xFFFE1D1D); // barrier
		display.setColor(2, 0xFF47F028); // Starting zone
		display.setColor(3, 0xFF51FB74); // Ending zone
		display.setColor(4, 0xFF28F0E3); // Safe zone
		// You can use images instead if you'd like.
		// d.setImage(1, "c:/data/ball.jpg");
		// d.setImage(2, "c:/data/cone.jpg");

		display.initializeWithGame(Design);
		c = 0;

	}

	public void draw() {
		background(200);
		grid = Design.getGrid();
		if (keyPressed) {
			if (key == 'i') {
				designMode = true;
				addObjects = true;
				count = 0;
			}

			if (key == 'o') {
				addObjects = true;
			}

			if (addObjects == true && key == 'y') {
				YObjects = true;
			}

			if (addObjects == true && key == 'x') {
				XObjects = true;
			}

			if (addObjects == true && key == 'z') {
				CircleObjects = true;
			}

			if (key == 'b' && designMode == true) {
				object = 1;
			}
			if (key == 's' && designMode == true) {
				object = 2;
			}
			if (key == 'e' && designMode == true) {
				object = 3;
			}
			if (key == 't' && designMode == true) {
				object = 4;
			}
			if (key == 'c' && designMode == true) {
				object = 0;

			}

			if (key == 'p') {
				designMode = false;
				addObjects = false;
				direction = 8;
			}

		}

		display.drawGrid(Design.getGrid()); // display the game

		if (designMode == false && addObjects == false) {
			if (count == 0) {
				play = new Play(Design.FindMiddleX(), Design.FindMiddleY(), this);
				count++;
			}
			play.draw(display);
			play.move(direction);
			Design.setFalse();
			play.setGrid(grid);
			if (play.isHittingBarrier() == true) {
				System.exit(0);
			}
			if (play.isInEndingZone() == true) {
				System.out.println("you are the winner gj");
				System.exit(0);
			}
		}

	}

	public void keyPressed() {
		if (key == 'a') {
			aPressed = true;
		}
		if (key == 's') {
			sPressed = true;
		}
		if (key == 'd') {
			dPressed = true;
		}
		if (key == 'w') {
			wPressed = true;
		}

		if (sPressed == true && aPressed == true) {
			direction = 7;
		} else if (aPressed == true && wPressed == true) {
			direction = 1;
		} else if (dPressed == true && sPressed == true) {
			direction = 5;
		} else if (wPressed == true && dPressed == true) {
			direction = 3;
		} else if (dPressed == true) {
			direction = 4;
		}

		else if (sPressed == true) {
			direction = 6;
		}else if (wPressed == true) {
			direction = 2;
		}
		else if (aPressed == true) {
			direction = 0;
		}

	}

	public void keyReleased() {
		if (key == 'a') {
			aPressed = false;
			direction = 8;
		}
		if (key == 'd') {
			dPressed = false;
			direction = 8;
		}
		if (key == 's') {
			sPressed = false;
			direction = 8;
		}
		if (key == 'w') {
			wPressed = false;
			direction = 8;
		}
	}

	public void mousePressed() {
		if (designMode == true) {
			Location loc = display.gridLocationAt(mouseX, mouseY);
			Design.move(loc.getRow(), loc.getCol(), object);
		}
		if (addObjects = true) {
			if (firstClickAddingObjects == true && YObjects == true) {
				// objects.add();
			}
		}

	}

	public void mouseDragged() {
		if (designMode == true) {
			Location loc = display.gridLocationAt(mouseX, mouseY);
			Design.move(loc.getRow(), loc.getCol(), object);
		}
	}

	// main method to launch this Processing sketch from computer
	public static void main(String[] args) {
		PApplet.main(new String[] { "RunMe" });
	}

	public void addObjects() {

	}

}