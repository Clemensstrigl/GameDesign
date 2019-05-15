import processing.core.PApplet;

public class Play {
	int x,y;
	PApplet window;
	
	public Play(int x, int y,PApplet window) {
		this.x = x;
		this.y = y;
		this.window = window;
	}
	
	public void draw() {
		window.ellipse(x, y, 20, 20);
	}
	
	public void move(int direction) {
		
		}
	}

