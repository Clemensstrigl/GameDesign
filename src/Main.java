import processing.core.PApplet;

public class Main extends PApplet{
	Background b;
	Design d;
	public void setup() {
		size(1000,1000);
		b = new Background(width,height,this);
		d = new Design(this);
	}
	
	public void draw() {
		background(150);
		b.DrawBackground();
		
		if(keyPressed) {
			if(key == 's') {
				d.staringzone();
			}
			
		}
	}
}
