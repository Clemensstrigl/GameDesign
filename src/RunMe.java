import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;
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
	int Xstage = 0, Ystage = 0, CStage = 0;
	float XStartX, yPositionX, XEndX, XSpeedX, YStartY, xPositionY, YEndY, YSpeedY, xPositionC,yPositionC,Radius,CSpeedC;
	boolean startXLinePath = false, startYLinePath = false;
	String option;

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
			for (int i = 0; i < objects.size(); i++) {
				if(play.isHitting(objects.get(i)) == true && grid[play.gridYPositionMiddle()][play.gridXPositionMiddle()] != 4) {
					play.restart();
				}

			}
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
		if (keyCode == LEFT) {
			aPressed = true;
		}
		if (keyCode == DOWN) {
			sPressed = true;
		}
		if (keyCode == RIGHT) {
			dPressed = true;
		}
		if (keyCode == UP) {
			wPressed = true;
		}

	}

	public void keyReleased() {
		if (keyCode == LEFT) {
			aPressed = false;
			direction = 8;
		}
		if (keyCode == RIGHT) {
			dPressed = false;
			direction = 8;
		}
		if (keyCode == DOWN) {
			sPressed = false;
			direction = 8;
		}
		if (keyCode == UP) {
			wPressed = false;
			direction = 8;
		}
		if (key == 'S') {
			Save();
		}
		if(key == 'l') {
			load();
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

//	private void finishCirclePlacement() {
//
//		String response = JOptionPane.showInputDialog(
//				"What do you want its CSPEED to be? Pls only write a hole number, this speed is derenmined by how many Pixels it moves. REMINDER this method runs 30 times a second");
//		CSpeedC = Integer.parseInt(response);
//
//		objects.add(new XMovingObjects(this, , yPositionX, XSpeedX, XEndX));
//		CircleObjects = false;
//		CStage = 0;
//		startRadiusLinePath = false;
//
//	}
//
//	private void contiueCirclePlacement() {
//		float CalcRadius = 0;
//		
//		if(xPositionC < mouseX) {
//			CalcRadius = mouseX - xPositionC;
//		}
//		if(xPositionC > mouseX) {
//			CalcRadius = xPositionC - mouseX;
//		}
//		
//		Radius = CalcRadius;
//		System.out.println("XEndX = " + XEndX);
//		CStage = 2;
//	}
//
//	private void startCiclePlacemnt() {
//		xPositionC = mouseX;
//		System.out.println("StartinX = " + xPositionC);
//		yPositionC = mouseY;
//		System.out.println("YPosition= " + yPositionC);
//		CStage = 1;
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
	
	public void Save() {
		String response = JOptionPane.showInputDialog( "What do you want to call your Game that will be saved?");
		try {
			PrintWriter out = new PrintWriter(new FileWriter("D:/Eclipse/Projects/Game Design/SavedGame/" + response + ".txt"));
			out.println(objects.size() + "," + Design.FindMiddleX() + "," + Design.FindMiddleY());
			for(int i = 0; i< objects.size(); i++) {
				out.println(objects.get(i).getStartingX() + "," + objects.get(i).getStartingY() + "," + objects.get(i).getSpeed() + "," + objects.get(i).getEndPosition() + "," + objects.get(i).whichObject());
			}
			for(int r = 0; r< grid.length; r++) {
				for(int c = 0; c< grid[0].length; c++) {
				out.print(grid[r][c] + ",");
			}
			out.println();
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public void load() {
		grid = Design.origionalGrid();
		objects.clear();
		JFileChooser jfc = new JFileChooser("D:\\Eclipse\\Projects\\Game Design",FileSystemView.getFileSystemView());
		int returnValue = jfc.showOpenDialog(null);
		// int returnValue = jfc.showSaveDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();
			option  = selectedFile.getAbsolutePath();
			grid = Design.origionalGrid();
			objects.clear();
		}
		
		try {
			BufferedReader in = new BufferedReader(new FileReader(option));
			
			String numString = in.readLine();
			String[] StartingVals = numString.split(",");
			int num = Integer.parseInt(StartingVals[0]);
			Design.setStartingX(Float.parseFloat(StartingVals[1])); 
			Design.setStartingY(Float.parseFloat(StartingVals[2])); 

			
			for (int i = 0; i < num; i++) {
				String line = in.readLine();
				String[] vals = line.split(",");
				float StartinX = Float.parseFloat(vals[0]);
				float StartinY = Float.parseFloat(vals[1]);
				float Speed = Float.parseFloat(vals[2]);
				float EndPosition = Float.parseFloat(vals[3]);
				float whichObject = Float.parseFloat(vals[4]);
				
				if(whichObject == 1) {
					objects.add(new XMovingObjects(this, StartinX, StartinY, Speed, EndPosition));
				}
				if(whichObject == 2) {
					objects.add(new YMovingObjects(this, StartinX, StartinY, Speed, EndPosition));
				}
				
			}
			
			
			
			for(int r = 0; r <grid.length;r++) {
				String line = in.readLine();
					String[] gridvals = line.split(",");
					int[] col = new int[gridvals.length];
					
					for(int i = 0; i< col.length; i++) {
						String q = gridvals[i];
						int w = Integer.parseInt(q);
						col[i] = w;
					}
					
					
					grid[r] = col;
				}
				
		
			in.close();
			Design.setLoadingPoints();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}

}