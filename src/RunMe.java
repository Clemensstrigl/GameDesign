import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.plaf.synth.SynthSliderUI;

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
	ArrayList<Objects> objects;
	boolean firstClickAddingObjects = true, done = false, allDone = false;
	boolean addObjects = false;
	int Xstage = 0, Ystage, cStage;
	float XStartX, yPositionX, XEndX, XSpeedX, YStartY, xPositionY, YEndY, YSpeedY;
	boolean startXLinePath = false, startYLinePath = false;

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
		objects = new ArrayList<Objects>();

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
			if (keyCode == CONTROL && key == 's') {
				Design.Save();
			}

			if (key == 'o') {
				addObjects = true;
				designMode = false;
				count = 0;

			}

			if (addObjects == true && key == 'y') {
				YObjects = true;
				System.out.println("YObjects = true");
			}

			if (addObjects == true && key == 'x') {
				XObjects = true;
				System.out.println("XObjects = true");
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
		if(startXLinePath == true && addObjects == true) {
			drawXPathLine();
		}
		if(startYLinePath == true && addObjects == true) {
			drawYPathLine();
		}

		display.drawGrid(Design.getGrid()); // display the game
		if (designMode == false && addObjects == false) {
			if (count == 0) {
				play = new Play(Design.FindMiddleX(), Design.FindMiddleY(), this);
				count++;
			}
			if (objects.size() != 0) {
				displayAllObjects();

			}
			direction();
			if (objects.size() != 0) {
				displayAllObjects();
			}
			play.draw(display);
			play.move(direction);
			Design.setFalse();
			play.setGrid(grid);
//			for (int i = 0; i < objects.size(); i++) {
//				play.isHitting(objects.get(i));
//
//			}
			if (play.isHittingBarrier() == true) {
				play.restart();
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

	public void direction() {
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
		} else if (sPressed == true) {
			direction = 6;
		} else if (wPressed == true) {
			direction = 2;
		} else if (aPressed == true) {
			direction = 0;
		}
	}

	public void mousePressed() {
		if (designMode == true) {
			Location loc = display.gridLocationAt(mouseX, mouseY);
			Design.move(loc.getRow(), loc.getCol(), object);
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

//

	public void mouseReleased() {
		if (XObjects) {
			if (Xstage == 0) {
				startXPlacemnt();
				startXLinePath = true;
			} else if (Xstage == 1) {
				contiueXPlacement();
			} else if (Xstage == 2) {
				finishXPlacement();
			}
		}
		if (YObjects) {
			if (Ystage == 0) {
				startYPlacemnt();
				startYLinePath = true;
			} else if (Ystage == 1) {
				contiueYPlacement();
			} else if (Ystage == 2) {
				finishYPlacement();
			}
		}

	}

	private void finishYPlacement() {
		String response = JOptionPane.showInputDialog(
				"What do you want its YSPEED to be? Pls only write a hole number, this speed is derenmined by how many Pixels it moves. REMINDER this method runs 30 times a second");
		YSpeedY = Integer.parseInt(response);

		objects.add(new YMovingObjects(this, xPositionY, YStartY, YSpeedY, YEndY));
		YObjects = false;
		Ystage = 0;
		startYLinePath = false;


	}

	private void contiueYPlacement() {
		YEndY = mouseY;
		System.out.println("EndingY = " + YEndY);
		Ystage = 2;

	}

	private void startYPlacemnt() {
		YStartY = mouseY;
		System.out.println("StartinY = " + YStartY);
		xPositionY = mouseX;
		System.out.println("XPosition = " + xPositionY);
		Ystage = 1;
	}

	private void finishXPlacement() {

		String response = JOptionPane.showInputDialog(
				"What do you want its XSPEED to be? Pls only write a hole number, this speed is derenmined by how many Pixels it moves. REMINDER this method runs 30 times a second");
		XSpeedX = Integer.parseInt(response);

		objects.add(new XMovingObjects(this, XStartX, yPositionX, XSpeedX, XEndX));
		XObjects = false;
		Xstage = 0;
		startXLinePath = false;

	}

	private void contiueXPlacement() {
		XEndX = mouseX;
		System.out.println("XEndX = " + XEndX);
		Xstage = 2;
	}

	private void startXPlacemnt() {
		XStartX = mouseX;
		System.out.println("StartinX = " + XStartX);
		yPositionX = mouseY;
		System.out.println("YPosition= " + yPositionX);
		Xstage = 1;
	}

//	public void addCircularObject() {
//		float StartingX = -50;
//		float StartingY = -50;
//		float Radius = 0;
//		if(mousePressed && firstClickAddingObjects == true) {
//			 StartingX = mouseX;
//			 yPosition = mouseY;
//			firstClickAddingObjects = false;
//		}
//		
//		
//		String response = JOptionPane.showInputDialog("What do you want its RADIUS to be? Pls only write a hole number, this speed is derenmined by how many Pixels it moves. REMINDER this method runs 30 times a second");
//		 Radius = Integer.parseInt(response);
//
//		 
//		objects.add(new XMovingObjects(this,StartingX,StartingY,Radius,EndingX));
//		
//		
//	}

	public void displayAllObjects() {
		for (int i = 0; i < objects.size(); i++) {
			objects.get(i).draw();
			objects.get(i).move();
			objects.get(i).changeDirection();

		}
	}

	public void drawXPathLine() {
		
		line(XStartX, yPositionX, mouseX, yPositionX);

	}

	public void drawYPathLine() {
	
		line(xPositionY, YStartY, xPositionY, mouseY);
	}

}