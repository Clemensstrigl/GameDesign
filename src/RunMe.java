import processing.core.*;

public class RunMe extends PApplet {
	int c;
	Design game;
	Display display;
	Play play;
	int object = 0;
	int direction;
	boolean aPressed, sPressed, dPressed, wPressed;
	boolean design = true;

	public void setup() {
		size(1200, 800); // set the size of the screen.

		// Create a game object
		game = new Design(40, 40, this);

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

		display.initializeWithGame(game);
		c = 0;

	}

	@Override
	public void draw() {
		background(200);
		if (keyPressed) {
			if (key == 'd') {
				design = true;
			}
			if (key == 'b' && design == true) {
				object = 1;
			}
			if (key == 's' && design == true) {
				object = 2;
			}
			if (key == 'e' && design == true) {
				object = 3;
			}
			if (key == 't' && design == true) {
				object = 4;
			}
			if (key == 'c' && design == true) {
				object = 0;

			}

			if (key == 'p' && design == false) {
				design = false;
				play = new Play(game.FindMiddleX(), game.FindMiddleY(), this);
			}
		}
		display.drawGrid(game.getGrid()); // display the game
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

		if (aPressed == true) {
			direction = 0;
		}
		if (aPressed == true && wPressed == true) {
			direction = 1;
		}
		if (wPressed == true) {
			direction = 2;
		}
		if (wPressed == true && dPressed == true) {
			direction = 3;
		}
		if (dPressed == true) {
			direction = 4;
		}
		if (dPressed == true && sPressed == true) {
			direction = 5;
		}
		if (sPressed == true) {
			direction = 6;
		}
		if (sPressed == true && aPressed == true) {
			direction = 7;
		}

		// play.move(direction);
	}

	public void keyReleased() {
		if (key == 'a') {
			aPressed = false;
		}
		if (key == 'd') {
			dPressed = false;
		}
		if (key == 's') {
			sPressed = false;
		}
		if (key == 'w') {
			wPressed = false;
		}
	}

	public void mousePressed() {
		if (design == true) {
			Location loc = display.gridLocationAt(mouseX, mouseY);
			game.move(loc.getRow(), loc.getCol(), object);
		}

	}

	public void mouseDragged() {
		if (design == true) {
			Location loc = display.gridLocationAt(mouseX, mouseY);
			game.move(loc.getRow(), loc.getCol(), object);
		}
	}

	// main method to launch this Processing sketch from computer
	public static void main(String[] args) {
		PApplet.main(new String[] { "RunMe" });
	}

}