import processing.core.*;

public class RunMe extends PApplet {
	int c;
	Design game;
	Display display;
	int object = 0;

	public void setup() {
		size(1200, 800); // set the size of the screen.

		// Create a game object
		game = new Design(40, 40);

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
if(keyPressed) {
			if(key == 'b') {
				object = 1;
			}
			if(key == 's') {
				object = 2;
			}
			if(key == 'e') {
				object = 3;
			}
			if(key == 't') {
				object = 4;
			}
		}
	
		display.initializeWithGame(game);
		c = 0;
		
	}
	@Override
	public void draw() {
		background(200);

		display.drawGrid(game.getGrid()); // display the game
	}

	public void mouseClicked() {
		Location loc = display.gridLocationAt(mouseX, mouseY);
		game.move(loc.getRow(), loc.getCol(), object);
		
	}

	// main method to launch this Processing sketch from computer
	public static void main(String[] args) {
		PApplet.main(new String[] { "RunMe" });
	}
}