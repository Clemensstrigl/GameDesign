import java.util.ArrayList;

import processing.core.PApplet;

public class Background {
	
	int height;
	int width;
	
	PApplet window;
	int roster = 15;
	int[][] Position= new int[window.width/roster][window.height/roster];
	public Background(int w, int h, PApplet win) {
		this.width = w;
		this.height = h;
		this.window = win;
		for(int i = 0; i<Position.length; i++ ) {
			for(int f = 0; f<Position[i].length; f++ ) {
				Position[i][f] = 150;
			}
		}
	}
	
	public void DrawBackground() {
		
		for(int w = 0; w<width; w+= roster) {
			for(int h = 0; h<height; h+= roster) {
				window.stroke(20);
				window.fill(Position[w][h]);
				window.rect(w, h, roster, roster);
				
				
				
				
			}
		}
	}
	public int[][] getArray() {
		return Position;
	}
	public void setArray(int[][] j) {
		Position = j;
	}
}
