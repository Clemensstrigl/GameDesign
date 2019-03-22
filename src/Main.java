import processing.core.PApplet;

public class Main extends PApplet{
	Background b;
	Design d;
	public void setup() {
		size(1000,1000);
		b = new Background(width,height,this);
		d = new Design();
	}
	
	public void draw() {
		background(150);
		b.DrawBackground(30);
		
		if(keyPressed) {
			if(key == 's') {
				d.staringzone();
			}
			
		}
	}
}
