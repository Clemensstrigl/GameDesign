import java.util.ArrayList;

import javax.swing.JOptionPane;

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
			if(keyCode == CONTROL && key == 's') {
				Design.Save();
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
			direction();
			if(objects.size() != 0) {
			displayAllObjects();
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
		}
		else if (sPressed == true) {
			direction = 6;
		} else if (wPressed == true) {
			direction = 2;
		}
		else if (aPressed == true) {
			direction = 0;
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

	public void addXObject() {
		if(XObjects == true) {
		float StartingX = -50;
		float EndingX = -50;
		float yPosition = -50;
		float XSpeed = 0;
		if(mousePressed && firstClickAddingObjects == true) {
			 StartingX = mouseX;
			 System.out.println("StartinX = " +StartingX);
			 yPosition = mouseY;
			 System.out.println("YPosition= " + yPosition);
			firstClickAddingObjects = false;
		}
		if(mousePressed && firstClickAddingObjects == false) {
			 EndingX = mouseX;
			firstClickAddingObjects = true;
		}
		
		String response = JOptionPane.showInputDialog("What do you want its XSPEED to be? Pls only write a hole number, this speed is derenmined by how many Pixels it moves. REMINDER this method runs 30 times a second");
		 XSpeed = Integer.parseInt(response);

		 
		objects.add(new XMovingObjects(this,StartingX,yPosition,XSpeed,EndingX));
		XObjects = false;
	}
		
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

	public void addYObject() {
		if(YObjects == true) {
		float StartingY = -50;
		float EndingY = -50;
		float xPosition = -50;
		float YSpeed = 0;
		if(mousePressed && firstClickAddingObjects == true) {
			 StartingY = mouseY;
			 System.out.println("StartinY = " +StartingY);
			 xPosition = mouseX;
			 System.out.println("XPosition = " +xPosition);

			firstClickAddingObjects = false;
		}
		if(mousePressed && firstClickAddingObjects == false) {
			 EndingY = mouseY;
			 System.out.println("EndingY = "+EndingY);

			firstClickAddingObjects = true;
		}
		
		String response = JOptionPane.showInputDialog("What do you want its YSPEED to be? Pls only write a hole number, this speed is derenmined by how many Pixels it moves. REMINDER this method runs 30 times a second");
		 YSpeed = Integer.parseInt(response);

		 
		objects.add(new YMovingObjects(this,StartingY,xPosition,YSpeed,EndingY));
		YObjects = false;
		}
		
	}
	
	public void displayAllObjects() {
		for(int i = 0; i<objects.size();i++) {
			 objects.get(i).draw();
			 objects.get(i).move();
			 objects.get(i).changeDirection();
			
		}
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}